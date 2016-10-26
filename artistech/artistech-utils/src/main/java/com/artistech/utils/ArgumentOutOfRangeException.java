/*
 * Copyright 2015-2016 ArtisTech, Inc.
 */
package com.artistech.utils;

/**
 * Exception for when a value is out of the acceptable range.
 *
 * Use in lat/lon
 *
 * @author matta
 */
public class ArgumentOutOfRangeException extends Exception {

    private final String _argument;

    /**
     * Constructor.
     *
     * @param argument
     * @param message
     */
    public ArgumentOutOfRangeException(String argument, String message) {
        super(message);
        _argument = argument;
    }

    /**
     * Get the argument that caused the exception.
     *
     * @return
     */
    public String getArgument() {
        return _argument;
    }
}
