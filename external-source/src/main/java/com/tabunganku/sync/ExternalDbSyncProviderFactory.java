package com.tabunganku.sync;

import org.keycloak.Config;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

public class ExternalDbSyncProviderFactory implements EventListenerProviderFactory {

    private static final String PROVIDER_ID = "tabungan-db-sync";


    @Override
    public EventListenerProvider create(KeycloakSession keycloakSession) {
        return new ExternalDbSyncProvider();
    }

    @Override
    public void init(Config.Scope scope) {

    }

    @Override
    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {

    }

    @Override
    public void close() {

    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }
}
