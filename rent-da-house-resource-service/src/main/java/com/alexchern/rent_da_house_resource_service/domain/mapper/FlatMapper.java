package com.alexchern.rent_da_house_resource_service.domain.mapper;

import com.alexchern.rent_da_house_resource_service.domain.dto.FlatCreateDto;
import com.alexchern.rent_da_house_resource_service.domain.dto.FlatDto;
import com.alexchern.rent_da_house_resource_service.domain.dto.FlatUpdateDto;
import com.alexchern.rent_da_house_resource_service.domain.entity.Flat;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.Collection;
import java.util.List;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public interface FlatMapper {

    FlatDto toDto(Flat flat);

    List<FlatDto> toDtos(Collection<Flat> flatCollection);

    Flat fromCreateDto(FlatCreateDto createDto);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE, nullValueCheckStrategy = ALWAYS)
    Flat mergeFlat(FlatUpdateDto updateDto, @MappingTarget Flat flat);
}
