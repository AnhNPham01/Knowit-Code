package com.knowit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.List;

public class AppTest {
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

    @Test
    public void testGetRoomsSortedByEarnings() {
        App app = new App();
        Room room1 = new Room(1, 2, 200.0, Room.RoomType.BRYLLUPSUITE);
        Room room2 = new Room(2, 3, 250.0, Room.RoomType.BRYLLUPSUITE);

        Booking booking1 = new Booking(1, room1, 2, new Date());
        Booking booking2 = new Booking(2, room2, 3, new Date());

        app.bookingService.addBooking(booking1);
        app.bookingService.addBooking(booking2);

        List<Room> rooms = app.getRoomsSortedByEarnings();
        assertEquals(2, rooms.size());
        assertEquals(room2, rooms.get(0)); // room2 has higher earnings
        assertEquals(room1, rooms.get(1));
    }
}

