package com.org.choosemysnooze.domain.orders.usecases.GetUsersOrders;

import an.awesome.pipelinr.Command;
import com.org.choosemysnooze.domain.orders.Order;
import com.org.choosemysnooze.domain.orders.OrderRepository;
import com.org.choosemysnooze.domain.users.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetUsersOrdersUseCase implements Command.Handler<GetUsersOrdersRequest, GetUsersOrdersResponse>
{
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public GetUsersOrdersUseCase(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public GetUsersOrdersResponse handle(GetUsersOrdersRequest getUsersOrdersRequest) {
        var user = userRepository.findByIdentity(getUsersOrdersRequest.getUserIdentity()).orElseThrow();

        return processResponse(orderRepository.findByUserIs(user));
    }

    private GetUsersOrdersResponse processResponse(List<Order> foundOrders)
    {
        return GetUsersOrdersResponse.builder()
                .orders(foundOrders)
                .build();
    }
}
