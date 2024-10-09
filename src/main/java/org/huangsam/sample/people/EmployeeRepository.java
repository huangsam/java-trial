package org.huangsam.sample.people;

import java.util.Map;
import java.util.HashMap;

public class EmployeeRepository {
    private final Map<Integer, Employee> employeeRecords = new HashMap<>();

    public void add(Employee employee) {
        employeeRecords.put(employee.getId(), employee);
    }

    public Employee findById(int id) {
        return employeeRecords.get(id);
    }
}
