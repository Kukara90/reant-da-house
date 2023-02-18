package com.alexchern.rent_da_house_resource_service.service;

import com.alexchern.rent_da_house_resource_service.domain.entity.Owner;
import com.alexchern.rent_da_house_resource_service.domain.repository.OwnerRepository;
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
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
public class OwnerServiceTest {
    public static final long TEST_ID = 123L;

    @Mock
    OwnerRepository ownerRepository;

    @Captor
    private ArgumentCaptor<Owner> ownerCaptor;

    @InjectMocks
    private OwnerService ownerService;

    @Test
    void should_get_all_owners() {
        // given
        Owner owner = Owner.builder().id(TEST_ID).build();

        doReturn(List.of(owner)).when(ownerRepository).findAll();

        // when
        List<Owner> result = ownerService.getAllOwners();

        // then
        verify(ownerRepository).findAll();
        verifyNoMoreInteractions(ownerRepository);

        assertThat(result).hasSize(1);
    }

    @Test
    void should_get_owner_by_id() {
        // given
        Owner owner = Owner.builder().id(TEST_ID).build();

        doReturn(Optional.of(owner)).when(ownerRepository).findById(TEST_ID);

        // when
        Owner result = ownerService.getOwnerById(TEST_ID);

        // then
        verify(ownerRepository).findById(TEST_ID);
        verifyNoMoreInteractions(ownerRepository);

        assertThat(result.getId()).isEqualTo(TEST_ID);
    }

    @Test
    void should_fail_get_by_id_if_entity_not_exists() {
        doReturn(Optional.empty()).when(ownerRepository).findById(TEST_ID);

        // when & then
        assertThatThrownBy(() -> ownerService.getOwnerById(TEST_ID))
                .isInstanceOf(EntityNotFoundException.class);

        verify(ownerRepository).findById(TEST_ID);
        verifyNoMoreInteractions(ownerRepository);
    }

    @Test
    void should_create_owner() {
        // given
        Owner owner = Owner.builder().firstName(TestConstants.OWNER_FIRST_NAME).build();
        Owner savedOwner = Owner.builder().id(TEST_ID).firstName(TestConstants.OWNER_FIRST_NAME).build();

        doReturn(savedOwner).when(ownerRepository).save(owner);
        // when
        Owner result = ownerService.createOwner(owner);

        // then
        verify(ownerRepository).save(owner);
        verifyNoMoreInteractions(ownerRepository);

        assertThat(result.getId()).isEqualTo(TEST_ID);
        assertThat(result.getFirstName()).isEqualTo(owner.getFirstName());
    }

    @Test
    void should_fail_on_create_if_save_fails() {
        // given
        Owner owner = Owner.builder().id(TEST_ID).build();
        doThrow(new TransactionSystemException(""))
                .when(ownerRepository).save(any(Owner.class));

        // when & then
        assertThatThrownBy(() -> ownerService.createOwner(owner))
                .isInstanceOf(TransactionSystemException.class);

        verify(ownerRepository).save(any(Owner.class));
        verifyNoMoreInteractions(ownerRepository);
    }

    @Test
    void should_edit_owner() {
        // given
        Owner initialOwner = Owner.builder().id(TEST_ID).firstName("NEW").build();
        Consumer<Owner> ownerConsumer = owner -> owner.setFirstName("NEW");

        doReturn(Optional.of(initialOwner)).when(ownerRepository).findById(TEST_ID);

        // when
        ownerService.editOwner(TEST_ID, ownerConsumer);

        // then
        verify(ownerRepository).findById(TEST_ID);
        verify(ownerRepository).save(ownerCaptor.capture());
        verifyNoMoreInteractions(ownerRepository);

        assertThat(ownerCaptor.getValue().getFirstName()).isEqualTo("NEW");
    }

    @Test
    void should_delete_owner() {
        // when
        ownerService.deleteOwner(TEST_ID);

        // then
        verify(ownerRepository).deleteById(TEST_ID);
        verifyNoMoreInteractions(ownerRepository);
    }
}
