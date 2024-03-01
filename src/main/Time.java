package main;

/**
 * Name: Sarah L. Lozier
 * Class: CMSC 215 - 6380
 * Project: Project 4
 * Date: March 5th, 2024
 * Description: This class represents a time with hours, minutes, and a meridian
 * (AM or PM).
 * It implements the Comparable interface to allow comparison between time
 * objects.
 * The class ensures immutability and performs input validation when
 * constructing time objects.
 */

public class Time implements Comparable<Time> {

    private final int hour;
    private final int minute;
    private final String meridian;

    /**
     * Constructs a Time object with the specified hour, minute, and meridian.
     * 
     * @param hour     the hour
     * @param minute   the minute
     * @param meridian the meridian indicator ("AM" or "PM")
     * @throws InvalidTime if the time is not in a valid 12-hour format
     */

    public Time(int hour, int minute, String meridian) throws InvalidTime {
        // Check the individual values for hour, minute and meridian are withincorrect
        // ranges
        if (hour < 1 || hour > 12 || minute < 0 || minute > 59 || (!meridian.equals("AM") && !meridian.equals("PM"))) {
            // If not throw InvalidTime class
            throw new InvalidTime("Invalid time format");
        }

        // Assign Time properies
        this.hour = hour;
        this.minute = minute;
        this.meridian = meridian;
    }

    /**
     * Constructs a Time object from a string representation of time.
     * 
     * @param time the string representation of time (e.g., "12:00 PM")
     * @throws InvalidTime if the string is not in a valid 12-hour format
     */

    public Time(String time) throws InvalidTime {

        // Separate the time string into 3 parts using the ":" and " "
        String[] parts = time.split("[: ]");

        // Assign the parts to hour, minute and meridian
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        String meridian = parts[2];

        // Check the individual values for hour, minute and meridian are withincorrect
        // ranges
        if (hour < 1 || hour > 12 || minute < 0 || minute > 59 || (!meridian.equals("AM") && !meridian.equals("PM"))) {
            // If not throw InvalidTime class
            throw new InvalidTime("Invalid time format");
        }

        // Assign Time properies
        this.hour = hour;
        this.minute = minute;
        this.meridian = meridian;
    }

    /**
     * Compares this Time object with another Time object for order.
     * 
     * @param other the Time object to be compared.
     * @return a negative integer, zero, or a positive integer as this Time is less
     *         than, equal to, or greater than the specified Time.
     */
    @Override
    public int compareTo(Time other) {
        // Convert both this time and the other time to a 24-hour format for comparison
        int thisMinutes = (this.hour % 12) + (this.meridian.equals("PM") ? 12 : 0);
        int otherMinutes = (other.hour % 12) + (other.meridian.equals("PM") ? 12 : 0);

        // Convert hours to minutes and add minute for precise comparison
        thisMinutes = thisMinutes * 60 + this.minute;
        otherMinutes = otherMinutes * 60 + other.minute;

        // Return 1 if thisMinutes > otherMinutes, -1 if thisMinutes < otherMinutes, 0
        // if thisMinutes = otherMinutes
        return Integer.compare(thisMinutes, otherMinutes);
    }

    /**
     * Returns a string representation of this Time object.
     * 
     * @return a string in the format "HH:MM AM/PM"
     */

    @Override
    public String toString() {
        return String.format("%02d:%02d %s", hour, minute, meridian);
    }

}
