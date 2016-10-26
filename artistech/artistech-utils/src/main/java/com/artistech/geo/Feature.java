/*
 * Copyright 2015-2016 ArtisTech, Inc.
 */
package com.artistech.geo;

import com.artistech.geo.bounding.BoundingBox;
import com.artistech.math.PointD;
import com.artistech.math.PointL;
import com.artistech.utils.ArgumentOutOfRangeException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * A class representing a feature to draw.
 *
 * @author matta
 */
public class Feature {

    public static final long CONVERSION_FACTOR = 100000000000L;
    private ArrayList<PointL> _coords;
    private ArrayList<PointD> _coordsD;

    private long _maxX;
    private long _maxY;
    private long _minX;
    private long _minY;

    private double _maxXD;
    private double _minXD;
    private double _maxYD;
    private double _minYD;

    /**
     *
     * @return @throws ArgumentOutOfRangeException
     */
    public BoundingBox toBoundingBox() throws ArgumentOutOfRangeException {
        return new BoundingBox(_maxXD, _maxYD, _minXD, _minYD);
    }

    /**
     *
     * @param pt
     * @return
     */
    public static PointD convert(PointL pt) {
        PointD ret = new PointD((double) pt.getX() / (double) CONVERSION_FACTOR, (double) pt.getY() / (double) CONVERSION_FACTOR);
        return ret;
    }

    /**
     *
     * @param pt
     * @return
     */
    public static PointL convert(PointD pt) {
        PointL ret = new PointL((long) (pt.getX() * CONVERSION_FACTOR), (long) (pt.getY() * CONVERSION_FACTOR));
        return ret;
    }

//        public Feature (System.Collections.Hashtable feature)
//        {
//            List<PointD> pt_list = new List<PointD> ();
//            System.Collections.ArrayList list = (System.Collections.ArrayList)((System.Collections.Hashtable)feature ["geometry"]) ["coordinates"];
//            foreach (System.Collections.ArrayList pt in list[0] as System.Collections.ArrayList) {
//                double x = (double)pt [0];
//                double y = (double)pt [1];
//                PointD ptd = new PointD (x, y);
//                pt_list.Add (ptd);
//            }
//            Initialize (pt_list);
//        }
    /**
     *
     * @param coords
     */
    public Feature(Collection<PointD> coords) {
        initialize(coords);
    }

    /**
     *
     * @param coords
     */
    private void initialize(Collection<PointD> coords) {
        _coordsD = new ArrayList<>(coords);
        _coords = new ArrayList<>();
        _maxX = Long.MIN_VALUE;
        _minX = Long.MAX_VALUE;
        _maxY = Long.MIN_VALUE;
        _minY = Long.MAX_VALUE;

        _maxXD = Double.MIN_VALUE;
        _minXD = Double.MAX_VALUE;
        _maxYD = Double.MIN_VALUE;
        _minYD = Double.MAX_VALUE;

        for (PointD pt : coords) {
            try {
                PointL new_pt = convert(pt);
                _coords.add(new_pt);
                if (_maxXD < pt.getX()) {
                    _maxX = new_pt.getX();
                    _maxXD = pt.getX();
                }
                if (_maxYD < pt.getY()) {
                    _maxY = new_pt.getY();
                    _maxYD = pt.getY();
                }

                if (_minXD > pt.getX()) {
                    _minX = new_pt.getX();
                    _minXD = pt.getX();
                }
                if (_minYD > pt.getY()) {
                    _minY = new_pt.getY();
                    _minYD = pt.getY();
                }
            } catch (Exception ex) {
//                    _nfy.PrintWarning (ex.Message);
//                    _nfy.PrintWarning (ex.StackTrace);
            }
        }
    }

    /**
     *
     * @return
     */
    public ArrayList<PointL> getCoords() {
        return _coords;
    }

    /**
     *
     * @return
     */
    public ArrayList<PointD> getCoordsD() {
        return _coordsD;
    }

    /**
     *
     * @return
     */
    public double getMaxXD() {
        return _maxXD;
    }

    /**
     *
     * @param value
     */
    public void setMaxXD(double value) {
        _maxXD = value;
    }

