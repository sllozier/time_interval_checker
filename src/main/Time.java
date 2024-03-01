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

    public Time(int hour, int minute, String meridian) throws InvalidTime {
        if (hour < 1 || hour > 12 || minute < 0 || minute > 59 || (!meridian.equals("AM") && !meridian.equals("PM"))) {
            throw new InvalidTime("Invalid time format");
        }
        this.hour = hour;
        this.minute = minute;
        this.meridian = meridian;
    }

    public Time(String time) throws InvalidTime {
        String[] parts = time.split("[: ]");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        String meridian = parts[2];
        if (hour < 1 || hour > 12 || minute < 0 || minute > 59 || (!meridian.equals("AM") && !meridian.equals("PM"))) {
            throw new InvalidTime("Invalid time format");
        }
        this.hour = hour;
        this.minute = minute;
        this.meridian = meridian;
    }

    @Override
    public int compareTo(Time other) {
        // Convert both this time and the other time to a 24-hour format for comparison
        int thisMinutes = (this.hour % 12) + (this.meridian.equals("PM") ? 12 : 0);
        int otherMinutes = (other.hour % 12) + (other.meridian.equals("PM") ? 12 : 0);

        thisMinutes = thisMinutes * 60 + this.minute; // Convert hours to minutes and add minute for precise comparison
        otherMinutes = otherMinutes * 60 + other.minute;

        return Integer.compare(thisMinutes, otherMinutes);
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d %s", hour, minute, meridian);
    }

    // private final int hours;
    // private final int minutes;
    // private final String meridian;
    // private static int compareCounter = 0;

    // public Time(String timeString) throws InvalidTime {
    // // Split the input time string into hours, minutes, and meridian
    // String[] parts = timeString.split(" ");
    // if (parts.length != 2)
    // throw new InvalidTime("Invalid time format. Use HH:MM AM/PM");

    // String[] timeParts = parts[0].split(":");
    // if (timeParts.length != 2)
    // throw new InvalidTime("Invalid time format. Use HH:MM AM/PM");

    // int hours, minutes;
    // try {
    // hours = Integer.parseInt(timeParts[0]);
    // minutes = Integer.parseInt(timeParts[1]);
    // } catch (NumberFormatException e) {
    // throw new InvalidTime("Invalid numeric values for hours or minutes");
    // }

    // // Validate hours, minutes, and meridian
    // if (hours < 1 || hours > 12 || minutes < 0 || minutes > 59 ||
    // (!parts[1].equals("AM") && !parts[1].equals("PM"))) {
    // throw new InvalidTime("Invalid time format or values");
    // }

    // this.hours = hours;
    // this.minutes = minutes;
    // this.meridian = parts[1];
    // }

    // public Time(String timeString) throws InvalidTime {
    // String[] parts = timeString.split(" ");
    // if (parts.length != 2)
    // throw new InvalidTime("Invalid time format. Use HH:MM AM/PM");

    // String[] timeParts = parts[0].split(":");
    // if (timeParts.length != 2)
    // throw new InvalidTime("Invalid time format. Use HH:MM AM/PM");

    // int hours, minutes;
    // try {
    // hours = Integer.parseInt(timeParts[0]);
    // minutes = Integer.parseInt(timeParts[1]);
    // } catch (NumberFormatException e) {
    // throw new InvalidTime("Invalid numeric values for hours or minutes");
    // }

    // if (hours < 1 || hours > 12)
    // throw new InvalidTime("Hours must be between 1 and 12");
    // if (minutes < 0 || minutes > 59)
    // throw new InvalidTime("Minutes must be between 0 and 59");

    // this.hours = hours;
    // this.minutes = minutes;
    // this.meridian = parts[1];
    // }

    // public Time(int hours, int minutes, String meridian) throws InvalidTime {
    // if (hours < 1 || hours > 12)
    // throw new InvalidTime("Hours must be between 1 and 12");
    // if (minutes < 0 || minutes > 59)
    // throw new InvalidTime("Minutes must be between 0 and 59");
    // if (!meridian.equals("AM") && !meridian.equals("PM"))
    // throw new InvalidTime("Meridian must be either AM or PM");

    // this.hours = hours;
    // this.minutes = minutes;
    // this.meridian = meridian;
    // }

    // private Time to24HourFormat() {
    // int militaryHours = hours;
    // if (meridian.equals("PM") && hours != 12) {
    // militaryHours += 12;
    // } else if (meridian.equals("AM") && hours == 12) {
    // militaryHours = 0;
    // }
    // // Format the time into HH:MM format
    // String militaryTime = String.format("%02d:%02d", militaryHours, minutes);
    // // Create a new Time object using the String constructor
    // return new Time(militaryTime);
    // }

    // private Time to12HourFormat() {
    // int displayHours = hours;
    // String displayMeridian = meridian;
    // if (hours > 12) {
    // displayHours -= 12;
    // displayMeridian = "PM";
    // } else if (hours == 0) {
    // displayHours = 12;
    // displayMeridian = "AM";
    // } else if (hours == 12) {
    // displayMeridian = "PM";
    // }
    // // Format the time into HH:MM AM/PM format
    // String displayTime = String.format("%02d:%02d %s", displayHours, minutes,
    // displayMeridian);
    // // Create a new Time object using the String constructor
    // return new Time(displayTime);
    // }

    // @Override
    // public int compareTo(Time other) {
    // compareCounter++;
    // Time this24HourTime = this.to24HourFormat();
    // Time other24HourTime = other.to24HourFormat();

    // if (!this24HourTime.meridian.equals(other24HourTime.meridian)) {
    // return this24HourTime.meridian.compareTo(other24HourTime.meridian);
    // } else {
    // if (this24HourTime.hours != other24HourTime.hours) {
    // return this24HourTime.hours - other24HourTime.hours;
    // } else {
    // return this24HourTime.minutes - other24HourTime.minutes;
    // }
    // }
    // }

    // @Override
    // public String toString() {
    // Time displayTime = this.to12HourFormat();
    // return String.format("%02d:%02d %s", displayTime.hours, displayTime.minutes,
    // displayTime.meridian);
    // }

    // @Override
    // public int compareTo(Time other) {
    // compareCounter++;
    // System.out.println("COMPARE TO THIS " + this.hours);
    // System.out.println("COMPARE TO OTHER " + other.hours);
    // // System.out.println("Compare Counter: " + compareCounter);
    // if (!this.meridian.equals(other.meridian)) {
    // // If the meridians are different, compare based on meridian
    // return this.meridian.compareTo(other.meridian);
    // } else {
    // // If the meridians are the same, compare based on hours and minutes
    // if (this.hours != other.hours) {
    // int hoursResult = this.hours - other.hours;
    // System.out.println("COMPARE HOURS RESULT " + hoursResult);
    // return hoursResult;
    // } else {
    // int minsResult = this.minutes - other.minutes;
    // System.out.println("COMPARE MINS RESULT " + minsResult);
    // return minsResult;
    // }

    // }

    // }

    // @Override
    // public String toString() {
    // return String.format("%02d:%02d %s", hours, minutes, meridian);
    // }
}
