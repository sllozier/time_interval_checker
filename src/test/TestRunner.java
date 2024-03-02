package test;

/**
 * Name: Sarah L. Lozier
 * Class: CMSC 215 - 6380
 * Project: Project 3
 * Date: February 20th, 2024
 * Description: TestRunner.java is a utility class designed to systematically
 * execute and report
 * on a series of unit tests covering the functionality of the TripCost,
 * and Project3 classes.
 */

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class TestRunner extends Application {
    // ANSI escape codes for text colors
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RED = "\u001B[31m";

    /**
     * Launches the JavaFX application to run the tests.
     * 
     * @param args Command line arguments passed to the application.
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
        // Suppress standard error
        PrintStream originalErr = System.err;
        System.setErr(new PrintStream(new ByteArrayOutputStream()));

        // Header for the test output
        System.out.println("-------------------------------------------------------");
        System.out.println(" T E S T S");
        System.out.println("-------------------------------------------------------");
        System.out.println("|");

        // Call test methods from different test classes
        runTimeTest();
        runInvalidTimeTest();
        runIntervalTest();
        runProject4Test();

        // Restore standard error
        System.setErr(originalErr);

        // Footer to indicate completion
        System.out.println("\nAll tests completed.");

        // Close the JavaFX application
        primaryStage.close();
        // Terminate the JavaFX application thread
        Platform.exit();
    }

    /**
     * Executes and reports on Time class tests. It displays the result and
     * execution time for each test.
     */
    private static void runTimeTest() {
        System.out.println("|+-- TimeTest");

        // Execute and display results for TripCost specific tests
        double executionTime1 = executeTest("testCreateTimeValid");
        double executionTime2 = executeTest("testCompareTime");
        double executionTime3 = executeTest("testCreateTimeInvalid");
        boolean result1 = TimeTest.testCreateTimeValid();
        boolean result2 = TimeTest.testCompareTime();
        boolean result3 = TimeTest.testCreateTimeInvalid();

        // Report on each test's execution time and result
        System.out.println("|");

        // Calculate and display execution times
        displayExecutionTime("testCreateTimeValid", executionTime1,
                result1);
        displayExecutionTime("testCompareTime", executionTime2, result2);
        displayExecutionTime("testCreateTimeInvalid", executionTime3, result3);
        System.out.println("|");
    }

    /**
     * Executes and reports on InvalidTime class tests. It displays the result and
     * execution time for each test.
     */
    private static void runInvalidTimeTest() {
        System.out.println("|+-- InvalidTimeTest");

        // Execute and display results for TripCost specific tests
        double executionTime1 = executeTest("testInvalidTimeWithMessage");
        boolean result1 = InvalidTimeTest.testInvalidTimeWithMessage();

        // Report on each test's execution time and result
        System.out.println("|");

        // Calculate and display execution times
        displayExecutionTime("testInvalidTimeWithMessage", executionTime1,
                result1);

        System.out.println("|");
    }

    /**
     * Executes and reports on Interval class tests. It displays the result and
     * execution time for each test.
     */
    private static void runIntervalTest() {
        System.out.println("|+-- IntervalTest");

        // Execute and display results for TripCost specific tests
        double executionTime1 = executeTest("testObjectWithinInterval");
        double executionTime2 = executeTest("testSubinterval");
        double executionTime3 = executeTest("testIntervalOverlaps");
        boolean result1 = IntervalTest.testObjectWithinInterval();
        boolean result2 = IntervalTest.testSubinterval();
        boolean result3 = IntervalTest.testIntervalOverlaps();

        // Report on each test's execution time and result
        System.out.println("|");

        // Calculate and display execution times
        displayExecutionTime("testObjectWithinInterval", executionTime1,
                result1);
        displayExecutionTime("testSubinterval", executionTime2, result2);
        displayExecutionTime("testIntervalOverlaps", executionTime3, result3);
        System.out.println("|");
    }

    /**
     * Executes and reports on Project4 class tests. It displays the result and
     * execution time for each test.
     */
    private static void runProject4Test() {
        System.out.println("|+-- Project4Test");

        // Execute and display results for TripCost specific tests
        double executionTime1 = executeTest("testValidInputs");
        double executionTime2 = executeTest("testInvalidInputs");
        boolean result1 = Project4Test.testValidInputs();
        boolean result2 = Project4Test.testInvalidInputs();

        // Report on each test's execution time and result
        System.out.println("|");

        // Calculate and display execution times
        displayExecutionTime("testValidInputs", executionTime1,
                result1);
        displayExecutionTime("testInvalidInputs", executionTime2, result2);
        System.out.println("|");
    }

    /**
     * Executes a specified test method and calculates its execution time.
     * 
     * @param testName The name of the test method to execute.
     * @return The execution time of the test method in seconds.
     */
    private static double executeTest(String testName) {
        // Record start time, execute test, then calculate duration
        long startTime = System.currentTimeMillis();
        boolean result = false;

        // Execute the corresponding test method
        switch (testName) {
            case "testCreateTimeValid":
                result = TimeTest.testCreateTimeValid();
                break;
            case "testCompareTime":
                result = TimeTest.testCompareTime();
                break;
            case "testCreateTimeInvalid":
                result = TimeTest.testCreateTimeInvalid();
                break;
            case "testInvalidTimeWithMessage":
                result = InvalidTimeTest.testInvalidTimeWithMessage();
                break;
            case "testObjectWithinInterval":
                result = IntervalTest.testObjectWithinInterval();
                break;
            case "testSubinterval":
                result = IntervalTest.testSubinterval();
                break;
            case "testIntervalOverlaps":
                result = IntervalTest.testIntervalOverlaps();
                break;
            case "testValidInputs":
                result = Project4Test.testValidInputs();
                break;
            case "testInvalidInputs":
                result = Project4Test.testInvalidInputs();
                break;
        }

        // Get the current time after the test
        long endTime = System.currentTimeMillis();
        // Calculate execution time in seconds
        double executionTime = (endTime - startTime) / 1000.0;
        return executionTime;
    }

    /**
     * Displays the execution time and result of a specified test.
     * 
     * @param testName      The name of the test method.
     * @param executionTime The execution time of the test method in seconds.
     * @param result        The result of the test (pass or fail).
     */
    private static void displayExecutionTime(String testName, double executionTime, boolean result) {
        if (executionTime >= 0) {
            String resultString = (result == true) ? ANSI_GREEN + "[OK]" : ANSI_RED + "[FAILED]";
            System.out.printf("| +-- %s %s - %.3f ss%s\n", resultString, testName, Math.abs(executionTime), ANSI_RESET);
        }
    }

}
