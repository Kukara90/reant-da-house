package com.alexchern.rent_da_house_resource_service.api.controller;

import com.alexchern.rent_da_house_resource_service.IntegrationTest;
import com.alexchern.rent_da_house_resource_service.domain.dto.FlatCreateDto;
import com.alexchern.rent_da_house_resource_service.domain.dto.FlatDto;
import com.alexchern.rent_da_house_resource_service.domain.dto.OwnerAssignDto;
import com.alexchern.rent_da_house_resource_service.domain.entity.Flat;
import com.alexchern.rent_da_house_resource_service.domain.entity.Owner;
import com.alexchern.rent_da_house_resource_service.domain.repository.FlatRepository;
import com.alexchern.rent_da_house_resource_service.domain.repository.OwnerRepository;
import com.alexchern.rent_da_house_resource_service.utils.TestConstants;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FlatControllerIT extends IntegrationTest {
    private static final UriComponentsBuilder FLATS_URL_BUILDER =
            UriComponentsBuilder.fromPath("/flats");
    private static final UriComponentsBuilder FLAT_URL_BUILDER =
            FLATS_URL_BUILDER.cloneBuilder().pathSegment("{flatId}");
    private static final UriComponentsBuilder FLAT_CREATE_URL_BUILDER =
            FLATS_URL_BUILDER.cloneBuilder();
    private static final UriComponentsBuilder FLAT_ASSIGN_OWNER_URL_BUILDER =
            FLAT_URL_BUILDER.cloneBuilder().path("/owner");

    @Autowired
    private FlatRepository flatRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @BeforeEach
    void setUp() {
        flatRepository.deleteAll();
        ownerRepository.deleteAll();
    }

    @Test
    void should_get_all_flats() throws Exception {
        // given
        Owner owner = ownerRepository.save(
                Owner.builder()
                        .firstName(TestConstants.OWNER_FIRST_NAME)
                        .lastName(TestConstants.OWNER_LAST_NAME)
                        .phoneNumber(TestConstants.OWNER_PHONE_NUMBER)
                        .isAgent(false)
                        .build()
        );

        Flat flat = flatRepository.save(
                Flat.builder()
                        .title(TestConstants.FLAT_TITLE)
                        .link(TestConstants.FLAT_LINK)
                        .image(TestConstants.FLAT_IMAGE)
                        .address(TestConstants.FLAT_ADDRESS)
                        .voteValue(0)
                        .shortDescription(TestConstants.FLAT_SHORT_DESCRIPTION)
                        .costPerMonth(50000)
                        .owner(owner)
                        .build()
        );

        String url = FLATS_URL_BUILDER.buildAndExpand().toUriString();

        // when
        List<FlatDto> result = fromJson(
                mockMvc.perform(get(url)).andExpect(status().isOk()),
                new TypeReference<>() {}
        );

        // then
        assertThat(result).hasSize(1).first().satisfies(flatDto -> {
            assertThat(flatDto.getId()).isEqualTo(flat.getId());
            assertThat(flatDto.getVersion()).isEqualTo(flat.getVersion());
            assertThat(flatDto.getTitle()).isEqualTo(flat.getTitle());
            assertThat(flatDto.getLink()).isEqualTo(flat.getLink());
            assertThat(flatDto.getImage()).isEqualTo(flat.getImage());
            assertThat(flatDto.getAddress()).isEqualTo(flat.getAddress());
            assertThat(flatDto.getVoteValue()).isEqualTo(flat.getVoteValue());
            assertThat(flatDto.getShortDescription()).isEqualTo(flat.getShortDescription());
            assertThat(flatDto.getCostPerMonth()).isEqualTo(flat.getCostPerMonth());

            assertThat(flatDto.getOwner()).satisfies(ownerDto -> {
                assertThat(ownerDto.getId()).isEqualTo(owner.getId());
                assertThat(ownerDto.getVersion()).isEqualTo(owner.getVersion());
                assertThat(ownerDto.getFirstName()).isEqualTo(owner.getFirstName());
                assertThat(ownerDto.getLastName()).isEqualTo(owner.getLastName());
                assertThat(ownerDto.getPhoneNumber()).isEqualTo(owner.getPhoneNumber());
                assertThat(ownerDto.isAgent()).isEqualTo(owner.isAgent());
            });
        });
    }

    @Test
    void should_get_flat_by_id() throws Exception {
        // given
        Flat flat = flatRepository.save(
                Flat.builder()
                        .title(TestConstants.FLAT_TITLE)
                        .address(TestConstants.FLAT_ADDRESS)
                        .costPerMonth(50000)
                        .build()
        );

        String url = FLAT_URL_BUILDER.buildAndExpand(flat.getId()).toUriString();

        // when
        FlatDto result = fromJson(
                mockMvc.perform(get(url)).andExpect(status().isOk()),
                new TypeReference<>() {
                }
        );

        // then
        assertThat(result).satisfies(flatDto -> {
            assertThat(flatDto.getId()).isEqualTo(flat.getId());
            assertThat(flatDto.getVersion()).isEqualTo(flat.getVersion());
            assertThat(flatDto.getTitle()).isEqualTo(flat.getTitle());
            assertThat(flatDto.getAddress()).isEqualTo(flat.getAddress());
            assertThat(flatDto.getCostPerMonth()).isEqualTo(flat.getCostPerMonth());
        });
    }

    @Test
    void should_create_flat() throws Exception {
        // given
        FlatCreateDto createDto = FlatCreateDto.builder()
                .title(TestConstants.FLAT_TITLE)
                .link(TestConstants.FLAT_LINK)
                .image(TestConstants.FLAT_IMAGE)
                .address(TestConstants.FLAT_ADDRESS)
                .shortDescription(TestConstants.FLAT_SHORT_DESCRIPTION)
                .costPerMonth(50000)
                .build();

        String url = FLAT_CREATE_URL_BUILDER.buildAndExpand().toUriString();

        // when
        FlatDto result = fromJson(
                mockMvc.perform(
                        post(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(toJson(createDto))
                ),
                FlatDto.class
        );

        // then
        assertThat(result).satisfies(flatDto -> {
            assertThat(flatDto.getId()).isEqualTo(1L);
            assertThat(flatDto.getVersion()).isEqualTo(1L);
            assertThat(flatDto.getTitle()).isEqualTo(createDto.getTitle());
            assertThat(flatDto.getLink()).isEqualTo(createDto.getLink());
            assertThat(flatDto.getImage()).isEqualTo(createDto.getImage());
            assertThat(flatDto.getAddress()).isEqualTo(createDto.getAddress());
            assertThat(flatDto.getShortDescription()).isEqualTo(createDto.getShortDescription());
            assertThat(flatDto.getCostPerMonth()).isEqualTo(createDto.getCostPerMonth());
        });
    }

    @Test
    void should_assign_owner() throws Exception {
        // given
        Owner owner = ownerRepository.save(
                Owner.builder()
                        .firstName(TestConstants.OWNER_FIRST_NAME)
                        .lastName(TestConstants.OWNER_LAST_NAME)
                        .phoneNumber(TestConstants.OWNER_PHONE_NUMBER)
                        .build()
        );

        Flat flat = flatRepository.save(
                Flat.builder()
                        .title(TestConstants.FLAT_TITLE)
                        .address(TestConstants.FLAT_ADDRESS)
                        .costPerMonth(50000)
                        .build()
        );

        OwnerAssignDto ownerAssignDto = OwnerAssignDto.builder()
                .ownerId(owner.getId())
                .build();

        String url = FLAT_ASSIGN_OWNER_URL_BUILDER.buildAndExpand(flat.getId()).toUriString();

        // when
        FlatDto result = fromJson(
                mockMvc.perform(
                        patch(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(toJson(ownerAssignDto))
                ),
                FlatDto.class
        );

        // then
        assertThat(result).satisfies(flatDto -> {
            assertThat(flatDto.getId()).isEqualTo(flat.getId());
            assertThat(flatDto.getVersion()).isEqualTo(flat.getVersion());
            assertThat(flatDto.getTitle()).isEqualTo(flat.getTitle());
            assertThat(flatDto.getAddress()).isEqualTo(flat.getAddress());
            assertThat(flatDto.getCostPerMonth()).isEqualTo(flat.getCostPerMonth());

            assertThat(flatDto.getOwner()).satisfies(ownerDto -> {
                assertThat(ownerDto.getId()).isEqualTo(owner.getId());
                assertThat(ownerDto.getVersion()).isEqualTo(owner.getVersion());
                assertThat(ownerDto.getFirstName()).isEqualTo(owner.getFirstName());
                assertThat(ownerDto.getLastName()).isEqualTo(owner.getLastName());
                assertThat(ownerDto.getPhoneNumber()).isEqualTo(owner.getPhoneNumber());
            });
        });
    }
}
