package ru.job4j.ood.isp.builder;

import ru.job4j.ood.isp.item.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class SimpleMenuBuilder implements MenuBuilder {
    @Override
    public List<MenuItem> makeMenu(List<MenuItem> menuItems) {
        List<MenuItem> rsl = new ArrayList<>();
        for (MenuItem item : menuItems) {
            rsl.add(item);
            if (item.getChildren().size() > 0) {
                rsl.addAll(this.makeMenu(item.getChildren()));
            }
        }
        return rsl;
    }
}
