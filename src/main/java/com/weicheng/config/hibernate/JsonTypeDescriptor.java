package com.weicheng.config.hibernate;

import com.weicheng.util.JacksonUtil;
import org.hibernate.annotations.common.reflection.java.JavaXMember;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractTypeDescriptor;
import org.hibernate.type.descriptor.java.MutableMutabilityPlan;
import org.hibernate.usertype.DynamicParameterizedType;

import java.util.Properties;

/**
 * Created by Weicheng on 3/30/2017.
 * Original author VLAD MIHALCEA
 * https://vladmihalcea.com/2016/06/20/how-to-map-json-objects-using-generic-hibernate-types/comment-page-1/#comment-9454
 */
public class JsonTypeDescriptor
        extends AbstractTypeDescriptor<Object>
        implements DynamicParameterizedType
{

    private Class<?> jsonObjectClass;
    private JavaXMember javaXMember;

    @Override
    public void setParameterValues(Properties parameters) {
        jsonObjectClass = ( (ParameterType) parameters.get( PARAMETER_TYPE ) )
                .getReturnedClass();
        javaXMember = ((JavaXMember) parameters.get( XPROPERTY ));
    }

    public JsonTypeDescriptor() {
        super( Object.class, new MutableMutabilityPlan<Object>() {
            @Override
            protected Object deepCopyNotNull(Object value) {
                return JacksonUtil.clone(value);
            }
        });
    }

    @Override
    public boolean areEqual(Object one, Object another) {
        if ( one == another ) {
            return true;
        }
        if ( one == null || another == null ) {
            return false;
        }
        return JacksonUtil.toJsonNode(JacksonUtil.toString(one)).equals(
                JacksonUtil.toJsonNode(JacksonUtil.toString(another)));
    }

    @Override
    public String toString(Object value) {
        return JacksonUtil.toString(value);
    }

    @Override
    public Object fromString(String string) {
        if(javaXMember != null && javaXMember.isCollection())
        {
            String className = javaXMember.getElementClass().getName();
            try
            {
                return JacksonUtil.mapJsonToObjectList(string, Class.forName(className));
            } catch (ClassNotFoundException e)
            {
                throw new IllegalArgumentException("The given collection of class "
                        + className + " cannot be found.");
            }
        }
        return JacksonUtil.fromString(string, jsonObjectClass);
    }

    @SuppressWarnings({ "unchecked" })
    @Override
    public <X> X unwrap(Object value, Class<X> type, WrapperOptions options) {
        if ( value == null ) {
            return null;
        }
        if ( String.class.isAssignableFrom( type ) ) {
            return (X) toString(value);
        }
        if ( Object.class.isAssignableFrom( type ) ) {
            return (X) JacksonUtil.toJsonNode(toString(value));
        }
        throw unknownUnwrap( type );
    }

    @Override
    public <X> Object wrap(X value, WrapperOptions options) {
        if ( value == null ) {
            return null;
        }
        return fromString(value.toString());
    }
}