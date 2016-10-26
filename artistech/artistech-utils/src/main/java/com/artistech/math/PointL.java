/*
 * Copyright 2015-2016 ArtisTech, Inc.
 */
package com.artistech.math;

/**
 * Represents a point of long values.
 *
 * @author matta
 */
public class PointL extends Point<Long> {

    /**
     * Constructor.
     */
    public PointL() {
        this(0, 0);
    }

    /**
     * Constructor.
     *
     * @param pt
     */
    public PointL(java.awt.Point pt) {
        this((long) pt.x, (long) pt.y);
    }

    /**
     * Constructor.
     *
     * @param x
     * @param y
     */
    public PointL(long x, long y) {
        super(x, y);
    }

    /**
     * Calculate distance.
     *
     * @param other
     * @return
     */
    public double DistanceTo(PointL other) {
        return Math.sqrt(Math.pow(this.getX() - other.getX(), 2) + Math.pow(this.getY() - other.getY(), 2));
    }
}
