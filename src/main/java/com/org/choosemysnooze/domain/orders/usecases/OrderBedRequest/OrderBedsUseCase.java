package com.org.choosemysnooze.domain.orders.usecases.OrderBedRequest;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Voidy;
import com.org.choosemysnooze.common.exceptions.NotFoundException;
import com.org.choosemysnooze.domain.beds.BedRepository;
import com.org.choosemysnooze.domain.orders.Order;
import com.org.choosemysnooze.domain.orders.OrderRepository;
import com.org.choosemysnooze.domain.users.usecases.FindUserByIdentity.FindUserByIdentityRequest;
import org.springframework.stereotype.Component;

@Component
public class OrderBedsUseCase implements Command.Handler<OrderBedsRequest, Voidy>
{
    private final OrderRepository orderRepository;

    private final BedRepository bedRepository;

    private final Pipeline pipeline;

    public OrderBedsUseCase(
            OrderRepository orderRepository,
            BedRepository bedRepository,
            Pipeline pipeline
    ) {
        this.orderRepository = orderRepository;
        this.bedRepository = bedRepository;
        this.pipeline = pipeline;
    }

    @Override
    public Voidy handle(OrderBedsRequest orderBedsRequest)
    {
        var beds = bedRepository.findByProductCodeIn(orderBedsRequest.getBedProductCodes());

        if (beds.isEmpty()) {
            throw new NotFoundException("No beds found with supplied IDs");
        }

        var user = new FindUserByIdentityRequest().execute(pipeline);

        orderRepository.save(Order.builder()
                .user(user)
                .beds(beds)
                .build()
        );

        return new Voidy();
    }
}
