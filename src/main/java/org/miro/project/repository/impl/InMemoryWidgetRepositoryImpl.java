package org.miro.project.repository.impl;

import org.miro.project.domain.WidgetEntity;
import org.miro.project.repository.WidgetRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Ali Karimizandi
 * @since 2021
 */
public class InMemoryWidgetRepositoryImpl implements WidgetRepository {

    private SortedSet<WidgetEntity> items = Collections.synchronizedSortedSet(new TreeSet<>());

    private AtomicLong identifier;

    public Page<WidgetEntity> getAll(Pageable pageable) {
        return null;
    }

    public Optional<WidgetEntity> getById(Long id) {
        return null;
    }

    public WidgetEntity persist(WidgetEntity entity) {
        Long id = identifier.getAndIncrement();
        entity.setId(id);
        items.add(entity);
        return entity;
    }

    public void merge(WidgetEntity entity) {

    }

    @Override
    public void removeById(Long id) {
    }
}