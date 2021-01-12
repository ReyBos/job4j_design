package ru.job4j.ood.srp.report;

import java.util.List;

public class ReportJson implements ReportType {
    @Override
    public String generate(List<Employee> employees) {
        StringBuilder text = new StringBuilder();
        text.append("{");
        for (int i = 0; i < employees.size(); i++) {
            text.append("\"").append(i).append("\": ")
                    .append("{").append("\"Name\": \"").append(employees.get(i).getName()).append("\",")
                    .append("\"Salary\": ").append(employees.get(i).getSalary());
            if (i + 1 == employees.size()) {
                text.append("}");
            } else {
                text.append("},");
            }
        }
        text.append("}");
        return text.toString();
    }
}
