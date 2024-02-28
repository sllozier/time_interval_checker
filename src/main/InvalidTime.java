package main;

/**
 * Name: Sarah L. Lozier
 * Class: CMSC 215 - 6380
 * Project: Project 4
 * Date: March 5th, 2024
 * Description: This exception class represents an invalid time input exception.
 */
public class InvalidTime extends Exception {
    /**
     * Constructs an InvalidTime object with the specified message.
     * 
     * @param message the detail message
     */
    public InvalidTime(String message) {
        super(message);
    }
}
