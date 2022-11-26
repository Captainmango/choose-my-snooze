package com.org.choosemysnooze;

import com.org.choosemysnooze.api.controllers.orders.OrdersController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ChooseMySnoozeApplicationTests extends IntegratedKeycloakTest {

	@Autowired
	private OrdersController ordersController;

	@Test
	@WithMockUser
	void contextLoads() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getPrincipal());

		assertNotNull(ordersController);
	}
}
