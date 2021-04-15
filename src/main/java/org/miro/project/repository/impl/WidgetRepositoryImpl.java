package org.miro.project.repository.impl;

import lombok.RequiredArgsConstructor;
import org.miro.project.domain.WidgetEntity;
import org.miro.project.repository.WidgetCustomRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Optional;

;

/**
 * @author Ali Karimizandi
 * @since 2021
 */
@RequiredArgsConstructor
@Repository
@Transactional
public class WidgetRepositoryImpl implements WidgetCustomRepository {

    private final ApplicationContext applicationContext;

    private WidgetCustomRepository repository;

    @Value("${application.use-database}")
    private boolean useDatabase;

    @PostConstruct
    public void init() {
        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
        repository = useDatabase ? autowireCapableBeanFactory.createBean(WidgetCustomRepositoryImpl.class)
                : autowireCapableBeanFactory.createBean(InMemoryWidgetCustomRepositoryImpl.class);
    }

    @Override
    public Page<WidgetEntity> getAll(Pageable pageable) {
        return repository.getAll(pageable);
    }

    @Override
    public Optional<WidgetEntity> getById(Long id) {
        return repository.getById(id);
    }

    @Override
    public WidgetEntity persist(WidgetEntity entity) {
        return repository.persist(entity);
    }

    @Override
    public void merge(WidgetEntity entity) {
        repository.merge(entity);
    }

    @Override
    public void removeById(Long id) {
        repository.removeById(id);
    }


}
