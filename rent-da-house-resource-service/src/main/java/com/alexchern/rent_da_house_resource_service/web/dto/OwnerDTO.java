package com.alexchern.rent_da_house_resource_service.web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OwnerDTO {

    private Long id;

    private String name;

    private String surname;

    private boolean isAgent;

    private String phoneNumber;

    private FlatDTO flat;
}
