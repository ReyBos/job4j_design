package ru.job4j.ood.srp.report;

import java.util.List;

public class ReportHR implements ReportType {
    @Override
    public String generate(List<Employee> employees) {
        employees.sort((o1, o2) -> Double.compare(o2.getSalary(), o1.getSalary()));
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : employees) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
