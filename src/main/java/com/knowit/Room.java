package com.knowit;

public class Room {
    private int roomId;
    private int capacity;
    private double pricePerDay;
    private RoomType roomType;

    // Constructor
    public Room(int roomId, int capacity, double pricePerDay, RoomType roomType) {
        this.roomId = IDGenerator.generateUniqueRoomId();
        this.capacity = capacity;
        this.pricePerDay = pricePerDay;
        this.roomType = roomType;
    }

    // Getter for roomId
    public int getRoomId() {
        return roomId;
    }

    // Setter for roomId
    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    // Getter for capacity
    public int getCapacity() {
        return capacity;
    }

    // Setter for capacity
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    // Getter for pricePerDay
    public double getPricePerDay() {
        return pricePerDay;
    }

    // Setter for pricePerDay
    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    // Getter for roomType
    public RoomType getRoomType() {
        return roomType;
    }

    // Setter for roomType
    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public enum RoomType {
        BRYLLUPSUITE,
        BUSINESSUITE,
        KVALITETSROM,
        LAVPRISROM
    }
}

