package com.alexchern.rent_da_house_resource_service.service;

import com.alexchern.rent_da_house_resource_service.domain.entity.Flat;
import com.alexchern.rent_da_house_resource_service.domain.entity.FlatViewing;
import com.alexchern.rent_da_house_resource_service.domain.repository.FlatViewingRepository;
import com.alexchern.rent_da_house_resource_service.utils.TestConstants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.TransactionSystemException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
public class FlatViewingServiceTest {
    public static final long TEST_ID = 123L;

    @Mock
    FlatViewingRepository flatViewingRepository;

    @Mock
    FlatService flatService;

    @Captor
    private ArgumentCaptor<FlatViewing> flatViewingCaptor;

    @InjectMocks
    private FlatViewingService flatViewingService;

    @Test
    void should_get_all_flat_viewings() {
        // given
        FlatViewing owner = FlatViewing.builder().id(TEST_ID).build();

        doReturn(List.of(owner)).when(flatViewingRepository).findAll();

        // when
        List<FlatViewing> result = flatViewingService.getAllFlatViewings();

        // then
        verify(flatViewingRepository).findAll();
        verifyNoMoreInteractions(flatViewingRepository);
        verifyNoInteractions(flatService);

        assertThat(result).hasSize(1);
    }

    @Test
    void should_get_flat_viewing_by_id() {
        // given
        FlatViewing flatViewing = FlatViewing.builder().id(TEST_ID).build();

        doReturn(Optional.of(flatViewing)).when(flatViewingRepository).findById(TEST_ID);

        // when
        FlatViewing result = flatViewingService.getFlatViewingById(TEST_ID);

        // then
        verify(flatViewingRepository).findById(TEST_ID);
        verifyNoMoreInteractions(flatViewingRepository);
        verifyNoInteractions(flatService);

        assertThat(result.getId()).isEqualTo(TEST_ID);
    }

    @Test
    void should_fail_get_by_id_if_entity_not_exists() {
        doReturn(Optional.empty()).when(flatViewingRepository).findById(TEST_ID);

        // when & then
        assertThatThrownBy(() -> flatViewingService.getFlatViewingById(TEST_ID))
                .isInstanceOf(EntityNotFoundException.class);

        verify(flatViewingRepository).findById(TEST_ID);
        verifyNoMoreInteractions(flatViewingRepository);
        verifyNoInteractions(flatService);
    }

    @Test
    void should_create_flat_viewing() {
        // given
        Flat flat = Flat.builder().id(TEST_ID).build();
        FlatViewing flatViewing = FlatViewing.builder().shortDescription(TestConstants.FLAT_VIEWING_SHORT_DESCRIPTION).build();
        FlatViewing savedFlatViewing = FlatViewing.builder().id(TEST_ID)
                .shortDescription(TestConstants.FLAT_VIEWING_SHORT_DESCRIPTION).build();

        doReturn(flat).when(flatService).getFlatById(TEST_ID);
        doReturn(savedFlatViewing).when(flatViewingRepository).save(flatViewing);

        // when
        FlatViewing result = flatViewingService.createFlatViewing(flatViewing, TEST_ID);

        // then
        verify(flatViewingRepository).save(flatViewing);
        verify(flatService).getFlatById(TEST_ID);
        verifyNoMoreInteractions(flatViewingRepository, flatService);

        assertThat(result.getId()).isEqualTo(TEST_ID);
        assertThat(result.getShortDescription()).isEqualTo(flatViewing.getShortDescription());
    }

    @Test
    void should_fail_on_create_if_save_fails() {
        // given
        Flat flat = Flat.builder().id(TEST_ID).build();
        FlatViewing flatViewing = FlatViewing.builder().id(TEST_ID).build();
        doReturn(flat).when(flatService).getFlatById(TEST_ID);
        doThrow(new TransactionSystemException(""))
                .when(flatViewingRepository).save(any(FlatViewing.class));

        // when & then
        assertThatThrownBy(() -> flatViewingService.createFlatViewing(flatViewing, TEST_ID))
                .isInstanceOf(TransactionSystemException.class);

        verify(flatViewingRepository).save(any(FlatViewing.class));
        verifyNoMoreInteractions(flatViewingRepository, flatService);
    }

    @Test
    void should_fail_on_create_if_flat_id_is_null() {
        // given
        FlatViewing flatViewing = FlatViewing.builder().id(TEST_ID).build();

        // when & then
        assertThatThrownBy(() -> flatViewingService.createFlatViewing(flatViewing, null))
                .isInstanceOf(IllegalArgumentException.class);

        verifyNoInteractions(flatViewingRepository, flatService);
    }

    @Test
    void should_fail_on_create_if_flat_not_exists() {
        // given
        FlatViewing flatViewing = FlatViewing.builder().id(TEST_ID).build();
        doThrow(new EntityNotFoundException()).when(flatService).getFlatById(TEST_ID);

        // when & then
        assertThatThrownBy(() -> flatViewingService.createFlatViewing(flatViewing, TEST_ID))
                .isInstanceOf(EntityNotFoundException.class);

        verifyNoMoreInteractions(flatService);
        verifyNoInteractions(flatViewingRepository);
    }

    @Test
    void should_edit_flat_viewing() {
        // given
        FlatViewing initialFlatViewing = FlatViewing.builder().id(TEST_ID).shortDescription(TestConstants.FLAT_VIEWING_SHORT_DESCRIPTION).build();
        Consumer<FlatViewing> flatViewingConsumer = flatViewing -> flatViewing.setShortDescription("NEW");

        doReturn(Optional.of(initialFlatViewing)).when(flatViewingRepository).findById(TEST_ID);

        // when
        flatViewingService.editFlatViewing(TEST_ID, flatViewingConsumer);

        // then
        verify(flatViewingRepository).findById(TEST_ID);
        verify(flatViewingRepository).save(flatViewingCaptor.capture());
        verifyNoMoreInteractions(flatViewingRepository);
        verifyNoInteractions(flatService);

        assertThat(flatViewingCaptor.getValue().getShortDescription()).isEqualTo("NEW");
    }

    @Test
    void should_delete_flat_viewing() {
        // when
        flatViewingService.deleteFlatViewing(TEST_ID);

        // then
        verify(flatViewingRepository).deleteById(TEST_ID);
        verifyNoMoreInteractions(flatViewingRepository);
        verifyNoInteractions(flatService);
    }
}
