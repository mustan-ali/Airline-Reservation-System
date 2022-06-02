package AirlineReservationSystem;

public class Person {
    public String name;
    public String address;

    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public static void show_all() {
        int counter = 0;
        for (int i = 0; i < 93; i++)
            System.out.print("-");
        System.out.println();
        System.out.printf("%5s | %-30s | %-50s |\n", "Index", "Full Name", "Address");
        for (int i = 0; i < 93; i++)
            if (i == 6 || i == 39 || i == 92)
                System.out.print("|");
            else
                System.out.print("-");
        System.out.println();

        if (ProjectDB.person_list.isEmpty()) {
            System.out.println("\t==> No Customers added yet <==");
        }
        for (Person p : ProjectDB.person_list) {
            System.out.printf("%5d | %-30s | %-50s |\n", ++counter, p.name, p.address);
        }
        for (int i = 0; i < 93; i++)
            System.out.print("-");
        System.out.println();
    }

}
