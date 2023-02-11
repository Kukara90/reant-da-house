package com.alexchern.rent_da_house_resource_service.web.dto.factory;

import com.alexchern.rent_da_house_resource_service.web.dto.FlatDTO;
import org.springframework.stereotype.Component;

@Component
public class FlatDTOFactory {

    public FlatDTO createFlatDTO(
            String title,
            String shortDescription,
            String link,
            String picture,
            String address,
            int voteValue,
            int costPerMonth
    ) {
        return FlatDTO.builder()
                .title(title)
                .shortDescription(shortDescription)
                .link(link)
                .picture(picture)
                .address(address)
                .voteValue(voteValue)
                .costPerMonth(costPerMonth)
                .build();
    }
}

