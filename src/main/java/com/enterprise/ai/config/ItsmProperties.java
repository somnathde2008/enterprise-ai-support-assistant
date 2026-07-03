package com.enterprise.ai.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "itsm")
public class ItsmProperties {

    private ItsmProvider provider;

    public ItsmProvider getProvider() {
        return provider;
    }

    public void setProvider(ItsmProvider provider) {
        this.provider = provider;
    }

}