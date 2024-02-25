package com.apromore.enrollmentrest.exception;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ErrorDetailsTest {

    @Test
    public void testErrorDetailsConstructor() {
        Date timestamp = new Date();
        String message = "Error message";
        String details = "Error details";

        ErrorDetails errorDetails = new ErrorDetails(timestamp, message, details);

        assertNotNull(errorDetails);
        assertEquals(timestamp, errorDetails.getTimestamp());
        assertEquals(message, errorDetails.getMessage());
        assertEquals(details, errorDetails.getDetails());
    }

    @Test
    public void testGetterSetterMethods() {
        ErrorDetails errorDetails = new ErrorDetails();

        Date timestamp = new Date();
        errorDetails.setTimestamp(timestamp);
        assertEquals(timestamp, errorDetails.getTimestamp());

        String message = "Error message";
        errorDetails.setMessage(message);
        assertEquals(message, errorDetails.getMessage());

        String details = "Error details";
        errorDetails.setDetails(details);
        assertEquals(details, errorDetails.getDetails());
    }
}