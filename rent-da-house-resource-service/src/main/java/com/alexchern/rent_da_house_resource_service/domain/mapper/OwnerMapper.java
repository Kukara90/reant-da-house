package com.alexchern.rent_da_house_resource_service.domain.mapper;

import com.alexchern.rent_da_house_resource_service.domain.entity.OwnerEntity;
import com.alexchern.rentahouse.web.dto.OwnerDTO;
import org.springframework.stereotype.Component;

@Component
public class OwnerMapper implements BaseMapper<OwnerEntity, OwnerDTO> {

    @Override
    public OwnerDTO toDTO(OwnerEntity ownerEntity) {

        if (ownerEntity == null) {
            return null;
        }

        return OwnerDTO.builder()
                .id(ownerEntity.getId())
                .name(ownerEntity.getName())
                .isAgent(ownerEntity.isAgent())
                .phoneNumber(ownerEntity.getPhoneNumber())
                .surname(ownerEntity.getSurname())
                .build();
    }

    @Override
    public OwnerEntity toEntity(OwnerDTO ownerDTO) {

        if (ownerDTO == null) {
            return null;
        }

        return OwnerEntity.builder()
                .isAgent(ownerDTO.isAgent())
                .name(ownerDTO.getName())
                .surname(ownerDTO.getSurname())
                .phoneNumber(ownerDTO.getPhoneNumber())
                .build();
    }
}
