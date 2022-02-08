package com.alexchern.rentahouse.web.dto.factory;

import com.alexchern.rentahouse.web.dto.FlatDTO;
import com.alexchern.rentahouse.web.dto.FlatViewingDTO;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class FlatViewingDTOFactory {

    public FlatViewingDTO createFlatViewingDTO(String shortDescription, String viewingDay, FlatViewingDTO flatViewingDTO) {

        //TODO implement viewing day logic
        return FlatViewingDTO.builder()
                .shortDescription(shortDescription)
                .viewingDay(Instant.now())
                .build();
    }
}
