// Copyright (C) 2019 Shapirates, All Rights Reserved
package com.shapirates.dropwizard.ao.jersey;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;

import com.shapirates.dropwizard.ao.ActiveObjects;
import com.shapirates.dropwizard.ao.ActiveObjectsFactory;

public class AoBinder extends AbstractBinder
{
    private final ActiveObjectsFactory activeObjectsFactory;

    public AoBinder(ActiveObjectsFactory activeObjectsFactory)
    {
        super();
        this.activeObjectsFactory = activeObjectsFactory;
    }

    @Override
    protected void configure()
    {
        bindFactory(activeObjectsFactory).to(ActiveObjects.class).in(RequestScoped.class);
    }
}
