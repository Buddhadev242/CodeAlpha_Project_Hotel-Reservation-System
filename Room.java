package Hotel_reservation_system;

import java.util.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

class Room {
    int roomNumber;
    String category;
    double pricePerNight;
    boolean isAvailable;

    public Room(int roomNumber, String category, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.pricePerNight = pricePerNight;
        this.isAvailable = true;
    }
}