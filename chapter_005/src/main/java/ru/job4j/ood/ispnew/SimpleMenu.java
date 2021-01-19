package ru.job4j.ood.ispnew;

import java.util.*;
import java.util.function.BiFunction;

public class SimpleMenu implements Menu {
    List<Node> roots;

    public SimpleMenu(String rootName, Action action) {
        roots = new LinkedList<>();
        addRoot(rootName, action);
    }

    public boolean addRoot(String rootName, Action action) {
        boolean rsl = false;
        Node root = new Node(rootName, action);
        if (!roots.contains(root)) {
            roots.add(root);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public boolean add(String parentName, String elementName, Action action) {
        Optional<Node> parent = findNode(parentName);
        Optional<Node> element = findNode(elementName);
        if (parent.isEmpty() || element.isPresent()) {
            return false;
        }
        parent.get().children.add(new Node(elementName, action, parent.get()));
        return true;
    }

    @Override
    public boolean remove(String elementName) {
        BiFunction<Node, Action, Boolean> function = (node, act) -> {
            if (roots.contains(node)) {
                roots.remove(node);
            } else {
                Node parent = node.parent;
                parent.children.remove(node);
            }
            return true;
        };
        return changeNode(elementName, null, function);
    }

    @Override
    public boolean update(String elementName, Action action) {
        BiFunction<Node, Action, Boolean> function = (node, act) -> {
            node.action = action;
            return true;
        };
        return changeNode(elementName, action, function);
    }

    private boolean changeNode(
            String elementName, Action action, BiFunction<Node, Action, Boolean> function
    ) {
        Optional<Node> optionalNode = findNode(elementName);
        boolean rsl = false;
        if (optionalNode.isPresent()) {
            rsl = function.apply(optionalNode.get(), action);
        }
        return rsl;
    }

    @Override
    public Optional<Action> get(String elementName) {
        Optional<Action> action = Optional.empty();
        Optional<Node> node = findNode(elementName);
        if (node.isPresent()) {
            action = Optional.of(node.get().action);
        }
        return action;
    }

    private Optional<Node> findNode(String name) {
        Optional<Node> found = Optional.empty();
        Queue<Node> nodes = new LinkedList<>(roots);
        while (!nodes.isEmpty()) {
            Node node = nodes.poll();
            if (node.name.equals(name)) {
                found = Optional.of(node);
                break;
            }
            nodes.addAll(node.children);
        }
        return found;
    }

    @Override
    public String unOrdered() {
        return unOrdered(roots, 0, new StringBuilder()).toString();
    }

    private StringBuilder unOrdered(List<Node> nodes, int lvl, StringBuilder out) {
        String prefix = "-".repeat(lvl);
        for (Node node : nodes) {
            out.append(prefix).append(" ").append(node.name);
            out.append(System.lineSeparator());
            if (node.children.size() != 0) {
                unOrdered(node.children, lvl + 1, out);
            }
        }
        return out;
    }

    @Override
    public String ordered() {
        return ordered(roots, "", new StringBuilder()).toString();
    }

    private StringBuilder ordered(List<Node> nodes, String prefix, StringBuilder out) {
        String curPrefix;
        for (int i = 0; i < nodes.size(); i++) {
            curPrefix = String.format("%s%s", prefix, ((i + 1) + "."));
            out.append(curPrefix).append(" ").append(nodes.get(i).name);
            out.append(System.lineSeparator());
            if (nodes.get(i).children.size() != 0) {
                ordered(nodes.get(i).children, curPrefix, out);
            }
        }
        return out;
    }

    private static class Node {
        private String name;
        private Action action;
        private Node parent;
        private List<Node> children;

        private Node(String name, Action action) {
            this.name = name;
            this.action = action;
            this.children = new LinkedList<>();
        }

        public Node(String name, Action action, Node parent) {
            this(name, action);
            this.parent = parent;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node node = (Node) o;
            return Objects.equals(name, node.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }
}
