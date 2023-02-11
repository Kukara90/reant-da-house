package com.alexchern.rent_da_house_resource_service.web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FlatDTO {

    private Long id;

    private String title;

    private String link;

    private String picture;

    private String address;

    private int voteValue;

    private String shortDescription;

    private int costPerMonth;

    private OwnerDTO owner;
}
