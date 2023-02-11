package com.alexchern.rent_da_house_resource_service.api.controller;

import com.alexchern.rent_da_house_resource_service.domain.dto.OwnerCreateDto;
import com.alexchern.rent_da_house_resource_service.domain.dto.OwnerDto;
import com.alexchern.rent_da_house_resource_service.domain.entity.Owner;
import com.alexchern.rent_da_house_resource_service.domain.mapper.OwnerMapper;
import com.alexchern.rent_da_house_resource_service.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/owners")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;
    private final OwnerMapper ownerMapper;

    @GetMapping
    public List<OwnerDto> getAllOwners() {
        return ownerMapper.toDtos(ownerService.getAllOwners());
    }

    @GetMapping("/{ownerId}")
    public OwnerDto getOwnerById(@PathVariable long ownerId) {
        return ownerMapper.toDto(ownerService.getOwnerById(ownerId));
    }

    @PostMapping
    public OwnerDto createOwner(@RequestBody OwnerCreateDto createDto) {
        Owner owner = ownerService.createOwner(
                createDto.getFirstName(),
                createDto.getLastName(),
                createDto.getPhoneNumber()
        );

        return ownerMapper.toDto(owner);
    }
}
