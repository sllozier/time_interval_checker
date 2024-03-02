package test;

/**
 * Name: Sarah L. Lozier
 * Class: CMSC 215 - 6380
 * Project: Project 4
 * Date: March 5th, 2024
 * Description: This class is responsible for generating a JSON file containing both valid and specific invalid time scenarios
 * for testing purposes. It generates a predefined number of valid times and a set of specific invalid times based on common
 * invalid time formats. The output is a JSON file that serves as input data for testing the functionality of time-related
 * classes and methods in the project.
 */

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateTimeData {

    /**
     * Main method to run all tests related to the Interval class.
     * 
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        String filePath = "time.json";
        int numTimes = 50; // Number of valid times

        // Generate random valid times
        List<Time> validTimes = generateRandomTimes(numTimes, true);
        // Generate specific invalid times
        List<Time> invalidTimes = generateSpecificInvalidTimes();

        // Write generated time data to file, including both valid and invalid times
        writeTimesToFile(validTimes, invalidTimes, filePath);
    }

    /**
     * Generates a list of random valid times.
     * 
     * @param numTimes The number of times to generate.
     * @param valid    A flag indicating if the times should be valid.
     * @return A list of generated Time objects.
     */
    private static List<Time> generateRandomTimes(int numTimes, boolean valid) {
        List<Time> times = new ArrayList<>();
        Random random = new Random();

        // Add two predefined times
        if (numTimes >= 2) {
            // index 0
            times.add(new Time(10, 30, "AM"));
            // index 1
            times.add(new Time(2, 15, "PM"));
        }

        for (int i = 2; i < numTimes; i++) {
            int hour = 1 + random.nextInt(12);
            int minute = random.nextInt(60);
            String meridian = random.nextBoolean() ? "AM" : "PM";

            times.add(new Time(hour, minute, meridian));
        }

        return times;
    }

    /**
     * Generates a list of specific invalid time scenarios.
     * 
     * @return A list of Time objects representing invalid times.
     */
    private static List<Time> generateSpecificInvalidTimes() {
        List<Time> times = new ArrayList<>();
        Random random = new Random();

        // Hour less than 1, including negative values
        for (int i = 0; i < 5; i++) {
            int invalidHour = -1 * random.nextInt(13);
            times.add(new Time(invalidHour, 1 + random.nextInt(58), "AM"));
        }

        // Hour greater than 12
        for (int i = 0; i < 5; i++) {
            times.add(new Time(13 + random.nextInt(11), 1 + random.nextInt(58), "PM"));
        }

        // Minute less than 0
        for (int i = 0; i < 5; i++) {
            times.add(new Time(1 + random.nextInt(11), -random.nextInt(59) - 1, "AM"));
        }

        // Minute greater than 59 (fixed to avoid conversion to valid time)
        for (int i = 0; i < 5; i++) {
            times.add(new Time(1 + random.nextInt(11), 60 + random.nextInt(21), "PM"));
        }

        // Meridian not equal to "AM" or "PM"
        for (int i = 0; i < 5; i++) {
            times.add(new Time(1 + random.nextInt(11), 1 + random.nextInt(58), random.nextBoolean() ? "XX" : "YY"));
        }

        return times;
    }

    /**
     * Writes the generated time data to a JSON file.
     * 
     * @param validTimes   A list of valid Time objects.
     * @param invalidTimes A list of invalid Time objects.
     * @param filePath     The path to the file where the data will be written.
     */
    private static void writeTimesToFile(List<Time> validTimes, List<Time> invalidTimes, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("{\n");

            // Write validTimes array
            writer.write("\"validTimes\": [\n");
            for (int i = 0; i < validTimes.size(); i++) {
                writer.write("  " + validTimes.get(i).toJson());
                if (i < validTimes.size() - 1) {
                    writer.write(",\n");
                }
            }
            writer.write("\n],\n");

            // Write invalidTimes array
            writer.write("\"invalidTimes\": [\n");
            for (int i = 0; i < invalidTimes.size(); i++) {
                writer.write("  " + invalidTimes.get(i).toJson());
                if (i < invalidTimes.size() - 1) {
                    writer.write(",\n");
                }
            }
            writer.write("\n]\n");

            writer.write("}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Time {
        int hour;
        int minute;
        String meridian;

        /**
         * Constructs a Time object.
         * 
         * @param hour     The hour part of the time.
         * @param minute   The minute part of the time.
         * @param meridian The meridian indicator ("AM" or "PM").
         */
        public Time(int hour, int minute, String meridian) {
            this.hour = hour;
            this.minute = minute;
            this.meridian = meridian;
        }

        /**
         * Converts the Time object to a JSON string format.
         * 
         * @return A string representation of the Time object in JSON format.
         */
        public String toJson() {
            // Adjust hour formatting to not include leading zero if hour is less than 10
            String formattedHour = (hour < 10) ? String.valueOf(hour) : String.format("%02d", hour % 24);
            String formattedMinute = String.format("%02d", minute);

            // For meridian, ensure it's either "AM", "PM", or the invalid indicator "XX"
            String meridianFormatted = "AM".equals(meridian) || "PM".equals(meridian) ? meridian : "XX";
            return String.format("\"%s:%s %s\"", formattedHour, formattedMinute, meridianFormatted);
        }

    }
}
