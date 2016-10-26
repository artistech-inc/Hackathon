/*
 * Copyright 2015-2016 ArtisTech, Inc.
 */
package com.artistech.math;

/**
 * Represents a point of float values.
 *
 * @author matta
 */
public class PointF extends Point<Float> {

    /**
     * Constructor.
     */
    public PointF() {
        this(0, 0);
    }

    /**
     * Constructor.
     *
     * @param pt
     */
    public PointF(java.awt.Point pt) {
        this(pt.x, pt.y);
    }

    /**
     * Constructor.
     *
     * @param x
     * @param y
     */
    public PointF(float x, float y) {
        super(x, y);
    }

    /**
     * Calculate distance.
     *
     * @param other
     * @return
     */
    public double distanceTo(PointF other) {
        return Math.sqrt(Math.pow(this.getX() - other.getX(), 2) + Math.pow(this.getY() - other.getY(), 2));
    }
}
