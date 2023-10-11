package com.knowit;

import java.util.Date;
/*
 * 
 * Booking.java represents one booking for one room
 */
public class Booking {
    private int bookingId;
    private Room room;
    private Date bookingDate;
    private int numberOfGuests;

    public Booking(int bookingId, Room room, int numberOfGuests, Date bookingDate) {
        this.bookingId = IDGenerator.generateUniqueBookingId();
        this.room = room;
        this.bookingDate = bookingDate;
        this.numberOfGuests = numberOfGuests;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }
}
