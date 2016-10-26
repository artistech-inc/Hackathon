/*
 * Copyright 2015 ArtisTech, Inc.
 */
package com.artistech.geo;

import com.artistech.utils.ArgumentOutOfRangeException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author matta
 */
public class CoordinateTest {
    
    public CoordinateTest() {
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
    public void serialize() {
        try {
            Coordinate c = new Coordinate(87.9, 78.6);
            ObjectMapper mapper = new ObjectMapper();
            String writeValueAsString = mapper.writeValueAsString(c);
            InputStream stream = new ByteArrayInputStream(writeValueAsString.getBytes(StandardCharsets.UTF_8));
            Coordinate readValue = mapper.readValue(stream, Coordinate.class);
            assertNotNull(readValue);
            assertEquals(87.9, readValue.getLongitude().getDegrees(), 0.0001);
            assertEquals(87, readValue.getLongitude().getWholeDegree());
            assertEquals(0.9, readValue.getLongitude().getMinutes(), 0.0001);
            assertEquals(78.6, readValue.getLatitude().getDegrees(), 0.0001);
            assertEquals(78, readValue.getLatitude().getWholeDegree());
            assertEquals(0.6, readValue.getLatitude().getMinutes(), 0.0001);

            c = new Coordinate(-87.9, -78.9);
            writeValueAsString = mapper.writeValueAsString(c);
            stream = new ByteArrayInputStream(writeValueAsString.getBytes(StandardCharsets.UTF_8));
            readValue = mapper.readValue(stream, Coordinate.class);
            assertNotNull(readValue);
            assertEquals(-87.9, readValue.getLongitude().getDegrees(), 0.0001);
            assertEquals(-87, readValue.getLongitude().getWholeDegree());
            assertEquals(0.9, readValue.getLongitude().getMinutes(), 0.0001);
            assertEquals(-78.9, readValue.getLatitude().getDegrees(), 0.0001);
            assertEquals(-78, readValue.getLatitude().getWholeDegree());
            assertEquals(0.9, readValue.getLatitude().getMinutes(), 0.0001);
        } catch (ArgumentOutOfRangeException | JsonProcessingException ex) {
            Logger.getLogger(CoordinateTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CoordinateTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
