package com.alexchern.rent_da_house_resource_service.service;

import java.util.List;

public interface BaseService<ENTITY> {

    ENTITY create(ENTITY entity);

    List<ENTITY> getAll();

    ENTITY getById(long entityId);
}
