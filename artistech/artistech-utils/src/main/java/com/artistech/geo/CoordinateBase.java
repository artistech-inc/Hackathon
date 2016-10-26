/*
 * Copyright 2015-2016 ArtisTech, Inc.
 */
package com.artistech.geo;

import com.artistech.math.AngleMeasure;
import com.artistech.utils.ArgumentOutOfRangeException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;

/**
 * Base class for a Coordinate point.
 *
 * @author matta
 */
public abstract class CoordinateBase implements Serializable {

    protected int _degree;
    protected double _minutes;
    protected int factor = 1;

    /**
     *
     * @param degrees
     */
    public CoordinateBase(double degrees) {
        setDegrees(degrees);
    }

    /**
     *
     * @param degree
     * @param minutes
     * @throws ArgumentOutOfRangeException
     */
    public CoordinateBase(int degree, double minutes) throws ArgumentOutOfRangeException {
        setDegrees(degree);
        if (minutes >= 1 || minutes < 0) {
            throw new ArgumentOutOfRangeException("minutes", "minutes cannot be greater than or equal to 1 or less than 0");
        }
        _minutes = minutes;
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public int getWholeDegree() {
        return Math.abs(_degree) * factor;
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public double getMinutes() {
        return _minutes;
    }

    /**
     *
     * @return
     */
    public final double getDegrees() {
        double factor2 = (double) this.factor;
        return factor2 * (Math.abs((double) _degree) + _minutes);
    }

    /**
     *
     * @param value
     */
    public final void setDegrees(double value) {
        factor = value < 0 ? -1 : 1;
        _degree = Math.abs((int) Math.floor(Math.abs(value)));
        _minutes = Math.abs(value) - Math.abs(_degree);
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public double getRadians() {
        return AngleMeasure.deg2rad(this.getDegrees());
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public abstract CardinalPoints getCardinalMark();
}
