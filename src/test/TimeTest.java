package test;

/**
 * Name: Sarah L. Lozier
 * Class: CMSC 215 - 6380
 * Project: Project 4
 * Date: March 5th, 2024
 * Description:
 */

import main.Time;
import main.InvalidTime;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TimeTest {

    public static void main(String[] args) {

        System.out.println("Test Create Time Valid: " + (testCreateTimeValid() ? "Passed" : "Failed"));
        System.out.println("Test Compare Time: " + (testCompareTime() ? "Passed" : "Failed"));
        System.out.println("Test Create Time Invalid: " + (testCreateTimeInvalid() ? "Passed" : "Failed"));
    }

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

    // Positive Test 1: Test creating a valid Time object
    public static boolean testCreateTimeValid() {
        Path jsonFilePath = Paths.get("time.json");
        try {
            String jsonData = new String(Files.readAllBytes(jsonFilePath));
            List<String> validTimes = parseJsonData(jsonData, true); // Assume true for valid times
            boolean passed = true;
            for (String validTime : validTimes) {
                try {
                    new Time(validTime); // Attempt to create a Time object
                } catch (InvalidTime e) {
                    System.out.println("Test failed for supposedly valid time string: " + validTime);
                    passed = false;
                    break; // Exit the loop on first failure
                }
            }
            return passed;
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Return false if any error occurs reading the file or parsing data
        }
    }

    // Positive Test 2: Test comparing two Time objects
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

    // Negative Test: Test creating a Time object with invalid hour
    public static boolean testCreateTimeInvalid() {
        try {
            String jsonData = new String(Files.readAllBytes(Paths.get("time.json")));
            List<String> invalidTimes = parseJsonData(jsonData, false);

            for (String invalidTime : invalidTimes) {
                try {
                    new Time(invalidTime);
                    System.out.println("An invalid time did not throw InvalidTime: " + invalidTime);
                    return false; // An invalid time should throw an exception, so we return false here
                } catch (InvalidTime e) {
                    // This is expected for invalid times
                }
            }
            return true; // All invalid times correctly threw exceptions
        } catch (Exception e) {
            System.out.println("Error during testCreateTimeInvalid: " + e.getMessage());
            return false;
        }
    }

}
