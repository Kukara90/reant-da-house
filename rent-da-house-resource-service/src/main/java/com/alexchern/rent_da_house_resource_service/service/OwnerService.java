package com.alexchern.rent_da_house_resource_service.service;

import com.alexchern.rent_da_house_resource_service.domain.entity.Owner;
import com.alexchern.rent_da_house_resource_service.domain.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;

    @Transactional(readOnly = true)
    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Owner getOwnerById(long ownerId) {
        return ownerRepository.findById(ownerId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Owner with id: %s doesn't exists", ownerId)));
    }

    public Owner createOwner(Owner owner) {
        return ownerRepository.save(owner);
    }
}
