package com.alexchern.rentahouse.domain.repository;

import com.alexchern.rentahouse.domain.entity.FlatEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlatRepository extends BaseRepository<FlatEntity> {

    Optional<List<FlatEntity>> findAllByTitleOrderByTitleDesc(String flatTitle);
}
