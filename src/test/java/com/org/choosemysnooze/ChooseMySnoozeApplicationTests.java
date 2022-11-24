package com.org.choosemysnooze;

import com.org.choosemysnooze.api.controllers.orders.OrdersController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ChooseMySnoozeApplicationTests {

	@Autowired
	private OrdersController ordersController;

	@Test
	void contextLoads() {
		assertThat(ordersController).isNotNull();
	}
}
