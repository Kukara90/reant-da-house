package com.alexchern.rentahouse.domain.mapper;

public interface BaseMapper<ENTITY, DTO> {

    DTO toDTO(ENTITY entity);

    ENTITY toEntity(DTO dto);
}
