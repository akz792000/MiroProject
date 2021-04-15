package org.miro.project.repository.impl;

import lombok.AllArgsConstructor;
import org.miro.project.domain.WidgetEntity;
import org.miro.project.repository.WidgetRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

/**
 * @author Ali Karimizandi
 * @since 2021
 */
@AllArgsConstructor
@Transactional
public class DatabaseWidgetRepositoryImpl implements WidgetRepository {

    private final EntityManager entityManager;

    @Override
    public Page<WidgetEntity> getAll(Pageable pageable) {
        List<WidgetEntity> entities = entityManager.createQuery("select U from WidgetEntity U order by U.zIndex")
                .setFirstResult(pageable.getPageNumber())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        return new PageImpl<>(entities, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), entities.size());
    }

    @Override
    public Optional<WidgetEntity> getById(Long id) {
        WidgetEntity widgetEntity = entityManager.find(WidgetEntity.class, id);
        return widgetEntity == null ? Optional.empty() : Optional.of(widgetEntity);
    }

    @Override
    public WidgetEntity persist(WidgetEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
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
            entityManager.merge(entity);
        }
    }

    @Override
    public void removeById(Long id) {
        Assert.notNull(id, "The given id must not be null!");
        WidgetEntity widgetEntity = this.entityManager.find(WidgetEntity.class, id);
        this.entityManager.remove(widgetEntity);
    }

    @Override
    public void removeAll() {
        Query query = entityManager.createQuery("delete from WidgetEntity");
        query.executeUpdate();
    }

    @Override
    public List<WidgetEntity> findInRegion(WidgetEntity entity) {
        return entityManager.createQuery("select U from WidgetEntity U where U.x >= ?1 and U.y >= ?2 and (U.height + U.y) <= ?3 and (U.width + U.x) <= ?4")
                .setParameter(1, entity.getX())
                .setParameter(2, entity.getY())
                .setParameter(3, entity.getHeight() + entity.getY())
                .setParameter(4, entity.getWidth() + entity.getX())
                .getResultList();
    }

}
