package com.weicheng.config.hibernate;

import com.fasterxml.jackson.databind.JsonNode;
import org.hibernate.type.descriptor.ValueBinder;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.JavaTypeDescriptor;
import org.hibernate.type.descriptor.sql.BasicBinder;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Weicheng on 3/30/2017.
 * Original author VLAD MIHALCEA
 * https://vladmihalcea.com/2016/06/20/how-to-map-json-objects-using-generic-hibernate-types/comment-page-1/#comment-9454
 */
public class JsonBinarySqlTypeDescriptor
        extends AbstractJsonSqlTypeDescriptor {

    public static final JsonBinarySqlTypeDescriptor INSTANCE =
            new JsonBinarySqlTypeDescriptor();

    @Override
    public <X> ValueBinder<X> getBinder(
            final JavaTypeDescriptor<X> javaTypeDescriptor) {
        return new BasicBinder<X>(javaTypeDescriptor, this) {
            @Override
            protected void doBind(
                    PreparedStatement st,
                    X value,
                    int index,
                    WrapperOptions options) throws SQLException
            {
                st.setObject(index,
                        javaTypeDescriptor.unwrap(
                                value, JsonNode.class, options), getSqlType()
                );
            }

            @Override
            protected void doBind(
                    CallableStatement st,
                    X value,
                    String name,
                    WrapperOptions options)
                    throws SQLException {
                st.setObject(name,
                        javaTypeDescriptor.unwrap(
                                value, JsonNode.class, options), getSqlType()
                );
            }
        };
    }
}