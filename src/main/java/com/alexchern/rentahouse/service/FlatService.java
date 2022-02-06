package com.alexchern.rentahouse.service;

import com.alexchern.rentahouse.domain.entity.FlatEntity;
import com.alexchern.rentahouse.domain.repository.BaseRepository;
import org.springframework.stereotype.Service;

@Service("flatService")
public class FlatService extends AbstractService<FlatEntity> {

    public FlatService(BaseRepository<FlatEntity> repository) {
        super(repository);
    }
}
