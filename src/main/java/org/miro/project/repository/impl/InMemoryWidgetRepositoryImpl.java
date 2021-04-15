package org.miro.project.repository.impl;

import org.miro.project.domain.WidgetEntity;
import org.miro.project.repository.WidgetRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * @author Ali Karimizandi
 * @since 2021
 */
public class InMemoryWidgetRepositoryImpl implements WidgetRepository {

    private SortedSet<WidgetEntity> items = Collections.synchronizedSortedSet(new TreeSet<>());

    private AtomicLong identifier = new AtomicLong(1L);

    public Page<WidgetEntity> getAll(Pageable pageable) {
        List<WidgetEntity> result = items.stream()
                .skip(pageable.getPageNumber() * pageable.getPageSize())
                .limit(pageable.getPageSize())
                .collect(Collectors.toCollection(ArrayList::new));
        return new PageImpl<>(result, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), result.size());
    }

    public Optional<WidgetEntity> getById(Long id) {
        for (WidgetEntity item : items) {
            if (item.getId().equals(id)) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }

    public WidgetEntity persist(WidgetEntity entity) {
        Long id = identifier.getAndIncrement();
        entity.setId(id);
        items.add(entity);
        return entity;
    }

    public void merge(WidgetEntity entity) {
        Optional<WidgetEntity> optional = getById(entity.getId());
        if (optional.isPresent()) {
            WidgetEntity widgetEntity = optional.get();
            widgetEntity.setX(entity.getX());
            widgetEntity.setY(entity.getY());
            widgetEntity.setHeight(entity.getHeight());
            widgetEntity.setWidth(entity.getWidth());
            widgetEntity.setLastModificationDate(entity.getLastModificationDate());
            widgetEntity.setZIndex(entity.getZIndex());
        }
    }

    @Override
    public void removeById(Long id) {
        items.removeIf((e) -> e.getId().equals(id));
    }

    @Override
    public void removeAll() {
        items = Collections.synchronizedSortedSet(new TreeSet<>());
    }

    @Override
    public List<WidgetEntity> findInRegion(WidgetEntity entity) {
        return items.stream().filter((e) -> e.getX() >= entity.getX()
                && e.getY() >= entity.getY()
                && (e.getHeight() + e.getY()) <= (entity.getHeight() + entity.getY())
                && (e.getWidth() + e.getX()) <= (entity.getWidth() + entity.getX())).collect(Collectors.toList());
    }

}
