package com.org.choosemysnooze.integration;

import com.org.choosemysnooze.IntegratedKeycloakTest;
import com.org.choosemysnooze.domain.orders.OrdersController;
import com.org.choosemysnooze.domain.users.UserAuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ChooseMySnoozeApplicationTests extends IntegratedKeycloakTest {

	@Autowired
	private OrdersController ordersController;

	@Autowired
	private UserAuthService userAuthService;

	@Test
	@WithMockUser
	void contextLoads() {
		var val = this.createAuthorisedUser();
		System.out.println(val);
		System.out.println(userAuthService.getIdentity());
		assertNotNull(ordersController);
	}
}
