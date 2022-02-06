package com.alexchern.rentahouse.web.facade;

import com.alexchern.rentahouse.domain.entity.FlatViewingEntity;
import com.alexchern.rentahouse.domain.mapper.FlatViewMapper;
import com.alexchern.rentahouse.service.FlatViewingService;
import com.alexchern.rentahouse.web.dto.FlatViewingDTO;
import com.alexchern.rentahouse.web.dto.factory.FlatViewingDTOFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class FlatViewingFacade implements BaseFacade<FlatViewingDTO> {

    private final FlatViewMapper flatViewMapper;

    private final FlatViewingService flatViewingService;

    private final FlatViewingDTOFactory flatViewingFactory;

    @Override
    public List<FlatViewingDTO> getAll() {
        return flatViewingService.getAll().stream()
                .map(flatViewMapper::toDTO)
                .collect(Collectors.toList());
    }

    /*public List<FlatViewingDTO> findAllByFlatId(long flatId) {
        return flatViewingService.findAllFlatViewingsByFlatId(flatId).stream()
                .map(flatViewMapper::toDTO)
                .collect(Collectors.toList());
    }*/

    @Override
    public FlatViewingDTO create(FlatViewingDTO flatViewingDTO) {

        FlatViewingEntity flatViewingEntity = flatViewingService.create(flatViewMapper.toEntity(flatViewingDTO));

        return flatViewMapper.toDTO(flatViewingEntity);
    }
}
