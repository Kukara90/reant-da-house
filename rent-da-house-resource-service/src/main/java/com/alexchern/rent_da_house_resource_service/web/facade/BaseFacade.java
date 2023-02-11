package com.alexchern.rent_da_house_resource_service.web.facade;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BaseFacade <DTO> {

    List<DTO> getAll();

    DTO create();
}
