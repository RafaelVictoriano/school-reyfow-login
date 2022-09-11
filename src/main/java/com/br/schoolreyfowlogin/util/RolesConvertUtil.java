package com.br.schoolreyfowlogin.util;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.br.schoolreyfowlogin.model.Role;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class RolesConvertUtil implements DynamoDBTypeConverter<String, List<Role>> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convert(List<Role> list) {
        try {
            return mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Role> unconvert(String s) {
        List<Role> roles;
        try {
            roles = mapper.readValue(s, new TypeReference<List<Role>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return roles;
    }
}
