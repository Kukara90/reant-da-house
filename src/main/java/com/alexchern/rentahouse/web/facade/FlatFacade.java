package com.alexchern.rentahouse.web.facade;

import com.alexchern.rentahouse.domain.entity.FlatEntity;
import com.alexchern.rentahouse.domain.mapper.FlatMapper;
import com.alexchern.rentahouse.service.BaseService;
import com.alexchern.rentahouse.service.FlatService;
import com.alexchern.rentahouse.web.dto.FlatDTO;
import com.alexchern.rentahouse.web.dto.factory.FlatDTOFactory;
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
            FlatDTO flatDTO
    ) {
        FlatDTO newFlatDTO = flatDTOFactory.createFlatDTO(flatDTO.getTitle(),
                flatDTO.getShortDescription(),
                flatDTO.getLink(),
                flatDTO.getPicture(),
                flatDTO.getAddress(),
                flatDTO.getVoteValue(),
                flatDTO.getCostPerMonth());

        FlatEntity newFlatEntity = flatService.create(flatMapper.toEntity(newFlatDTO));

        return flatMapper.toDTO(newFlatEntity);
    }
}
