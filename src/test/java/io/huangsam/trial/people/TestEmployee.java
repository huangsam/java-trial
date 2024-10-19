package io.huangsam.trial.people;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Inspired by content regarding Java Streams, which have been available
 * since Java 8. We are trying the various Stream methods like
 * {@code map} and {@code filter}.
 * <br>
 * Note that terminal operations is how streams do not get ignored. These
 * are methods like {@code forEach}, {@code collect} and {@code reduce}.
 *
 * @see <a href="https://stackify.com/streams-guide-java-8/">Stackify post</a>
 */
public class TestEmployee {
    private static final Employee[] TEST_ARRAY = {
            new Employee(1, "Jeff Bezos", 100000.0),
            new Employee(2, "Bill Gates", 200000.0),
            new Employee(3, "Mark Zuckerberg", 300000.0),
            new Employee(4, "Tim Cook", 400000.0)};

    // https://stackoverflow.com/a/16748184
    private static final List<Employee> TEST_LIST = Arrays.asList(TEST_ARRAY);

    private static final EmployeeRepository TEST_REPO = new EmployeeRepository();

    @BeforeAll
    static void setup() {
        // Ensure findByID returns a valid result
        TEST_LIST.forEach(TEST_REPO::add);
    }

    @Test
    void testEmployeeGetter() {
        Employee employee = new Employee(1, "John Doe", 100000);
        assertEquals(1, employee.getId());
        assertEquals("John Doe", employee.getName());
        assertEquals(100000, employee.getSalary());
    }

    @Test
    void testEmployeeIdToEmployeeStream() {
        Integer[] empIds = {1, 2, 3};
        List<Employee> employeesFromRepo = Stream.of(empIds).map(TEST_REPO::findById).toList();
        assertEquals(empIds.length, employeesFromRepo.size());
    }

    @Test
    void testCollectStreamThenList() {
        List<Employee> employees = TEST_LIST.stream().toList();
        assertEquals(TEST_LIST.size(), employees.size());
    }

    @Test
    void testFilterEmployeesFromStream() {
        Integer[] employeeIds = {1, 2, 3};
        List<Employee> employees = Stream.of(employeeIds)
                .map(TEST_REPO::findById)
                .filter(Objects::nonNull)
                .filter(emp -> emp.getSalary() > 100000.0)
                .toList();

        assertEquals(2, employees.size());

        Employee first = employees.get(0);
        Employee second = employees.get(1);

        assertEquals("Bill Gates", first.getName());
        assertEquals("Mark Zuckerberg", second.getName());
    }

    @Test
    void testFindFirstEmployeeFromStream() {
        Integer[] employeeIds = {1, 2, 3, 4};
        Employee employee = Stream.of(employeeIds)
                .map(TEST_REPO::findById)
                .filter(Objects::nonNull)
                .filter(emp -> emp.getSalary() > 200000.0)
                .findFirst()
                .orElse(null);
        assertNotNull(employee);
        assertEquals("Mark Zuckerberg", employee.getName());
    }

    @Test
    void testIncreaseSalaryViaPeekThenCount() {
        long count = TEST_LIST.stream()
                .peek(emp -> {
                    emp.increaseSalary(20.0);
                    assertEquals(20, emp.getSalary() % 100);
                    emp.increaseSalary(-20.0);
                    assertEquals(0, emp.getSalary() % 100);
                })
                .filter(emp -> emp.getSalary() > 100000.0)
                .count();
        assertEquals(3L, count);
    }

    @Test
    void testFlatMapEmployeeNames() {
        List<List<String>> namesNested = Arrays.asList(
                Arrays.asList("Jeff", "Bezos"),
                Arrays.asList("Bill", "Gates"),
                Arrays.asList("Mark", "Zuckerberg"));

        List<String> namesFlattened = namesNested.stream()
                .flatMap(Collection::stream)
                .toList();

        assertEquals(namesFlattened.size(), namesNested.size() * 2);

        assertInstanceOf(List.class, namesNested.get(0));

        assertInstanceOf(String.class, namesFlattened.get(0));
    }

    @Test
    void testMinAndMaxSalary() {
        Employee cheap = TEST_LIST.stream()
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(NoSuchElementException::new);
        Employee pricey = TEST_LIST.stream()
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(NoSuchElementException::new);
        assertEquals(100000.0, cheap.getSalary());
        assertEquals(400000.0, pricey.getSalary());
    }

    @Test
    void testDistinctValuesFromIntegers() {
        List<Integer> distinct = Stream.of(2, 5, 3, 2, 4, 3)
                .distinct()
                .toList();
        assertEquals(distinct, Arrays.asList(2, 5, 3, 4));
    }

    @Test
    void testBooleanMatchesOnEmployees() {
        assertTrue(TEST_LIST.stream()
                .allMatch(emp -> emp.getSalary() > 0.0));
        assertFalse(TEST_LIST.stream()
                .anyMatch(emp -> emp.getId() > 10));
        assertTrue(TEST_LIST.stream()
                .noneMatch(emp -> emp.getName().equals("Foo Bar")));
    }
}
