package org.huangsam.sample.people;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

import org.huangsam.sample.people.Employee;

public class EmployeeRepository {
    private HashMap<Integer, Employee> employeeRecords = new HashMap<>();

    public void add(Employee employee) {
        employeeRecords.put(employee.getId(), employee);
    }

    public Employee findById(int id) {
        return employeeRecords.get(id);
    }
}
