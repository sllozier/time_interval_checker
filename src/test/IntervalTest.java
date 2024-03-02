package test;

/**
 * Name: Sarah L. Lozier
 * Class: CMSC 215 - 6380
 * Project: Project 4
 * Date: March 5th, 2024
 * Description:This test class is designed to validate the functionality of the Interval class.
 * It includes tests for checking if an object is within a specified interval, if one interval is a subinterval of another,
 * and if two intervals overlap. These tests ensure the Interval class accurately represents and manipulates interval data.
 */

import main.Interval;

public class IntervalTest {

    /**
     * Main method to run all tests related to the Interval class.
     * 
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        System.out.println("Test Object Within Interval: " + (testObjectWithinInterval() ? "Passed" : "Failed"));
        System.out.println("Test Subinterval: " + (testSubinterval() ? "Passed" : "Failed"));
        System.out.println("Test Interval Overlaps: " + (testIntervalOverlaps() ? "Passed" : "Failed"));
    }

    /**
     * Tests that an object is correctly identified as within an interval.
     * 
     * @return true if the object is within the interval, false otherwise.
     */
    public static boolean testObjectWithinInterval() {
        Interval<Integer> interval = new Interval<>(10, 20);
        // Should be true
        boolean isWithin = interval.within(15);
        // Should be false
        boolean isNotWithin = interval.within(25);
        return isWithin && !isNotWithin;
    }

    /**
     * Tests that an interval is correctly identified as a subinterval of another
     * interval.
     * 
     * @return true if the interval is a subinterval, false otherwise.
     */
    public static boolean testSubinterval() {
        Interval<Integer> interval = new Interval<>(10, 20);
        // Should be true
        Interval<Integer> subInterval = new Interval<>(12, 18);
        // Should be false
        Interval<Integer> notSubInterval = new Interval<>(8, 22);
        return interval.subinterval(subInterval) && !interval.subinterval(notSubInterval);
    }

    /**
     * Tests that intervals overlap correctly.
     * 
     * @return true if the intervals overlap, false if they do not overlap.
     */
    public static boolean testIntervalOverlaps() {
        Interval<Integer> interval1 = new Interval<>(10, 20);
        // Should overlap
        Interval<Integer> overlappingInterval = new Interval<>(15, 25);
        // Should not overlap
        Interval<Integer> nonOverlappingInterval = new Interval<>(21, 30);

        boolean doesOverlap = interval1.overlaps(overlappingInterval);
        boolean doesNotOverlap = !interval1.overlaps(nonOverlappingInterval);
        return doesOverlap && doesNotOverlap;
    }

}
