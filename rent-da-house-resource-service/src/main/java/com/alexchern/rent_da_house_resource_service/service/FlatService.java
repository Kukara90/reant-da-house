package com.alexchern.rent_da_house_resource_service.service;

import com.alexchern.rentahouse.domain.entity.FlatEntity;
import com.alexchern.rentahouse.domain.repository.BaseRepository;
import org.springframework.stereotype.Service;

@Service("flatService")
public class FlatService extends AbstractService<FlatEntity> {

    public FlatService(BaseRepository<FlatEntity> repository) {
        super(repository);
    }
}
