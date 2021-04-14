package org.miro.project.repository.impl;

import lombok.AllArgsConstructor;
import org.miro.project.domain.WidgetEntity;
import org.miro.project.repository.WidgetCustomRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * @author Ali Karimizandi
 * @since 2021
 */
@AllArgsConstructor
@Repository
@Transactional
public class WidgetCustomRepositoryImpl implements WidgetCustomRepository {

    private final EntityManager entityManager;

    public Page<WidgetEntity> getAll(Pageable pageable) {
        List<WidgetEntity> entities = entityManager.createQuery("select U from WidgetEntity U order by U.zIndex")
                .setFirstResult(pageable.getPageNumber())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        return new PageImpl<>(entities, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), entities.size());
    }

    public Optional<WidgetEntity> getById(Long id) {
        WidgetEntity widgetEntity = entityManager.find(WidgetEntity.class, id);
        return widgetEntity == null ? Optional.empty() : Optional.of(widgetEntity);
    }

    public WidgetEntity persist(WidgetEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void merge(WidgetEntity entity) {
        entityManager.merge(entity);
    }

    @Override
    public void removeById(Long id) {
        Assert.notNull(id, "The given id must not be null!");
        WidgetEntity widgetEntity = this.entityManager.find(WidgetEntity.class, id);
        this.entityManager.remove(widgetEntity);
    }
}
