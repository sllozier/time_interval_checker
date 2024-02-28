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
    private final int hours;
    private final int minutes;
    private final String meridian;

    public Time(String timeString) throws InvalidTime {
        String[] parts = timeString.split(" ");
        if (parts.length != 2)
            throw new InvalidTime("Invalid time format. Use HH:MM AM/PM");

        String[] timeParts = parts[0].split(":");
        if (timeParts.length != 2)
            throw new InvalidTime("Invalid time format. Use HH:MM AM/PM");

        int hours, minutes;
        try {
            hours = Integer.parseInt(timeParts[0]);
            minutes = Integer.parseInt(timeParts[1]);
        } catch (NumberFormatException e) {
            throw new InvalidTime("Invalid numeric values for hours or minutes");
        }

        if (hours < 1 || hours > 12)
            throw new InvalidTime("Hours must be between 1 and 12");
        if (minutes < 0 || minutes > 59)
            throw new InvalidTime("Minutes must be between 0 and 59");

        this.hours = hours;
        this.minutes = minutes;
        this.meridian = parts[1];
    }

    public Time(int hours, int minutes, String meridian) throws InvalidTime {
        if (hours < 1 || hours > 12)
            throw new InvalidTime("Hours must be between 1 and 12");
        if (minutes < 0 || minutes > 59)
            throw new InvalidTime("Minutes must be between 0 and 59");
        if (!meridian.equals("AM") && !meridian.equals("PM"))
            throw new InvalidTime("Meridian must be either AM or PM");

        this.hours = hours;
        this.minutes = minutes;
        this.meridian = meridian;
    }

    @Override
    public int compareTo(Time other) {
        if (this.meridian.equals("AM") && other.meridian.equals("PM")) {
            return -1; // AM comes before PM
        } else if (this.meridian.equals("PM") && other.meridian.equals("AM")) {
            return 1; // PM comes after AM
        } else {
            // Both times are either AM or PM
            if (this.hours != other.hours) {
                return this.hours - other.hours;
            } else {
                return this.minutes - other.minutes;
            }
        }
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d %s", hours, minutes, meridian);
    }
}
