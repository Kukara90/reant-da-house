package com.alexchern.rent_da_house_resource_service.domain.mapper;

public interface BaseMapper<ENTITY, DTO> {

    DTO toDTO(ENTITY entity);

    ENTITY toEntity(DTO dto);
}
