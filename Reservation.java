package Hotel_reservation_system;

import java.time.LocalDate;

class Reservation {
    int reservationId;
    int roomNumber;
    String guestName;
    LocalDate checkIn;
    LocalDate checkOut;
    double totalPrice;

    public Reservation(int reservationId, int roomNumber, String guestName, LocalDate checkIn, LocalDate checkOut, double totalPrice) {
        this.reservationId = reservationId;
        this.roomNumber = roomNumber;
        this.guestName = guestName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.totalPrice = totalPrice;
    }
}

