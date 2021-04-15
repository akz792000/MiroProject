package org.miro.project.web.rest;

import lombok.RequiredArgsConstructor;
import org.miro.project.domain.WidgetEntity;
import org.miro.project.service.WidgetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Ali Karimizandi
 * @since 2021
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/widget")
public class WidgetController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final WidgetService service;

    @GetMapping(value = "/getById")
    public WidgetEntity getById(@RequestParam Long id) throws Exception {
        log.info("Getting widget details from the database based on id.");
        return service.getById(id);
    }

    @GetMapping(value = "/getAll/{page}")
    public List<WidgetEntity> getAll(@PathVariable("page") int page, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        log.info("Getting widget details from the database.");
        Page<WidgetEntity> result = service.getAll(page, pageSize);
        return result.getContent();
    }

    @PostMapping(value = "/persist")
    public WidgetEntity persist(final @RequestBody @Valid WidgetEntity entity) {
        log.info("Saving widget details in the database.");
        return service.persist(entity);
    }

    @PutMapping(value = "/merge")
    public void merge(final @RequestBody @Valid WidgetEntity entity) {
        log.info("Merging widget details in the database.");
        service.merge(entity);
    }

    @DeleteMapping(value = "/removeById/{id}")
    public void removeById(final @PathVariable(value = "id") Long id) {
        log.info("Deleting widget details from the database.");
        service.removeById(id);
    }

    /*
     * this method implements extra just for test
     */
    @DeleteMapping(value = "/removeAll")
    public void removeAll() {
        log.info("Deleting all widget details from the database.");
        service.removeAll();
    }

    @PostMapping(value = "/findInRegion")
    public List<WidgetEntity> findInRegion(@RequestBody @Valid WidgetEntity region) {
        log.info("Getting widget details from the database in the requested region.");
        return service.findInRegion(region);
    }

}
