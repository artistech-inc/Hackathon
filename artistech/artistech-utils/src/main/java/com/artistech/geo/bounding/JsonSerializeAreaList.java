/*
 * Copyright 2015-2016 ArtisTech, Inc.
 */

package com.artistech.geo.bounding;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

/**
 * Serialize a collection of bounding areas to a JSON string.
 *
 * @author matta
 */
public class JsonSerializeAreaList  extends com.fasterxml.jackson.databind.JsonSerializer<Collection<BoundingArea>> {

    @Override
    public void serialize(Collection<BoundingArea> t, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {
        HashMap<String, Object> map = new HashMap<>();
        for(BoundingArea ba : t) {
            map.put(ba.getClass().getName(), ba);
        }
        jg.writeObject(map);
    }
    
}
