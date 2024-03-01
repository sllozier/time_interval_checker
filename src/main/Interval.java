package main;

/**
 * Name: Sarah L. Lozier
 * Class: CMSC 215 - 6380
 * Project: Project 4
 * Date: March 5th, 2024
 * Description: This class represents an interval with a start and end of a
 * generic type.
 * It provides methods to check if an object is inside the interval,
 * if an interval is a subinterval, and if two intervals overlap.
 */

public class Interval<T extends Comparable<T>> {

    private final T start;
    private final T end;

    /**
     * Constructs an Interval object with the specified start and end points.
     * 
     * @param start the start point of the interval
     * @param end   the end point of the interval
     */
    public Interval(T start, T end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Checks if a given object is within the interval.
     * 
     * @param object the object to check
     * @return true if the object is within the interval, false otherwise
     */
    public boolean within(T object) {
        return object.compareTo(start) >= 0 && object.compareTo(end) <= 0;
    }

    /**
     * Checks if another interval is a subinterval of this interval.
     * 
     * @param other the interval to check against this interval
     * @return true if the other interval is a subinterval, false otherwise
     */
    public boolean subinterval(Interval<T> other) {

        // Cases that if both are true would create a subinterval
        boolean startCondition = other.start.compareTo(this.start) >= 0;
        boolean endCondition = other.end.compareTo(this.end) <= 0;
        return startCondition && endCondition;
    }

    /**
     * Checks if another interval overlaps with this interval.
     * 
     * @param other the interval to check for overlap with this interval
     * @return true if there is any overlap, false otherwise
     */
    public boolean overlaps(Interval<T> other) {

        // Cases that would be considered an overlap in intervals
        boolean condition1 = other.start.compareTo(this.start) >= 0 && other.start.compareTo(this.end) <= 0;
        boolean condition2 = other.end.compareTo(this.start) >= 0 && other.end.compareTo(this.end) <= 0;
        boolean condition3 = this.start.compareTo(other.start) >= 0 && this.start.compareTo(other.end) <= 0;
        boolean condition4 = this.end.compareTo(other.start) >= 0 && this.end.compareTo(other.end) <= 0;
        return condition1 || condition2 || condition3 || condition4;
    }

}
