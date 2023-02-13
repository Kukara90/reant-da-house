package com.alexchern.rent_da_house_resource_service.domain.mapper;

import com.alexchern.rent_da_house_resource_service.domain.dto.FlatDto;
import com.alexchern.rent_da_house_resource_service.domain.dto.OwnerCreateDto;
import com.alexchern.rent_da_house_resource_service.domain.dto.OwnerDto;
import com.alexchern.rent_da_house_resource_service.domain.entity.Flat;
import com.alexchern.rent_da_house_resource_service.domain.entity.Owner;
import com.alexchern.rent_da_house_resource_service.utils.TestConstants;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

public class OwnerMapperTest {
    public static final long TEST_ID = 123L;

    private static final OwnerMapper ownerMapper = Mappers.getMapper(OwnerMapper.class);

    @Test
    void should_convert_to_dto() {
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

        Owner owner = Owner.builder()
                .id(TEST_ID)
                .version(0L)
                .firstName(TestConstants.OWNER_FIRST_NAME)
                .lastName(TestConstants.OWNER_LAST_NAME)
                .phoneNumber(TestConstants.OWNER_PHONE_NUMBER)
                .isAgent(true)
                .flat(flat)
                .build();

        // when
        OwnerDto result = ownerMapper.toDto(owner);

        // then
        assertThat(result.getId()).isEqualTo(owner.getId());
        assertThat(result.getVersion()).isEqualTo(owner.getVersion());
        assertThat(result.getFirstName()).isEqualTo(owner.getFirstName());
        assertThat(result.getLastName()).isEqualTo(owner.getLastName());
        assertThat(result.getPhoneNumber()).isEqualTo(owner.getPhoneNumber());
        assertThat(result.getIsAgent()).isEqualTo(owner.getIsAgent());

        FlatDto resultFlat = result.getFlat();
        assertThat(resultFlat.getId()).isEqualTo(flat.getId());
        assertThat(resultFlat.getVersion()).isEqualTo(flat.getVersion());
        assertThat(resultFlat.getTitle()).isEqualTo(flat.getTitle());
        assertThat(resultFlat.getLink()).isEqualTo(flat.getLink());
        assertThat(resultFlat.getImage()).isEqualTo(flat.getImage());
        assertThat(resultFlat.getAddress()).isEqualTo(flat.getAddress());
        assertThat(resultFlat.getVoteValue()).isEqualTo(flat.getVoteValue());
        assertThat(resultFlat.getShortDescription()).isEqualTo(flat.getShortDescription());
        assertThat(resultFlat.getCostPerMonth()).isEqualTo(flat.getCostPerMonth());
    }

    @Test
    void should_convert_form_create_dto() {
        // given
        OwnerCreateDto createDto = OwnerCreateDto.builder()
                .firstName(TestConstants.OWNER_FIRST_NAME)
                .lastName(TestConstants.OWNER_LAST_NAME)
                .phoneNumber(TestConstants.OWNER_PHONE_NUMBER)
                .build();

        // when
        Owner result = ownerMapper.fromCreateDto(createDto);

        // then
        assertThat(result.getId()).isNull();
        assertThat(result.getVersion()).isEqualTo(0L);
        assertThat(result.getFirstName()).isEqualTo(createDto.getFirstName());
        assertThat(result.getLastName()).isEqualTo(createDto.getLastName());
        assertThat(result.getPhoneNumber()).isEqualTo(createDto.getPhoneNumber());
        assertThat(result.getIsAgent()).isEqualTo(false);
    }
}
