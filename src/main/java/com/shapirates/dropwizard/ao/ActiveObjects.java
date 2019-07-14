// Copyright (C) 2019 Shapirates, All Rights Reserved
package com.shapirates.dropwizard.ao;

import java.sql.SQLException;
import java.util.Map;

import org.apache.log4j.Logger;

import com.atlassian.activeobjects.external.ActiveObjectsModuleMetaData;
import com.atlassian.sal.api.transaction.TransactionCallback;

import net.java.ao.DBParam;
import net.java.ao.EntityManager;
import net.java.ao.EntityStreamCallback;
import net.java.ao.Query;
import net.java.ao.RawEntity;

public class ActiveObjects
{
    private final EntityManager entityManager;

    private final Logger log = Logger.getLogger(this.getClass());

    public ActiveObjects(EntityManager entityManager)
    {
        super();
        this.entityManager = entityManager;
    }

    public <K> int count(Class<? extends RawEntity<K>> type)
    {
        try
        {
            return entityManager.count(type);
        }
        catch (SQLException e)
        {
            log.error(e);
        }
        return 0;
    }

    public <K> int count(Class<? extends RawEntity<K>> type, Query query)
    {
        try
        {
            return entityManager.count(type, query);
        }
        catch (SQLException e)
        {
            log.error(e);
        }
        return 0;
    }

    public <K> int count(Class<? extends RawEntity<K>> type, String criteria, Object... parameters)
    {
        try
        {
            return entityManager.count(type, criteria, parameters);
        }
        catch (SQLException e)
        {
            log.error(e);
        }
        return 0;
    }

    public <T extends RawEntity<K>, K> T create(Class<T> type, DBParam... arg1)
    {
        try
        {
            return entityManager.create(type, arg1);
        }
        catch (SQLException e)
        {
            log.error(e);
        }
        return null;
    }

    public <T extends RawEntity<K>, K> T create(Class<T> type, Map<String, Object> arg1)
    {
        try
        {
            return entityManager.create(type, arg1);
        }
        catch (SQLException e)
        {
            log.error(e);
        }
        return null;
    }

    public void delete(RawEntity<?>... arg0)
    {
        try
        {
            entityManager.delete(arg0);
        }
        catch (SQLException e)
        {
            log.error(e);
        }
    }

    public <K> int deleteWithSQL(Class<? extends RawEntity<K>> arg0, String arg1, Object... arg2)
    {
        try
        {
            return entityManager.deleteWithSQL(arg0, arg1, arg2);
        }
        catch (SQLException e)
        {
            log.error(e);
        }
        return 0;
    }

    public <T> T executeInTransaction(TransactionCallback<T> arg0)
    {
        return null;
    }

    public <T extends RawEntity<K>, K> T[] find(Class<T> type)
    {
        try
        {
            return entityManager.find(type);
        }
        catch (SQLException e)
        {
            log.error(e);
        }
        return null;
    }

    public <T extends RawEntity<K>, K> T[] find(Class<T> type, Query query)
    {
        try
        {
            return entityManager.find(type, query);
        }
        catch (SQLException e)
        {
            log.error(e);
        }
        return null;
    }

    public <T extends RawEntity<K>, K> T[] find(Class<T> type, String criteria, Object... parameters)
    {
        try
        {
            return entityManager.find(type, criteria, parameters);
        }
        catch (SQLException e)
        {
            log.error(e);
        }
        return null;
    }

    public <T extends RawEntity<K>, K> T[] find(Class<T> type, String field, Query query)
    {
        try
        {
            return entityManager.find(type, field, query);
        }
        catch (SQLException e)
        {
            log.error(e);
        }
        return null;
    }

    public <T extends RawEntity<K>, K> T[] findWithSQL(Class<T> type, String keyField, String sql, Object... parameters)
    {
        try
        {
            return entityManager.findWithSQL(type, keyField, sql, parameters);
        }
        catch (SQLException e)
        {
            log.error(e);
        }
        return null;
    }

    public void flush(RawEntity<?>... entities)
    {
        entityManager.flush(entities);
    }

    public void flushAll()
    {
        entityManager.flushAll();
    }

    public <T extends RawEntity<K>, K> T[] get(Class<T> type, K... keys)
    {
        try
        {
            return entityManager.get(type, keys);
        }
        catch (SQLException e)
        {
            log.error(e);
        }
        return null;
    }

    public <T extends RawEntity<K>, K> T get(Class<T> type, K key)
    {
        try
        {
            return entityManager.get(type, key);
        }
        catch (SQLException e)
        {
            log.error(e);
        }
        return null;
    }

    public void migrate(Class<? extends RawEntity<?>>... entities)
    {
        try
        {
            entityManager.migrate(entities);
        }
        catch (SQLException e)
        {
            log.error(e);
        }
    }

    public void migrateDestructively(Class<? extends RawEntity<?>>... entities)
    {
        try
        {
            entityManager.migrateDestructively(entities);
        }
        catch (SQLException e)
        {
            log.error(e);
        }
    }

    public ActiveObjectsModuleMetaData moduleMetaData()
    {
        return null;
    }

    public <T extends RawEntity<K>, K> void stream(Class<T> type, EntityStreamCallback<T, K> streamCallback)
    {
        try
        {
            entityManager.stream(type, streamCallback);
        }
        catch (SQLException e)
        {
            log.error(e);
        }
    }

    public <T extends RawEntity<K>, K> void stream(Class<T> type, Query query, EntityStreamCallback<T, K> streamCallback)
    {
        try
        {
            entityManager.stream(type, query, streamCallback);
        }
        catch (SQLException e)
        {
            log.error(e);
        }
    }

}
