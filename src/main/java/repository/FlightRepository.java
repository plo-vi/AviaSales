package repository;

import domain.Flight;
import domain.NotFoundException;

public class FlightRepository {
    private Flight[] flights = new Flight[0];

    public void save(Flight flight) {
        int length = flights.length + 1;
        Flight[] tmp = new Flight[length];
        System.arraycopy(flights, 0, tmp, 0, flights.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = flight;
        flights = tmp;
    }

    public Flight[] findAll() {
        return flights;
    }

    public Flight[] findById(int id) {
        Flight[] tmp = new Flight[1];
        for (Flight flight : flights) {
            if (flight.getId() == id) {
                tmp[0] = flight;
            }
        }
        if (tmp[0] == null) {
            throw new NotFoundException("Flight with id: " + id + " not found");
        } else {
            return tmp;
        }
    }

    public void removeById(int id) {
        findById(id);
        int length = flights.length - 1;
        Flight[] tmp = new Flight[length];
        int index = 0;
        for (Flight flight : flights) {
            if (flight.getId() != id) {
                tmp[index] = flight;
                index++;
            }
        }
        flights = tmp;
    }
}
