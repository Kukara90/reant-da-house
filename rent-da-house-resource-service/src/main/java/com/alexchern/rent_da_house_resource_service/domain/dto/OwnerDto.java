package com.alexchern.rent_da_house_resource_service.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class OwnerDto {
    private Long id;
    private long version;
    private String firstName;
    private String lastName;
    private boolean isAgent;
    private String phoneNumber;
    private FlatDto flat;
}
