package com.org.choosemysnooze.domain.orders;

import an.awesome.pipelinr.Voidy;
import com.org.choosemysnooze.BaseSystemTest;
import com.org.choosemysnooze.CreatesMockUsers;
import com.org.choosemysnooze.common.exceptions.NotFoundException;
import com.org.choosemysnooze.domain.beds.Bed;
import com.org.choosemysnooze.domain.beds.usecases.FindBedsByProductCodeRequest;
import com.org.choosemysnooze.domain.orders.usecases.OrderBedRequest.OrderBedsRequest;
import com.org.choosemysnooze.domain.orders.usecases.GetUsersOrders.GetUsersOrdersRequest;
import com.org.choosemysnooze.domain.orders.usecases.GetUsersOrders.GetUsersOrdersResponse;
import com.org.choosemysnooze.domain.users.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class OrdersControllerTest extends BaseSystemTest
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

        when(pipeline.send(request)).thenReturn(response);

        mockMvc.perform(get("/api/v1/orders")).andExpectAll(
                status().isOk(),
                jsonPath("$.orders", hasSize(1))
        );

        verify(pipeline).send(request);
    }
    @Test
    public void testUserCanPlaceOrderForBed() throws Exception
    {
        var beds = List.of("b");

        var orderBedsRequest = OrderBedsRequest.builder()
                .build();

        var bedsRequest = FindBedsByProductCodeRequest.builder()
                .bedProductCodes(beds)
                .build();

        var jsonRequest = mapper.writeValueAsString(bedsRequest);

        mockMvc.perform(post("/api/v1/orders")
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isCreated()
        );

        verify(pipeline).send(orderBedsRequest);
        verify(pipeline).send(bedsRequest);
    }

    @Test
    public void testOrderNotPlacedForNonexistentBedNoOrderPlaced() throws Exception
    {
        var request = OrderBedsRequest.builder()
                .bedProductCodes(List.of("random string"))
                .build();

        var jsonRequest = mapper.writeValueAsString(request);

        when(pipeline.send(request)).thenThrow(NotFoundException.class);

        mockMvc.perform(post("/api/v1/orders")
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isNotFound()
        );

        verify(pipeline).send(request);
    }

    @Test
    public void testOrderMultipleBeds() throws Exception
    {
        var request = OrderBedsRequest.builder()
                .bedProductCodes(List.of("a", "b", "c"))
                .build();

        var jsonRequest = mapper.writeValueAsString(request);

        when(pipeline.send(request)).thenReturn(new Voidy());

        mockMvc.perform(post("/api/v1/orders")
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isCreated()
        );

        verify(pipeline).send(request);
    }

    @Test
    public void testOrderMultipleBedsSomeExistSomeDoNotPartialOrderPlaced() throws Exception
    {
        var request = OrderBedsRequest.builder()
                .bedProductCodes(List.of("a", "random string", "b"))
                .build();

        var jsonRequest = mapper.writeValueAsString(request);

        when(pipeline.send(request)).thenReturn(new Voidy());

        mockMvc.perform(post("/api/v1/orders")
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isCreated()
        );

        verify(pipeline).send(request);
    }
}
