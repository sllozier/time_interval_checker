package test;

/**
 * Name: Sarah L. Lozier
 * Class: CMSC 215 - 6380
 * Project: Project 4
 * Date: March 5th, 2024
 * Description:
 */

import main.Project4;
import main.Time;
import main.Interval;
import main.InvalidTime;

public class Project4Test {

    /**
     * Test that intervals are correctly identified as overlapping.
     */
    public static boolean testIntervalsOverlap() {
        try {
            // Assuming a method exists to directly compare intervals without needing GUI
            // interaction.
            Time start1 = new Time("10:00 AM");
            Time end1 = new Time("11:00 AM");
            Interval<Time> interval1 = new Interval<>(start1, end1);

            Time start2 = new Time("10:30 AM");
            Time end2 = new Time("11:30 AM");
            Interval<Time> interval2 = new Interval<>(start2, end2);

            // This method would ideally be part of the Project4 class or related logic to
            // be tested.
            boolean overlap = Project4.checkIntervalsOverlap(interval1, interval2);

            return overlap;
        } catch (InvalidTime e) {
            // Handle the exception if an invalid time is somehow created.
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Test that intervals are correctly identified as not overlapping.
     */
    public static boolean testIntervalsDoNotOverlap() {
        // Similar to testIntervalsOverlap but with intervals that do not overlap.
        // This is left as an exercise to implement.
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Test Intervals Overlap: " + (testIntervalsOverlap() ? "Passed" : "Failed"));
        System.out.println("Test Intervals Do Not Overlap: " + (testIntervalsDoNotOverlap() ? "Passed" : "Failed"));
    }
}
