// Copyright (C) 2019 Shapirates, All Rights Reserved
package com.shapirates.dropwizard.ao;

import static com.atlassian.activeobjects.ao.ConverterUtils.toUpperCase;
import static org.apache.commons.codec.digest.DigestUtils.md5Hex;
import static org.apache.commons.lang3.StringUtils.right;

import org.glassfish.hk2.api.Factory;

import com.atlassian.activeobjects.ao.AtlassianTablePrefix;
import com.atlassian.activeobjects.ao.PrefixedSchemaConfiguration;
import com.atlassian.activeobjects.config.ActiveObjectsConfiguration;
import com.atlassian.activeobjects.internal.Prefix;
import com.atlassian.activeobjects.internal.SimplePrefix;

import io.dropwizard.db.DataSourceFactory;
import net.java.ao.EntityManager;
import net.java.ao.atlassian.AtlassianFieldNameConverter;
import net.java.ao.atlassian.AtlassianIndexNameConverter;
import net.java.ao.atlassian.AtlassianSequenceNameConverter;
import net.java.ao.atlassian.AtlassianTableNameConverter;
import net.java.ao.atlassian.AtlassianTriggerNameConverter;
import net.java.ao.builder.EntityManagerBuilder;

public class ActiveObjectsFactory implements Factory<ActiveObjects>
{
    private final DataSourceFactory dataSourceFactory;

    private final String namespace;

    public ActiveObjectsFactory(DataSourceFactory dataSourceFactory, String namespace)
    {
        this.namespace = namespace;
        this.dataSourceFactory = dataSourceFactory;
    }

    private String digest(String s)
    {
        return md5Hex(s);
    }

    private String digest(String s, int n)
    {
        return right(digest(s), n);
    }

    public void dispose(ActiveObjects activeObjects)
    {
    }

    public ActiveObjects provide()
    {
        String hash = digest(namespace, 6);
        Prefix prefix = new SimplePrefix(toUpperCase(ActiveObjectsConfiguration.AO_TABLE_PREFIX + "_" + hash), "_");
        AtlassianTablePrefix tablePrefix = new AtlassianTablePrefix(prefix);
        AtlassianTableNameConverter tableNameConverter = new AtlassianTableNameConverter(tablePrefix);
        AtlassianFieldNameConverter fieldNameConverter = new AtlassianFieldNameConverter();
        AtlassianSequenceNameConverter sequenceNameConverter = new AtlassianSequenceNameConverter();
        AtlassianTriggerNameConverter triggerNameConverter = new AtlassianTriggerNameConverter();
        AtlassianIndexNameConverter indexNameConverter = new AtlassianIndexNameConverter();
        PrefixedSchemaConfiguration schemaConfiguration = new PrefixedSchemaConfiguration(prefix);

        EntityManager entityManager = EntityManagerBuilder.url(dataSourceFactory.getUrl()).username(dataSourceFactory.getUser())
            .password(dataSourceFactory.getPassword()).auto().tableNameConverter(tableNameConverter)
            .fieldNameConverter(fieldNameConverter).sequenceNameConverter(sequenceNameConverter)
            .triggerNameConverter(triggerNameConverter).indexNameConverter(indexNameConverter)
            .schemaConfiguration(schemaConfiguration).build();

        return new ActiveObjects(entityManager);
    }
}
