package org.miro.project.repository;

import org.miro.project.domain.WidgetEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Ali Karimizandi
 * @since 2021
 */
@Repository
public interface WidgetRepository extends CrudRepository<WidgetEntity, Long>, WidgetCustomRepository {

}
