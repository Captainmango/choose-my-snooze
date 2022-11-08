package com.org.choosemysnooze.api.controllers.orders;

import com.org.choosemysnooze.api.controllers.BaseController;
import com.org.choosemysnooze.domain.orders.usecases.getUsersOrders.GetUsersOrdersRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/orders")
public class OrdersController extends BaseController
{
    @GetMapping(path = "/", produces = "application/json")
    public ResponseEntity<?> getUsersOrders()
    {
        var request = GetUsersOrdersRequest.builder()
                .userId(1)
                .build();

        var response = pipeline.send(request);

        return ResponseEntity.ok().body(response);
    }
}
