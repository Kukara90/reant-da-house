package com.alexchern.rent_da_house_resource_service.service;

import com.alexchern.rent_da_house_resource_service.domain.entity.FlatViewing;
import com.alexchern.rent_da_house_resource_service.domain.repository.FlatViewingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
public class FlatViewingServiceTest {
    public static final long TEST_ID = 123L;

    @Mock
    FlatViewingRepository flatViewingRepository;

    private FlatViewingService flatViewingService;

    @BeforeEach
    public void setUp() {
        flatViewingService = new FlatViewingService(flatViewingRepository);
    }

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
    }
}
