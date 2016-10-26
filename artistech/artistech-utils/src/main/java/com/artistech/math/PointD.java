/*
 * Copyright 2015-2016 ArtisTech, Inc.
 */
package com.artistech.math;

/**
 * Represents a point of double value.
 *
 * @author matta
 */
public class PointD extends Point<Double> {

    /**
     * Constructor.
     */
    public PointD() {
        this(0, 0);
    }

    /**
     * Constructor.
     *
     * @param pt
     */
    public PointD(java.awt.Point pt) {
        this((double) pt.x, (double) pt.y);
    }

    /**
     * Constructor.
     *
     * @param x
     * @param y
     */
    public PointD(double x, double y) {
        super(x, y);
    }

    /**
     * Calculate distance.
     *
     * @param other
     * @return
     */
    public double distanceTo(PointD other) {
        return Math.sqrt(Math.pow(this.getX() - other.getX(), 2) + Math.pow(this.getY() - other.getY(), 2));
    }
}
