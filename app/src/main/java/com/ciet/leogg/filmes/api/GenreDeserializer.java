package com.ciet.leogg.filmes.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GenreDeserializer  extends JsonDeserializer<List<String>> {

    @Override
    public List<String> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        List<String> out = new ArrayList<>();
        ObjectCodec codec = p.getCodec();
        TreeNode tree = codec.readTree(p);
        for(JsonNode node : (ArrayNode)tree){
            out.add(node.get("name").asText());
        }
        return out;
    }
}
