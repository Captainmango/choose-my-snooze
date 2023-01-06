package com.org.choosemysnooze.domain.orders.usecases.GetUsersOrders;

import com.org.choosemysnooze.domain.orders.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUsersOrdersResponse
{
    private List<Order> orders;
}
