package com.alexchern.rent_da_house_resource_service.domain.mapper;

import com.alexchern.rent_da_house_resource_service.domain.dto.OwnerCreateDto;
import com.alexchern.rent_da_house_resource_service.domain.dto.OwnerDto;
import com.alexchern.rent_da_house_resource_service.domain.dto.OwnerUpdateDto;
import com.alexchern.rent_da_house_resource_service.domain.entity.Owner;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.Collection;
import java.util.List;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public interface OwnerMapper {

    OwnerDto toDto(Owner owner);

    List<OwnerDto> toDtos(Collection<Owner> ownerCollection);

    Owner fromCreateDto(OwnerCreateDto createDto);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE, nullValueCheckStrategy = ALWAYS)
    Owner mergeOwner(OwnerUpdateDto updateDto, @MappingTarget Owner owner);
}
