package ru.job4j.ood.srp.report;

import java.util.List;

public class ReportProgrammer implements ReportType {
    @Override
    public String generate(List<Employee> employees) {
        StringBuilder text = new StringBuilder();
        text.append("<html><body>")
                .append("<p>Name; Hired; Fired; Salary;</p>");
        for (Employee employee : employees) {
            text.append("<p>")
                    .append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append("</p>");
        }
        text.append("</body></html>");
        return text.toString();
    }
}
