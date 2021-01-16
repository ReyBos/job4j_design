package ru.job4j.ood.isp.item;

public interface MenuItem extends MenuItemChildren {
    String getName();

    boolean doAction();
}
