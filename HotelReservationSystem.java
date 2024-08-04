package Hotel_reservation_system;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HotelReservationSystem {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Search Available Rooms");
            System.out.println("2. Make Reservation");
            System.out.println("3. View Reservation Details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    searchRooms(hotel, scanner);
                    break;
                case 2:
                    makeReservation(hotel, scanner);
                    break;
                case 3:
                    viewReservation(hotel, scanner);
                    break;
                case 4:
                    System.out.println("Thank you for using our Hotel Reservation System. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void searchRooms(Hotel hotel, Scanner scanner) {
        System.out.print("Enter check-in date (YYYY-MM-DD): ");
        LocalDate checkIn = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter check-out date (YYYY-MM-DD): ");
        LocalDate checkOut = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter room category (Standard/Deluxe/Suite): ");
        String category = scanner.nextLine();

        List<Room> availableRooms = hotel.searchAvailableRooms(checkIn, checkOut, category);
        if (availableRooms.isEmpty()) {
            System.out.println("No available rooms found for the given criteria.");
        } else {
            System.out.println("Available Rooms:");
            for (Room room : availableRooms) {
                System.out.println("Room " + room.roomNumber + " - " + room.category + " - $" + room.pricePerNight + " per night");
            }
        }
    }

    private static void makeReservation(Hotel hotel, Scanner scanner) {
        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter guest name: ");
        String guestName = scanner.nextLine();
        System.out.print("Enter check-in date (YYYY-MM-DD): ");
        LocalDate checkIn = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter check-out date (YYYY-MM-DD): ");
        LocalDate checkOut = LocalDate.parse(scanner.nextLine());

        Reservation reservation = hotel.makeReservation(roomNumber, guestName, checkIn, checkOut);
        if (reservation == null) {
            System.out.println("Unable to make reservation. Room might be unavailable for the selected dates.");
        } else {
            System.out.println("Reservation made successfully.");
            hotel.displayReservationDetails(reservation);
            
            System.out.print("Enter payment method (Credit Card/Cash): ");
            String paymentMethod = scanner.nextLine();
            hotel.processPayment(reservation, paymentMethod);
        }
    }

    private static void viewReservation(Hotel hotel, Scanner scanner) {
        System.out.print("Enter reservation ID: ");
        int reservationId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Reservation reservation = hotel.reservations.stream()
                                      .filter(r -> r.reservationId == reservationId)
                                      .findFirst()
                                      .orElse(null);

        if (reservation == null) {
            System.out.println("Reservation not found.");
        } else {
            hotel.displayReservationDetails(reservation);
        }
    }
}
