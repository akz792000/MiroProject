package org.miro.project.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class WidgetEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Long width;

    private Long height;

    private Long zIndex;

}
