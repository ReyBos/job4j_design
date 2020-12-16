package ru.job4j.jdbc;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ConfigTest {
    private static final Logger LOG = LoggerFactory.getLogger(Config.class.getName());

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void readAndGetConfig() throws IOException {
        File configFile = folder.newFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(configFile))) {
            writer.write("key1=val1");
            writer.write(System.lineSeparator());
            writer.write("key2=val2");
        } catch (IOException e) {
            LOG.error("Ошибка записи файла конфигурации", e);
        }
        Config config = new Config(configFile.getAbsolutePath());
        config.load();
        assertThat(config.get("key1"), is("val1"));
        assertThat(config.get("key2"), is("val2"));
    }
}