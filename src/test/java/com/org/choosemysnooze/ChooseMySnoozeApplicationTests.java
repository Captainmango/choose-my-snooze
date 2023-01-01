package com.org.choosemysnooze;

import com.org.choosemysnooze.domain.orders.OrdersController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

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
