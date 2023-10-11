package com.knowit;

import java.util.ArrayList;
import java.util.List;

public class BookingService {
    private List<Room> rooms;
    private List<Booking> bookings;

    // Constructor
    public BookingService() {
        rooms = new ArrayList<>();
        bookings = new ArrayList<>();
    }

    // Method to add a room
    public void addRoom(Room room) {
        rooms.add(room);
    }

    // Method to remove a room
    public void removeRoom(Room room) {
        rooms.remove(room);
    }

    // Method to add a booking
    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    // Method to remove a booking
    public void removeBooking(Booking booking) {
        bookings.remove(booking);
    }

    // Getter for rooms
    public List<Room> getRooms() {
        return rooms;
    }

    

    // Setter for rooms
    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    // Getter for bookings
    public List<Booking> getBookings() {
        return bookings;
    }

    // Setter for bookings
    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    // Other methods for booking operations
}

