package io.huangsam.trial.libs;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Demonstrates advanced Mockito techniques: Spies, ArgumentCaptors, and Matchers.
 */
public class TestAdvancedMocking {

    /**
     * Interface for a simple repository to mock.
     */
    interface DataRepository {
        void saveData(String data);
        String findData(String id);
    }

    /**
     * A simple service using the repository.
     */
    static class DataService {
        private final DataRepository repository;

        DataService(DataRepository repository) {
            this.repository = repository;
        }

        void processAndSave(String input) {
            String processed = input.toUpperCase();
            repository.saveData(processed);
        }

        String getConcatenatedData(String id1, String id2) {
            return repository.findData(id1) + " " + repository.findData(id2);
        }
    }

    @Test
    public void testSpyingOnRealObject() {
        // Spies allow calling real methods while still being able to verify or stub.
        List<String> realList = new ArrayList<>();
        List<String> spyList = spy(realList);

        spyList.add("one");
        spyList.add("two");

        verify(spyList).add("one");
        verify(spyList).add("two");
        assertEquals(2, spyList.size());

        // We can overwrite real behavior
        doReturn(100).when(spyList).size();
        assertEquals(100, spyList.size());
    }

    @Test
    public void testArgumentCaptor() {
        DataRepository repository = mock(DataRepository.class);
        DataService service = new DataService(repository);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        service.processAndSave("hello");

        // Capture what was passed to saveData
        verify(repository).saveData(captor.capture());
        assertEquals("HELLO", captor.getValue());
    }

    @Test
    public void testCustomArgumentMatcher() {
        DataRepository repository = mock(DataRepository.class);
        DataService service = new DataService(repository);

        service.processAndSave("test-data");

        // Verify using a custom matcher (argThat)
        verify(repository).saveData(argThat(str -> str != null && str.startsWith("TEST-")));
        verify(repository, atLeastOnce()).saveData(anyString());
    }
}
