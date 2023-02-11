package com.alexchern.rent_da_house_resource_service.domain.repository;

import com.alexchern.rent_da_house_resource_service.domain.entity.FlatEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlatRepository extends BaseRepository<FlatEntity> {

    Optional<List<FlatEntity>> findAllByTitleOrderByTitleDesc(String flatTitle);
}
