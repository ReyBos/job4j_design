package ru.job4j.ood.isp.builder;

import ru.job4j.ood.isp.item.MenuItem;

import java.util.List;

public interface MenuBuilder {
    List<MenuItem> makeMenu(List<MenuItem> menuItems);
}
