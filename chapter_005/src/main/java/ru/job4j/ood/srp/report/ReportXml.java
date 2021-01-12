package ru.job4j.ood.srp.report;

import java.util.List;

public class ReportXml implements ReportType {
    @Override
    public String generate(List<Employee> employees) {
        StringBuilder text = new StringBuilder();
        text.append("<Employees>");
        for (Employee employee : employees) {
            text
                    .append("<Employee>")
                    .append("<Name>")
                    .append(employee.getName())
                    .append("</Name>")
                    .append("<Salary>")
                    .append(employee.getSalary())
                    .append("</Salary>")
                    .append("</Employee>");
        }
        text.append("</Employees>");
        return text.toString();
    }
}
