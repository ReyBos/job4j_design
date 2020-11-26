package ru.job4j.tree;

import java.util.*;
import java.util.function.BiPredicate;

public class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    Tree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> parentNode = findBy(parent);
        if (findBy(child).isPresent() || parentNode.isEmpty()) {
            return false;
        }
        return parentNode.get().children.add(new Node<>(child));
    }

    public boolean isBinary() {
        BiPredicate<Node<E>, E> func = (node, val) -> node.children.size() > 2;
        return findElemByPredicate(func, null).isEmpty();
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        BiPredicate<Node<E>, E> func = (node, val) -> node.value.equals(val);
        return findElemByPredicate(func, value);
    }

    private Optional<Node<E>> findElemByPredicate(BiPredicate<Node<E>, E> func, E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (func.test(el, value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}