/*
 * Copyright 2015-2016 ArtisTech, Inc.
 */
package com.artistech.geo.bounding;

import com.artistech.geo.Coordinate;
import com.artistech.geo.GridConversion;
import java.awt.Graphics2D;
import java.io.Serializable;

/**
 * Interface functionality for a bounding area.
 *
 * @author matta
 */
public abstract class BoundingArea implements Serializable {

    /**
     * Does the current bounding area contain the specified coordinate?
     *
     * @param coord
     * @return
     */
    public abstract boolean contains(Coordinate coord);

    /**
     * Get a random coordinate within the bounding area.
     *
     * @return
     */
    public abstract Coordinate randomCoordinate();

    /**
     * Create a copy of the current bounding area.
     *
     * @return
     */
    public abstract BoundingArea copy();

    /**
     * Draw the current bounding area.
     *
     * @param g
     * @param gc
     */
    public abstract void draw(Graphics2D g, GridConversion gc);
}
