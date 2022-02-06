package com.alexchern.rentahouse.web.facade;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BaseFacade <DTO> {

    List<DTO> getAll();

    DTO create(DTO dto);
}
