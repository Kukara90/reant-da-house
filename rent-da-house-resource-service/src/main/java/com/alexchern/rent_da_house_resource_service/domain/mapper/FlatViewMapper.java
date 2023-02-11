package com.alexchern.rent_da_house_resource_service.domain.mapper;

import com.alexchern.rent_da_house_resource_service.domain.entity.FlatViewingEntity;
import com.alexchern.rentahouse.web.dto.FlatViewingDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FlatViewMapper implements BaseMapper<FlatViewingEntity, FlatViewingDTO> {

    private final OwnerMapper ownerMapper;
    private final FlatMapper flatMapper;

    @Override
    public FlatViewingDTO toDTO(FlatViewingEntity flatViewingEntity) {
        return FlatViewingDTO.builder()
                .shortDescription(flatViewingEntity.getShortDescription())
                .viewingDay(flatViewingEntity.getViewingDay())
                .flatDTO(flatMapper.toDTO(flatViewingEntity.getFlat()))
                .build();
    }

    @Override
    public FlatViewingEntity toEntity(FlatViewingDTO flatViewingDTO) {
        return FlatViewingEntity.builder()
                .viewingDay(flatViewingDTO.getViewingDay())
                .shortDescription(flatViewingDTO.getShortDescription())
                .flat(flatMapper.toEntity(flatViewingDTO.getFlatDTO()))
                .build();
    }
}
