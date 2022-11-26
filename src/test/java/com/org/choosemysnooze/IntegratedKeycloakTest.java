package com.org.choosemysnooze;

import dasniko.testcontainers.keycloak.KeycloakContainer;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@TestPropertySource(locations = "/application.properties")
public abstract class IntegratedKeycloakTest
{
    @Container
    private static final KeycloakContainer keycloak = new KeycloakContainer()
            .withRealmImportFile("/realm-export.json")
            .withAdminUsername("admin")
            .withAdminPassword("password")
            .withExposedPorts(8080);

    @DynamicPropertySource
    static void registerNewIssuerUri(DynamicPropertyRegistry registry)
    {
        registry.add("spring.security.client.provider.keycloak.issuer-uri", () -> keycloak.getAuthServerUrl() + "/realms/master");
    }
}
