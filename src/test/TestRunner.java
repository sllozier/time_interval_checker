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

    // Launch the JavaFX application
    public static void main(String[] args) {

        launch(args);
    }

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

        // Restore standard error
        System.setErr(originalErr);

        // Footer to indicate completion
        System.out.println("\nAll tests completed.");

        // Close the JavaFX application
        primaryStage.close();
        // Terminate the JavaFX application thread
        Platform.exit();
    }

    // Executes and reports on Time class tests
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

    // Executes and reports on InvalidTime class tests
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

    // Executes and reports on Interval class tests
    private static void runIntervalTest() {
        System.out.println("|+-- InervalTest");

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

    // Executes and reports on Project3 class tests
    // private static void runProject3Test() {
    // System.out.println("|+-- Project3Test");

    // // Execute and display results for Project3 specific tests
    // double executionTime1 = executeTest("testPositiveScenario");
    // double executionTime2 = executeTest("testTextFieldExceptions");
    // double executionTime3 = executeTest("testComboBoxExceptions");
    // boolean result1 = Project3Test.testPositiveScenario();
    // boolean result2 = Project3Test.testTextFieldExceptions();
    // boolean result3 = Project3Test.testComboBoxExceptions();

    // // Report on each test's execution time and result
    // System.out.println("|");

    // // Calculate and display execution times
    // displayExecutionTime("testPositiveScenario", executionTime1, result1);
    // displayExecutionTime("testTextFieldExceptions", executionTime2, result2);
    // displayExecutionTime("testComboBoxExceptions", executionTime3, result3);
    // System.out.println("|");
    // }

    // Executes a test and returns the execution time
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
        }

        // Get the current time after the test
        long endTime = System.currentTimeMillis();
        // Calculate execution time in seconds
        double executionTime = (endTime - startTime) / 1000.0;
        return executionTime;
    }

    // Displays the execution time and result of a test
    private static void displayExecutionTime(String testName, double executionTime, boolean result) {
        if (executionTime >= 0) {
            String resultString = (result == true) ? ANSI_GREEN + "[OK]" : ANSI_RED + "[FAILED]";
            System.out.printf("| +-- %s %s - %.3f ss%s\n", resultString, testName, Math.abs(executionTime), ANSI_RESET);
        }
    }

}
