package com.alexchern.rentahouse.service;

import com.alexchern.rentahouse.domain.entity.FlatEntity;
import com.alexchern.rentahouse.domain.repository.BaseRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service("flatService")
public class FlatService extends AbstractService<FlatEntity> {

    public FlatService(BaseRepository<FlatEntity> repository) {
        super(repository);
    }

    @Override
    public FlatEntity update(long entityId, FlatEntity flatUpdateData) {
        FlatEntity flatForUpdate = getRepository().getById(entityId);

        FlatEntity flatForSave = populate(flatForUpdate, flatUpdateData);

        return getRepository().saveAndFlush(flatForSave);
    }

    //TODO think how to generify this logic
    private FlatEntity populate(FlatEntity flatForUpdate, FlatEntity flatUpdateData) {
        flatForUpdate.setTitle(flatUpdateData.getTitle());
        flatForUpdate.setPicture(flatUpdateData.getPicture());
        flatForUpdate.setLink(flatUpdateData.getLink());
        flatForUpdate.setShortDescription(flatUpdateData.getShortDescription());
        flatForUpdate.setOwner(flatUpdateData.getOwner());
        flatForUpdate.setCostPerMonth(flatUpdateData.getCostPerMonth());
        flatForUpdate.setVoteValue(flatUpdateData.getVoteValue());

        return flatForUpdate;
    }
}
