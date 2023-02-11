package com.alexchern.rent_da_house_resource_service.domain.repository;

import com.alexchern.rent_da_house_resource_service.domain.entity.Flat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlatRepository extends JpaRepository<Flat, Long> {

}
