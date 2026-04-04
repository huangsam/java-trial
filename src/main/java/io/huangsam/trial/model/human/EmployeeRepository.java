package io.huangsam.trial.model.human;

import java.util.HashMap;
import java.util.Map;

/**
 * Repository for managing employee records.
 */
public class EmployeeRepository {
    /**
     * Constructs an employee repository.
     */
    public EmployeeRepository() {
        // Default constructor
    }

    private final Map<Integer, Employee> employeeRecords = new HashMap<>();

    /**
     * Adds an employee to the repository.
     *
     * @param employee the employee to add
     */
    public void add(Employee employee) {
        employeeRecords.put(employee.getId(), employee);
    }

    /**
     * Finds an employee by their ID.
     *
     * @param id the ID of the employee
     * @return the employee, or null if not found
     */
    public Employee findById(int id) {
        return employeeRecords.get(id);
    }
}
