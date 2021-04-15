package org.miro.project.service;

import lombok.RequiredArgsConstructor;
import org.miro.project.domain.WidgetEntity;
import org.miro.project.repository.WidgetRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

/**
 * @author Ali Karimizandi
 * @since 2021
 */
@RequiredArgsConstructor
@Service
public class WidgetService {

    private final WidgetRepository repository;

    public Page<WidgetEntity> getAll(int page, int pageSize) {
        Assert.isTrue(pageSize <= 500, "Page size should be equal or less than 500");
        PageRequest pageable = PageRequest.of(page - 1, pageSize);
        return repository.getAll(pageable);
    }

    public WidgetEntity getById(Long id) {
        Optional<WidgetEntity> entity = repository.getById(id);
        if (entity.isPresent()) {
            return entity.get();
        }
        return null;
    }

    public WidgetEntity persist(WidgetEntity entity) {
        repository.persist(entity);
        return entity;
    }

    public void merge(WidgetEntity entity) {
        repository.merge(entity);
    }

    public void removeById(Long id) {
        repository.removeById(id);
    }

    public void removeAll() {
        repository.removeAll();
    }

    public List<WidgetEntity> findInRegion(WidgetEntity entity) {
        return repository.findInRegion(entity);
    }

}
