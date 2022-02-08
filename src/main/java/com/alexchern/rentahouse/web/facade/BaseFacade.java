package com.alexchern.rentahouse.web.facade;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BaseFacade <DTO> {

    List<DTO> getAll();

    DTO create(DTO dto);

    DTO getByID(long dtoId);

    DTO update(long dtoId, DTO dto);

    void delete(long dtoId);
}
