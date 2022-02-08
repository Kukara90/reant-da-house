package com.alexchern.rentahouse.service;

import com.alexchern.rentahouse.domain.repository.BaseRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Getter
@Transactional
public abstract class AbstractService<ENTITY> implements BaseService<ENTITY> {

    private final BaseRepository<ENTITY> repository;


    @Override
    public ENTITY create(ENTITY entity) {
        return repository.save(entity);
    }

    @Override
    public List<ENTITY> getAll() {
        return repository.findAll();
    }

    @Override
    public ENTITY getById(long entityId) {
        return repository.getById(entityId);
    }

    public abstract ENTITY update(long entityId, ENTITY entity);

    @Override
    public void delete(long entityId) {
        repository.deleteById(entityId);
    }
}
