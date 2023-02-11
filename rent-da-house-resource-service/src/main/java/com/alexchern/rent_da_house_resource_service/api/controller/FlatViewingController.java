package com.alexchern.rent_da_house_resource_service.api.controller;

import com.alexchern.rent_da_house_resource_service.domain.dto.FlatViewingDto;
import com.alexchern.rent_da_house_resource_service.domain.mapper.FlatViewingMapper;
import com.alexchern.rent_da_house_resource_service.service.FlatViewingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
