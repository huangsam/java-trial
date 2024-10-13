package org.huangsam.sample.people;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Inspired by content regarding Java Streams in Java 8. Testing things
 * based off of {@code Streams.of}, as well as trying the various methods
 * like {@code map}, {@code forEach}, etc.
 *
 * @see <a href="https://stackify.com/streams-guide-java-8/">Stackify post</a>
 */
public class TestEmployee {
    private static final Employee[] EMP_ARRAY = {
        new Employee(1, "Jeff Bezos", 100000.0),
        new Employee(2, "Bill Gates", 200000.0),
        new Employee(3, "Mark Zuckerberg", 300000.0)
    };

    // https://stackoverflow.com/a/16748184
    private static final List<Employee> EMP_LIST = Arrays.asList(EMP_ARRAY);

    private static final EmployeeRepository EMP_REPO = new EmployeeRepository();

    @BeforeAll
    static void setup() {
        EMP_LIST.forEach(EMP_REPO::add);
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
        EMP_LIST.forEach(e -> {
            double originalSalary = e.getSalary();
            e.salaryIncrement(10.0);
            assertEquals(e.getSalary(), originalSalary + 10.0);
        });
    }

    @Test
    void testEmployeeIdToEmployeeStream() {
        Integer[] empIds = {1, 2, 3};
        List<Employee> employeesFromRepo = Stream.of(empIds).map(EMP_REPO::findById).toList();
        assertEquals(empIds.length, employeesFromRepo.size());
    }

    @Test
    void testCollectStreamThenList() {
        List<Employee> employees = EMP_LIST.stream().toList();
        assertEquals(EMP_LIST.size(), employees.size());
    }
}
