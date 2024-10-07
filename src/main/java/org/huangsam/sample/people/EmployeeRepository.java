package org.huangsam.sample.people;

import java.util.HashMap;

public class EmployeeRepository {
    private final HashMap<Integer, Employee> employeeRecords = new HashMap<>();

    public void add(Employee employee) {
        employeeRecords.put(employee.getId(), employee);
    }

    public Employee findById(int id) {
        return employeeRecords.get(id);
    }
}
