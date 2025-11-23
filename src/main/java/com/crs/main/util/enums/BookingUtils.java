package com.crs.main.util.enums;

public class BookingUtils {
    public enum BookingStatus {
        PENDING,
        CONFIRMED,
        CANCELLED,
        ACTIVE,     // Customer has the car right now
        COMPLETED,  // Car returned
    }
    public enum BookingType {
        ONLINE,
        OFFLINE,
        TELEPHONE
    }
}
