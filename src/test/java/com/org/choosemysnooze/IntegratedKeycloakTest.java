package com.org.choosemysnooze;

import dasniko.testcontainers.keycloak.KeycloakContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.Map;

@Testcontainers
public abstract class IntegratedKeycloakTest
{
    @Value("${server.keycloak-db-host}")
    private static String keycloakDbHost;
    private static final Network myNetwork = Network.newNetwork();

    @Container
    private static final GenericContainer<?> postgreSQLContainer = new GenericContainer<>(DockerImageName.parse("bitnami/postgresql:latest"))
            .withNetwork(myNetwork)
            .withEnv(Map.of(
                    "POSTGRESQL_USERNAME", "admin",
                    "POSTGRESQL_PASSWORD", "admin",
                    "POSTGRESQL_DATABASE", "keycloak-db"
            ));

    @Container
    private static final KeycloakContainer keycloak = new KeycloakContainer("bitnami/keycloak:latest")
            .withNetwork(myNetwork)
            .withRealmImportFile("/realm-export.json")
            .withEnv("KEYCLOAK_DATABASE_NAME", "keycloak-db")
            .withEnv("KEYCLOAK_DATABASE_HOST", keycloakDbHost)
            .withEnv("KEYCLOAK_DATABASE_USER", "admin")
            .withEnv("KEYCLOAK_DATABASE_PASSWORD", "admin")
            .withEnv("KEYCLOAK_ADMIN_USER", "admin")
            .withEnv("KEYCLOAK_ADMIN_PASSWORD", "password");

    @DynamicPropertySource
    static void registerNewIssuerUri(DynamicPropertyRegistry registry)
    {
        registry.add("spring.security.client.provider.keycloak.issuer-uri",
                () -> keycloak.getAuthServerUrl() + "/realms/choose-my-snooze");

        registry.add("server.keycloak-db-host", postgreSQLContainer::getContainerName);
    }
}
