package test;

/**
 * Name: Sarah L. Lozier
 * Class: CMSC 215 - 6380
 * Project: Project 4
 * Date: March 5th, 2024
 * Description:
 */

import main.Interval;

public class IntervalTest {

    public static void main(String[] args) {
        System.out.println("Test Object Within Interval: " + (testObjectWithinInterval() ? "Passed" : "Failed"));
        System.out.println("Test Subinterval: " + (testSubinterval() ? "Passed" : "Failed"));
        System.out.println("Test Interval Overlaps: " + (testIntervalOverlaps() ? "Passed" : "Failed"));
    }

    // Test that an object is correctly identified as within an interval
    public static boolean testObjectWithinInterval() {
        Interval<Integer> interval = new Interval<>(10, 20);
        boolean isWithin = interval.within(15); // Should be true
        boolean isNotWithin = interval.within(25); // Should be false
        return isWithin && !isNotWithin;
    }

    // Test that an interval is correctly identified as a subinterval of another
    public static boolean testSubinterval() {
        Interval<Integer> interval = new Interval<>(10, 20);
        Interval<Integer> subInterval = new Interval<>(12, 18); // Should be true
        Interval<Integer> notSubInterval = new Interval<>(8, 22); // Should be false
        return interval.subinterval(subInterval) && !interval.subinterval(notSubInterval);
    }

    // Test that intervals overlap correctly
    public static boolean testIntervalOverlaps() {
        Interval<Integer> interval1 = new Interval<>(10, 20);
        Interval<Integer> overlappingInterval = new Interval<>(15, 25); // Should overlap
        Interval<Integer> nonOverlappingInterval = new Interval<>(21, 30); // Should not overlap

        boolean doesOverlap = interval1.overlaps(overlappingInterval);
        boolean doesNotOverlap = !interval1.overlaps(nonOverlappingInterval);
        return doesOverlap && doesNotOverlap;
    }

}
