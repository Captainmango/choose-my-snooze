package com.org.choosemysnooze.domain.users;

import com.org.choosemysnooze.domain.orders.Order;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "user")
@Table(name = "users")
public class User
{
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            nullable = false,
            updatable = false
    )
    private long id;

    @Getter
    @Setter
    @Column(
            name = "identity"
    )
    private String identity;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;
}
