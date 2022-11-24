package com.org.choosemysnooze;

import dasniko.testcontainers.keycloak.KeycloakContainer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

public abstract class IntegratedTest
{
    @Container
    static
    KeycloakContainer keycloak = new KeycloakContainer("bitnami/keycloak:latest")
            .withRealmImportFile("keycloak/realm-export.json");

    @Container
    static
    GenericContainer<?> postgreSQLContainer = new GenericContainer<>(DockerImageName.parse("bitnami/postgresql:latest"));

    @BeforeAll
    public static void setUpAuth()
    {
        postgreSQLContainer.start();
        keycloak.start();
    }

    @AfterAll
    public static void tearDownContainers()
    {
        if (keycloak.isRunning()) {
            keycloak.stop();
        }

        if (postgreSQLContainer.isRunning()) {
            postgreSQLContainer.stop();
        }
    }
}
