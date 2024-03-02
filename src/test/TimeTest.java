package test;

/**
 * Name: Sarah L. Lozier
 * Class: CMSC 215 - 6380
 * Project: Project 4
 * Date: March 5th, 2024
 * Description: This class is designed to test the functionality of the Time class. It includes 
 * methods to validate the creation of Time objects with both valid and invalid inputs, and to compare
 * the order of two Time objects. The tests ensure that Time objects are correctly instantiated and 
 * compared according to the 12-hour clock format.
 */

import main.Time;
import main.InvalidTime;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TimeTest {

    /**
     * Main method to run all tests related to the Time class.
     * 
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {

        System.out.println("Test Create Time Valid: " + (testCreateTimeValid() ? "Passed" : "Failed"));
        System.out.println("Test Compare Time: " + (testCompareTime() ? "Passed" : "Failed"));
        System.out.println("Test Create Time Invalid: " + (testCreateTimeInvalid() ? "Passed" : "Failed"));
    }

    /**
     * Parses JSON data to extract either valid or invalid time strings.
     * 
     * @param jsonData The string containing JSON data.
     * @param valid    A boolean indicating whether to extract valid or invalid
     *                 times.
     * @return A list of time strings extracted from the JSON data.
     */
    private static List<String> parseJsonData(String jsonData, boolean valid) {
        List<String> times = new ArrayList<>();
        try {
            String timesString;
            if (valid) {
                // Extract valid times
                int start = jsonData.indexOf("\"validTimes\": [") + "\"validTimes\": [".length();
                int end = jsonData.indexOf("]", start);
                timesString = jsonData.substring(start, end);
            } else {
                // Extract invalid times
                int start = jsonData.indexOf("\"invalidTimes\": [") + "\"invalidTimes\": [".length();
                int end = jsonData.indexOf("]", start);
                timesString = jsonData.substring(start, end);
            }

            // Split the times string into individual time strings and trim the quotes
            String[] timesArray = timesString.split(",");
            for (String time : timesArray) {
                times.add(time.trim().replace("\"", ""));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return times;
    }

    /**
     * Tests the creation of Time objects with valid time strings.
     * Validates that no InvalidTime exceptions are thrown for valid inputs.
     * 
     * @return true if all valid times are correctly instantiated, false otherwise.
     */
    public static boolean testCreateTimeValid() {
        Path jsonFilePath = Paths.get("time.json");
        try {
            String jsonData = new String(Files.readAllBytes(jsonFilePath));
            // Assume true for valid times
            List<String> validTimes = parseJsonData(jsonData, true);
            boolean passed = true;
            for (String validTime : validTimes) {
                try {
                    // Attempt to create a Time object
                    new Time(validTime);
                } catch (InvalidTime e) {
                    System.out.println("Test failed for supposedly valid time string: " + validTime);
                    passed = false;
                    // Exit the loop on first failure
                    break;
                }
            }
            return passed;
        } catch (Exception e) {
            e.printStackTrace();
            // Return false if any error occurs reading the file or parsing data
            return false;
        }
    }

    /**
     * Tests the comparison of two Time objects to ensure correct ordering.
     * Validates that Time.compareTo() method correctly identifies the order.
     * 
     * @return true if the comparison logic is correct, false otherwise.
     */
    public static boolean testCompareTime() {
        try {
            Boolean passed = true;
            String jsonData = new String(Files.readAllBytes(Paths.get("time.json")));
            List<String> validTimes = parseJsonData(jsonData, true);

            if (validTimes.size() < 2) {
                System.out.println("Not enough valid times provided for comparison.");
                return false;
            }

            Time time1 = new Time(validTimes.get(0));
            Time time2 = new Time(validTimes.get(1));
            passed = time1.compareTo(time2) < 0;
            return passed;
        } catch (Exception e) {
            System.out.println("Error during testCompareTime: " + e.getMessage());
            return false;
        }
    }

    /**
     * Tests the creation of Time objects with invalid time strings.
     * Validates that an InvalidTime exception is thrown for each invalid input.
     * 
     * @return true if all invalid times correctly throw InvalidTime exceptions,
     *         false otherwise.
     */
    public static boolean testCreateTimeInvalid() {
        try {
            String jsonData = new String(Files.readAllBytes(Paths.get("time.json")));
            List<String> invalidTimes = parseJsonData(jsonData, false);

            for (String invalidTime : invalidTimes) {
                try {
                    new Time(invalidTime);
                    System.out.println("An invalid time did not throw InvalidTime: " + invalidTime);
                    // An invalid time should throw an exception, so we return false here
                    return false;
                } catch (InvalidTime e) {
                    // This is expected for invalid times
                }
            }
            // All invalid times correctly threw exceptions
            return true;
        } catch (Exception e) {
            System.out.println("Error during testCreateTimeInvalid: " + e.getMessage());
            return false;
        }
    }

}
