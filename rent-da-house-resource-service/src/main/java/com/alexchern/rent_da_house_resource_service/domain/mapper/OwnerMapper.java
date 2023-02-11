package com.alexchern.rent_da_house_resource_service.domain.mapper;

import com.alexchern.rent_da_house_resource_service.domain.dto.OwnerDto;
import com.alexchern.rent_da_house_resource_service.domain.entity.Owner;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface OwnerMapper {

    OwnerDto toDto(Owner owner);
    List<OwnerDto> toDtos(Collection<Owner> ownerCollection);
}
