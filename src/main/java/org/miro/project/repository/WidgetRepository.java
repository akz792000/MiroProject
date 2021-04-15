package org.miro.project.repository;

import org.miro.project.domain.WidgetEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @author Ali Karimizandi
 * @since 2021
 */
public interface WidgetRepository {

    Page<WidgetEntity> getAll(Pageable pageable);

    Optional<WidgetEntity> getById(Long var1);

    WidgetEntity persist(WidgetEntity entity);

    void merge(WidgetEntity entity);

    void removeById(Long id);

    void removeAll();

    List<WidgetEntity> findInRegion(WidgetEntity entity);

}
