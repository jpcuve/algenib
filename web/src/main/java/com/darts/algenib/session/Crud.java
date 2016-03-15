package com.darts.algenib.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jpc on 2/9/16.
 */
public class Crud {
    private static final Logger LOGGER = LoggerFactory.getLogger(Crud.class);
    @PersistenceContext(unitName = "simba")
    protected EntityManager em;

    public <T> Builder<T> builder(Class<T> aClass){
        return new Builder<>(em, aClass);
    }

    public <T> Builder<T> builder(){
        return new Builder<>(em, null);
    }

    public static class Builder<T> {
        private final EntityManager em;
        private final Class<T> aClass;
        private boolean named;
        private String expression;
        private Map<String, Object> parameters = new HashMap<>();
        private int first = -1;
        private int limit = -1;

        private Builder(EntityManager em, Class<T> aClass) {
            this.em = em;
            this.aClass = aClass;
        }

        public Builder<T> explicit(String query){
            this.named = false;
            this.expression = query;
            return this;
        }

        public Builder<T> named(String query){
            this.named = true;
            this.expression = query;
            return this;
        }

        public Builder<T> first(int first){
            this.first = first;
            return this;
        }

        public Builder<T> limit(int limit){
            this.limit = limit;
            return this;
        }

        public Builder<T> param(String param, Object value){
            parameters.put(param, value);
            return this;
        }

        public Builder<T> params(String param, Object value, String param2, Object value2){
            parameters.put(param, value);
            parameters.put(param2, value2);
            return this;
        }

        public Builder<T> params(String param, Object value, String param2, Object value2, String param3, Object value3){
            parameters.put(param, value);
            parameters.put(param2, value2);
            parameters.put(param3, value3);
            return this;
        }

        private void augment(Query q){
            for (final Map.Entry<String, ?> entry: parameters.entrySet()){
                q.setParameter(entry.getKey(), entry.getValue());
            }
            if (first >= 0){
                q.setFirstResult(first);
            }
            if (limit >= 0){
                q.setMaxResults(limit);
            }
        }

        private Query apply(){
            final Query q = named ? em.createNamedQuery(expression) : em.createQuery(expression);
            augment(q);
            return q;
        }

        private TypedQuery<T> apply(Class<T> aClass){
            final TypedQuery<T> q = named ? em.createNamedQuery(expression, aClass) : em.createQuery(expression, aClass);
            augment(q);
            return q;
        }

        public List<T> results(){
            return apply(aClass).getResultList();
        }

        public T result(){
            try{
                return apply(aClass).getSingleResult();
            } catch (NoResultException e){
                return null;
            }
        }

        public int update(){
            return apply().executeUpdate();
        }

    }

    public <T> T create(T t){
        assert t != null;
        refreshParents(t);
        em.persist(t);
        em.flush();
        em.refresh(t);
        return t;
    }

    public <T, ID extends Serializable> T findOne(Class<T> clazz, ID pk){
        assert pk != null;
        return em.find(clazz, pk);
    }

    public <T> T update(T t){
        assert t != null;
        refreshParents(t);
        return em.merge(t);
    }

    public <T> void refreshParents(T t){
        final Object[] noArgs = new Object[0];
        try{
            final BeanInfo info = Introspector.getBeanInfo(t.getClass());
            for (final PropertyDescriptor propertyDescriptor: info.getPropertyDescriptors()){
                final Class<?> clazz = propertyDescriptor.getPropertyType();
                if (clazz.isAnnotationPresent(Entity.class)){
                    try{
                        final Object field = propertyDescriptor.getReadMethod().invoke(t, noArgs);
                        if (field != null){
                            LOGGER.info("refresh parents, merging: {}", field);
                            propertyDescriptor.getWriteMethod().invoke(t, em.merge(field));
                        }
                    } catch (IllegalAccessException| InvocationTargetException e2){
                        LOGGER.error("cannot access parent", e2);
                    }
                }
            }
        } catch(IntrospectionException e){
            LOGGER.error("cannot refresh parents", e);
        }
    }

    public <T, ID extends Serializable> void delete(Class<T> clazz, ID pk){
        assert pk != null;
        em.remove(em.getReference(clazz, pk));
    }

/*
    private void applyParameters(Query query, Map<String, ?> parameters){
        for (final Map.Entry<String, ?> entry: parameters.entrySet()){
            query.setParameter(entry.getKey(), entry.getValue());
        }
    }

    public <T> List<T> findByQuery(Class<T> clazz, String query){
        return findByQuery(clazz, query, null);
    }

    public <T> List<T> findByQuery(Class<T> clazz, String query, Map<String, ?> parameters){
        final TypedQuery<T> typedQuery = em.createQuery(query, clazz);
        if (parameters != null){
            applyParameters(typedQuery, parameters);
        }
        return typedQuery.getResultList();
    }

    public <T> List<T> findByNamedQuery(Class<T> clazz, String namedQuery){
        return findByNamedQuery(clazz, namedQuery, null);
    }

    public <T> List<T> findByNamedQuery(Class<T> clazz, String namedQuery, Map<String, ?> parameters){
        final TypedQuery<T> typedQuery = em.createNamedQuery(namedQuery, clazz);
        if (parameters != null){
            applyParameters(typedQuery, parameters);
        }
        return typedQuery.getResultList();
    }

    public int updateByNamedQuery(String namedQuery){
        return updateByNamedQuery(namedQuery, null);
    }

    public int updateByNamedQuery(String namedQuery, Map<String, ?> parameters){
        final Query query = em.createNamedQuery(namedQuery);
        if (parameters != null){
            applyParameters(query, parameters);
        }
        return query.executeUpdate();
    }
*/
}
