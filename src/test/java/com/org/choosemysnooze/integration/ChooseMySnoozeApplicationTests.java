package com.org.choosemysnooze.integration;

import com.org.choosemysnooze.IntegratedKeycloakTest;
import com.org.choosemysnooze.domain.orders.OrdersController;
import com.org.choosemysnooze.domain.users.usecases.GetIdentity.GetIdentityRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ChooseMySnoozeApplicationTests extends IntegratedKeycloakTest {

	@Autowired
	private OrdersController ordersController;

	@Test
	void contextLoads()
	{
		assertNotNull(ordersController);
	}

	@Test
	void hasDefaultSubjectForMockUsers()
	{
		this.createMockUser();

		assertEquals(DEFAULT_SUBJECT, new GetIdentityRequest().execute(pipeline));
	}

	//@TODO: Look at creating users via the admin client for integration tests
}
