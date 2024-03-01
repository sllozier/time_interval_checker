package test;

/**
 * Name: Sarah L. Lozier
 * Class: CMSC 215 - 6380
 * Project: Project 4
 * Date: March 5th, 2024
 * Description: 
 */

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateTimeData {
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

    // Generate random valid times
    private static List<Time> generateRandomTimes(int numTimes, boolean valid) {
        List<Time> times = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numTimes; i++) {
            int hour = 1 + random.nextInt(12);
            int minute = random.nextInt(60);
            String meridian = random.nextBoolean() ? "AM" : "PM";

            times.add(new Time(hour, minute, meridian));
        }

        return times;
    }

    // Generate 5 of each specific invalid time scenario
    private static List<Time> generateSpecificInvalidTimes() {
        List<Time> times = new ArrayList<>();
        Random random = new Random();

        // Hour less than 1, including negative values
        for (int i = 0; i < 5; i++) {
            int invalidHour = -1 * random.nextInt(13); // Generates a random negative number between -1 and -12
            times.add(new Time(invalidHour, 1 + random.nextInt(58), "AM"));
        }

        // Hour greater than 12
        for (int i = 0; i < 5; i++) {
            times.add(new Time(13 + random.nextInt(11), 1 + random.nextInt(58), "PM")); // 13 to 23
        }

        // Minute less than 0
        for (int i = 0; i < 5; i++) {
            times.add(new Time(1 + random.nextInt(11), -random.nextInt(59) - 1, "AM")); // -0
        }

        // Minute greater than 59 (fixed to avoid conversion to valid time)
        for (int i = 0; i < 5; i++) {
            times.add(new Time(1 + random.nextInt(11), 60 + random.nextInt(21), "PM")); // Directly using 60
        }

        // Meridian not equal to "AM" or "PM"
        for (int i = 0; i < 5; i++) {
            times.add(new Time(1 + random.nextInt(11), 1 + random.nextInt(58), random.nextBoolean() ? "XX" : "YY"));
        }

        return times;
    }

    // Write times data to a JSON file, including both valid and invalid times
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

        public Time(int hour, int minute, String meridian) {
            this.hour = hour;
            this.minute = minute;
            this.meridian = meridian;
        }

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
