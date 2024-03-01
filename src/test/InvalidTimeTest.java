package test;

/**
 * Name: Sarah L. Lozier
 * Class: CMSC 215 - 6380
 * Project: Project 4
 * Date: March 5th, 2024
 * Description:
 */

import main.InvalidTime;

public class InvalidTimeTest {

    public static void main(String[] args) {
        System.out.println(
                "Test InvalidTime with Specific Message: " + (testInvalidTimeWithMessage() ? "Passed" : "Failed"));
    }

    /**
     * Test that an InvalidTime exception correctly stores and returns a specific
     * message.
     * 
     * @return true if the test passes, false otherwise.
     */
    public static boolean testInvalidTimeWithMessage() {
        String expectedMessage = "This is a test message";
        try {
            // Attempt to throw the InvalidTime exception with a specific message
            throw new InvalidTime(expectedMessage);
        } catch (InvalidTime e) {
            // Check if the message from the exception matches the expected message
            return e.getMessage().equals(expectedMessage);
        } catch (Exception e) {
            // If any other type of exception is caught, the test fails
            System.out.println("Unexpected exception type caught.");
            return false;
        }
    }
}