package com.org.choosemysnooze.domain.orders.usecases.OrderBedRequest;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Voidy;
import com.org.choosemysnooze.common.exceptions.NotFoundException;
import com.org.choosemysnooze.domain.beds.BedRepository;
import com.org.choosemysnooze.domain.orders.Order;
import com.org.choosemysnooze.domain.orders.OrderRepository;
import com.org.choosemysnooze.domain.users.UserAuthService;
import com.org.choosemysnooze.domain.users.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class OrderBedsUseCase implements Command.Handler<OrderBedsRequest, Voidy>
{
    private final OrderRepository orderRepository;

    private final BedRepository bedRepository;

    private final UserRepository userRepository;

    private final UserAuthService userAuthService;

    public OrderBedsUseCase(
            OrderRepository orderRepository,
            BedRepository bedRepository,
            UserRepository userRepository,
            UserAuthService userAuthService
    ) {
        this.orderRepository = orderRepository;
        this.bedRepository = bedRepository;
        this.userRepository = userRepository;
        this.userAuthService = userAuthService;
    }

    @Override
    public Voidy handle(OrderBedsRequest orderBedsRequest)
    {
        var beds = bedRepository.findByProductCodeIn(orderBedsRequest.getBedIds());

        if (beds.isEmpty()) {
            throw new NotFoundException("No beds found with supplied IDs");
        }

        var user = userRepository.findByIdentity(userAuthService.getIdentity())
                .orElseThrow(
                        () -> new NotFoundException("User not found")
                );

        orderRepository.save(Order.builder()
                .user(user)
                .beds(beds)
                .build()
        );

        return new Voidy();
    }
}
