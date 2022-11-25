package com.org.choosemysnooze;

import dasniko.testcontainers.keycloak.KeycloakContainer;
import org.junit.ClassRule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.io.File;
import java.util.Map;

@Testcontainers
public abstract class IntegratedKeycloakTest
{
    @Value("${server.keycloak-db-host}")
    private static String keycloakDbHost;

//    @ClassRule
//    public static final Network myNetwork = Network.newNetwork();
//
//    @Container
//    private static final GenericContainer<?> postgreSQLContainer = new GenericContainer<>(DockerImageName.parse("bitnami/postgresql:latest"))
//            .withNetwork(myNetwork)
//            .withNetworkAliases("postgresql")
//            .withEnv("POSTGRESQL_USERNAME", "admin")
//            .withEnv("POSTGRESQL_PASSWORD", "admin")
//            .withEnv("POSTGRESQL_DATABASE", "keycloak-db")
//            .withExposedPorts(5432);

    @Container
    private static final KeycloakContainer keycloak = new KeycloakContainer()
//            .withNetwork(myNetwork)
            .withRealmImportFile("/realm-export.json")
//            .withEnv("KEYCLOAK_DATABASE_NAME", "keycloak-db")
//            .withEnv("KEYCLOAK_DATABASE_HOST", "postgresql")
//            .withEnv("KEYCLOAK_DATABASE_PORT", "5432")
//            .withEnv("KEYCLOAK_DATABASE_USER", "admin")
//            .withEnv("KEYCLOAK_DATABASE_PASSWORD", "admin")
            .withEnv("KEYCLOAK_ADMIN_USER", "admin")
            .withEnv("KEYCLOAK_ADMIN_PASSWORD", "password")
            .withExposedPorts(8080);


    @DynamicPropertySource
    static void registerNewIssuerUri(DynamicPropertyRegistry registry)
    {
        registry.add("spring.security.client.provider.keycloak.issuer-uri", () -> keycloak.getAuthServerUrl() + "/realms/master");
    }
}
