package ru.job4j.ood.ispnew;

import java.util.Optional;

public interface CRUD<K, V> {
    boolean add(K parentName, K elementName, V action);

    boolean remove(K elementName);

    boolean update(K elementName, V action);

    Optional<V> get(K elementName);
}