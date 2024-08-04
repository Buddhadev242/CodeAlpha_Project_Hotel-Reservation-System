package Hotel_reservation_system;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
class Hotel {
    List<Room> rooms;
    List<Reservation> reservations;
    int nextReservationId;

    public Hotel() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
        nextReservationId = 1;

        // Initialize some rooms
        rooms.add(new Room(101, "Standard", 100));
        rooms.add(new Room(102, "Standard", 100));
        rooms.add(new Room(201, "Deluxe", 150));
        rooms.add(new Room(202, "Deluxe", 150));
        rooms.add(new Room(301, "Suite", 250));
    }

    public List<Room> searchAvailableRooms(LocalDate checkIn, LocalDate checkOut, String category) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.category.equalsIgnoreCase(category) && isRoomAvailable(room, checkIn, checkOut)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    private boolean isRoomAvailable(Room room, LocalDate checkIn, LocalDate checkOut) {
        for (Reservation reservation : reservations) {
            if (reservation.roomNumber == room.roomNumber) {
                if ((checkIn.isBefore(reservation.checkOut) || checkIn.isEqual(reservation.checkOut)) &&
                    (checkOut.isAfter(reservation.checkIn) || checkOut.isEqual(reservation.checkIn))) {
                    return false;
                }
            }
        }
        return true;
    }

    public Reservation makeReservation(int roomNumber, String guestName, LocalDate checkIn, LocalDate checkOut) {
        Room room = rooms.stream().filter(r -> r.roomNumber == roomNumber).findFirst().orElse(null);
        if (room == null || !isRoomAvailable(room, checkIn, checkOut)) {
            return null;
        }

        long nights = ChronoUnit.DAYS.between(checkIn, checkOut);
        double totalPrice = room.pricePerNight * nights;

        Reservation reservation = new Reservation(nextReservationId++, roomNumber, guestName, checkIn, checkOut, totalPrice);
        reservations.add(reservation);
        return reservation;
    }

    public boolean processPayment(Reservation reservation, String paymentMethod) {
        // Simulating payment processing
        System.out.println("Processing payment of $" + reservation.totalPrice + " using " + paymentMethod);
        System.out.println("Payment successful");
        return true;
    }

    public void displayReservationDetails(Reservation reservation) {
        System.out.println("Reservation Details:");
        System.out.println("Reservation ID: " + reservation.reservationId);
        System.out.println("Guest Name: " + reservation.guestName);
        System.out.println("Room Number: " + reservation.roomNumber);
        System.out.println("Check-in Date: " + reservation.checkIn);
        System.out.println("Check-out Date: " + reservation.checkOut);
        System.out.println("Total Price: $" + reservation.totalPrice);
    }
}