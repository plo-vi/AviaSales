package manager;

import domain.Flight;
import repository.FlightRepository;

import java.util.Arrays;

public class FlightManager {

    private FlightRepository repository;
    private String search;

    public FlightManager(FlightRepository repository) {
        this.repository = repository;
    }

    public void add(Flight flight) {
        repository.save(flight);
    }

    public void removeById(Flight flight) {
        repository.save(flight);
    }

    public Flight[] searchById(int id) {
        return repository.findById(id);
    }

    public Flight[] getAll() {
        return repository.findAll();
    }

    public Flight[] search(String departure, String arrival) {
        Flight[] result = new Flight[0];
        for (Flight flight: repository.findAll()) {
            if (flight.matches(departure, arrival)) {
                Flight[] tmp = new Flight[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = flight;
                result = tmp;
            }
        }
        Arrays.sort(result);
        return result;
    }
}
