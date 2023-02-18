package com.alexchern.rent_da_house_resource_service.service;

import com.alexchern.rent_da_house_resource_service.domain.entity.Flat;
import com.alexchern.rent_da_house_resource_service.domain.entity.FlatViewing;
import com.alexchern.rent_da_house_resource_service.domain.repository.FlatViewingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.function.Consumer;

@Service
@Transactional
@RequiredArgsConstructor
public class FlatViewingService {

    private final FlatViewingRepository flatViewingRepository;
    private final FlatService flatService;

    @Transactional(readOnly = true)
    public List<FlatViewing> getAllFlatViewings() {
        return flatViewingRepository.findAll();
    }

    @Transactional(readOnly = true)
    public FlatViewing getFlatViewingById(long flatViewingId) {
        return flatViewingRepository.findById(flatViewingId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Flat viewing with id: %s doesn't exists", flatViewingId)));
    }

    public FlatViewing createFlatViewing(FlatViewing flatViewing, Long flatId) {
        if (flatId == null) {
            throw new IllegalArgumentException(
                    "When creating a viewing, flat id must not be null"
            );
        }

        Flat flat = flatService.getFlatById(flatId);
        flatViewing.setFlat(flat);

        return flatViewingRepository.save(flatViewing);
    }

    public FlatViewing editFlatViewing(long flatViewingId, Consumer<FlatViewing> modifier) {
        FlatViewing flatViewing = getFlatViewingById(flatViewingId);
        modifier.accept(flatViewing);

        return flatViewingRepository.save(flatViewing);
    }

    public void deleteFlatViewing(long flatViewingId) {
        flatViewingRepository.deleteById(flatViewingId);
    }
}
