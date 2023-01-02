package com.org.choosemysnooze.domain.orders;

import com.org.choosemysnooze.BaseControllerTest;
import com.org.choosemysnooze.fixtures.OrdersFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class OrdersControllerTest extends BaseControllerTest
{
    @InjectMocks
    private OrdersController ordersController;

    private MockMvc mockMvc;

    @Autowired
    private OrdersFixture ordersFixture;

    @BeforeEach
    public void setUp()
    {
        this.createMockUser();
        mockMvc = MockMvcBuilders.standaloneSetup(ordersController).build();
    }

    @Test
    public void testUsersCanHaveOrders() throws Exception
    {
        ordersFixture.run();
        mockMvc.perform(get("/api/v1/orders/")).andExpectAll(
                status().isOk(),
                jsonPath("$.orders", hasSize(1))
        );
    }
//    @Test
//    public void testUserCanPlaceOrderForBed() throws Exception
//    {
//        var request = OrderBedRequest.builder()
//                .userId(1)
//                .bedIds(List.of("b"))
//                .build();
//
//        var jsonRequest = mapper.writeValueAsString(request);
//
//        mockMvc.perform(post("/api/v1/orders", jsonRequest)).andExpect(
//                status().isAccepted()
//        );
//    }
//
//    @Test
//    public void testOrderNotPlacedForNonexistentBed() throws Exception
//    {
//        var request = OrderBedRequest.builder()
//                .userId(1)
//                .bedId(List.of("random string"))
//                .build();
//
//        var jsonRequest = mapper.writeValueAsString(request);
//
//        mockMvc.perform(post("/api/v1/orders", jsonRequest)).andExpect(
//                status().isNotFound()
//        );
//    }
//
//    @Test
//    public void testOrderMultipleBeds() throws Exception
//    {
//        var request = OrderBedRequest.builder()
//                .userId(1)
//                .bedId(List.of("a", "b", "c"))
//                .build();
//
//        var jsonRequest = mapper.writeValueAsString(request);
//
//        mockMvc.perform(post("/api/v1/orders", jsonRequest)).andExpect(
//                status().isAccepted()
//        );
//    }
//
//    @Test
//    public void testOrderMultipleBedsSomeExistSomeDoNot() throws Exception
//    {
//        var request = OrderBedRequest.builder()
//                .userId(1)
//                .bedId(List.of("a", "random string", "b"))
//                .build();
//
//        var jsonRequest = mapper.writeValueAsString(request);
//
//        mockMvc.perform(post("/api/v1/orders", jsonRequest)).andExpect(
//                status().isNotFound()
//        );
//    }
}
