package ru.job4j.ood.srp.report;

import java.util.List;
import java.util.function.Predicate;

public class ReportEngine {
    private Store store;
    private ReportType reportType;

    public ReportEngine(Store store, ReportType reportType) {
        this.store = store;
        this.reportType = reportType;
    }

    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter);
        return reportType.generate(employees);
    }
}