package com.alexchern.rent_da_house_resource_service.domain.mapper;

import com.alexchern.rent_da_house_resource_service.domain.dto.FlatViewingCreateDto;
import com.alexchern.rent_da_house_resource_service.domain.dto.FlatViewingDto;
import com.alexchern.rent_da_house_resource_service.domain.dto.FlatViewingUpdateDto;
import com.alexchern.rent_da_house_resource_service.domain.entity.FlatViewing;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Collection;
import java.util.List;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public interface FlatViewingMapper {

    FlatViewingDto toDto(FlatViewing flatViewing);

    List<FlatViewingDto> toDtos(Collection<FlatViewing> flatViewingCollection);

    @Mapping(target = "flat", ignore = true)
    FlatViewing fromCreateDto(FlatViewingCreateDto createDto);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE, nullValueCheckStrategy = ALWAYS)
    FlatViewing mergeFlatViewing(FlatViewingUpdateDto updateDto, @MappingTarget FlatViewing flatViewing);
}
