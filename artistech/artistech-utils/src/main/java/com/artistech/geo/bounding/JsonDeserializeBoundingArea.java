/*
 * Copyright 2015-2016 ArtisTech, Inc.
 */
package com.artistech.geo.bounding;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Deserialize a JSON string to a bounding area.
 *
 * @author matta
 */
public class JsonDeserializeBoundingArea extends com.fasterxml.jackson.databind.JsonDeserializer<BoundingArea> {

    private final static ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public BoundingArea deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        ObjectCodec oc = jp.getCodec();
        JsonNode node = oc.readTree(jp);
        Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
        while (fields.hasNext()) {
            try {
                Map.Entry<String, JsonNode> m = fields.next();
                Class<?> c = Class.forName(m.getKey());
                return (BoundingArea) MAPPER.readValue(m.getValue().toString(), c);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(JsonDeserializeBoundingArea.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

}
