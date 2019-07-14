// Copyright (C) 2019 Shapirates, All Rights Reserved
package com.shapirates.dropwizard.ao;

import java.util.ArrayList;
import java.util.List;

import com.shapirates.dropwizard.ao.config.AoConfiguration;
import com.shapirates.dropwizard.ao.config.AoFactory;
import com.shapirates.dropwizard.ao.jersey.AoBinder;

import io.dropwizard.Configuration;
import io.dropwizard.ConfiguredBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.DatabaseConfiguration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import net.java.ao.RawEntity;

public abstract class AoBundle<C extends Configuration> implements ConfiguredBundle<C>, DatabaseConfiguration<C>, AoConfiguration<C>
{
    private Class<? extends RawEntity<?>>[] entities;

    private ActiveObjectsFactory activeObjectsFactory;

    private String namespace;

    public AoBundle()
    {
    }

    public ActiveObjects getActiveObjects()
    {
        if (activeObjectsFactory != null)
        {
            return activeObjectsFactory.provide();
        }
        return null;

    }

    public void initialize(Bootstrap<?> bootstrap)
    {

    }

    public void initTables()
    {
        getActiveObjects().migrate(entities);
    }

    public void run(C configuration, Environment environment) throws Exception
    {
        final AoFactory aoConfig = getAoFactory(configuration);
        setNamespace(aoConfig.getNamespace());
        setEntities(aoConfig.getEntity());

        DataSourceFactory dataSourceFactory = (DataSourceFactory)getDataSourceFactory(configuration);
        if (activeObjectsFactory == null)
        {
            activeObjectsFactory = new ActiveObjectsFactory(dataSourceFactory, namespace);
        }

        environment.jersey().register(new AoBinder(activeObjectsFactory));
        initTables();
    }

    private void setEntities(String[] entity)
    {
        List<Class> entities = new ArrayList<Class>();
        for (String clazzName : entity)
        {
            try
            {
                entities.add(Class.forName(clazzName));
            }
            catch (ClassNotFoundException e)
            {
            }
        }
        this.entities = entities.toArray(new Class[entities.size()]);
    }

    private void setNamespace(String namespace)
    {
        this.namespace = namespace;
    }
}
