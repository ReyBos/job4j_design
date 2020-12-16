package ru.job4j.jdbc;

import org.junit.Test;

import java.io.FileReader;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TableEditorTest {
    @Test
    public void checkTableEditor() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileReader("./src/main/resources/app.properties"));
        try (TableEditor tableEditor = new TableEditor(properties)) {
            tableEditor.createTable("test");
            tableEditor.addColumn("test", "column1", "text");
            tableEditor.addColumn("test", "column2", "text");
            tableEditor.addColumn("test", "column3", "text");
            tableEditor.renameColumn("test", "column3", "column_change");
            tableEditor.dropColumn("test", "column2");
            StringBuilder expected = new StringBuilder();
            expected.append(String.format("%-15s %-15s%n", "column", "type"));
            expected.append(String.format("%-15s %-15s%n", "column1", "text"));
            expected.append(String.format("%-15s %-15s%n", "column_change", "text"));
            assertThat(tableEditor.getScheme("test"), is(expected.toString()));
            tableEditor.dropTable("test");
            assertThat(tableEditor.getScheme("test"), is(String.format("%-15s %-15s%n", "column", "type")));
        }
    }
}