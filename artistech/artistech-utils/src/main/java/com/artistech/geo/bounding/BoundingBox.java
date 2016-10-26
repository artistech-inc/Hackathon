/*
 * Copyright 2015-2016 ArtisTech, Inc.
 */
package com.artistech.geo.bounding;

import com.artistech.utils.ArgumentOutOfRangeException;
import com.artistech.geo.Coordinate;
import com.artistech.geo.GridConversion;
import com.artistech.math.PointF;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of a simple bounding box.
 *
 * @author matta
 */
public class BoundingBox extends BoundingArea implements Serializable {

    private Coordinate _min;
    private Coordinate _max;

    /**
     * Constructor.
     */
    public BoundingBox() {
        this(Coordinate.MAX, Coordinate.MIN);
    }

    /**
     * Constructor.
     *
     * @param maxX
     * @param maxY
     * @param minX
     * @param minY
     * @throws ArgumentOutOfRangeException
     */
    public BoundingBox(double maxX, double maxY, double minX, double minY) throws ArgumentOutOfRangeException {
        this(new Coordinate(maxX, maxY), new Coordinate(minX, minY));
    }

    /**
     * Constructor.
     *
     * @param max
     * @param min
     */
    public BoundingBox(Coordinate max, Coordinate min) {
        try {
            double minX = Math.min(max.getLongitude().getDegrees(), min.getLongitude().getDegrees());
            double maxX = Math.max(max.getLongitude().getDegrees(), min.getLongitude().getDegrees());
            double minY = Math.min(max.getLatitude().getDegrees(), min.getLatitude().getDegrees());
            double maxY = Math.max(max.getLatitude().getDegrees(), min.getLatitude().getDegrees());

            _min = new Coordinate(minX, minY);
            _max = new Coordinate(maxX, maxY);
        } catch (ArgumentOutOfRangeException ex) {
            Logger.getLogger(BoundingBox.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Get the bounding min.
     *
     * @return
     */
    public Coordinate getMin() {
        return _min;
    }

    /**
     * Get the bounding max.
     *
     * @return
     */
    public Coordinate getMax() {
        return _max;
    }

    /**
     * Set the bounding min.
     *
     * @param value
     */
    public void setMin(Coordinate value) {
        _min = value;
    }

    /**
     * Set the bounding max.
     *
     * @param value
     */
    public void setMax(Coordinate value) {
        _max = value;
    }

    /**
     * Check to see if the specified coordinate is in the bounding box.
     *
     * @param coord
     * @return
     */
    @Override
    public boolean contains(Coordinate coord) {
        return coord.getLatitude().getDegrees() <= _max.getLatitude().getDegrees()
                && coord.getLatitude().getDegrees() >= _min.getLatitude().getDegrees()
                && coord.getLongitude().getDegrees() <= _max.getLongitude().getDegrees()
                && coord.getLongitude().getDegrees() >= _min.getLongitude().getDegrees();
    }

    /**
     * Get a random coordinate within the bounds.
     *
     * @return
     */
    @Override
    public Coordinate randomCoordinate() {
        return Coordinate.getRandomCoordinate(_min, _max);
    }

    /**
     * Create a copy of the box.
     *
     * @return
     */
    @Override
    public BoundingArea copy() {
        return new BoundingBox(this._max, this._min);
    }

    /**
     * Draw the box.
     *
     * @param g
     * @param gc
     */
    @Override
    public void draw(Graphics2D g, GridConversion gc) {
        PointF min = gc.convert(_min);
        PointF max = gc.convert(_max);

        int x = Math.round(min.getX());
        int y = Math.round(min.getY());
        java.awt.Point p1 = new java.awt.Point(x, y);
        x = Math.round(max.getX());
        y = Math.round(max.getY());
        java.awt.Point p2 = new java.awt.Point(x, y);

        g.drawLine(p1.x, p1.y, p2.x, p1.y);
        g.drawLine(p1.x, p1.y, p1.x, p2.y);
        g.drawLine(p2.x, p2.y, p2.x, p1.y);
        g.drawLine(p2.x, p2.y, p1.x, p2.y);
    }
}
