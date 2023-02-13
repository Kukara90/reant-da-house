package com.alexchern.rent_da_house_resource_service.service;

import com.alexchern.rent_da_house_resource_service.domain.entity.Flat;
import com.alexchern.rent_da_house_resource_service.domain.entity.Owner;
import com.alexchern.rent_da_house_resource_service.domain.repository.FlatRepository;
import com.alexchern.rent_da_house_resource_service.utils.TestConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.TransactionSystemException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
public class FlatServiceTest {
    public static final long TEST_ID = 123L;

    @Mock
    FlatRepository flatRepository;

    @Mock
    OwnerService ownerService;

    @Captor
    ArgumentCaptor<Flat> flatCaptor;

    private FlatService flatService;

    @BeforeEach
    public void setUp() {
        flatService = new FlatService(flatRepository, ownerService);
    }

    @Test
    void should_get_all_flats() {
        // given
        Flat flat = Flat.builder().id(TEST_ID).build();

        doReturn(List.of(flat)).when(flatRepository).findAll();

        // when
        List<Flat> result = flatService.getAllFlats();

        // then
        verify(flatRepository).findAll();
        verifyNoMoreInteractions(flatRepository);
        verifyNoInteractions(ownerService);

        assertThat(result).hasSize(1);
    }

    @Test
    void should_get_flat_by_id() {
        // given
        Flat flat = Flat.builder().id(TEST_ID).build();

        doReturn(Optional.of(flat)).when(flatRepository).findById(TEST_ID);

        // when
        Flat result = flatService.getFlatById(TEST_ID);

        // then
        verify(flatRepository).findById(TEST_ID);
        verifyNoMoreInteractions(flatRepository);
        verifyNoInteractions(ownerService);

        assertThat(result.getId()).isEqualTo(TEST_ID);
    }

    @Test
    void should_fail_get_by_id_if_entity_not_exists() {
        doReturn(Optional.empty()).when(flatRepository).findById(TEST_ID);

        // when & then
        assertThatThrownBy(() -> flatService.getFlatById(TEST_ID))
                .isInstanceOf(EntityNotFoundException.class);

        verify(flatRepository).findById(TEST_ID);
        verifyNoMoreInteractions(flatRepository);
        verifyNoInteractions(ownerService);
    }

    @Test
    void should_create_flat() {
        // given
        Flat flat = Flat.builder().title(TestConstants.FLAT_TITLE).build();
        Flat savedFlat = Flat.builder().id(123L).title(TestConstants.FLAT_TITLE).build();

        doReturn(savedFlat).when(flatRepository).save(flat);
        // when
        Flat result = flatService.createFlat(flat);

        // then
        verify(flatRepository).save(flat);
        verifyNoMoreInteractions(flatRepository);
        verifyNoInteractions(ownerService);

        assertThat(result.getId()).isEqualTo(TEST_ID);
        assertThat(result.getTitle()).isEqualTo(flat.getTitle());
    }

    @Test
    void should_fail_on_create_if_save_fails() {
        // given
        Flat flat = Flat.builder().id(TEST_ID).build();
        doThrow(new TransactionSystemException(""))
                .when(flatRepository).save(any(Flat.class));

        // when & then
        assertThatThrownBy(() -> flatService.createFlat(flat))
                .isInstanceOf(TransactionSystemException.class);

        verify(flatRepository).save(any(Flat.class));
        verifyNoMoreInteractions(flatRepository);
        verifyNoInteractions(ownerService);
    }

    @Test
    void should_assign_owner() {
        // given
        Flat flat = Flat.builder().id(TEST_ID).build();
        Owner owner = Owner.builder().id(TEST_ID).build();

        doReturn(Optional.of(flat)).when(flatRepository).findById(TEST_ID);
        doReturn(owner).when(ownerService).getOwnerById(TEST_ID);

        // when
        flatService.assignOwner(flat.getId(), owner.getId());

        // then
        verify(flatRepository).save(flatCaptor.capture());
        verify(flatRepository).findById(TEST_ID);
        verify(ownerService).getOwnerById(TEST_ID);
        verifyNoMoreInteractions(flatRepository, ownerService);

        Flat result = flatCaptor.getValue();
        assertThat(result.getOwner().getId()).isEqualTo(owner.getId());
    }
}
