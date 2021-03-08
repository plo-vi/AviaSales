package manager;

import domain.Flight;
import domain.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.FlightRepository;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FlightManagerTest {
    private FlightRepository repository = new FlightRepository();
    private FlightManager manager = new FlightManager(repository);
    private Flight first = new Flight(1, 1000, "DME", "LED", 90);
    private Flight second = new Flight(2, 2000, "DME", "LED", 95);
    private Flight third = new Flight(3, 3000, "DME", "LED", 100);
    private Flight fourth = new Flight(4, 4000, "VKO", "LED", 110);
    private Flight fifth = new Flight(5, 5000, "LED", "DME", 200);
    private Flight sixth = new Flight(6, 6000, "DME", "VOZ", 60);

    @BeforeEach
    public void setUp() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fourth);
        repository.save(fifth);
        repository.save(sixth);
    }

    @Test
    public void shouldSearchAndSort() {
        Flight[] expected = new Flight[]{first, second, third};
        Flight[] actual = manager.search("DME", "LED");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchAndSortInEqualsPrice() {
        Flight[] expected = new Flight[]{fifth};
        Flight[] actual = manager.search("lEd", "dmE");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchNotInList() {
        Flight[] expected = new Flight[0];
        Flight[] actual = manager.search("Dme", "Kzn");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRemoveByIdInList() {
        repository.removeById(2);
        Flight[] expected = new Flight[]{first, third, fourth, fifth, sixth};
        Flight[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldThrowException() {
        assertThrows(NotFoundException.class, () -> repository.removeById(18));
    }

    @Test
    void shouldSearchById() {
        Flight[] expected = new Flight[]{second};
        Flight[] actual = manager.searchById(2);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldAddNewFlight() {
        Flight seventh = new Flight(7, 7000, "VOG", "KZN", 92);
        repository.save(seventh);
        Flight[] expected = new Flight[]{first, second, third, fourth, fifth, sixth, seventh};
        Flight[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }
}