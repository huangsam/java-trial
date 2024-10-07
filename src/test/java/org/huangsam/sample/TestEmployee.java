package org.huangsam.sample;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.huangsam.sample.people.Employee;
import org.huangsam.sample.people.EmployeeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestEmployee {
    private static final Employee[] arrayOfEmps = {
        new Employee(1, "Jeff Bezos", 100000.0),
        new Employee(2, "Bill Gates", 200000.0),
        new Employee(3, "Mark Zuckerberg", 300000.0)
    };

    private static final List<Employee> empList = Arrays.asList(arrayOfEmps);

    private static final EmployeeRepository empRepo = new EmployeeRepository();

    @BeforeAll
    static void setup() {
        empList.forEach(empRepo::add);
    }

    @Test
    void testEmployeeGetter() {
        Employee employee = new Employee(1, "John Doe", 100000);
        assertEquals(1, employee.getId());
        assertEquals("John Doe", employee.getName());
        assertEquals(100000, employee.getSalary());
    }

    @Test
    void testSalaryIncrement() {
        empList.forEach(e -> {
            double originalSalary = e.getSalary();
            e.salaryIncrement(10.0);
            assertEquals(e.getSalary(), originalSalary + 10.0);
        });
    }

    @Test
    void testEmployeeIdToEmployeeStream() {
        Integer[] empIds = {1, 2, 3};
        List<Employee> employeesFromRepo = Stream.of(empIds).map(empRepo::findById).toList();
        assertEquals(empIds.length, employeesFromRepo.size());
    }

    @Test
    void testCollectStreamThenList() {
        List<Employee> employees = empList.stream().toList();
        assertEquals(empList.size(), employees.size());
    }
}
