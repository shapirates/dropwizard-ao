// Copyright (C) 2019 Shapirates, All Rights Reserved
package com.shapirates.dropwizard.ao.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AoFactory
{
    @JsonProperty
    private String[] entity;

    @JsonProperty
    private String namespace;

    public String[] getEntity()
    {
        return entity;
    }

    public void setEntity(String[] entity)
    {
        this.entity = entity;
    }

    public String getNamespace()
    {
        return namespace;
    }

    public void setNamespace(String namespace)
    {
        this.namespace = namespace;
    }
}
