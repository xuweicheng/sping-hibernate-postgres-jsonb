package com.weicheng.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Weicheng on 3/30/2017.
 * Original author VLAD MIHALCEA
 * https://vladmihalcea.com/2016/06/20/how-to-map-json-objects-using-generic-hibernate-types/comment-page-1/#comment-9454
 */
@Service
public class JacksonUtil
{
    static ObjectMapper jsonObjectMapper = new ObjectMapper();

    public JacksonUtil()
    {
        // This is postgres DateTime format.
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        // TODO: 5/9/2017 turn off getter and setter
        // get all
        // http://stackoverflow.com/questions/7105745/how-to-specify-jackson-to-only-use-fields-preferably-globally
        jsonObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        jsonObjectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        jsonObjectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        jsonObjectMapper.registerModule(new JavaTimeModule());
        jsonObjectMapper.registerModule(new Hibernate5Module());
        jsonObjectMapper.setDateFormat(df);
    }

    public static <T> T fromString(String string, Class<T> clazz) {
        try {
            return jsonObjectMapper.readValue(string, clazz);
        } catch (IOException e) {
            throw new IllegalArgumentException("The given string value: "
                    + string + " cannot be transformed to Json object");
        }
    }

    public static <T> List<T> mapJsonToObjectList(String json, Class clazz)
    {
        List<T> list;

        TypeFactory t = TypeFactory.defaultInstance();
        try
        {
            list = jsonObjectMapper.readValue(json, t.constructCollectionType(ArrayList.class,clazz));
        } catch (IOException e)
        {
            throw new IllegalArgumentException("The given string value: "
                    + json + " cannot be transformed to List of " + clazz.getName());
        }

        return list;
    }

    public static String toString(Object value) {
        try {
            return jsonObjectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("The given Json object value: "
                    + value + " cannot be transformed to a String");
        }
    }

    public static JsonNode toJsonNode(String value) {
        try {
            return jsonObjectMapper.readTree(value);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static <T> T clone(T value) {
        return fromString(toString(value), (Class<T>) value.getClass());
    }
}