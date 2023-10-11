package com.knowit;

public class IDGenerator {
    private static int roomIdCounter = 1;
    private static int bookingIdCounter = 1;

    public static int generateUniqueRoomId() {
        return roomIdCounter++;
    }

    public static int generateUniqueBookingId() {
        return bookingIdCounter++;
    }
}

