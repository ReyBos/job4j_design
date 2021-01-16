package ru.job4j.ood.isp.io;

public class StubOutput implements Output {
    StringBuilder buffer = new StringBuilder();

    @Override
    public void print(Object ob) {
        if (ob != null) {
            buffer.append(ob.toString());
        } else {
            buffer.append("null");
        }
    }

    @Override
    public void println(Object ob) {
        print(ob);
        buffer.append(System.lineSeparator());
    }

    @Override
    public String toString() {
        return buffer.toString();
    }
}
