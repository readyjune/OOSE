package edu.curtin.matheval;

public class UserInputException extends Exception {
    public UserInputException(String msg) {
        super(msg);
    }

    public UserInputException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
