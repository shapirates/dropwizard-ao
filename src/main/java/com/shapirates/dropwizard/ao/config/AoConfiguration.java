// Copyright (C) 2019 Shapirates, All Rights Reserved
package com.shapirates.dropwizard.ao.config;

import io.dropwizard.Configuration;

@FunctionalInterface
public interface AoConfiguration<C extends Configuration>
{
    AoFactory getAoFactory(C configuration);
}
