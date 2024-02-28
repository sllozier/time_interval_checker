package main;

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
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Name: Sarah L. Lozier
 * Class: CMSC 215 - 6380
 * Project: Project 4
 * Date: March 5th, 2024
 * Description: Project4 class implements a GUI interface for comparing time
 * intervals and checking if a given time is within the intervals.
 * It contains two buttons: "Compare Intervals" and "Check Time" which perform
 * the respective operations and display the results.
 * The GUI layout includes text fields for inputting start and end times of two
 * intervals, a text field for entering a time to check,
 * and a label to display the output messages.
 */
public class Project4 extends Application {

    private TextField textField1;
    private TextField textField2;
    private TextField textField3;
    private TextField textField4;
    private TextField timeToCheckField;
    private TextField intervalInfoField;
    private TextField overlapOutputField;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        GridPane gridPane = createGridPane();
        root.setCenter(gridPane);

        Scene scene = new Scene(root, 500, 350);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Time Interval Checker");
        primaryStage.show();
    }

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

    private void createLabels(GridPane gridPane) {
        String[] labels = { "Start Time", "End Time", "Time Interval 1", "Time Interval 2", "Time to Check" };
        int[] rows = { 0, 0, 1, 2, 5 };
        int[] cols = { 1, 2, 0, 0, 0 };
        for (int i = 0; i < labels.length; i++) {
            Label label = new Label(labels[i]);
            label.setFont(Font.font("Arial", 12)); // Set font size if needed
            StackPane stackPane = new StackPane();
            stackPane.getChildren().add(label);
            StackPane.setAlignment(label, Pos.CENTER);
            GridPane.setConstraints(stackPane, cols[i], rows[i]);
            gridPane.getChildren().add(stackPane);
        }
    }

    private void createFields(GridPane gridPane) {
        textField1 = new TextField();
        addFocusListeners(textField1);
        GridPane.setConstraints(textField1, 1, 1);
        textField2 = new TextField();
        addFocusListeners(textField2);
        GridPane.setConstraints(textField2, 2, 1);
        textField3 = new TextField();
        addFocusListeners(textField3);
        GridPane.setConstraints(textField3, 1, 2);
        textField4 = new TextField();
        addFocusListeners(textField4);
        GridPane.setConstraints(textField4, 2, 2);
        intervalInfoField = new TextField();
        intervalInfoField.setEditable(false);
        GridPane.setConstraints(intervalInfoField, 0, 4);
        GridPane.setColumnSpan(intervalInfoField, 3);
        intervalInfoField.setMaxWidth(Double.MAX_VALUE);
        timeToCheckField = new TextField();
        addFocusListeners(timeToCheckField);
        GridPane.setConstraints(timeToCheckField, 1, 5, 2, 1);
        overlapOutputField = new TextField();
        overlapOutputField.setEditable(false);
        GridPane.setConstraints(overlapOutputField, 0, 7);
        GridPane.setColumnSpan(overlapOutputField, 3);
        overlapOutputField.setMaxWidth(Double.MAX_VALUE);
        gridPane.getChildren().addAll(textField1, textField2, textField3, textField4, timeToCheckField,
                intervalInfoField, overlapOutputField);
    }

    private void createButtons(GridPane gridPane) {
        Button compareIntervalsButton = new Button("Compare Intervals");
        compareIntervalsButton.setBorder(new Border(
                new BorderStroke(Color.ROYALBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
        addHoverEffects(compareIntervalsButton);
        compareIntervalsButton.setOnAction(event -> handleCompareIntervalsButtonAction());
        GridPane.setConstraints(compareIntervalsButton, 0, 3);
        GridPane.setColumnSpan(compareIntervalsButton, 3);
        compareIntervalsButton.setMaxWidth(Double.MAX_VALUE); // Make the button fill the width

        Button checkTimeButton = new Button("Check Time");
        checkTimeButton.setBorder(new Border(
                new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
        addHoverEffects(checkTimeButton);
        checkTimeButton.setOnAction(event -> handleCheckTimeButtonAction());
        GridPane.setConstraints(checkTimeButton, 0, 6);
        GridPane.setColumnSpan(checkTimeButton, 3);
        checkTimeButton.setMaxWidth(Double.MAX_VALUE); // Make the button fill the width

        Button clearButton = new Button("Clear Fields");
        clearButton.setBorder(new Border(
                new BorderStroke(Color.FIREBRICK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
        addHoverEffects(clearButton);
        clearButton.setOnAction(event -> handleClearButtonAction());
        GridPane.setConstraints(clearButton, 0, 8);
        GridPane.setColumnSpan(clearButton, 3);
        clearButton.setMaxWidth(Double.MAX_VALUE); // Make the button fill the width
        gridPane.getChildren().addAll(compareIntervalsButton, checkTimeButton, clearButton);

    }

    private void handleCompareIntervalsButtonAction() {
        // Retrieve start and end times from text fields
        String startTimeStr1 = textField1.getText().trim();
        String endTimeStr1 = textField2.getText().trim();
        String startTimeStr2 = textField3.getText().trim();
        String endTimeStr2 = textField4.getText().trim();

        // Check if any of the fields are empty
        if (startTimeStr1.isEmpty() || endTimeStr1.isEmpty() ||
                startTimeStr2.isEmpty() || endTimeStr2.isEmpty()) {
            intervalInfoField.setText("Please enter start and end times for both intervals.");
            return;
        }

        // Parse start and end times
        try {
            Time startTime1 = new Time(startTimeStr1);
            Time endTime1 = new Time(endTimeStr1);
            Time startTime2 = new Time(startTimeStr2);
            Time endTime2 = new Time(endTimeStr2);

            System.out.println("INTERVAL 1:" + startTimeStr1 + endTimeStr1);
            System.out.println("INTERVAL 2:" + startTimeStr2 + endTimeStr2);

            // Create Interval objects
            Interval<Time> interval1 = new Interval<>(startTime1, endTime1);
            Interval<Time> interval2 = new Interval<>(startTime2, endTime2);

            // Compare intervals and set appropriate message
            if (interval1.subinterval(interval2)) {
                intervalInfoField.setText("Interval 1 is a sub-interval of interval 2");
            } else if (interval2.subinterval(interval1)) {
                intervalInfoField.setText("Interval 2 is a sub-interval of interval 1");
            } else if (interval1.overlaps(interval2)) {
                intervalInfoField.setText("The intervals overlap");
            } else {
                intervalInfoField.setText("The intervals are disjoint");
            }
        } catch (InvalidTime e) {
            intervalInfoField.setText("Invalid time format or values. Use HH:MM AM/PM");
        }
    }

    private void handleCheckTimeButtonAction() {
        // Retrieve time to check from the text field
        String timeToCheckStr = timeToCheckField.getText().trim();

        // Check if the field is empty
        if (timeToCheckStr.isEmpty()) {
            overlapOutputField.setText("Please enter a time to check.");
            return;
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
            overlapOutputField.setText("Invalid time format or values. Use HH:MM AM/PM");
        }
    }

    private void handleClearButtonAction() {
        // Implement action for Check Time Button
    }

    // Method to add focus listeners to highlight text fields when focused
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

    // Method to add hover effects to buttons and combo boxes
    private void addHoverEffects(Control control) {
        // Add hover effect to change border color
        control.setOnMouseEntered(e -> {
            Button button = (Button) control; // Cast to Button
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
            Button button = (Button) control; // Cast to Button
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