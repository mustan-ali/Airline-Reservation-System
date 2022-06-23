package AirlineReservationSystem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static ConsoleColors cc = new ConsoleColors();

    public static void main(String[] args) {

        Person person1 = new Person("Ali", "123 Street");
        ProjectDB.add(person1);

        Person person2 = new Person("Jeff", "123 Street");
        ProjectDB.add(person2);

        Passenger passenger1 = new Passenger(person1, 1);
        ProjectDB.add(passenger1);

        FlightDescription flightDescription1 = new FlightDescription("Karachi", "Lahore", "01:00", "02:45", 10);
        ProjectDB.add(flightDescription1);

        ScheduledFlight scheduledFlight1 = new ScheduledFlight(flightDescription1, "25/06/2022");
        ProjectDB.add(scheduledFlight1);


        print_header();
        main_menu();
    }

    //To exit the program
    private static void exitMessage(){
        System.out.println("Thank you for using airline reservation system");
    }

    private static void print_header() {
        System.out.println(cc.GREY_BACKGROUND + "<><><><><><><><><><><><><><><><><><><><>" + cc.RESET);
        System.out.println(cc.RED_BACKGROUND_BRIGHT + cc.BLACK_BOLD + "       Airline Reservation System       " + cc.RESET);
        System.out.println(cc.GREY_BACKGROUND + "<><><><><><><><><><><><><><><><><><><><>" + cc.RESET);
        System.out.print("\n");
    }


    private static void main_menu() {
        System.out.println(cc.RED_BACKGROUND + cc.BLACK_BOLD + "---------->  Main Menu  <-----------" + cc.RESET);
        System.out.println(cc.GREY_BACKGROUND + cc.BLACK_BOLD + "1- Passengers Menu                  " + cc.RESET);
        System.out.println(cc.GREY_BACKGROUND + cc.BLACK_BOLD + "2- Flight Management Menu           " + cc.RESET);
        System.out.println(cc.GREY_BACKGROUND + cc.RED_BOLD + "3- Exit System                      " + cc.RESET);
        System.out.println(cc.RED_BACKGROUND + cc.BLACK_BOLD + "------------------------------------" + cc.RESET);
        short choice=4;
        Scanner input = new Scanner(System.in);
        do {
            System.out.print("Choice: ");
            //choice = input.nextShort();
            try{
                choice = input.nextShort();
                input.nextLine();
            }catch (InputMismatchException e){
                System.out.println();
            }
            switch (choice) {
                case 1:
                    System.out.println();
                    passengers_menu();
                    break;
                case 2:
                    System.out.println();
                    flights_menu();
                    break;
                case 3:
                    exitMessage();
                    break;
                case 4:
                    main_menu();
                    break;
                default:
                    System.out.println("ERROR: Choice not valid!");
            }
        } while (choice < 1 || choice > 4);
    }

    private static void passengers_menu() {
        System.out.println(cc.RED_BACKGROUND + cc.BLACK_BOLD + "------->  Passengers Menu  <--------" + cc.RESET);
        System.out.println(cc.GREY_BACKGROUND + cc.BLACK_BOLD + "1- Add Customer                     " + cc.RESET);
        System.out.println(cc.GREY_BACKGROUND + cc.BLACK_BOLD + "2- View All Customers               " + cc.RESET);
        System.out.println(cc.GREY_BACKGROUND + cc.BLACK_BOLD + "3- Remove Customer                  " + cc.RESET);
        System.out.println(cc.GREY_BACKGROUND + cc.BLACK_BOLD + "4- New Reservation                  " + cc.RESET);
        System.out.println(cc.GREY_BACKGROUND + cc.BLACK_BOLD + "5- view All Reservations            " + cc.RESET);
        System.out.println(cc.GREY_BACKGROUND + cc.BLACK_BOLD + "6- Cancel Reservation               " + cc.RESET);
        System.out.println(cc.GREY_BACKGROUND + cc.RED_BOLD + "7- Main Menu                        " + cc.RESET);
        System.out.println(cc.RED_BACKGROUND + cc.BLACK_BOLD + "------------------------------------" + cc.RESET);
        short choice=8;
        int index;
        Scanner input = new Scanner(System.in);
        do {
            System.out.print("Choice: ");
            try{
                choice = input.nextShort();
                input.nextLine();
            }catch (InputMismatchException e){
                System.out.println("Invalid Choice");
            }

            switch (choice) {
                case 1:
                    System.out.println("---->  NEW CUSTOMERS  <----");
                    input = new Scanner(System.in); // refresh scanner to avoid errors
                    System.out.print("Full Name: ");
                    String name = input.nextLine();
                    System.out.print("Address: ");
                    String address = input.nextLine();
                {
                    try {
                        ProjectDB.add(new Person(name, address));
                    } catch (Exception ex) {
                        System.out.println("ERROR: File not Found!");
                    }
                }
                System.out.println("Added successfully : " + name + "\n");
                passengers_menu();
                break;

                case 2:
                    System.out.println("=> CUSTOMERS TABLE  <----");
                    Person.show_all();
                    passengers_menu();
                    break;

                case 3:
                    System.out.println("---->  CUSTOMERS TABLE  <----");
                    Person.show_all();
                    if (ProjectDB.person_list.size() == 0) {
                        passengers_menu();
                    }
                    else {
                        do {
                            System.out.print("Customer Index to remove : ");
                            index = input.nextInt();
                        } while (index < 1 || index > ProjectDB.person_list.size());
                        ProjectDB.person_list.remove(ProjectDB.person_list.get(index - 1));
                        System.out.println("Removed Successfully!\n");
                        passengers_menu();
                    }
                    break;
                case 4:
                    System.out.println("---->  NEW RESERVATION   <----");
                    //Choose person
                    Person.show_all();
                    if (ProjectDB.person_list.size() == 0) {
                        passengers_menu();
                    }
                    else {
                        do {
                            System.out.print("Customer Index : ");
                            index = input.nextInt();
                        } while (index < 1 || index > ProjectDB.person_list.size());
                        Person p = ProjectDB.person_list.get(index - 1);
                        //Choose flight
                        ScheduledFlight scf;

                        ScheduledFlight.show_all();
                        if (ProjectDB.scheduled_flight_list.size() == 0) {
                            passengers_menu();
                        }
                        else {
                            do {
                                System.out.print("Flight Index : ");
                                index = input.nextInt();
                            } while (index < 1 || index > ProjectDB.scheduled_flight_list.size());
                            scf = ProjectDB.scheduled_flight_list.get(index - 1);
                            if (scf.capacity == Passenger.getSCFlightPassengersCount(scf.flight_number) || ProjectDB.passenger_list.size() == 0) {
                                System.out.println("This flight is at maximum capacity.");
                            }
                            else {
                                int prevLen = ProjectDB.passenger_list.size();
                                {
                                    try {
                                        ProjectDB.add(new Passenger(p, scf.flight_number));
                                    } catch (Exception ex) {
                                        System.out.println("ERROR : FILE NOT FOUND !");
                                    }
                                }
                                int afterLen = ProjectDB.passenger_list.size();
                                if (prevLen != afterLen) {
                                    System.out.println("Reservation completed : " + p.name + " (" + scf.from + " -> " + scf.to + ")\n");
                                }
                            }
                            passengers_menu();
                        }
                    }
                    //passengers_menu();
                    break;
                case 5:
                    System.out.println("---->  RESERVATIONS TABLE  <----");
                    Passenger.show_all();
                    passengers_menu();
                    break;
                case 6:
                    System.out.println("---->  RESERVATIONS TABLE  <----");
                    Passenger.show_all();
                    if (ProjectDB.passenger_list.size() == 0) {
                        passengers_menu();
                    }
                    else {
                        do {
                            System.out.print("Passenger Index to Cancel trip for : ");
                            index = input.nextInt();
                        } while (index < 1 || index > ProjectDB.passenger_list.size());
                        ProjectDB.passenger_list.remove(ProjectDB.passenger_list.get(index - 1));
                        System.out.println("Reservation Canceled Successfully!\n");
                        passengers_menu();
                    }
                    break;

                case 7:
                    System.out.println();
                    main_menu();
                    break;
                case 8:
                    passengers_menu();
                    break;
                default:
                    System.out.println("ERROR: Choice not valid");
            }
        } while (choice < 1 || choice > 8);

    }

    private static void flights_menu() {
        System.out.println(cc.RED_BACKGROUND + cc.BLACK_BOLD + "---->  Flight Management Menu  <----" + cc.RESET);
        System.out.println(cc.GREY_BACKGROUND + cc.BLACK_BOLD + "1- Add New Flight Description       " + cc.RESET);
        System.out.println(cc.GREY_BACKGROUND + cc.BLACK_BOLD + "2- View All Flight Description      " + cc.RESET);
        System.out.println(cc.GREY_BACKGROUND + cc.BLACK_BOLD + "3- Remove Flight Description        " + cc.RESET);
        System.out.println(cc.GREY_BACKGROUND + cc.BLACK_BOLD + "4- Schedule New Flight              " + cc.RESET);
        System.out.println(cc.GREY_BACKGROUND + cc.BLACK_BOLD + "5- view All Scheduled Flights       " + cc.RESET);
        System.out.println(cc.GREY_BACKGROUND + cc.BLACK_BOLD + "6- Cancel Scheduled Flight          " + cc.RESET);
        System.out.println(cc.GREY_BACKGROUND + cc.BLACK_BOLD + "7- View Scheduled Flight Passengers " + cc.RESET);
        System.out.println(cc.GREY_BACKGROUND + cc.RED_BOLD + "8- Main Menu                        " + cc.RESET);
        System.out.println(cc.RED_BACKGROUND + cc.BLACK_BOLD + "------------------------------------" + cc.RESET);
        short choice=9;
        int index;
        Scanner input = new Scanner(System.in);
        do {
            System.out.print("Choice: ");
            //choice = input.nextShort();
            try{
                choice = input.nextShort();
                input.nextLine();
            }catch (InputMismatchException e){
                System.out.println("Invalid Choice");
            }

            switch (choice) {
                case 1:
                    System.out.println("---->  NEW FLIGHT DESCRIPTION  <----");
                    input = new Scanner(System.in); // refresh scanner to avoid errors
                    System.out.print("From : ");
                    String from = input.nextLine();
                    System.out.print("To   : ");
                    String to = input.nextLine();
                    String depTime, arrTime;

                    System.out.print("Departure time (HH:MM): ");
                    depTime = input.nextLine();

                    System.out.print("Arrival   time (HH:MM): ");
                    arrTime = input.nextLine();


                    System.out.print("Capacity : ");
                    input = new Scanner(System.in);
                    int cap = input.nextInt();
                    int prevSize = ProjectDB.flight_desc_list.size();
                {
                    try {
                        ProjectDB.add(new FlightDescription(from, to, depTime, arrTime, cap));
                    } catch (Exception ex) {
                        System.out.println("ERROR: File not Found!");
                    }
                }
                int afterSize = ProjectDB.flight_desc_list.size();
                if (prevSize != afterSize) {
                    System.out.println("Flight Description added successfully : " + from + " -> " + to + "\n");
                }
                flights_menu();
                break;

                case 2:
                    System.out.println("---->  FLIGHT DESCRIPTION TABLE  <----");
                    FlightDescription.show_all();
                    flights_menu();
                    break;
                case 3:
                    System.out.println("---->  FLIGHT DESCRIPTION TABLE  <----");
                    FlightDescription.show_all();
                    if (ProjectDB.flight_desc_list.size() == 0) {
                        flights_menu();
                    }
                    else {
                        do {
                            System.out.print("Flight description index to remove : ");
                            index = input.nextInt();
                        } while (index < 1 || index > ProjectDB.flight_desc_list.size());
                        ProjectDB.flight_desc_list.remove(ProjectDB.flight_desc_list.get(index - 1));

                        System.out.println("Flight description removed Successfully!\n");
                        flights_menu();
                    }
                    break;
                case 4:
                    System.out.println("---->  FLIGHT DESCRIPTION TABLE  <----");
                    FlightDescription.show_all();
                    if (ProjectDB.flight_desc_list.size() == 0) {
                        flights_menu();
                    }
                    else {
                        do {
                            System.out.print("Flight description index to schedule : ");
                            index = input.nextInt();
                        } while (index < 1 || index > ProjectDB.flight_desc_list.size());
                        FlightDescription fd = ProjectDB.flight_desc_list.get(index - 1);
                        input = new Scanner(System.in); // refresh scanner to avoid errors
                        String date;

                        System.out.print("Date (YYYY/MM/DD) : ");
                        date = input.nextLine();

                        int prevLen = ProjectDB.scheduled_flight_list.size();
                        {
                            try {
                                ProjectDB.add(new ScheduledFlight(fd, date));
                            } catch (Exception ex) {
                                System.out.println("ERROR : FILE NOT FOUND !");
                            }
                        }
                        int afterLen = ProjectDB.scheduled_flight_list.size();
                        if (prevLen != afterLen) {
                            System.out.println("Scheduled " + date + " for flight : " + fd.from + " -> " + fd.to + "\n");
                        }
                        flights_menu();
                    }
                    //flights_menu();
                    break;
                case 5:
                    System.out.println("---->  SCHEDULED FLIGHTS TABLE  <----");
                    ScheduledFlight.show_all();
                    flights_menu();
                    break;

                case 6:
                    System.out.println("---->  SCHEDULED FLIGHT TABLE  <----");
                    ScheduledFlight.show_all();
                    if (ProjectDB.scheduled_flight_list.size() == 0) {
                        flights_menu();
                    }
                    else {
                        do {
                            System.out.print("Scheduled Flight index to canceled : ");
                            index = input.nextInt();
                        } while (index < 1 || index > ProjectDB.scheduled_flight_list.size());
                        ProjectDB.scheduled_flight_list.remove(ProjectDB.scheduled_flight_list.get(index - 1));
                        System.out.println("Scheduled Flight & Reservations canceled Successfully!\n");
                        flights_menu();
                    }
                    break;

                case 7:
                    System.out.println("---->  SCHEDULED FLIGHT TABLE  <----");
                    ScheduledFlight.show_all();
                    do {
                        System.out.print("Flight Index : ");
                        index = input.nextInt();
                    } while (index < 1 || index > ProjectDB.scheduled_flight_list.size());
                    int flight_num = ProjectDB.scheduled_flight_list.get(index - 1).flight_number;
                    Passenger.show_only_flight_no(flight_num);
                    flights_menu();
                    break;
                case 8:
                    System.out.println();
                    main_menu();
                    break;
                case 9:
                    flights_menu();
                    break;
                default:
                    System.out.println("ERROR: Choice not valid");
            }
        } while (choice < 1 || choice > 9);
    }
}