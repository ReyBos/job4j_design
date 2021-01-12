package ru.job4j.ood.srp.report;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import java.util.Calendar;

public class ReportEngineTest {
    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportType reportType = new ReportFull();
        ReportEngine engine = new ReportEngine(store, reportType);
        String expect = "Name; Hired; Fired; Salary;"
                + System.lineSeparator()
                + worker.getName() + ";"
                + worker.getHired() + ";"
                + worker.getFired() + ";"
                + worker.getSalary() + ";"
                + System.lineSeparator();
        assertThat(engine.generate(em -> true), is(expect));
    }

    @Test
    public void whenHRGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 10);
        Employee worker2 = new Employee("Sidor", now, now, 100);
        store.add(worker);
        store.add(worker2);
        ReportType reportType = new ReportHR();
        ReportEngine engine = new ReportEngine(store, reportType);
        String expect = "Name; Salary;"
                + System.lineSeparator()
                + worker2.getName() + ";"
                + worker2.getSalary() + ";"
                + System.lineSeparator()
                + worker.getName() + ";"
                + worker.getSalary() + ";"
                + System.lineSeparator();
        assertThat(engine.generate(em -> true), is(expect));
    }

    @Test
    public void whenBookkeepingGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportType reportType = new ReportBookkeeping();
        ReportEngine engine = new ReportEngine(store, reportType);
        String expect = "Name; Hired; Fired; Salary with ndfl;"
                + System.lineSeparator()
                + worker.getName() + ";"
                + worker.getHired() + ";"
                + worker.getFired() + ";"
                + (worker.getSalary() * 1.13) + ";"
                + System.lineSeparator();
        assertThat(engine.generate(em -> true), is(expect));
    }

    @Test
    public void whenProgrammerGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportType reportType = new ReportProgrammer();
        ReportEngine engine = new ReportEngine(store, reportType);
        String expect = "<html><body>"
                + "<p>Name; Hired; Fired; Salary;</p><p>"
                + worker.getName() + ";"
                + worker.getHired() + ";"
                + worker.getFired() + ";"
                + worker.getSalary() + ";"
                + "</p></body></html>";
        assertThat(engine.generate(em -> true), is(expect));
    }

    @Test
    public void whenJsonGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportType reportType = new ReportJson();
        ReportEngine engine = new ReportEngine(store, reportType);
        String expect = "{\"0\": {"
                + "\"Name\": \"" + worker.getName() + "\","
                + "\"Salary\": " + worker.getSalary() + "}}";
        assertThat(engine.generate(em -> true), is(expect));
    }

    @Test
    public void whenXmlGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportType reportType = new ReportXml();
        ReportEngine engine = new ReportEngine(store, reportType);
        String expect = "<Employees>"
                + "<Employee>"
                + "<Name>" + worker.getName() + "</Name>"
                + "<Salary>" + worker.getSalary() + "</Salary>"
                + "</Employee>"
                + "</Employees>";
        assertThat(engine.generate(em -> true), is(expect));
    }
}