package domain;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Flight implements Comparable {
    private int id;
    private int price;
    private String departure;
    private String arrival;
    private int time;

    @Override
    public int compareTo(Object o) {
        Flight f = (Flight) o;
        return this.price - f.price;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", price=" + price +
                ", departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                ", time=" + time +
                '}';
    }

    public boolean matches(String searchDeparture, String searchArrival) {
        if (this.getDeparture().equalsIgnoreCase(searchDeparture) &&
                this.getArrival().equalsIgnoreCase(searchArrival)) {
            return true;
        } else return false;
    }
}
