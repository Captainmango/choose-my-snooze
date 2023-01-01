package com.org.choosemysnooze.domain.orders;

import an.awesome.pipelinr.Pipeline;
import com.org.choosemysnooze.domain.orders.usecases.getUsersOrders.GetUsersOrdersRequest;
import com.org.choosemysnooze.domain.orders.usecases.getUsersOrders.GetUsersOrdersResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class OrdersControllerTest
{
    @InjectMocks
    private OrdersController ordersController;

    @Mock
    private Pipeline pipeline;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp()
    {
        mockMvc = MockMvcBuilders.standaloneSetup(ordersController).build();
    }

    @Test
    public void testUsersCanHaveOrders() throws Exception
    {
        var request = GetUsersOrdersRequest.builder()
                .userId(1)
                .build();

        when(pipeline.send(request))
                .thenReturn(
                        GetUsersOrdersResponse.builder().orders(
                                List.of(
                                    Order.builder().build()
                                )
                        ).build()
                );

        mockMvc.perform(get("/api/v1/orders/")).andExpectAll(
                status().isOk(),
                jsonPath("$.orders", hasSize(1))
        );
    }
}
