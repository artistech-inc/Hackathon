/*
 * Copyright 2015 ArtisTech, Inc.
 */
package com.artistech.geo;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author matta
 */
public class LongitudeTest {

    private static final Logger LOGGER = Logger.getLogger(LongitudeTest.class.getName());

    public LongitudeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testInit() {
        LOGGER.log(Level.INFO, "init");
        double l1Expected = -77;
        double l2Expected = -76.9;
        Longitude l1 = new Longitude(l1Expected);
        Longitude l2 = new Longitude(l2Expected);
        assertEquals(l1Expected, l1.getDegrees(), 0);
        assertEquals(l2Expected, l2.getDegrees(), 0);
        assertEquals(-77, l1.getWholeDegree());
        assertEquals(-76, l2.getWholeDegree());
        assertEquals(0.0, l1.getMinutes(), 0);
        assertEquals(0.9, l2.getMinutes(), 0.0001);
    }

    @Test
    public void testMax() {
        LOGGER.log(Level.INFO, "testMax");
        Longitude l1;
        l1 = new Longitude(180.1);
        assertEquals(-179.9, l1.getDegrees(), 0.0001);
        l1 = new Longitude(179.9);
        assertEquals(179.9, l1.getDegrees(), 0.0001);
    }

    @Test
    public void testMax2x() {
        LOGGER.log(Level.INFO, "testMax2x");
        Longitude l1;
        l1 = new Longitude(360.1);
        assertEquals(0.1, l1.getDegrees(), 0.0001);
    }

    @Test
    public void testMin() {
        LOGGER.log(Level.INFO, "testMin");
        Longitude l1;
        l1 = new Longitude(-180.1);
        assertEquals(179.9, l1.getDegrees(), 0.0001);
        l1 = new Longitude(-180.0);
        assertEquals(-180, l1.getDegrees(), 0.0001);
        l1 = new Longitude(-179.9);
        assertEquals(-179.9, l1.getDegrees(), 0.0001);
    }

    @Test
    public void testMin2x() {
        LOGGER.log(Level.INFO, "testMin2x");
        Longitude l1;
        l1 = new Longitude(-360.1);
        assertEquals(-0.1, l1.getDegrees(), 0.0001);
    }
}
