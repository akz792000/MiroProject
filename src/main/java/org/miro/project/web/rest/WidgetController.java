package org.miro.project.web.rest;

import lombok.RequiredArgsConstructor;
import org.miro.project.domain.WidgetEntity;
import org.miro.project.service.WidgetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController("/widget")
public class WidgetController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final WidgetService service;

    @PostMapping(value = "/save")
    public Long save(final @RequestBody @Valid WidgetEntity entity) {
        log.info("Saving widget details in the database.");
        service.save(entity);
        return entity.getId();
    }

    @GetMapping(value = "/getAll")
    public List<WidgetEntity> getAll() {
        log.info("Getting widget details from the database.");
        return service.getAll();

    }
}
