package org.miro.project;

import org.junit.jupiter.api.Test;
import org.miro.project.domain.WidgetEntity;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author Ali Karimizandi
 * @since 2021
 */
public class DataStructureTests {

    @Test
    public void sample() {
        SortedSet<WidgetEntity> items = Collections.synchronizedSortedSet(new TreeSet<>());

        // add entity 1
        WidgetEntity e1 = new WidgetEntity();
        e1.setId(1L);
        e1.setZIndex(1);
        items.add(e1);

        // add entity 2
        WidgetEntity e2 = new WidgetEntity();
        e2.setId(2L);
        e2.setZIndex(2);
        items.add(e2);

        // add entity 3
        WidgetEntity e3 = new WidgetEntity();
        e3.setId(3L);
        e3.setZIndex(3);
        items.add(e3);

        items.forEach((e) -> System.out.print("[" + e.getId() + "-" + e.getZIndex() + "]"));

        // add entity 4
        WidgetEntity e4 = new WidgetEntity();
        e4.setId(4L);
        e4.setZIndex(2);
        items.add(e4);

        System.out.println("");
        items.forEach((e) -> System.out.print("[" + e.getId() + "-" + e.getZIndex() + "]"));
    }

}
