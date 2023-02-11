package com.alexchern.rent_da_house_resource_service.web.facade;

import com.alexchern.rent_da_house_resource_service.web.dto.FlatDTO;
import com.alexchern.rentahouse.domain.entity.FlatEntity;
import com.alexchern.rentahouse.domain.mapper.FlatMapper;
import com.alexchern.rentahouse.service.FlatService;
import com.alexchern.rent_da_house_resource_service.web.dto.factory.FlatDTOFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class FlatFacade implements BaseFacade<FlatDTO> {

    private final FlatService flatService;

    private final FlatMapper flatMapper;

    private final FlatDTOFactory flatDTOFactory;

    @Override
    public List<FlatDTO> getAll() {
        return flatService.getAll().stream()
                .map(flatMapper::toDTO)
                .collect(Collectors.toList());
    }


    @Override
    public FlatDTO create(
    ) {

        FlatEntity newFlatEntity = flatService.create(FlatEntity.builder().build());

        return flatMapper.toDTO(newFlatEntity);
    }
}
