package com.alexchern.rentahouse.service;

import com.alexchern.rentahouse.domain.entity.FlatViewingEntity;
import com.alexchern.rentahouse.domain.repository.BaseRepository;
import org.springframework.stereotype.Service;

@Service("flatViewingService")
public class FlatViewingService extends AbstractService<FlatViewingEntity> {

    public FlatViewingService(BaseRepository<FlatViewingEntity> repository) {
        super(repository);
    }

    @Override
    public FlatViewingEntity update(long entityId, FlatViewingEntity flatViewingData) {
        FlatViewingEntity flatViewingForUpdate = getRepository().getById(entityId);

        FlatViewingEntity flatViewingForSave = populate(flatViewingForUpdate, flatViewingData);

        return getRepository().saveAndFlush(flatViewingForSave);
    }

    //TODO think how to generify this logic
    private FlatViewingEntity populate(FlatViewingEntity flatViewingForUpdate, FlatViewingEntity flatViewingUpdateData) {
        flatViewingForUpdate.setViewingDay(flatViewingUpdateData.getViewingDay());
        flatViewingForUpdate.setShortDescription(flatViewingUpdateData.getShortDescription());

        return flatViewingForUpdate;
    }
}
