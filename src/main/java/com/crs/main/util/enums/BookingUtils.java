package com.crs.main.util.enums;

public class BookingUtils {
    public enum BookingStatus {
        PENDING, // Awaiting confirmation (from rental company)
        CONFIRMED, // Booking confirmed (by rental company)
        CANCELLED, // Booking was cancelled (by user or rental company)
        NO_SHOW, // User did not show up for pickup
        COMPLETED,  // Booking completed
    }
    public enum BookingType {
        ONLINE, // Booking made through website or app
        OFFLINE, // Booking made in person at rental location
        AGENT, // Booking made through a travel agent
        TELEPHONE // Booking made over the phone
    }
}
