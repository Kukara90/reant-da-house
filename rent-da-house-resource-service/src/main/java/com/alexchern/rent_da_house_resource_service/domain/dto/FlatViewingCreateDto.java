package com.alexchern.rent_da_house_resource_service.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.Instant;

@Data
@Builder
@Jacksonized
public class FlatViewingCreateDto {
    private String shortDescription;
    private Instant viewingDay;
    private Long flatId;
}