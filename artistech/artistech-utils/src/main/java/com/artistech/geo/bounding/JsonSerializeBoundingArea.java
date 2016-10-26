/*
 * Copyright 2015-2016 ArtisTech, Inc.
 */

package com.artistech.geo.bounding;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.HashMap;

/**
 * Serialize a bounding area to a JSON string.
 *
 * @author matta
 */
public class JsonSerializeBoundingArea  extends com.fasterxml.jackson.databind.JsonSerializer<BoundingArea> {

    @Override
    public void serialize(BoundingArea t, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(t.getClass().getName(), t);
        jg.writeObject(map);
    }
    
}
