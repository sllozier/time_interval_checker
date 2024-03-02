package test;

/**
 * Name: Sarah L. Lozier
 * Class: CMSC 215 - 6380
 * Project: Project 4
 * Date: March 5th, 2024
 * Description:This class is dedicated to testing the InvalidTime exception class of the time management system. 
 * The test aims to verify the proper instantiation and message retrieval of the InvalidTime exception, ensuring that it 
 * accurately represents error conditions related to invalid time inputs within the system. This suite focuses on validating 
 * the core functionality and reliability of error handling mechanisms that are critical for maintaining the integrity of 
 * the application's temporal logic.
 */

import main.InvalidTime;

public class InvalidTimeTest {
    /**
     * Main method to run all tests related to the Invalid Time class.
     * 
     * @param args Command line arguments (not used).
     */
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