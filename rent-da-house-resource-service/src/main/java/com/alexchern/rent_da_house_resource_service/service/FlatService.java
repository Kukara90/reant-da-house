package com.alexchern.rent_da_house_resource_service.service;

import com.alexchern.rent_da_house_resource_service.domain.entity.Flat;
import com.alexchern.rent_da_house_resource_service.domain.entity.Owner;
import com.alexchern.rent_da_house_resource_service.domain.repository.FlatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FlatService {

    private final FlatRepository flatRepository;
    private final OwnerService ownerService;

    @Transactional(readOnly = true)
    public List<Flat> getAllFlats() {
        return flatRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Flat getFlatById(long flatId) {
        return flatRepository.findById(flatId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Flat with id: %s doesn't exists", flatId)));
    }

    public Flat createFlat(Flat flat) {
        return flatRepository.save(flat);
    }

    public Flat assignOwner(long flatId, long ownerId) {
        Flat flat = getFlatById(flatId);
        Owner owner = ownerService.getOwnerById(ownerId);

        flat.setOwner(owner);
        return flatRepository.save(flat);
    }
}
