package ru.job4j.serialization.json;

public class Material {
    private String name;

    public Material(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Material{"
                + "name='" + name + '\''
                + '}';
    }
}
