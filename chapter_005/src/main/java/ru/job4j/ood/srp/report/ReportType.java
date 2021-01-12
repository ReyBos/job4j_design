package ru.job4j.ood.srp.report;

import java.util.List;

public interface ReportType {
    String generate(List<Employee> employees);
}
