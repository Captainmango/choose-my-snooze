package com.org.choosemysnooze.domain.orders.usecases.OrderBedRequest;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Voidy;
import com.org.choosemysnooze.domain.orders.Order;
import com.org.choosemysnooze.domain.orders.OrderRepository;
import com.org.choosemysnooze.domain.users.usecases.FindUserByIdentity.FindUserByIdentityRequest;
import org.springframework.stereotype.Component;

@Component
public class OrderBedsUseCase implements Command.Handler<OrderBedsRequest, Voidy>
{
    private final OrderRepository orderRepository;

    private final Pipeline pipeline;

    public OrderBedsUseCase(
            OrderRepository orderRepository,
            Pipeline pipeline
    ) {
        this.orderRepository = orderRepository;
        this.pipeline = pipeline;
    }

    @Override
    public Voidy handle(OrderBedsRequest orderBedsRequest)
    {
        var user = new FindUserByIdentityRequest().execute(pipeline);

        orderRepository.save(Order.builder()
                .user(user)
                .beds(orderBedsRequest.getBeds())
                .build()
        );

        return new Voidy();
    }
}
