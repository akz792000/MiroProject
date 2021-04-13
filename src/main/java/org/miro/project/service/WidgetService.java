package org.miro.project.service;

import lombok.RequiredArgsConstructor;
import org.miro.project.domain.WidgetEntity;
import org.miro.project.repository.WidgetRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class WidgetService {

    private final WidgetRepository repository;

    public void save(final WidgetEntity entity) {
        repository.save(entity);
    }

    public List<WidgetEntity> getAll() {
        final List<WidgetEntity> entities = new ArrayList<>();
        repository.findAll().forEach(entity -> entities.add(entity));
        return entities;
    }

}
