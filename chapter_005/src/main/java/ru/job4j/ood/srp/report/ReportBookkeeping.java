package ru.job4j.ood.srp.report;

import java.util.List;

public class ReportBookkeeping implements ReportType {
    @Override
    public String generate(List<Employee> employees) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary with ndfl;")
                .append(System.lineSeparator());
        for (Employee employee : employees) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append((employee.getSalary() * 1.13)).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
