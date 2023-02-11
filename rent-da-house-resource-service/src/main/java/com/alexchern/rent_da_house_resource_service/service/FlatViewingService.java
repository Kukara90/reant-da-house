package com.alexchern.rent_da_house_resource_service.service;

import com.alexchern.rent_da_house_resource_service.domain.entity.FlatViewing;
import com.alexchern.rent_da_house_resource_service.domain.repository.FlatViewingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FlatViewingService {

    private final FlatViewingRepository flatViewingRepository;

    @Transactional(readOnly = true)
    public List<FlatViewing> getAllFlatViewings() {
        return flatViewingRepository.findAll();
    }

    @Transactional(readOnly = true)
    public FlatViewing getFlatViewingById(long flatViewingId) {
        return flatViewingRepository.findById(flatViewingId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Flat viewing with id: %s doesn't exists", flatViewingId)));
    }
}
