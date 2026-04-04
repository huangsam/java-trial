package io.huangsam.trial.model.human;

/**
 * Represents an employee in the system.
 */
public class Employee {
    private final int id;
    private final String name;
    private double salary;

    /**
     * Constructs a new Employee instance.
     *
     * @param id the ID of the employee
     * @param name the name of the employee
     * @param salary the initial salary of the employee
     */
    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    /**
     * Gets the employee's ID.
     *
     * @return the employee ID
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the employee's name.
     *
     * @return the employee name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the employee's salary.
     *
     * @return the employee salary
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Increases the employee's salary by a given increment.
     *
     * @param increment the amount to add to the salary
     */
    public void increaseSalary(double increment) {
        this.salary += increment;
    }
}
