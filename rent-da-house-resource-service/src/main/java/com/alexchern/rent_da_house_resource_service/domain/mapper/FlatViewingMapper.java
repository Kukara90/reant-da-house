package com.alexchern.rent_da_house_resource_service.domain.mapper;

import com.alexchern.rent_da_house_resource_service.domain.dto.FlatViewingDto;
import com.alexchern.rent_da_house_resource_service.domain.entity.FlatViewing;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface FlatViewingMapper {

    FlatViewingDto toDto(FlatViewing flatViewing);
    List<FlatViewingDto> toDtos(Collection<FlatViewing> flatViewingCollection);
}
