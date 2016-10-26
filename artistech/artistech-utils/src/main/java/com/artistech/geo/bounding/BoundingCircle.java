/*
 * Copyright 2015-2016 ArtisTech, Inc.
 */
package com.artistech.geo.bounding;

import com.artistech.utils.ArgumentOutOfRangeException;
import com.artistech.geo.Coordinate;
import com.artistech.geo.DistanceMeasure;
import com.artistech.geo.DistanceUnit;
import com.artistech.geo.GridConversion;
import com.artistech.geo.Latitude;
import com.artistech.geo.Longitude;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of a simple bounding circle.
 *
 * @author matta
 */
public class BoundingCircle extends BoundingArea implements Serializable {

    private Coordinate _center;
    private DistanceMeasure _radius;

    /**
     * Constructor.
     */
    public BoundingCircle() {
        try {
            _center = new Coordinate(0, 0);
            _radius = new DistanceMeasure(0, DistanceUnit.KILOMETER);
        } catch (ArgumentOutOfRangeException ex) {
            Logger.getLogger(BoundingCircle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Constructor.
     *
     * @param lon
     * @param lat
     * @param radius
     * @throws ArgumentOutOfRangeException
     */
    public BoundingCircle(double lon, double lat, DistanceMeasure radius) throws ArgumentOutOfRangeException {
        this(new Coordinate(new Longitude(lon), new Latitude(lat)), radius);
    }

    /**
     * Constructor.
     *
     * @param lon
     * @param lat
     * @param radius
     */
    public BoundingCircle(Longitude lon, Latitude lat, DistanceMeasure radius) {
        this(new Coordinate(lon, lat), radius);
    }

    /**
     * Constructor.
     *
     * @param center
     * @param radius
     */
    public BoundingCircle(Coordinate center, DistanceMeasure radius) {
        _center = center;
        _radius = radius;
    }

    /**
     * Check to see if the specified coordinate exists in the current bounding
     * circle.
     *
     * @param coord
     * @return
     */
    @Override
    public boolean contains(Coordinate coord) {
        boolean retval = false;
        if (_center.distance(coord, DistanceUnit.MILE).getDistance() <= _radius.toMiles().getDistance()) {
            retval = true;
        }
        return retval;
    }

    /**
     * Get a random coordinate in the circle.
     *
     * @return
     */
    @Override
    public Coordinate randomCoordinate() {
        return Coordinate.getRandomCoordinate(_center, _radius);
    }

    /**
     * Create a copy of the bounding circle.
     *
     * @return
     */
    @Override
    public BoundingArea copy() {
        return new BoundingCircle(this._center, this._radius);
    }

    /**
     * Get the center of the bounding circle.
     *
     * @return
     */
    public Coordinate getCenter() {
        return _center;
    }

    /**
     * Set the center of the bounding circle.
     *
     * @param value
     */
    public void setCenter(Coordinate value) {
        _center = value;
    }

    /**
     * Get the radius of the bounding circle.
     *
     * @return
     */
    public DistanceMeasure getRadius() {
        return _radius;
    }

    /**
     * Set the radius of the bounding circle.
     *
     * @param value
     */
    public void setRadius(DistanceMeasure value) {
        _radius = value;
    }

    /**
     * Draw the bounding circle.
     *
     * currently not implemented.
     *
     * @param g
     * @param gc
     */
    @Override
    public void draw(Graphics2D g, GridConversion gc) {
//        PointF min = gc.convert(_center);
//        Coordinate moved = _center.add(_radius, AngleMeasure.MIN_RADIANS);
//        PointF dist = gc.convert(moved);
//        
//        g.drawOval((int)(min.getX() - dist.getX()), (int)(min.getY() - dist.getX()), 10, 10);
//
//        int x = Math.round(min.getX());
//        int y = Math.round(min.getY());
//        java.awt.Point p1 = new java.awt.Point(x, y);
//        x = Math.round(dist.getX());
//        y = Math.round(dist.getY());
//        java.awt.Point p2 = new java.awt.Point(x, y);
    }
}
