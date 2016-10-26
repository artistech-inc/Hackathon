/*
 * Copyright 2015-2016 ArtisTech, Inc.
 */
package com.artistech.geo.bounding;

import com.artistech.geo.Coordinate;
import com.artistech.geo.GridConversion;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Represent a composite/compound bounding area.
 *
 * This can be a collection of boxes, circle, other composites, or other.
 *
 * @author matta
 */
public class CompositeBoundingArea extends BoundingArea implements Serializable {

    private final ArrayList<BoundingArea> _areas;

    /**
     * Get the bounding areas.
     *
     * @return
     */
    @JsonSerialize(using = JsonSerializeAreaList.class, as = Collection.class)
    public Collection<BoundingArea> getBoundingAreas() {
        return _areas;
    }

    /**
     * Set the bounding areas.
     *
     * @param value
     */
    @JsonDeserialize(using = JsonDeserializeAreaList.class, as = Collection.class)
    public void setBoundingAreas(Collection<BoundingArea> value) {
        _areas.clear();
        _areas.addAll(value);
    }

    /**
     * Constructor.
     */
    public CompositeBoundingArea() {
        _areas = new ArrayList<>();
    }

    /**
     * Constructor.
     *
     * @param areas
     */
    public CompositeBoundingArea(List<BoundingArea> areas) {
        _areas = new ArrayList<>(areas);
    }

    /**
     * Check to see if the specified point exists in any of the contained
     * bounding areas.
     *
     * @param coord
     * @return
     */
    @Override
    public boolean contains(Coordinate coord) {
        boolean retVal = false;
        for (BoundingArea ba : _areas) {
            if (ba.contains(coord)) {
                retVal = true;
                break;
            }
        }
        return retVal;
    }

    /**
     * Get a random sub-area and then get a random coordinate from that area.
     *
     * @return
     */
    @Override
    public Coordinate randomCoordinate() {
        BoundingArea ba = com.artistech.utils.Random.getRandom(_areas);
        if (ba != null) {
            return ba.randomCoordinate();
        }
        return null;
    }

    /**
     * Create a copy of the current bounding area by copying all sub-areas.
     *
     * @return
     */
    @Override
    public BoundingArea copy() {
        CompositeBoundingArea retVal = new CompositeBoundingArea();
        for (BoundingArea ba : _areas) {
            retVal.addBoundingArea(ba.copy());
        }
        return retVal;
    }

    /**
     * Add a bounding area.
     *
     * @param area
     */
    public void addBoundingArea(BoundingArea area) {
        if (!_areas.contains(area)) {
            _areas.add(area);
        }
    }

    /**
     * Remove a bounding area.
     *
     * @param area
     */
    public void removeBoundingArea(BoundingArea area) {
        if (_areas.contains(area)) {
            _areas.remove(area);
        }
    }

    /**
     * Clear the bounding areas.
     */
    public void clear() {
        _areas.clear();
    }

    /**
     * Is the bounding area empty?
     *
     * @return
     */
    @JsonIgnore
    public boolean isEmpty() {
        return _areas.isEmpty();
    }

    /**
     * Is the bounding area null or empty?
     *
     * @param area
     * @return
     */
    public static boolean IsNullOrEmpty(CompositeBoundingArea area) {
        return (area == null || area.isEmpty());
    }

    /**
     * Draw the bounding areas.
     *
     * @param g
     * @param gc
     */
    @Override
    public void draw(Graphics2D g, GridConversion gc) {
        for (BoundingArea area : _areas) {
            area.draw(g, gc);
        }
    }
}
