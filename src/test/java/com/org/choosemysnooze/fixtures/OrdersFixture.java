package com.org.choosemysnooze.fixtures;

import com.org.choosemysnooze.CreatesMockUsers;
import com.org.choosemysnooze.domain.beds.Bed;
import com.org.choosemysnooze.domain.orders.Order;
import com.org.choosemysnooze.domain.orders.OrderRepository;
import com.org.choosemysnooze.domain.users.User;
import com.org.choosemysnooze.domain.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrdersFixture
{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    public void run() throws Exception {
        var user = userRepository.findByIdentity(CreatesMockUsers.DEFAULT_SUBJECT).or(
            () -> Optional.of(userRepository.save(User.builder().identity(CreatesMockUsers.DEFAULT_SUBJECT).build()))
        ).orElseThrow(
                () -> new Exception("Big time error happened :(")
        );

        if (orderRepository.findAll().size() == 0) {
            var orderList = List.of(
                    Order.builder()
                        .beds(List.of(Bed.builder().name("TestBed1").price(234f).productCode("34").build()))
                        .user(user)
                        .build(),
                    Order.builder()
                            .beds(List.of(Bed.builder().name("TestBed2").price(111f).productCode("35").build()))
                            .user(user)
                            .build()
            );

            orderRepository.saveAll(orderList);
        }

    }
}
