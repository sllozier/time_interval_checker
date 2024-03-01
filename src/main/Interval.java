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

    public boolean within(T object) {
        return object.compareTo(start) >= 0 && object.compareTo(end) <= 0;
    }

    public boolean subinterval(Interval<T> other) {
        System.out.println("SUBINTERVAL THIS START " + this.start);
        System.out.println("SUBINTERVAL THIS END " + this.end);
        System.out.println("SUBINTERVAL OTHER START " + other.start);
        System.out.println("SUBINTERVAL OTHER END " + other.end);
        boolean startCondition = other.start.compareTo(this.start) >= 0;
        boolean endCondition = other.end.compareTo(this.end) <= 0;
        System.out
                .println("Subinterval check - Start condition: " + startCondition + ", End condition: " + endCondition);
        return startCondition && endCondition;
    }

    public boolean overlaps(Interval<T> other) {
        System.out.println("OVERLAPS THIS START " + this.start);
        System.out.println("OVERLAPS THIS END " + this.end);
        System.out.println("OVERLAPS OTHER START " + other.start);
        System.out.println("OVERLAPS OTHER END " + other.end);
        boolean condition1 = other.start.compareTo(this.start) >= 0 && other.start.compareTo(this.end) <= 0;
        boolean condition2 = other.end.compareTo(this.start) >= 0 && other.end.compareTo(this.end) <= 0;
        boolean condition3 = this.start.compareTo(other.start) >= 0 && this.start.compareTo(other.end) <= 0;
        boolean condition4 = this.end.compareTo(other.start) >= 0 && this.end.compareTo(other.end) <= 0;
        System.out.println("Overlaps check - Condition1: " + condition1 + ", Condition2: " + condition2
                + ", Condition3: " + condition3 + ", Condition4: " + condition4);
        return condition1 || condition2 || condition3 || condition4;
    }

    // private final T start;
    // private final T end;

    // public Interval(T start, T end) {
    // this.start = start;
    // this.end = end;
    // }

    // /**
    // * Returns the start of the interval.
    // *
    // * @return the start of the interval
    // */
    // public T getStart() {
    // System.out.println("GET START " + start);
    // return start;
    // }

    // /**
    // * Returns the end of the interval.
    // *
    // * @return the end of the interval
    // */
    // public T getEnd() {
    // System.out.println("GET END " + end);
    // return end;
    // }

    // public boolean within(T object) {
    // System.out.println("Within method called");

    // return object.compareTo(start) >= 0 && object.compareTo(end) <= 0;
    // }

    // // public boolean within(T object) {
    // // return object.compareTo(start) >= 0 && object.compareTo(end) <= 0;
    // // }

    // public boolean subinterval(Interval<T> interval) {
    // T thisStart = this.getStart();
    // T thisEnd = this.getEnd();
    // T intervalStart = interval.getStart();
    // T intervalEnd = interval.getEnd();

    // System.out.println("SUBINTERVAL THIS START " + thisStart);
    // System.out.println("SUBINTERVAL THIS END " + thisEnd);
    // System.out.println("SUBINTERVAL INTERVAL START " + intervalStart);
    // System.out.println("SUBINTERVAL INTERVAL END " + intervalEnd);

    // // Check if interval is a subinterval of this
    // boolean isSubinterval1 = thisStart.compareTo(intervalStart) <= 0 &&
    // thisEnd.compareTo(intervalEnd) >= 0;

    // // Check if this is a subinterval of interval
    // boolean isSubinterval2 = thisStart.compareTo(intervalStart) >= 0 &&
    // thisEnd.compareTo(intervalEnd) <= 0;

    // // Return true if either condition is met
    // return isSubinterval1 || isSubinterval2;
    // }

    // public boolean overlaps(Interval<T> interval) {
    // T thisStart = this.getStart();
    // T thisEnd = this.getEnd();
    // T intervalStart = interval.getStart();
    // T intervalEnd = interval.getEnd();

    // System.out.println("OVERLAPS THIS START " + thisStart);
    // System.out.println("OVERLAPS THIS END " + thisEnd);
    // System.out.println("OVERLAPS INTERVAL START " + intervalStart);
    // System.out.println("OVERLAPS INTERVAL END " + intervalEnd);

    // return thisStart.compareTo(intervalStart) < 0 ||
    // thisEnd.compareTo(intervalEnd) > 0;
    // }

}
