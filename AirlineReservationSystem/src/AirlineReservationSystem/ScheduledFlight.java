package AirlineReservationSystem;

public class ScheduledFlight extends FlightDescription {
    public String date;
    public int flight_number;

    public ScheduledFlight(FlightDescription f_desc, String date) {
        super(f_desc.from, f_desc.to, f_desc.departure_time, f_desc.arrival_time, f_desc.capacity);
        this.date = date;
        this.flight_number = generate_flight_num();
    }

    private static int generate_flight_num() {
        int max = 0;
        for (ScheduledFlight scf : ProjectDB.scheduled_flight_list) {
            if (max < scf.flight_number)
                max = scf.flight_number;
        }
        return max + 1;
    }

    public static void show_all() {
        int counter = 0;
        for (int i = 0; i < 113; i++)
            System.out.print("-");
        System.out.println();
        System.out.printf("%5s | %-5s | %-10s | %-20s | %-20s | %-10s | %-10s | %-10s |\n", "Index", "FN", "Date", "FROM", "To", "Dep Time", "Arr Time", "Passengers");
        for (int i = 0; i < 113; i++)
            if (i == 6 || i == 14 || i == 27 || i == 50 || i == 73 || i == 86 || i == 99 || i == 112)
                System.out.print("|");
            else
                System.out.print("-");
        System.out.println();

        if (ProjectDB.scheduled_flight_list.isEmpty()) {
            System.out.println("\t==> No Scheduled flights added yet <==");
        }

        for (ScheduledFlight scf : ProjectDB.scheduled_flight_list) {
            int pNumber = Passenger.getSCFlightPassengersCount(scf.flight_number);
            String pCount = (pNumber == scf.capacity) ? "Full(" + pNumber + ")" : Integer.toString(pNumber);
            System.out.printf("%5d | %5d | %-10s | %-20s | %-20s | %-10s | %-10s | %10s |\n",
                    ++counter, scf.flight_number, scf.date, scf.from, scf.to, scf.departure_time, scf.arrival_time, pCount);
        }
        for (int i = 0; i < 113; i++)
            System.out.print("-");
        System.out.println();
    }

}
