package com.org.choosemysnooze.domain.orders;

import com.org.choosemysnooze.common.BaseController;
import com.org.choosemysnooze.domain.beds.usecases.FindBedsByProductCode.FindBedsByProductCodeRequest;
import com.org.choosemysnooze.domain.orders.usecases.OrderBedRequest.OrderBedsRequest;
import com.org.choosemysnooze.domain.orders.usecases.GetUsersOrders.GetUsersOrdersRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/orders")
public class OrdersController extends BaseController
{
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "", produces = "application/json")
    public ResponseEntity<?> getUsersOrders() {
        var response = pipeline.send(GetUsersOrdersRequest.builder()
                .build());

        return ResponseEntity.ok().body(response);
    }

    @PostMapping(path = "", produces = "application/json")
    public ResponseEntity<?> placeOrderForBed(@RequestBody FindBedsByProductCodeRequest bedsByProductCodeRequest)
    {
        var bedsFound = new FindBedsByProductCodeRequest(bedsByProductCodeRequest.getBedProductCodes()).execute(pipeline);
        pipeline.send(OrderBedsRequest.builder().beds(bedsFound).build());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
