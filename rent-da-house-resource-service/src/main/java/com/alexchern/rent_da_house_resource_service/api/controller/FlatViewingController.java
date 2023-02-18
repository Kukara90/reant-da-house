package com.alexchern.rent_da_house_resource_service.api.controller;

import com.alexchern.rent_da_house_resource_service.domain.dto.FlatViewingCreateDto;
import com.alexchern.rent_da_house_resource_service.domain.dto.FlatViewingDto;
import com.alexchern.rent_da_house_resource_service.domain.dto.FlatViewingUpdateDto;
import com.alexchern.rent_da_house_resource_service.domain.entity.FlatViewing;
import com.alexchern.rent_da_house_resource_service.domain.mapper.FlatViewingMapper;
import com.alexchern.rent_da_house_resource_service.service.FlatViewingService;
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
@RequestMapping("/flat-viewings")
@RequiredArgsConstructor
public class FlatViewingController {

    private final FlatViewingService flatViewingService;
    private final FlatViewingMapper flatViewingMapper;

    @GetMapping
    public List<FlatViewingDto> getAllFlats() {
        return flatViewingMapper.toDtos(flatViewingService.getAllFlatViewings());
    }

    @GetMapping("/{flatViewingId}")
    public FlatViewingDto getFlatById(@PathVariable long flatViewingId) {
        return flatViewingMapper.toDto(flatViewingService.getFlatViewingById(flatViewingId));
    }

    @PostMapping
    public FlatViewingDto createFlatViewing(@RequestBody FlatViewingCreateDto createDto) {
        FlatViewing flatViewing = flatViewingMapper.fromCreateDto(createDto);

        return flatViewingMapper.toDto(flatViewingService.createFlatViewing(flatViewing, createDto.getFlatId()));
    }

    @PatchMapping("/{flatViewingId}")
    public FlatViewingDto editFlatViewing(
            @PathVariable long flatViewingId,
            @RequestBody FlatViewingUpdateDto updateDto
    ) {
        FlatViewing updatedFlatViewing = flatViewingService.editFlatViewing(flatViewingId,
                flatViewing -> flatViewingMapper.mergeFlatViewing(updateDto, flatViewing));

        return flatViewingMapper.toDto(updatedFlatViewing);
    }

    @DeleteMapping("/{flatViewingId}")
    public ResponseEntity<?> deleteFlatViewing(@PathVariable long flatViewingId) {
        flatViewingService.deleteFlatViewing(flatViewingId);
        return ResponseEntity.ok().build();
    }
}
