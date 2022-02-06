package com.alexchern.rentahouse.domain.mapper;

import com.alexchern.rentahouse.domain.entity.FlatEntity;
import com.alexchern.rentahouse.web.dto.FlatDTO;
import org.springframework.stereotype.Component;

@Component
public class FlatMapper implements BaseMapper<FlatEntity, FlatDTO> {

    @Override
    public FlatDTO toDTO(FlatEntity flatEntity) {
        return FlatDTO.builder()
                .id(flatEntity.getId())
                .title(flatEntity.getTitle())
                .link(flatEntity.getLink())
                .picture(flatEntity.getPicture())
                .address(flatEntity.getAddress())
                .costPerMonth(flatEntity.getCostPerMonth())
                .shortDescription(flatEntity.getShortDescription())
                .build();
    }

    @Override
    public FlatEntity toEntity(FlatDTO flatDTO) {
        return FlatEntity.builder()
                .title(flatDTO.getTitle())
                .link(flatDTO.getLink())
                .picture(flatDTO.getPicture())
                .shortDescription(flatDTO.getShortDescription())
                .address(flatDTO.getAddress())
                .costPerMonth(flatDTO.getCostPerMonth())
                .build();
    }
}
