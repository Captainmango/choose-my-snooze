package com.org.choosemysnooze.domain.beds;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "bed")
@Table(name = "beds")
public class Bed
{

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            updatable = false,
            nullable = false
    )
    private long id;

    @Getter
    @Setter
    @Column(
            name = "name",
            nullable = false,
            unique = true
    )
    private String name;

    @Getter
    @Column(
            name = "product_code",
            nullable = false,
            unique = true
    )
    private String productCode;

    @Getter
    @Setter
    @Column(
            name = "price",
            precision = 2

    )
    private float price;

    @Getter
    @CreationTimestamp
    private Instant createdAt;

    @Getter
    @UpdateTimestamp
    private Instant updatedAt;

    @PrePersist
    protected void onCreate()
    {
        if (Objects.isNull(this.productCode)) {
            this.productCode = UUID.randomUUID().toString();
        }
    }
}
