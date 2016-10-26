/*
 * Copyright 2015-2016 ArtisTech, Inc.
 */
package com.artistech.math;

/**
 * Represents strange attractor function.
 *
 * @author matta
 */
public class StrangeAttactor {

    private double _x;
    private double _y;

    /**
     * Constructor.
     *
     * @param x
     * @param y
     */
    public StrangeAttactor(double x, double y) {
        _x = x;
        _y = y;
    }

    /**
     * Constructor.
     *
     * @param pt
     */
    public StrangeAttactor(PointD pt) {
        this(pt.getX(), pt.getY());
    }

    /**
     * Get the next point.
     *
     * @return
     */
    public PointD next() {
        double x = _y + 1 - (1.4 * Math.pow(_x, 2));
        double y = 0.3 * _x;

        _x = x;
        _y = y;

        return new PointD(x, y);
    }
}
