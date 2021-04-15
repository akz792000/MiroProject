package org.miro.project;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.*;
import org.miro.project.domain.WidgetEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MiroProjectApplicationTests {

    private final Logger log = LoggerFactory.getLogger(MiroProjectApplicationTests.class);

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    public String getUrl() {
        return "http://localhost:" + port + "/widget";
    }

    public WidgetEntity getById(Long id) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(getUrl() + "/getById")
                .queryParam("id", 1L);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        HttpEntity<WidgetEntity> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                httpEntity,
                WidgetEntity.class);
        return response.getBody();
    }

    public List<WidgetEntity> getAll(int page, int pageSize) {
        // set uri
        Map<String, Object> uriParam = new HashMap<>();
        uriParam.put("page", page);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(getUrl() + "/getAll/{page}")
                .queryParam("pageSize", pageSize);
        String url = builder.build(uriParam).toString();

        // send request
        HttpEntity<WidgetEntity[]> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                WidgetEntity[].class,
                uriParam);
        return Arrays.asList(response.getBody());
    }

    public List<WidgetEntity> getAll() {
        return getAll(1, 10);
    }

    public WidgetEntity persist(WidgetEntity entity) {
        HttpEntity<WidgetEntity> httpEntity = new HttpEntity<>(entity);
        ResponseEntity<WidgetEntity> response = this.restTemplate.postForEntity(getUrl() + "/persist", httpEntity, WidgetEntity.class);
        return response.getBody();
    }

    public void merge(WidgetEntity entity) {
        this.restTemplate.put(getUrl() + "/merge", entity);
    }

    //@Disabled
    @DisplayName("Base persist")
    @Order(1)
    @Test
    public void persist() {
        // persist
        WidgetEntity entity = persist(WidgetEntity.builder()
                .height(100)
                .width(100)
                .zIndex(1)
                .lastModificationDate(new Date())
                .build());
        assertThat(entity.getId()).isEqualTo(1L);
    }

    //@Disabled
    @DisplayName("Base getById")
    @Order(2)
    @Test
    public void getById() {
        // get by id
        WidgetEntity entity = getById(1L);
        Condition<WidgetEntity> condition = new Condition<>(
                m -> m.getId().equals(1L),
                "not equal"
        );
        assertThat(entity).isNotNull().is(condition);
    }

    //@Disabled
    @DisplayName("Base Merge")
    @Order(3)
    @Test
    public void merge() {
        // get by id
        WidgetEntity entity = getById(1L);

        // merge
        entity.setHeight(200);
        merge(entity);

        // get by id
        entity = getById(1L);
        assertThat(entity.getHeight()).isEqualTo(200L);
    }

    //@Disabled
    @DisplayName("Base removeById")
    @Order(4)
    @Test
    public void removeById() {
        // remove
        Map<String, Object> param = new HashMap<>();
        param.put("id", 1);
        this.restTemplate.delete(getUrl() + "/removeById/{id}", param);

        // check if it was removed
        WidgetEntity entity = getById(1L);
        assertThat(entity).isNull();
    }

    //@Disabled
    @DisplayName("Base findAll")
    @Order(5)
    @Test
    public void findAll() {
        // save 10 items
        for (int i = 1; i <= 10; i++) {
            WidgetEntity entity = WidgetEntity.builder()
                    .height(i * 10)
                    .width(i * 10)
                    .zIndex(i)
                    .build();
            persist(entity);
        }

        // getAll
        List<WidgetEntity> entities = getAll();
        assertThat(entities).size().isEqualTo(10);
    }

    //@Disabled
    @DisplayName("Middle insert")
    @Order(6)
    @Test
    public void middleInsert() {
        WidgetEntity entity;

        // persist
        entity = WidgetEntity.builder().zIndex(2).build();
        persist(entity);

        // set uri
        List<WidgetEntity> entities = getAll(1, 20);
        entities.forEach((e) -> log.info(String.valueOf(e.getZIndex())));
        assertThat(entities).size().isEqualTo(11);
    }

}
