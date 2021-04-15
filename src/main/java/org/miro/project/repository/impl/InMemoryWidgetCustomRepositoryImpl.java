package org.miro.project.repository.impl;

import lombok.AllArgsConstructor;
import org.miro.project.domain.WidgetEntity;
import org.miro.project.repository.WidgetCustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Ali Karimizandi
 * @since 2021
 */
@AllArgsConstructor
@Repository
@Transactional
public class InMemoryWidgetCustomRepositoryImpl implements WidgetCustomRepository {

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
