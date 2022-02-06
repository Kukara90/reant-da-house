package com.alexchern.rentahouse.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlatViewingDTO {

    private long id;

    private String shortDescription;

    private Instant viewingDay;

    private FlatDTO flatDTO;
}