    /**
     *
     * @return
     */
    public double getMaxYD() {
        return _maxYD;
    }

    /**
     *
     * @param value
     */
    public void setMaxYD(double value) {
        _maxYD = value;
    }

    /**
     *
     * @return
     */
    public double getMinXD() {
        return _minXD;
    }

    /**
     *
     * @param value
     */
    public void setMinXD(double value) {
        _minXD = value;
    }

    /**
     *
     * @return
     */
    public double getMinYD() {
        return _minYD;
    }

    /**
     *
     * @param value
     */
    public void setMinYD(double value) {
        _minYD = value;
    }

    /**
     *
     * @return
     */
    public long getMaxX() {
        return _maxX;
    }

    /**
     *
     * @param value
     */
    public void setMaxX(long value) {
        _minX = value;
    }

    /**
     *
     * @return
     */
    public long getMaxY() {
        return _maxY;
    }

    /**
     *
     * @param value
     */
    public void setMaxY(long value) {
        _maxY = value;
    }

    /**
     *
     * @return
     */
    public long getMinX() {
        return _minX;
    }

    /**
     *
     * @param value
     */
    public void setMinX(long value) {
        _minX = value;
    }

    /**
     *
     * @return
     */
    public long getMinY() {
        return _minY;
    }

    /**
     *
     * @param value
     */
    public void setMinY(long value) {
        _minY = value;
    }

//        public PointD randomPointD ()
//        {
//            decimal x = Utils.Random.Instance.NextDecimal ((decimal)_minXD, (decimal)_maxXD);
//            decimal y = Utils.Random.Instance.NextDecimal ((decimal)_minYD, (decimal)_maxYD);
//            return new PointD ((double)x, (double)y);
//        }
    /**
     *
     * @param p
     * @param poly
     * @return
     */
    public static boolean pointInPolygon(PointD p, ArrayList<PointD> poly) {
        PointD p1, p2;

        boolean inside = false;

        if (poly.size() < 3) {
            return inside;
        }

        PointD oldPoint = poly.get(poly.size() - 1);

        //for (int i = 0; i < poly.Count(); i++)
        for (PointD newPoint : poly) {
            //Point<double> newPoint = poly[i];

            if (newPoint.getX() > oldPoint.getX()) {
                p1 = oldPoint;
                p2 = newPoint;
            } else {
                p1 = newPoint;
                p2 = oldPoint;
            }

            if ((newPoint.getX() < p.getX()) == (p.getX() <= oldPoint.getX())
                    && (p.getY() - p1.getY()) * (p2.getX() - p1.getX())
                    < (p2.getY() - p1.getY()) * (p.getX() - p1.getX())) {
                inside = !inside;
            }

            oldPoint = newPoint;
        }

        return inside;
    }

    /**
     *
     * @param p
     * @return
     */
    public boolean pointInPolygon(PointL p) {
        return pointInPolygon(p, this._coords);
    }

    /**
     *
     * @param p
     * @return
     */
    public boolean PointInPolygon(PointD p) {
        return pointInPolygon(p, this._coordsD);
    }

    /**
     *
     * @param p
     * @param poly
     * @return
     */
    public static boolean pointInPolygon(PointL p, ArrayList<PointL> poly) {
        PointL p1, p2;

        boolean inside = false;

        if (poly.size() < 3) {
            return inside;
        }

        PointL oldPoint = poly.get(poly.size() - 1);

        //for (int i = 0; i < poly.Count(); i++)
        for (PointL newPoint : poly) {
            //Point<double> newPoint = poly[i];

            if (newPoint.getX() > oldPoint.getX()) {
                p1 = oldPoint;
                p2 = newPoint;
            } else {
                p1 = newPoint;
                p2 = oldPoint;
            }

            if ((newPoint.getX() < p.getX()) == (p.getX() <= oldPoint.getX())
                    && (p.getY() - p1.getY()) * (p2.getX() - p1.getX())
                    < (p2.getY() - p1.getY()) * (p.getX() - p1.getX())) {
                inside = !inside;
            }

            oldPoint = newPoint;
        }

        return inside;
    }
}
