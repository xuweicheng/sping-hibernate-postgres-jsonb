package com.weicheng.config.hibernate;

import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.usertype.DynamicParameterizedType;

import java.util.Properties;

/**
 * Created by Weicheng on 3/30/2017.
 * Original author VLAD MIHALCEA
 * https://vladmihalcea.com/2016/06/20/how-to-map-json-objects-using-generic-hibernate-types/comment-page-1/#comment-9454
 */
public class JsonBinaryType
        extends AbstractSingleColumnStandardBasicType<Object>
        implements DynamicParameterizedType
{
    public static final String JSONB = "jsonb";

    public JsonBinaryType() {
        super(
                JsonBinarySqlTypeDescriptor.INSTANCE,
                new JsonTypeDescriptor()
        );
    }

    public String getName() {
        return JSONB;
    }

    @Override
    public void setParameterValues(Properties parameters) {
        ((JsonTypeDescriptor) getJavaTypeDescriptor())
                .setParameterValues(parameters);
    }

}