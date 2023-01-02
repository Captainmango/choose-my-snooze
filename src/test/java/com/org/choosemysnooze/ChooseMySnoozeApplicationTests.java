package com.org.choosemysnooze.integration;

import com.org.choosemysnooze.IntegratedKeycloakTest;
import com.org.choosemysnooze.domain.orders.OrdersController;
import dasniko.testcontainers.keycloak.KeycloakContainer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ChooseMySnoozeApplicationTests extends IntegratedKeycloakTest {

	@Autowired
	private OrdersController ordersController;

	@Test
	@WithMockUser
	void contextLoads() {
		assertNotNull(ordersController);
	}
}
