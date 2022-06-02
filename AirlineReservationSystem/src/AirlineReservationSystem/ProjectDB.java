package AirlineReservationSystem;

import java.util.ArrayList;

public class ProjectDB {

    public static ArrayList<Person> person_list = new ArrayList<>();
    public static ArrayList<Passenger> passenger_list = new ArrayList<>();
    public static ArrayList<FlightDescription> flight_desc_list = new ArrayList<>();
    public static ArrayList<ScheduledFlight> scheduled_flight_list = new ArrayList<>();


    public static void add(Person person) {
        for (Person p : person_list) {
            if (p.name.equals(person.name)) {
                System.out.println("Can't save this data!");
                System.out.println(person.name + " : Already saved!");
                return;
            }
        }
        person_list.add(person);
    }

    public static void add(Passenger passenger) {
        for (Passenger p : passenger_list) {
            if (p.flight_number == passenger.flight_number && p.name.equals(passenger.name)) {
                System.out.println("Can't save this data!");
                System.out.println(passenger.name + " : Already reserved this flight!");
                return;
            }
        }
        passenger_list.add(passenger);
    }

    public static void add(FlightDescription flight_desc) {
        for (FlightDescription flight : flight_desc_list) {
            if (flight.arrival_time.equals(flight_desc.arrival_time) &&
                    flight.departure_time.equals(flight_desc.departure_time) &&
                    flight.from.equals(flight_desc.from) &&
                    flight.to.equals(flight_desc.to) &&
                    flight.capacity == flight_desc.capacity) {
                System.out.println("Can't save this data!");
                System.out.println("This Flight description Already exists!");
                return;
            }
        }
        flight_desc_list.add(flight_desc);
    }

    public static void add(ScheduledFlight sc_flight) {
        for (ScheduledFlight flight : scheduled_flight_list) {
            if (flight.arrival_time.equals(sc_flight.arrival_time) &&
                    flight.departure_time.equals(sc_flight.departure_time) &&
                    flight.from.equals(sc_flight.from) &&
                    flight.to.equals(sc_flight.to) &&
                    flight.capacity == sc_flight.capacity &&
                    flight.date.equals(sc_flight.date)) {
                System.out.println("Can't save this data!");
                System.out.println("This Flight Already scheduled!");
                return;
            }
        }
        scheduled_flight_list.add(sc_flight);
    }
}
