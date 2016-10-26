/*
 * Copyright 2015 ArtisTech, Inc.
 */
package com.artistech.geo.bounding;

import com.artistech.geo.Coordinate;
import com.artistech.utils.ArgumentOutOfRangeException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author matta
 */
public class BoundingBoxTest {

    public BoundingBoxTest() {
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

    /**
     * Test of getMin method, of class BoundingBox.
     */
    @Test
    public void testGetMin() {
        Logger.getLogger(BoundingBoxTest.class.getName()).log(Level.INFO, "getMin");
        try {
            BoundingBox bb = new BoundingBox(-77.0, 38.9, -76.9, 39.0);
            Assert.assertEquals(38.9, bb.getMin().getLatitude().getDegrees(), 0.0001);
            Assert.assertEquals(-77.0, bb.getMin().getLongitude().getDegrees(), 0.0001);
        } catch (ArgumentOutOfRangeException ex) {
            fail(ex.getMessage());
        }
    }

    /**
     * Test of getMax method, of class BoundingBox.
     */
    @Test
    public void testGetMax() {
        Logger.getLogger(BoundingBoxTest.class.getName()).log(Level.INFO, "getMax");
        try {
            BoundingBox bb = new BoundingBox(-77.0, 38.9, -76.9, 39.0);
            Assert.assertEquals(39.0, bb.getMax().getLatitude().getDegrees(), 0.0001);
            Assert.assertEquals(-76.9, bb.getMax().getLongitude().getDegrees(), 0.0001);
        } catch (ArgumentOutOfRangeException ex) {
            fail(ex.getMessage());
            Logger.getLogger(BoundingBoxTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of contains method, of class BoundingBox.
     */
    @Test
    public void testContains() {
        Logger.getLogger(BoundingBoxTest.class.getName()).log(Level.INFO, "contains");
        try {
            BoundingBox bb = new BoundingBox(-77.0, 38.9, -76.9, 39.0);
            Coordinate randomCoordinate = bb.randomCoordinate();
            bb.contains(randomCoordinate);
            Assert.assertTrue(bb.contains(randomCoordinate));
            Coordinate coord = new Coordinate(88.0, 88.0);
            Assert.assertFalse(bb.contains(coord));
        } catch (ArgumentOutOfRangeException ex) {
            fail(ex.getMessage());
        }
    }

    /**
     * Test of randomCoordinate method, of class BoundingBox.
     */
    @Test
    public void testRandomCoordinate() {
        Logger.getLogger(BoundingBoxTest.class.getName()).log(Level.INFO, "randomCoordinate");
        try {
            BoundingBox bb = new BoundingBox(-77.0, 38.9, -76.9, 39.0);
            for (int ii = 0; ii < 10; ii++) {
                Coordinate randomCoordinate = bb.randomCoordinate();
                Assert.assertTrue(randomCoordinate.getLatitude().getDegrees() <= bb.getMax().getLatitude().getDegrees());
                Assert.assertTrue(randomCoordinate.getLongitude().getDegrees() <= bb.getMax().getLongitude().getDegrees());
                Assert.assertTrue(randomCoordinate.getLatitude().getDegrees() >= bb.getMin().getLatitude().getDegrees());
                Assert.assertTrue(randomCoordinate.getLongitude().getDegrees() >= bb.getMin().getLongitude().getDegrees());
                Assert.assertTrue(bb.contains(randomCoordinate));
            }
        } catch (ArgumentOutOfRangeException ex) {
            fail(ex.getMessage());
        }
    }

    /**
     * Test of copy method, of class BoundingBox.
     */
    @Test
    public void testCopy() {
    }
}
