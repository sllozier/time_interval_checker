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

    public Interval(T start, T end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the start of the interval.
     * 
     * @return the start of the interval
     */
    public T getStart() {
        return start;
    }

    /**
     * Returns the end of the interval.
     * 
     * @return the end of the interval
     */
    public T getEnd() {
        return end;
    }

    public boolean within(T object) {
        return object.compareTo(start) >= 0 && object.compareTo(end) <= 0;
    }

    public boolean subinterval(Interval<T> interval) {
        T thisStart = this.getStart();
        T thisEnd = this.getEnd();
        T intervalStart = interval.getStart();
        T intervalEnd = interval.getEnd();

        return thisStart.compareTo(intervalStart) >= 0 && thisEnd.compareTo(intervalEnd) <= 0;
    }

    public boolean overlaps(Interval<T> interval) {
        T thisStart = this.getStart();
        T thisEnd = this.getEnd();
        T intervalStart = interval.getStart();
        T intervalEnd = interval.getEnd();

        return thisStart.compareTo(intervalEnd) < 0 && thisEnd.compareTo(intervalStart) > 0;
    }

}
