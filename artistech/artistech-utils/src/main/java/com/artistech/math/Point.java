/*
 * Copyright 2015-2016 ArtisTech, Inc.
 */
package com.artistech.math;

import java.io.Serializable;

/**
 * Represents a point.
 *
 * @author matta
 * @param <T>
 */
public class Point<T> implements Serializable {

    private T _x;
    private T _y;

    /**
     * Constructor.
     *
     * @param x
     * @param y
     */
    public Point(T x, T y) {
        _x = x;
        _y = y;
    }

    /**
     * Get X.
     *
     * @return
     */
    public T getX() {
        return _x;
    }

    /**
     * Get Y.
     *
     * @return
     */
    public T getY() {
        return _y;
    }

    /**
     * Set X.
     *
     * @param value
     */
    public void setX(T value) {
        _x = value;
    }

    /**
     * Set Y.
     *
     * @param value
     */
    public void setY(T value) {
        _y = value;
    }

    /**
     * Convert to String.
     *
     * @return
     */
    @Override
    public String toString() {
        return _x.getClass().getName() + ": (" + _x.toString() + ", " + _y.toString() + ")";
    }

}
