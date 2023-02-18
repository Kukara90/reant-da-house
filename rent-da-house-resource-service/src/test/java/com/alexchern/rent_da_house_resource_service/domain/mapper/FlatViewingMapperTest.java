package com.alexchern.rent_da_house_resource_service.domain.mapper;

import com.alexchern.rent_da_house_resource_service.domain.dto.FlatDto;
import com.alexchern.rent_da_house_resource_service.domain.dto.FlatViewingDto;
import com.alexchern.rent_da_house_resource_service.domain.dto.FlatViewingUpdateDto;
import com.alexchern.rent_da_house_resource_service.domain.dto.OwnerDto;
import com.alexchern.rent_da_house_resource_service.domain.entity.Flat;
import com.alexchern.rent_da_house_resource_service.domain.entity.FlatViewing;
import com.alexchern.rent_da_house_resource_service.domain.entity.Owner;
import com.alexchern.rent_da_house_resource_service.utils.TestConstants;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class FlatViewingMapperTest {
    public static final long TEST_ID = 123L;

    private static final FlatViewingMapper flatViewingMapper = Mappers.getMapper(FlatViewingMapper.class);

    @Test
    void should_convert_to_dto() {
        // given
        Owner owner = Owner.builder()
                .id(123L)
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

        FlatViewing flatViewing = FlatViewing.builder()
                .id(TEST_ID)
                .version(0L)
                .flat(flat)
                .viewingDay(Instant.parse("2022-04-09T10:15:30.00Z"))
                .shortDescription(TestConstants.FLAT_VIEWING_SHORT_DESCRIPTION)
                .build();

        // when
        FlatViewingDto result = flatViewingMapper.toDto(flatViewing);

        // then
        assertThat(result.getId()).isEqualTo(flatViewing.getId());
        assertThat(result.getVersion()).isEqualTo(flatViewing.getVersion());
        assertThat(result.getViewingDay()).isEqualTo(flatViewing.getViewingDay());
        assertThat(result.getShortDescription()).isEqualTo(flatViewing.getShortDescription());

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

        OwnerDto resultOwner = resultFlat.getOwner();
        assertThat(resultOwner.getId()).isEqualTo(owner.getId());
        assertThat(resultOwner.getVersion()).isEqualTo(owner.getVersion());
        assertThat(resultOwner.getFirstName()).isEqualTo(owner.getFirstName());
        assertThat(resultOwner.getLastName()).isEqualTo(owner.getLastName());
        assertThat(resultOwner.getPhoneNumber()).isEqualTo(owner.getPhoneNumber());
        assertThat(resultOwner.getIsAgent()).isEqualTo(owner.getIsAgent());
    }

    @Test
    void should_merge_flat_viewing() {
        // given
        FlatViewing flatViewing = FlatViewing.builder()
                .id(TEST_ID)
                .version(0L)
                .viewingDay(Instant.parse("2022-04-09T10:15:30.00Z"))
                .shortDescription(TestConstants.FLAT_VIEWING_SHORT_DESCRIPTION)
                .build();

        FlatViewingUpdateDto updateDto = FlatViewingUpdateDto.builder()
                .shortDescription("FLAT_VIEWING_NEW_SHORT_DESCRIPTION")
                .viewingDay(Instant.now().plus(5, ChronoUnit.MINUTES))
                .build();

        // when
        FlatViewing result = flatViewingMapper.mergeFlatViewing(updateDto, flatViewing);

        // then
        assertThat(result.getId()).isEqualTo(flatViewing.getId());
        assertThat(result.getVersion()).isEqualTo(flatViewing.getVersion());
        assertThat(result.getViewingDay()).isEqualTo(flatViewing.getViewingDay());
        assertThat(result.getShortDescription()).isEqualTo(flatViewing.getShortDescription());

        assertThat(result.getFlat()).isNull();
    }
}
