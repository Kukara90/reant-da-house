package com.alexchern.rent_da_house_resource_service.domain.mapper;

import com.alexchern.rent_da_house_resource_service.domain.dto.FlatCreateDto;
import com.alexchern.rent_da_house_resource_service.domain.dto.FlatDto;
import com.alexchern.rent_da_house_resource_service.domain.entity.Flat;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface FlatMapper {

    FlatDto toDto(Flat flat);
    List<FlatDto> toDtos(Collection<Flat> flatCollection);

    Flat fromCreateDto(FlatCreateDto createDto);
}
