package org.example.Exceptions;

public class SelfContact extends RuntimeException {
    public SelfContact() {
        super("Stop Hitting Yourself");
    }
}
