package com.alexchern.rentahouse.web.dto.factory;

import com.alexchern.rentahouse.web.dto.FlatDTO;
import com.alexchern.rentahouse.web.dto.FlatViewingDTO;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class FlatViewingDTOFactory {

    public FlatViewingDTO createFlatViewingDTO(String shortDescription, String viewingDay, FlatDTO flatDTO) {

        //TODO implement viewing day logic
        return FlatViewingDTO.builder()
                .shortDescription(shortDescription)
                .viewingDay(Instant.now())
                .flatDTO(flatDTO)
                .build();
    }
}
