/*
 * Copyright 2015-2016 ArtisTech, Inc.
 */
package com.artistech.geo;

import com.artistech.math.PointF;

/**
 * Class providing functionality for mapping a screen point into world space.
 *
 * @author matta
 */
public class GridConversion {

    private GridConversionPoint min;
    private GridConversionPoint max;

    /**
     * Constructor.
     *
     * @param min
     * @param max
     */
    public GridConversion(GridConversionPoint min, GridConversionPoint max) {
        this.min = min;
        this.max = max;
    }

    /**
     * Constructor.
     */
    public GridConversion() {

    }

    /**
     * Get the minimum conversion point.
     *
     * @return
     */
    public final GridConversionPoint getMin() {
        return min;
    }

    /**
     * Set the minimum conversion point.
     *
     * @param value
     */
    public final void setMin(GridConversionPoint value) {
        min = value;
    }

    /**
     * Get the maximum conversion point.
     *
     * @return
     */
    public final GridConversionPoint getMax() {
        return max;
    }

    /**
     * Set the maximum conversion point.
     *
     * @param value
     */
    public final void setMax(GridConversionPoint value) {
        max = value;
    }

    /**
     * Convert a screen point to world coordinates.
     *
     * @param pt
     * @return
     */
    public Coordinate convert(PointF pt) {
        return GridConversionPoint.convert(min, max, pt);
    }

    /**
     * Convert a world coordinate to screen point.
     *
     * @param pt
     * @return
     */
    public PointF convert(Coordinate pt) {
        return GridConversionPoint.convert(min, max, pt);
    }
}
