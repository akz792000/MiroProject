package org.miro.project.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Ali Karimizandi
 * @since 2021
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(indexes = {
        @Index(columnList = "zIndex"),
})
public class WidgetEntity implements Comparable<WidgetEntity> {

    @Id
    @GeneratedValue
    private Long id;

    private int x;

    private int y;

    private int width;

    private int height;

    private int zIndex;

    private Date lastModificationDate;

    @Override
    public int compareTo(WidgetEntity o) {
        return this.zIndex > o.zIndex? 1 : -1;
    }
}
