package com.knowit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.List;

public class AppTest {

    private App app;
    private Room room;

    @BeforeEach
    void setUp() {
        app = new App();
        room = app.registerRoom(2, Room.RoomType.BRYLLUPSUITE, 200.0);
    }

    @Test
    void testBookRoomSuccess() {
        Date bookingDate = new Date();
        int numberOfGuests = 2;
        Booking booking = app.bookRoom(room.getRoomId(), numberOfGuests, bookingDate);

        assertNotNull(booking, "Booking should not be null");
        assertEquals(room, booking.getRoom(), "Room in booking should match the booked room");
        assertEquals(numberOfGuests, booking.getNumberOfGuests(), "Number of guests should match");
        assertEquals(bookingDate, booking.getBookingDate(), "Booking date should match");
    }

    @Test
    void testBookRoomFailure() {
        // Attempt to book a room on an already booked date
        Date bookingDate = new Date();
        int numberOfGuests = 2;
        Booking booking1 = app.bookRoom(room.getRoomId(), numberOfGuests, bookingDate);

        // Attempt to book the same room on the same date
        Booking booking2 = app.bookRoom(room.getRoomId(), 1, bookingDate);

        assertNull(booking2, "Booking should fail due to the room being already booked");
    }

    @Test
    void testBookRoomRoomNotFound() {
        // Attempt to book a room with an invalid room ID
        int invalidRoomId = 999; // Room with this ID doesn't exist
        Date bookingDate = new Date();
        int numberOfGuests = 2;
        Booking booking = app.bookRoom(invalidRoomId, numberOfGuests, bookingDate);

        assertNull(booking, "Booking should fail because the room was not found");
    }

    @Test
    public void testIsAvailable() {
        App app = new App();
        Room room = new Room(1, 2, 200.0, Room.RoomType.BRYLLUPSUITE);
        Date bookingDate = new Date();
        int numberOfGuests = 2;

        assertTrue(app.isAvailable(room, bookingDate, numberOfGuests));
    }

    @Test
    public void testIsNotAvailableRoomAlreadyBooked() {
        App app = new App();
        Room room = new Room(1, 2, 200.0, Room.RoomType.BRYLLUPSUITE);
        Date bookingDate = new Date();
        int numberOfGuests = 2;

        Booking booking = new Booking(1, room, numberOfGuests, bookingDate);
        app.bookingService.addBooking(booking);

        assertFalse(app.isAvailable(room, bookingDate, numberOfGuests));
    }

    @Test
    public void testIsNotAvailableExceedsCapacity() {
        App app = new App();
        Room room = new Room(1, 2, 200.0, Room.RoomType.BRYLLUPSUITE);
        Date bookingDate = new Date();
        int numberOfGuests = 3;

        assertFalse(app.isAvailable(room, bookingDate, numberOfGuests));
    }

    @Test
    public void testGetTotalEarningsForRoom() {
        App app = new App();
        Room room = new Room(1, 2, 200.0, Room.RoomType.BRYLLUPSUITE);
        Date bookingDate = new Date();
        int numberOfGuests = 2;

        Booking booking = new Booking(1, room, numberOfGuests, bookingDate);
        app.bookingService.addBooking(booking);

        double totalEarnings = app.getTotalEarningsForRoom(room.getRoomId());
        assertEquals(200.0, totalEarnings);
    }
/* 
    @Test
    public void testGetRoomsSortedByEarnings() {
        App app1 = new App();
        BookingService bookingService1 = new BookingService();
        Room room1 = new Room(1, 2, 200.0, Room.RoomType.BRYLLUPSUITE);
        Room room2 = new Room(2, 3, 250.0, Room.RoomType.BRYLLUPSUITE);

        Booking booking1 = new Booking(1, room1, 2, new Date());
        Booking booking2 = new Booking(2, room2, 3, new Date());

        bookingService1.addBooking(booking1);
        bookingService1.addBooking(booking2);
        bookingService1.addRoom(room1);
        bookingService1.addRoom(room2);
        app1.bookRoom(booking1);

        List<Room> rooms = app1.getRoomsSortedByEarnings();
        assertEquals(2, rooms.size());
        assertEquals(room1, rooms.get(0)); // room2 has higher earnings
        assertEquals(room2, rooms.get(1));
    } */

    @Test
    public void testGetRoomsSortedByEarnings() {
        App app1 = new App();

        Room room1 = new Room(1, 2, 200.0, Room.RoomType.BRYLLUPSUITE);
        Room room2 = new Room(2, 3, 250.0, Room.RoomType.BRYLLUPSUITE);

        Booking booking1 = new Booking(1, room1, 2, new Date());
        Booking booking2 = new Booking(2, room2, 3, new Date());

        app1.bookingService.addBooking(booking1);
        app1.bookingService.addBooking(booking2);

        List<Room> rooms1 = app1.getRoomsSortedByEarnings();
        assertEquals(6, rooms1.size());
        assertEquals(room2, rooms1.get(0)); // room2 has higher earnings
        assertEquals(room1, rooms1.get(1));
    }
}

