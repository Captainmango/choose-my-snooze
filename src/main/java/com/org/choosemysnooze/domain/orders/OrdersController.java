package com.org.choosemysnooze.domain.orders;

import com.org.choosemysnooze.common.BaseController;
import com.org.choosemysnooze.domain.orders.usecases.OrderBedRequest.OrderBedsRequest;
import com.org.choosemysnooze.domain.orders.usecases.GetUsersOrders.GetUsersOrdersRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/orders")
public class OrdersController extends BaseController
{
    @GetMapping(path = "", produces = "application/json")
    public ResponseEntity<?> getUsersOrders() {
        var response = pipeline.send(GetUsersOrdersRequest.builder()
                .build());

        return ResponseEntity.ok().body(response);
    }

    @PostMapping(path = "", produces = "application/json")
    public ResponseEntity<?> placeOrderForBed(@RequestBody OrderBedsRequest orderBedsRequest)
    {
        pipeline.send(orderBedsRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
