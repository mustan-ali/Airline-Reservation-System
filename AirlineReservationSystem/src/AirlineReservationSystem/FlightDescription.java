package AirlineReservationSystem;

public class FlightDescription {
    public String from;
    public String to;
    public String departure_time;
    public String arrival_time;
    public int capacity;

    public FlightDescription(String from, String to, String departureTime, String arrivalTime, int capacity) {
        this.from = from;
        this.to = to;
        this.departure_time = departureTime;
        this.arrival_time = arrivalTime;
        this.capacity = capacity;
    }


    public static void show_all() {
        int counter = 0;
        for (int i = 0; i < 90; i++)
            System.out.print("-");
        System.out.println();
        System.out.printf("%5s | %-20s | %-20s | %-10s | %-10s | %-8s |\n", "Index", "FROM", "To", "Dep Time", "Arr Time", "Capacity");
        for (int i = 0; i < 90; i++)
            if (i == 6 || i == 29 || i == 52 || i == 65 || i == 78 || i == 89)
                System.out.print("|");
            else
                System.out.print("-");
        System.out.println();

        if (ProjectDB.flight_desc_list.isEmpty()) {
            System.out.println("\t==> No Flight descriptions added yet <==");
        }
        for (FlightDescription fd : ProjectDB.flight_desc_list) {
            System.out.printf("%5d | %-20s | %-20s | %-10s | %-10s | %8d |\n",
                    ++counter, fd.from, fd.to, fd.departure_time, fd.arrival_time, fd.capacity);
        }
        for (int i = 0; i < 90; i++)
            System.out.print("-");
        System.out.println();
    }
}
