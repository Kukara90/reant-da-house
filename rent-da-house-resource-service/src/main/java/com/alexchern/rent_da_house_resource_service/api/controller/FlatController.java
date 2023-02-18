package com.alexchern.rent_da_house_resource_service.api.controller;

import com.alexchern.rent_da_house_resource_service.domain.dto.FlatCreateDto;
import com.alexchern.rent_da_house_resource_service.domain.dto.FlatDto;
import com.alexchern.rent_da_house_resource_service.domain.dto.FlatUpdateDto;
import com.alexchern.rent_da_house_resource_service.domain.dto.OwnerAssignDto;
import com.alexchern.rent_da_house_resource_service.domain.entity.Flat;
import com.alexchern.rent_da_house_resource_service.domain.mapper.FlatMapper;
import com.alexchern.rent_da_house_resource_service.service.FlatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/flats")
@RequiredArgsConstructor
public class FlatController {

    private final FlatService flatService;
    private final FlatMapper flatMapper;

    @GetMapping
    public List<FlatDto> getAllFlats() {
        return flatMapper.toDtos(flatService.getAllFlats());
    }

    @GetMapping("/{flatId}")
    public FlatDto getFlatById(@PathVariable long flatId) {
        return flatMapper.toDto(flatService.getFlatById(flatId));
    }

    @PostMapping
    public FlatDto createFlat(@RequestBody FlatCreateDto createDto) {
        Flat flat = flatMapper.fromCreateDto(createDto);
        return flatMapper.toDto(flatService.createFlat(flat));
    }

    @PatchMapping("/{flatId}/owner")
    public FlatDto assignOwner(
            @PathVariable long flatId,
            @RequestBody OwnerAssignDto assignDto
    ) {
        return flatMapper.toDto(flatService.assignOwner(flatId, assignDto.getOwnerId()));
    }

    @PatchMapping("/{flatId}")
    public FlatDto editFlat(
            @PathVariable long flatId,
            @RequestBody FlatUpdateDto updateDto
    ) {
        Flat updatedFlat = flatService.editFlat(flatId,
                flat -> flatMapper.mergeFlat(updateDto, flat));

        return flatMapper.toDto(updatedFlat);
    }

    @DeleteMapping("/{flatId}")
    public ResponseEntity<?> deleteFlat(@PathVariable long flatId) {
        flatService.deleteFlat(flatId);
        return ResponseEntity.ok().build();
    }
}
