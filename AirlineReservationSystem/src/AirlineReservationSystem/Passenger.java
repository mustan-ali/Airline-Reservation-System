package AirlineReservationSystem;

import java.util.ArrayList;

public class Passenger extends Person {
    public int flight_number;

    public Passenger(Person person, int flight_number) {
        super(person.name, person.address);
        this.flight_number = flight_number;
    }

    public static int getSCFlightPassengersCount(int flight_num) {
        int counter = 0;
        for (Passenger pa : ProjectDB.passenger_list) {
            if (pa.flight_number == flight_num)
                counter++;
        }
        return counter;

    }

    public static void show_all() {
        int counter = 0;
        for (int i = 0; i < 48; i++)
            System.out.print("-");
        System.out.println();
        System.out.printf("%5s | %-5s | %-30s |\n", "Index", "FN", "Full Name");
        for (int i = 0; i < 48; i++)
            if (i == 6 || i == 14 || i == 47)
                System.out.print("|");
            else
                System.out.print("-");
        System.out.println();

        if (ProjectDB.passenger_list.isEmpty()) {
            System.out.println("\t==> No Reservations added yet <==");
        }

        for (Passenger p : ProjectDB.passenger_list) {
            System.out.printf("%5d | %5d | %-30s |\n", ++counter, p.flight_number, p.name);
        }
        for (int i = 0; i < 48; i++)
            System.out.print("-");
        System.out.println();
    }

    public static void show_only_flight_no(int flight_num) {
        ArrayList<Passenger> output = new ArrayList<>();
        for (Passenger pa : ProjectDB.passenger_list) {
            if (pa.flight_number == flight_num)
                output.add(pa);
        }

        int counter = 0;
        for (int i = 0; i < 40; i++)
            System.out.print("-");
        System.out.println();

        System.out.printf("%5s | %-30s |\n", "Index", "Full Name");
        for (int i = 0; i < 40; i++)
            if (i == 6 || i == 39)
                System.out.print("|");
            else
                System.out.print("-");
        System.out.println();

        if (output.isEmpty()) {
            System.out.println("\t=> No Reservations added yet <=");
        }

        for (Passenger p : output) {
            System.out.printf("%5d | %-30s |\n", ++counter, p.name);
        }
        for (int i = 0; i < 40; i++)
            System.out.print("-");
        System.out.println();
    }

}
