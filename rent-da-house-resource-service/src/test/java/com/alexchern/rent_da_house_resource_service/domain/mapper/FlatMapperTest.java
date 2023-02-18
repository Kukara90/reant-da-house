package com.alexchern.rent_da_house_resource_service.domain.mapper;

import com.alexchern.rent_da_house_resource_service.domain.dto.FlatCreateDto;
import com.alexchern.rent_da_house_resource_service.domain.dto.FlatDto;
import com.alexchern.rent_da_house_resource_service.domain.dto.FlatUpdateDto;
import com.alexchern.rent_da_house_resource_service.domain.dto.OwnerDto;
import com.alexchern.rent_da_house_resource_service.domain.entity.Flat;
import com.alexchern.rent_da_house_resource_service.domain.entity.Owner;
import com.alexchern.rent_da_house_resource_service.utils.TestConstants;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

public class FlatMapperTest {
    public static final long TEST_ID = 123L;

    private static final FlatMapper flatMapper = Mappers.getMapper(FlatMapper.class);

    @Test
    void should_convert_to_dto() {
        // given
        Owner owner = Owner.builder()
                .id(TEST_ID)
                .version(0L)
                .firstName(TestConstants.OWNER_FIRST_NAME)
                .lastName(TestConstants.OWNER_LAST_NAME)
                .phoneNumber(TestConstants.OWNER_PHONE_NUMBER)
                .isAgent(false)
                .build();

        Flat flat = Flat.builder()
                .id(TEST_ID)
                .version(0L)
                .title(TestConstants.FLAT_TITLE)
                .link(TestConstants.FLAT_LINK)
                .image(TestConstants.FLAT_IMAGE)
                .address(TestConstants.FLAT_ADDRESS)
                .voteValue(0)
                .shortDescription(TestConstants.FLAT_SHORT_DESCRIPTION)
                .costPerMonth(50000)
                .owner(owner)
                .build();

        // when
        FlatDto result = flatMapper.toDto(flat);

        // then
        assertThat(result.getId()).isEqualTo(flat.getId());
        assertThat(result.getVersion()).isEqualTo(flat.getVersion());
        assertThat(result.getTitle()).isEqualTo(flat.getTitle());
        assertThat(result.getLink()).isEqualTo(flat.getLink());
        assertThat(result.getImage()).isEqualTo(flat.getImage());
        assertThat(result.getAddress()).isEqualTo(flat.getAddress());
        assertThat(result.getVoteValue()).isEqualTo(flat.getVoteValue());
        assertThat(result.getShortDescription()).isEqualTo(flat.getShortDescription());
        assertThat(result.getCostPerMonth()).isEqualTo(flat.getCostPerMonth());

        OwnerDto resultOwner = result.getOwner();
        assertThat(resultOwner.getId()).isEqualTo(owner.getId());
        assertThat(resultOwner.getVersion()).isEqualTo(owner.getVersion());
        assertThat(resultOwner.getFirstName()).isEqualTo(owner.getFirstName());
        assertThat(resultOwner.getLastName()).isEqualTo(owner.getLastName());
        assertThat(resultOwner.getPhoneNumber()).isEqualTo(owner.getPhoneNumber());
        assertThat(resultOwner.getIsAgent()).isEqualTo(owner.getIsAgent());
    }

    @Test
    void should_convert_form_create_dto() {
        // given
        FlatCreateDto createDto = FlatCreateDto.builder()
                .title(TestConstants.FLAT_TITLE)
                .link(TestConstants.FLAT_LINK)
                .image(TestConstants.FLAT_IMAGE)
                .address(TestConstants.FLAT_ADDRESS)
                .shortDescription(TestConstants.FLAT_SHORT_DESCRIPTION)
                .costPerMonth(50000)
                .build();

        // when
        Flat result = flatMapper.fromCreateDto(createDto);

        // then
        assertThat(result.getId()).isNull();
        assertThat(result.getVersion()).isEqualTo(0L);
        assertThat(result.getTitle()).isEqualTo(createDto.getTitle());
        assertThat(result.getLink()).isEqualTo(createDto.getLink());
        assertThat(result.getImage()).isEqualTo(createDto.getImage());
        assertThat(result.getAddress()).isEqualTo(createDto.getAddress());
        assertThat(result.getVoteValue()).isEqualTo(0);
        assertThat(result.getShortDescription()).isEqualTo(createDto.getShortDescription());
        assertThat(result.getCostPerMonth()).isEqualTo(createDto.getCostPerMonth());
    }

    @Test
    void should_merge_flat() {
        // given
        Flat flat = Flat.builder()
                .id(TEST_ID)
                .version(0L)
                .title(TestConstants.FLAT_TITLE)
                .link(TestConstants.FLAT_LINK)
                .image(TestConstants.FLAT_IMAGE)
                .address(TestConstants.FLAT_ADDRESS)
                .voteValue(0)
                .shortDescription(TestConstants.FLAT_SHORT_DESCRIPTION)
                .costPerMonth(50000)
                .build();

        FlatUpdateDto updateDto = FlatUpdateDto.builder()
                .title("FLAT_NEW_TITLE")
                .link("FLAT_NEW_LINK")
                .image("FLAT_NEW_IMAGE")
                .address("FLAT_NEW_ADDRESS")
                .voteValue(3)
                .shortDescription("FLAT_NEW_SHORT_DESCRIPTION")
                .costPerMonth(200)
                .build();

        // when
        Flat result = flatMapper.mergeFlat(updateDto, flat);

        // then
        assertThat(result.getId()).isEqualTo(flat.getId());
        assertThat(result.getVersion()).isEqualTo(flat.getVersion());
        assertThat(result.getTitle()).isEqualTo(updateDto.getTitle());
        assertThat(result.getLink()).isEqualTo(updateDto.getLink());
        assertThat(result.getImage()).isEqualTo(updateDto.getImage());
        assertThat(result.getAddress()).isEqualTo(updateDto.getAddress());
        assertThat(result.getVoteValue()).isEqualTo(updateDto.getVoteValue());
        assertThat(result.getShortDescription()).isEqualTo(updateDto.getShortDescription());
        assertThat(result.getCostPerMonth()).isEqualTo(updateDto.getCostPerMonth());

        assertThat(result.getOwner()).isNull();
    }
}
