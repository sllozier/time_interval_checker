package test;

/**
 * Name: Sarah L. Lozier
 * Class: CMSC 215 - 6380
 * Project: Project 4
 * Date: March 5th, 2024
 * Description:This class is designed to test the functionality of the Project4 class. It includes
 * methods to verify that the application correctly handles both valid and invalid inputs for 
 * comparing time intervals and checking if a given time is within those intervals. The tests ensure 
 * that the application responds appropriately to different types of time inputs.
 */

import main.Project4;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Project4Test extends Application {

    /**
     * Main method to run all tests related to the Project4 class.
     * 
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initializes the JavaFX stage and runs all test suites, displaying their
     * results.
     * It overrides the JavaFX Application's start method.
     * 
     * @param primaryStage The primary stage for this application.
     */
    @Override
    public void start(Stage primaryStage) {
        // Initialize your JavaFX components here
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);

        // Output the results of the test
        System.out.println("Test Valid Inputs: " + (testValidInputs() ? "Passed" : "Failed"));
        System.out.println("Test Invalid Inputs: " + (testInvalidInputs() ? "Passed" : "Failed"));

        // Close the primaryStage to exit the JavaFX application
        primaryStage.close();
    }

    /**
     * Parses JSON data to extract either valid or invalid time strings for testing.
     * 
     * @param jsonData The JSON data as a string.
     * @param valid    Specifies whether to extract valid (true) or invalid (false)
     *                 times.
     * @return A list of time strings based on the specified criteria.
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
     * Tests the handling of valid inputs within the Project4 application.
     * Validates that the application correctly processes valid time intervals
     * without throwing exceptions.
     * 
     * @return true if the test passes (no exceptions thrown for valid inputs),
     *         false otherwise.
     */
    public static boolean testValidInputs() {
        // boolean passed = false;
        Path jsonFilePath = Paths.get("time.json");

        try {
            String jsonData = new String(Files.readAllBytes(jsonFilePath));
            // Assume true for valid times
            List<String> validTimes = parseJsonData(jsonData, true);

            Random random = new Random();
            if (validTimes.size() >= 4) {
                List<Integer> selectedIndexes = new ArrayList<>(4);

                // Generate four unique random indexes
                while (selectedIndexes.size() < 5) {
                    int newIndex = random.nextInt(validTimes.size());
                    if (!selectedIndexes.contains(newIndex)) {
                        selectedIndexes.add(newIndex);
                    }
                }

                // Simulate valid input
                TextField textField1 = new TextField(validTimes.get(selectedIndexes.get(0)));
                TextField textField2 = new TextField(validTimes.get(selectedIndexes.get(1)));
                TextField textField3 = new TextField(validTimes.get(selectedIndexes.get(2)));
                TextField textField4 = new TextField(validTimes.get(selectedIndexes.get(3)));
                TextField timeToCheckField = new TextField(validTimes.get(selectedIndexes.get(4)));
                TextField outputField = new TextField();

                // Create instance of Project4
                Project4 project4 = new Project4();

                project4.handleCompareIntervalsButtonAction(textField1, textField2, textField3, textField4, outputField,
                        true);
                project4.handleCheckTimeButtonAction(textField1, textField2, textField3,
                        textField4, timeToCheckField, outputField,
                        true);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Tests the handling of invalid inputs within the Project4 application.
     * Validates that the application correctly identifies and handles invalid
     * time intervals.
     * 
     * @return true if the test passes (appropriate handling of invalid inputs),
     *         false otherwise.
     */
    public static boolean testInvalidInputs() {

        Path jsonFilePath = Paths.get("time.json");

        try {
            String jsonData = new String(Files.readAllBytes(jsonFilePath));
            // Assume true for valid times
            List<String> invalidTimes = parseJsonData(jsonData, false);

            Random random = new Random();
            if (invalidTimes.size() >= 4) {
                List<Integer> selectedIndexes = new ArrayList<>(4);

                // Generate four unique random indexes
                while (selectedIndexes.size() < 5) {
                    int newIndex = random.nextInt(invalidTimes.size());
                    if (!selectedIndexes.contains(newIndex)) {
                        selectedIndexes.add(newIndex);
                    }
                }

                // Simulate valid input
                TextField textField1 = new TextField(invalidTimes.get(selectedIndexes.get(0)));
                TextField textField2 = new TextField(invalidTimes.get(selectedIndexes.get(1)));
                TextField textField3 = new TextField(invalidTimes.get(selectedIndexes.get(2)));
                TextField textField4 = new TextField(invalidTimes.get(selectedIndexes.get(3)));
                TextField timeToCheckField = new TextField(invalidTimes.get(selectedIndexes.get(4)));
                TextField outputField = new TextField();
                // Create instance of Project4
                Project4 project4 = new Project4();

                project4.handleCompareIntervalsButtonAction(textField1, textField2, textField3, textField4, outputField,
                        false);
                project4.handleCheckTimeButtonAction(textField1, textField2, textField3, textField4, timeToCheckField,
                        outputField,
                        false);
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }

    }
}
