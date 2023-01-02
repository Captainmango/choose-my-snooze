package com.org.choosemysnooze.domain.orders;

import com.org.choosemysnooze.BaseSystemTest;
import com.org.choosemysnooze.CreatesMockUsers;
import com.org.choosemysnooze.domain.beds.Bed;
import com.org.choosemysnooze.domain.orders.usecases.getUsersOrders.GetUsersOrdersRequest;
import com.org.choosemysnooze.domain.orders.usecases.getUsersOrders.GetUsersOrdersResponse;
import com.org.choosemysnooze.domain.users.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class OrdersTest extends BaseSystemTest
{
    @InjectMocks
    private OrdersController ordersController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp()
    {
        this.createMockUser();
        mockMvc = MockMvcBuilders.standaloneSetup(ordersController).build();
    }

    @Test
    public void testUsersCanHaveOrders() throws Exception
    {
        var request = GetUsersOrdersRequest.builder()
            .userIdentity(CreatesMockUsers.DEFAULT_SUBJECT)
            .build();

        var response = GetUsersOrdersResponse.builder()
            .orders(List.of(
                Order.builder()
                    .beds(List.of(Bed.builder().name("TestBed1").price(234f).productCode("34").build()))
                    .user(User.builder().build())
                    .build()
                )).build();

        when(userAuthService.getIdentity()).thenReturn(CreatesMockUsers.DEFAULT_SUBJECT);
        when(pipeline.send(request)).thenReturn(response);

        mockMvc.perform(get("/api/v1/orders/")).andExpectAll(
                status().isOk(),
                jsonPath("$.orders", hasSize(1))
        );

        verify(userAuthService).getIdentity();
        verify(pipeline).send(request);
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
