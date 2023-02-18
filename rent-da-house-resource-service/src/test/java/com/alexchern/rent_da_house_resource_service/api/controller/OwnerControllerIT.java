package com.alexchern.rent_da_house_resource_service.api.controller;

import com.alexchern.rent_da_house_resource_service.IntegrationTest;
import com.alexchern.rent_da_house_resource_service.domain.dto.OwnerCreateDto;
import com.alexchern.rent_da_house_resource_service.domain.dto.OwnerDto;
import com.alexchern.rent_da_house_resource_service.domain.dto.OwnerUpdateDto;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OwnerControllerIT extends IntegrationTest {
    private static final UriComponentsBuilder OWNERS_URL_BUILDER =
            UriComponentsBuilder.fromPath("/owners");
    private static final UriComponentsBuilder OWNER_URL_BUILDER =
            OWNERS_URL_BUILDER.cloneBuilder().pathSegment("{ownerId}");

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private FlatRepository flatRepository;

    @BeforeEach
    public void setUp() {
        ownerRepository.deleteAll();
        flatRepository.deleteAll();
    }

    @Test
    void should_get_all_owners() throws Exception {
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

        Owner owner = ownerRepository.save(
                Owner.builder()
                        .firstName(TestConstants.OWNER_FIRST_NAME)
                        .lastName(TestConstants.OWNER_LAST_NAME)
                        .phoneNumber(TestConstants.OWNER_PHONE_NUMBER)
                        .isAgent(false)
                        .flat(flat)
                        .build()
        );

        String url = OWNERS_URL_BUILDER.buildAndExpand().toUriString();

        // when
        List<OwnerDto> result = fromJson(
                mockMvc.perform(get(url)).andExpect(status().isOk()),
                new TypeReference<>() {
                }
        );

        // then
        assertThat(result).hasSize(1).first().satisfies(ownerDto -> {
            assertThat(ownerDto.getId()).isEqualTo(owner.getId());
            assertThat(ownerDto.getVersion()).isEqualTo(owner.getVersion());
            assertThat(ownerDto.getFirstName()).isEqualTo(owner.getFirstName());
            assertThat(ownerDto.getLastName()).isEqualTo(owner.getLastName());
            assertThat(ownerDto.getPhoneNumber()).isEqualTo(owner.getPhoneNumber());
            assertThat(ownerDto.getIsAgent()).isEqualTo(owner.getIsAgent());

            assertThat(ownerDto.getFlat()).satisfies(flatDto -> {
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
    void should_get_owner_by_id() throws Exception {
        // given
        Owner owner = ownerRepository.save(
                Owner.builder()
                        .firstName(TestConstants.OWNER_FIRST_NAME)
                        .lastName(TestConstants.OWNER_LAST_NAME)
                        .phoneNumber(TestConstants.OWNER_PHONE_NUMBER)
                        .isAgent(true)
                        .build()
        );

        String url = OWNER_URL_BUILDER.buildAndExpand(owner.getId()).toUriString();

        // when
        OwnerDto result = fromJson(
                mockMvc.perform(get(url)).andExpect(status().isOk()),
                new TypeReference<>() {
                }
        );

        // then
        assertThat(result).satisfies(ownerDto -> {
            assertThat(ownerDto.getId()).isEqualTo(owner.getId());
            assertThat(ownerDto.getVersion()).isEqualTo(owner.getVersion());
            assertThat(ownerDto.getFirstName()).isEqualTo(owner.getFirstName());
            assertThat(ownerDto.getLastName()).isEqualTo(owner.getLastName());
            assertThat(ownerDto.getPhoneNumber()).isEqualTo(owner.getPhoneNumber());
            assertThat(ownerDto.getIsAgent()).isEqualTo(owner.getIsAgent());
        });
    }

    @Test
    void should_create_owner() throws Exception {
        // given
        OwnerCreateDto createDto = OwnerCreateDto.builder()
                .firstName(TestConstants.OWNER_FIRST_NAME)
                .lastName(TestConstants.OWNER_LAST_NAME)
                .phoneNumber(TestConstants.OWNER_PHONE_NUMBER)
                .build();

        String url = OWNERS_URL_BUILDER.buildAndExpand().toUriString();

        // when
        OwnerDto result = fromJson(
                mockMvc.perform(
                        post(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(toJson(createDto))
                ),
                OwnerDto.class
        );

        // then
        assertThat(result).satisfies(ownerDto -> {
            // id of result is 3L cause of db sequence, two inserts from test above
            // TODO: think how to reset sequence to set id for 1L
            assertThat(ownerDto.getId()).isPositive();
            assertThat(ownerDto.getVersion()).isEqualTo(0L);
            assertThat(ownerDto.getFirstName()).isEqualTo(createDto.getFirstName());
            assertThat(ownerDto.getLastName()).isEqualTo(createDto.getLastName());
            assertThat(ownerDto.getPhoneNumber()).isEqualTo(createDto.getPhoneNumber());
            assertThat(ownerDto.getIsAgent()).isEqualTo(false);
        });
    }

    @Test
    void should_edit_owner() throws Exception {
        // given
        Owner owner = ownerRepository.save(
                Owner.builder()
                        .firstName(TestConstants.OWNER_FIRST_NAME)
                        .lastName(TestConstants.OWNER_LAST_NAME)
                        .phoneNumber(TestConstants.OWNER_PHONE_NUMBER)
                        .isAgent(true)
                        .build()
        );

        OwnerUpdateDto updateDto = OwnerUpdateDto.builder()
                .phoneNumber("NEW")
                .build();

        String url = OWNER_URL_BUILDER.buildAndExpand(owner.getId()).toUriString();

        // when
        OwnerDto result = fromJson(
                mockMvc.perform(
                        patch(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(toJson(updateDto))
                ),
                OwnerDto.class
        );

        // then
        assertThat(result).satisfies(ownerDto -> {
            assertThat(ownerDto.getId()).isEqualTo(owner.getId());
            assertThat(ownerDto.getVersion()).isEqualTo(owner.getVersion() + 1L);
            assertThat(ownerDto.getFirstName()).isEqualTo(owner.getFirstName());
            assertThat(ownerDto.getLastName()).isEqualTo(owner.getLastName());
            assertThat(ownerDto.getPhoneNumber()).isEqualTo(updateDto.getPhoneNumber());
            assertThat(ownerDto.getIsAgent()).isEqualTo(owner.getIsAgent());
        });
    }

    @Test
    void should_delete_owner() throws Exception {
        // given
        Owner owner = ownerRepository.save(
                Owner.builder()
                        .firstName(TestConstants.OWNER_FIRST_NAME)
                        .lastName(TestConstants.OWNER_LAST_NAME)
                        .phoneNumber(TestConstants.OWNER_PHONE_NUMBER)
                        .isAgent(true)
                        .build()
        );

        String url = OWNER_URL_BUILDER.buildAndExpand(owner.getId()).toUriString();

        // when
        mockMvc.perform(delete(url)).andExpect(status().isOk());

        // then
        assertThat(ownerRepository.findAll()).isEmpty();
    }
}
