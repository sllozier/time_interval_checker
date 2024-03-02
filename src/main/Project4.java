package main;

/**
 * Name: Sarah L. Lozier
 * Class: CMSC 215 - 6380
 * Project: Project 4
 * Date: March 5th, 2024
 * Description: Project4 class implements a GUI interface for comparing time
 * intervals and checking if a given time is within those intervals.
 * This class provides functionality through a user interface with text fields
 * for inputting start and end times
 * of two intervals, a field for entering a time to check, and buttons for
 * performing the checks.
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Project4 extends Application {

    private TextField textField1;
    private TextField textField2;
    private TextField textField3;
    private TextField textField4;
    private TextField timeToCheckField;
    private TextField overlapOutputField;

    /**
     * Starts the application and sets up the primary stage with its scene and
     * layout.
     * 
     * @param primaryStage the primary stage for this application
     */
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        GridPane gridPane = createGridPane();
        root.setCenter(gridPane);

        Scene scene = new Scene(root, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Time Interval Checker");
        primaryStage.show();
    }

    /**
     * Creates and configures the main grid pane layout including labels, fields,
     * and buttons.
     * 
     * @return the configured GridPane instance
     */
    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        createLabels(gridPane);
        createFields(gridPane);
        createButtons(gridPane);

        return gridPane;
    }

    /**
     * Creates and places labels in the grid pane.
     * 
     * @param gridPane the GridPane to add labels to
     */
    private void createLabels(GridPane gridPane) {

        // Create an array of strings to be used in each label
        String[] labels = { "Start Time", "End Time", "Time Interval 1", "Time Interval 2", "Time to Check" };

        // Create arrays to hold the placement values of each label in the grid pane
        int[] rows = { 0, 0, 1, 2, 5 };
        int[] cols = { 1, 2, 0, 0, 0 };

        // Loop over the labels array
        for (int i = 0; i < labels.length; i++) {

            // Create a new label for each string in the labels array
            Label label = new Label(labels[i]);

            // Set font size for each label
            label.setFont(Font.font("Arial", 12));

            // Create a stack pane to assist in laying out our labels in order and add them
            StackPane stackPane = new StackPane();
            stackPane.getChildren().add(label);

            // Align and place the stack pane appropriately in the grid pane
            StackPane.setAlignment(label, Pos.CENTER);

            // Add stack pane to grid pane with proper placements and span values
            gridPane.add(stackPane, cols[i], rows[i]);
        }
    }

    /**
     * Creates and configures text fields for user input, adding them to the grid
     * pane.
     * 
     * @param gridPane the GridPane to add fields to
     */
    private void createFields(GridPane gridPane) {

        // Create text fields
        textField1 = new TextField();
        textField2 = new TextField();
        textField3 = new TextField();
        textField4 = new TextField();
        timeToCheckField = new TextField();
        overlapOutputField = new TextField();

        // Special settings for the output field
        overlapOutputField.setEditable(false);
        overlapOutputField.setMaxWidth(Double.MAX_VALUE);

        // Add focus listeners to the editable fields
        addFocusListeners(textField1);
        addFocusListeners(textField2);
        addFocusListeners(textField3);
        addFocusListeners(textField4);
        addFocusListeners(timeToCheckField);

        // Add each text field to the grid pane with proper placements and span values
        gridPane.add(textField1, 1, 1);
        gridPane.add(textField2, 2, 1);
        gridPane.add(textField3, 1, 2);
        gridPane.add(textField4, 2, 2);
        gridPane.add(timeToCheckField, 1, 5, 2, 1);
        gridPane.add(overlapOutputField, 0, 7, 3, 1);
    }

    /**
     * Creates and adds buttons to the grid pane, setting their actions.
     * 
     * @param gridPane the GridPane to add buttons to
     */
    private void createButtons(GridPane gridPane) {

        // Create each button
        Button compareIntervalsButton = new Button("Compare Intervals");
        Button checkTimeButton = new Button("Check Time");
        Button clearButton = new Button("Clear Fields");

        // Set button borders
        compareIntervalsButton.setBorder(new Border(
                new BorderStroke(Color.ROYALBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
        checkTimeButton.setBorder(new Border(
                new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
        clearButton.setBorder(new Border(
                new BorderStroke(Color.FIREBRICK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));

        // Make the button fill the width
        compareIntervalsButton.setMaxWidth(Double.MAX_VALUE);
        checkTimeButton.setMaxWidth(Double.MAX_VALUE);
        clearButton.setMaxWidth(Double.MAX_VALUE);

        // Add hover effect to each button
        addHoverEffects(compareIntervalsButton);
        addHoverEffects(checkTimeButton);
        addHoverEffects(clearButton);

        // Button event handlers
        compareIntervalsButton.setOnAction(
                event -> handleCompareIntervalsButtonAction(textField1, textField2, textField3, textField4,
                        overlapOutputField, true));
        checkTimeButton.setOnAction(
                event -> handleCheckTimeButtonAction(textField1, textField2, textField3, textField4, timeToCheckField,
                        overlapOutputField,
                        true));
        clearButton.setOnAction(event -> handleClearButtonAction());

        // Add each button to the grid pane with proper placements and span values
        gridPane.add(compareIntervalsButton, 0, 3, 3, 1);
        gridPane.add(checkTimeButton, 0, 6, 3, 1);
        gridPane.add(clearButton, 2, 8);
    }

    /**
     * Compares two time intervals inputted by the user and displays the result.
     * This method handles the action for the "Compare Intervals" button.
     * 
     * @param textField1         First start time input field
     * @param textField2         First end time input field
     * @param textField3         Second start time input field
     * @param textField4         Second end time input field
     * @param overlapOutputField Field to display the result of comparison
     * @param displayAlerts      Whether to display alerts for invalid input
     */
    public void handleCompareIntervalsButtonAction(TextField textField1, TextField textField2, TextField textField3,
            TextField textField4, TextField overlapOutputField, boolean displayAlerts) {
        // Retrieve the input values for both intervals
        String startTimeStr1 = textField1.getText().trim();
        String endTimeStr1 = textField2.getText().trim();
        String startTimeStr2 = textField3.getText().trim();
        String endTimeStr2 = textField4.getText().trim();

        // Validate input formats
        if (!isValidTimeFormat(startTimeStr1) || !isValidTimeFormat(endTimeStr1) ||
                !isValidTimeFormat(startTimeStr2) || !isValidTimeFormat(endTimeStr2)) {
            throw new IllegalArgumentException("Invalid time format. Please use 'HH:MM AM/PM'.");
        }

        try {
            // Parse the input strings into Time objects
            Time interval1Start = new Time(startTimeStr1);
            Time interval1End = new Time(endTimeStr1);
            Time interval2Start = new Time(startTimeStr2);
            Time interval2End = new Time(endTimeStr2);

            // Create Interval objects for both intervals
            Interval<Time> interval1 = new Interval<>(interval1Start, interval1End);
            Interval<Time> interval2 = new Interval<>(interval2Start, interval2End);

            // Step 1: Check for Overlapping Intervals
            boolean overlap = interval1.overlaps(interval2) || interval2.overlaps(interval1);
            // boolean overlap2 = interval2.overlaps(interval1);

            // Step 2: Check for Subintervals
            boolean subinterval1 = interval1.subinterval(interval2);
            boolean subinterval2 = interval2.subinterval(interval1);

            // Check for subintervals first
            if (subinterval1) {
                overlapOutputField.setText("Interval 2 is a sub-interval of interval 1");
            } else if (subinterval2) {
                overlapOutputField.setText("Interval 1 is a sub-interval of interval 2");
            } else if (overlap) {
                // Then check for overlapping if no subinterval relationship exists
                overlapOutputField.setText("The intervals overlap");
            } else {
                overlapOutputField.setText("The intervals are disjoint");
            }

        } catch (InvalidTime e) {
            if (displayAlerts) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid input");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            } else {
                // Log the error instead of showing an alert
                System.err.println("Invalid time format or values. Use HH:MM AM/PM. " + e.getMessage());
            }
        } catch (IllegalArgumentException e) {
            if (displayAlerts) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid input");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            } else {
                // Log the error instead of showing an alert
                System.err.println("Invalid time format or values. Use HH:MM AM/PM. " + e.getMessage());
            }
        }
    }

    /**
     * Checks if a given time is within the inputted time intervals and displays the
     * result. This method handles the action for the "Check Time" button.
     * 
     * @param textField1         First interval start time input field
     * @param textField2         First interval end time input field
     * @param textField3         Second interval start time input field
     * @param textField4         Second interval end time input field
     * @param timeToCheckField   Field to input the time to check
     * @param overlapOutputField Field to display the result
     * @param displayAlerts      Whether to display alerts for invalid input
     */
    public void handleCheckTimeButtonAction(TextField textField1, TextField textField2, TextField textField3,
            TextField textField4, TextField timeToCheckField, TextField overlapOutputField, boolean displayAlerts) {
        // Retrieve time to check from the text field
        String timeToCheckStr = timeToCheckField.getText().trim();

        // Check if the field is empty
        if (timeToCheckStr.isEmpty()) {
            overlapOutputField.setText("Please enter a time to check.");
            return;
        }

        if (!isValidTimeFormat(timeToCheckStr)) {
            throw new IllegalArgumentException("Invalid time format. Please use 'HH:MM AM/PM'.");
        }

        // Parse the time to check
        try {
            Time timeToCheck = new Time(timeToCheckStr);

            // Retrieve start and end times from text fields
            String startTimeStr1 = textField1.getText().trim();
            String endTimeStr1 = textField2.getText().trim();
            String startTimeStr2 = textField3.getText().trim();
            String endTimeStr2 = textField4.getText().trim();

            // Parse start and end times of intervals
            Time startTime1 = new Time(startTimeStr1);
            Time endTime1 = new Time(endTimeStr1);
            Time startTime2 = new Time(startTimeStr2);
            Time endTime2 = new Time(endTimeStr2);

            // Create Interval objects
            Interval<Time> interval1 = new Interval<>(startTime1, endTime1);
            Interval<Time> interval2 = new Interval<>(startTime2, endTime2);

            // Check if the time is within the intervals
            boolean inInterval1 = interval1.within(timeToCheck);
            boolean inInterval2 = interval2.within(timeToCheck);

            // Generate output message based on the results
            if (inInterval1 && inInterval2) {
                overlapOutputField.setText("Both intervals contain the time " + timeToCheck);
            } else if (inInterval1) {
                overlapOutputField.setText("Only interval 1 contains the time " + timeToCheck);
            } else if (inInterval2) {
                overlapOutputField.setText("Only interval 2 contains the time " + timeToCheck);
            } else {
                overlapOutputField.setText("Neither interval contains the time " + timeToCheck);
            }
        } catch (InvalidTime e) {
            if (displayAlerts) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid input");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            } else {
                // Log the error instead of showing an alert
                System.err.println("Invalid time format or values. Use HH:MM AM/PM. " + e.getMessage());
            }
        } catch (IllegalArgumentException e) {
            if (displayAlerts) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid input");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            } else {
                // Log the error instead of showing an alert
                System.err.println("Invalid time format or values. Use HH:MM AM/PM. " + e.getMessage());
            }
        }
    }

    /**
     * Handles the action for the "Clear Fields" button.
     * Clears all input and output fields.
     */
    private void handleClearButtonAction() {
        // Clear all text fields
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        timeToCheckField.setText("");
        overlapOutputField.setText("");
    }

    /**
     * Validates the format of the input time string.
     * 
     * @param time the time string to validate
     * @return true if the time format is valid, false otherwise
     */
    private boolean isValidTimeFormat(String time) {
        // Regex to match valid time formats like "10:30 AM" (with optional leading and
        // trailing spaces)
        // It does not allow extra spaces within the time itself
        String regex = "^\\s*(\\d{1,2}):(\\d{2})\\s+(AM|PM)\\s*$";
        return time.matches(regex);
    }

    /**
     * Adds focus listeners to text fields to highlight them when focused.
     * 
     * @param textField the TextField to add a focus listener to
     */
    private void addFocusListeners(TextField textField) {
        // Set default border
        textField.setBorder(
                new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));

        // Add focus listener to change border
        textField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (isNowFocused) {
                textField.setBorder(new Border(
                        new BorderStroke(Color.LIGHTBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
            } else {
                textField.setBorder(new Border(
                        new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
            }
        });
    }

    /**
     * Adds hover effects to buttons, changing their border color when hovered.
     * 
     * @param control the Control (Button) to add hover effects to
     */
    private void addHoverEffects(Control control) {
        // Add hover effect to change border color
        control.setOnMouseEntered(e -> {
            Button button = (Button) control;
            if (button.getText().equals("Clear Fields")) {
                control.setBorder(new Border(new BorderStroke(Color.FIREBRICK.brighter(), BorderStrokeStyle.SOLID,
                        null, new BorderWidths(1))));
            } else if (button.getText().equals("Check Time")) {
                control.setBorder(new Border(new BorderStroke(Color.GREEN.brighter(), BorderStrokeStyle.SOLID, null,
                        new BorderWidths(1))));
            } else if (button.getText().equals("Compare Intervals")) {
                control.setBorder(new Border(new BorderStroke(Color.ROYALBLUE.brighter(), BorderStrokeStyle.SOLID, null,
                        new BorderWidths(1))));
            }
        });
        // Reset border color on mouse exit
        control.setOnMouseExited(e -> {
            Button button = (Button) control;
            if (button.getText().equals("Clear Fields")) {
                control.setBorder(new Border(
                        new BorderStroke(Color.FIREBRICK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
            } else if (button.getText().equals("Check Time")) {
                control.setBorder(new Border(
                        new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
            } else if (button.getText().equals("Compare Intervals")) {
                control.setBorder(new Border(
                        new BorderStroke(Color.ROYALBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
            }
        });

    }

    public static void main(String[] args) {
        launch(args);
    }
}