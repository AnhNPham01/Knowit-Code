package com.knowit;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.knowit.Room.RoomType;

/**
 * This is the App.java file that runs the application.
 *
 */
public class App {
    
    static BookingService bookingService = new BookingService(); // Create an instance of BookingService

    public Room registerRoom(int capacity, RoomType roomType, double pricePerDay) {
        int roomId = IDGenerator.generateUniqueRoomId(); // Generer unik ID
        Room room = new Room(roomId, capacity, pricePerDay, roomType); // Opprett rom
        bookingService.addRoom(room); // Legg til rommet i listen
        return room;
    }

    public Room findRoomById(int roomId) {
        for (Room room : bookingService.getRooms()) {
            if (room.getRoomId() == roomId) {
                return room; // Returner rommet hvis ID-en samsvarer
            }
        }
        return null; // Returner null hvis rommet med angitt ID ikke ble funnet
    }

    public static boolean isAvailable(Room room, Date bookingDate, int numberOfGuests) {
        for (Booking booking : bookingService.getBookings()) {
            if (booking.getRoom() == room && booking.getBookingDate().equals(bookingDate)) {
                return false; // Room is already booked on the specified date
            }
        }

        if (numberOfGuests > room.getCapacity()) {
            return false; // Number of guests exceeds room capacity
        }

        return true; // Room is available
    }

    public Booking bookRoom(int roomId, int numberOfGuests, Date bookingDate) {
        // Finn rommet med angitt ID
        Room room = findRoomById(roomId);
    
        if (room != null && isAvailable(room, bookingDate, numberOfGuests)) {
            // Opprett en booking for rommet
            Booking booking = new Booking(IDGenerator.generateUniqueBookingId(), room, numberOfGuests, bookingDate);
            // Legg til bookingen i en liste av bookinger
            bookingService.addBooking(booking);
            return booking;
        }
    
        return null; // Booking feilet (rommet finnes ikke eller er ikke tilgjengelig)
    }
    

    /*
     * Denne metoden itererer gjennom alle bookinger og sjekker om rom-ID-en i hver 
     * booking samsvarer med den angitte rom-ID-en. Hvis de samsvarer, 
     * legges prisen per dag for rommet til total inntjening.
     */
    
    public double getTotalEarningsForRoom(int roomId) {
        double totalEarnings = 0.0;
        for (Booking booking : bookingService.getBookings()) {
            if (booking.getRoom().getRoomId() == roomId) {
                totalEarnings += booking.getRoom().getPricePerDay();
            }
        }
        return totalEarnings;
    }
    
    public List<Room> getRoomsSortedByEarnings() {
        List<Room> roomsCopy = new ArrayList<>(bookingService.getRooms());
        roomsCopy.sort(Comparator.comparingDouble(room -> getTotalEarningsForRoom(((Room) room).getRoomId())).reversed());
        return roomsCopy;
    }

    public int getLongestConsecutiveBookingDays(int roomId) {
        List<Booking> roomBookings = new ArrayList<>();
        for (Booking booking : bookingService.getBookings()) {
            if (booking.getRoom().getRoomId() == roomId) {
                roomBookings.add(booking);
            }
        }
        
        int longestConsecutiveDays = 0;
        int currentConsecutiveDays = 0;
        Date previousDate = null;
        
        for (Booking booking : roomBookings) {
            if (previousDate == null || isNextDay(previousDate, booking.getBookingDate())) {
                currentConsecutiveDays++;
            } else {
                currentConsecutiveDays = 1;
            }
            
            if (currentConsecutiveDays > longestConsecutiveDays) {
                longestConsecutiveDays = currentConsecutiveDays;
            }
            
            previousDate = booking.getBookingDate();
        }
        
        return longestConsecutiveDays;
    }
    
    private boolean isNextDay(Date date1, Date date2) {
        long DAY_IN_MS = 1000 * 60 * 60 * 24;
        // Calculate the time difference in milliseconds
        long timeDiff = date2.getTime() - date1.getTime();
        
        // Check if the time difference is exactly one day
        return timeDiff == DAY_IN_MS;
    }
    

    
    public static void main(String[] args) {
        App app = new App();
    
        // Register a room
        Room room1 = app.registerRoom(2, Room.RoomType.BRYLLUPSUITE, 200.0);
    
        // Book a room
        Date bookingDate = new Date();
        int numberOfGuests = 2;
        Booking booking1 = app.bookRoom(room1.getRoomId(), numberOfGuests, bookingDate);
    
        if (booking1 != null) {
            System.out.println("Booking successful!");
        } else {
            System.out.println("Booking failed.");
        }
    
        // Book another room
        Room room2 = app.registerRoom(3, Room.RoomType.BUSINESSUITE, 250.0);
        bookingDate = new Date(); // Use a different date
        numberOfGuests = 3;
        Booking booking2 = app.bookRoom(room2.getRoomId(), numberOfGuests, bookingDate);
    
        if (booking2 != null) {
            System.out.println("Booking successful!");
        } else {
            System.out.println("Booking failed.");
        }
    
        // Calculate total earnings for a room
        double totalEarnings = app.getTotalEarningsForRoom(room1.getRoomId());
        System.out.println("Total earnings for room 1: $" + totalEarnings);
    
        // Get rooms sorted by earnings
        List<Room> roomsSortedByEarnings = app.getRoomsSortedByEarnings();
        System.out.println("Rooms sorted by earnings:");
        for (Room room : roomsSortedByEarnings) {
            System.out.println("Room ID: " + room.getRoomId() + ", Total Earnings: $" + app.getTotalEarningsForRoom(room.getRoomId()));
        }
    
        // Get the longest consecutive booking days for a room
        int longestConsecutiveDays = app.getLongestConsecutiveBookingDays(room1.getRoomId());
        System.out.println("Longest consecutive booking days for room 1: " + longestConsecutiveDays);
    }
    
}
