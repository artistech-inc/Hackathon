/*
 * Copyright 2015-2016 ArtisTech, Inc.
 */
package com.artistech.geo;

import com.artistech.utils.ArgumentOutOfRangeException;

/**
 * Longitude is built to wrap around.
 *
 * @author matta
 */
public class Longitude extends CoordinateBase {

    public static final int MAX_LON = 180;
    public static final int MIN_LON = -180;

    /**
     * Default Constructor.
     */
    public Longitude() {
        this(0);
    }

    /**
     * Constructor.
     *
     * @param degrees
     */
    public Longitude(double degrees) {
        super(degrees);
        while (super.getDegrees() < MIN_LON * 2) {
            super.setDegrees(super.getDegrees() + (MAX_LON * 2));
        }
        while (super.getDegrees() > MAX_LON * 2) {
            super.setDegrees(super.getDegrees() - (MAX_LON * 2));
        }
        while (super.getDegrees() < MIN_LON) {
            super._degree = (int) Math.floor((super.getDegrees() + (MAX_LON * 2)));
            if (Math.abs(super._minutes) > 0) {
                super._minutes = 1.0 - Math.abs(super._minutes);
                _degree += super._degree < 0 ? 1 : 0;
                factor *= -1;
            }
        }
        while (super.getDegrees() > MAX_LON) {
            super._degree = (int) Math.floor((super.getDegrees() - (MAX_LON * 2)));
            if (Math.abs(super._minutes) > 0) {
                super._minutes = 1.0 - Math.abs(super._minutes);
                _degree += super._degree < 0 ? 1 : 0;
                factor *= -1;
            }
        }
    }

    /**
     * Constructor.
     *
     * @param degree_whole
     * @param minutes
     * @throws ArgumentOutOfRangeException
     */
    public Longitude(int degree_whole, double minutes) throws ArgumentOutOfRangeException {
        super(degree_whole, minutes);

        while (super.getDegrees() < MIN_LON) {
            super._degree = (int) Math.floor((super.getDegrees() + (MAX_LON * 2)));
        }
        while (super.getDegrees() > MAX_LON) {
            super._degree = (int) Math.floor((super.getDegrees() - (MAX_LON * 2)));
        }

        if (super.getDegrees() < MIN_LON || super.getDegrees() > MAX_LON) {
            throw new ArgumentOutOfRangeException("degrees", "Longitude values cannot be less than -180 or greater than 180");
        }
    }

    /**
     * Get the cardinal point.
     *
     * Basically the hemisphere.
     *
     * @return
     */
    @Override
    public CardinalPoints getCardinalMark() {
        return super.getDegrees() >= 0 ? CardinalPoints.E : CardinalPoints.W;
    }
}
