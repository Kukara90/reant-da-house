package com.alexchern.rent_da_house_resource_service.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class FlatCreateDto {

    private String title;

    private String link;

    private String picture;

    private String address;

    private String shortDescription;

    private int costPerMonth;
}
