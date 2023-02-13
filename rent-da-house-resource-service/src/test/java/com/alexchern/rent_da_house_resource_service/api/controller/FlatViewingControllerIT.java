package com.alexchern.rent_da_house_resource_service.api.controller;

import com.alexchern.rent_da_house_resource_service.IntegrationTest;
import com.alexchern.rent_da_house_resource_service.domain.dto.FlatViewingDto;
import com.alexchern.rent_da_house_resource_service.domain.entity.Flat;
import com.alexchern.rent_da_house_resource_service.domain.entity.FlatViewing;
import com.alexchern.rent_da_house_resource_service.domain.repository.FlatRepository;
import com.alexchern.rent_da_house_resource_service.domain.repository.FlatViewingRepository;
import com.alexchern.rent_da_house_resource_service.utils.TestConstants;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FlatViewingControllerIT extends IntegrationTest {
    private static final UriComponentsBuilder FLAT_VIEWINGS_URL_BUILDER =
            UriComponentsBuilder.fromPath("/flat-viewings");
    private static final UriComponentsBuilder FLAT_VIEWING_URL_BUILDER =
            FLAT_VIEWINGS_URL_BUILDER.cloneBuilder().pathSegment("{flatViewingId}");

    @Autowired
    private FlatViewingRepository flatViewingRepository;

    @Autowired
    private FlatRepository flatRepository;

    @BeforeEach
    public void setUp() {
        flatViewingRepository.deleteAll();
        flatRepository.deleteAll();
    }

    @Test
    void should_get_all_flat_viewings() throws Exception {
        // given
        Flat flat = flatRepository.save(
                Flat.builder()
                        .title(TestConstants.FLAT_TITLE)
                        .link(TestConstants.FLAT_LINK)
                        .image(TestConstants.FLAT_IMAGE)
                        .address(TestConstants.FLAT_ADDRESS)
                        .voteValue(0)
                        .shortDescription(TestConstants.FLAT_SHORT_DESCRIPTION)
                        .costPerMonth(50000)
                        .build()
        );

        FlatViewing flatViewing = flatViewingRepository.save(
                FlatViewing.builder()
                        .shortDescription(TestConstants.FLAT_VIEWING_SHORT_DESCRIPTION)
                        .viewingDay(Instant.parse("2022-04-09T10:15:30.00Z"))
                        .flat(flat)
                        .build()
        );

        String url = FLAT_VIEWINGS_URL_BUILDER.buildAndExpand().toUriString();

        // when
        List<FlatViewingDto> result = fromJson(
                mockMvc.perform(get(url)).andExpect(status().isOk()),
                new TypeReference<>() {
                }
        );

        // then
        assertThat(result).hasSize(1).first().satisfies(flatViewingDto -> {
            assertThat(flatViewingDto.getId()).isEqualTo(flatViewing.getId());
            assertThat(flatViewingDto.getVersion()).isEqualTo(flatViewing.getVersion());
            assertThat(flatViewingDto.getShortDescription()).isEqualTo(flatViewing.getShortDescription());
            assertThat(flatViewingDto.getViewingDay()).isEqualTo(flatViewing.getViewingDay());

            assertThat(flatViewingDto.getFlat()).satisfies(flatDto -> {
                assertThat(flatDto.getId()).isEqualTo(flat.getId());
                assertThat(flatDto.getVersion()).isEqualTo(flat.getVersion());
                assertThat(flatDto.getTitle()).isEqualTo(flat.getTitle());
                assertThat(flatDto.getLink()).isEqualTo(flat.getLink());
                assertThat(flatDto.getImage()).isEqualTo(flat.getImage());
                assertThat(flatDto.getAddress()).isEqualTo(flat.getAddress());
                assertThat(flatDto.getVoteValue()).isEqualTo(flat.getVoteValue());
                assertThat(flatDto.getShortDescription()).isEqualTo(flat.getShortDescription());
                assertThat(flatDto.getCostPerMonth()).isEqualTo(flat.getCostPerMonth());
            });
        });
    }

    @Test
    void should_get_flat_viewing_by_id() throws Exception {
        // given
        FlatViewing flatViewing = flatViewingRepository.save(
                FlatViewing.builder()
                        .shortDescription(TestConstants.FLAT_VIEWING_SHORT_DESCRIPTION)
                        .viewingDay(Instant.parse("2022-04-09T10:15:30.00Z"))
                        .build()
        );

        String url = FLAT_VIEWING_URL_BUILDER.buildAndExpand(flatViewing.getId()).toUriString();

        // when
        FlatViewingDto result = fromJson(
                mockMvc.perform(get(url)).andExpect(status().isOk()),
                FlatViewingDto.class
        );

        // then
        assertThat(result).satisfies(flatViewingDto -> {
            assertThat(flatViewingDto.getId()).isEqualTo(flatViewing.getId());
            assertThat(flatViewingDto.getVersion()).isEqualTo(flatViewing.getVersion());
            assertThat(flatViewingDto.getShortDescription()).isEqualTo(flatViewing.getShortDescription());
            assertThat(flatViewingDto.getViewingDay()).isEqualTo(flatViewing.getViewingDay());
        });
    }
}
