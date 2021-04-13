package org.miro.project.repository;

import org.miro.project.domain.WidgetEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WidgetRepository extends CrudRepository<WidgetEntity, Integer> {

}
