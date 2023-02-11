package com.alexchern.rent_da_house_resource_service.service;

import com.alexchern.rentahouse.domain.entity.FlatViewingEntity;
import com.alexchern.rentahouse.domain.repository.BaseRepository;
import org.springframework.stereotype.Service;

@Service("flatViewingService")
public class FlatViewingService extends AbstractService<FlatViewingEntity> {


    public FlatViewingService(BaseRepository<FlatViewingEntity> repository) {
        super(repository);
    }

    /*@Override
    public FlatViewingEntity getById(long entityId) {
        return flatViewingRepository.findById(entityId)
                .orElseThrow(
                        () -> new EntityNotFoundException(String.format("Flat with Id: %s doesn't exists", entityId))
                );
    }*/
}
