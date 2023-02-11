package com.alexchern.rent_da_house_resource_service.domain.repository;

import com.alexchern.rent_da_house_resource_service.domain.entity.FlatViewingEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlatViewingRepository extends BaseRepository<FlatViewingEntity> {

    @Override
    Optional<FlatViewingEntity> findById(long flatViewingId);

    Optional<List<FlatViewingEntity>> findAllByFlatId(long flatId);
}
