package br.dev.s2w.jfoods.api.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class DeliveryOrder {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal subtotal;

    private BigDecimal deliveryFee;

    private BigDecimal totalAmount;

    @Embedded
    private Address deliveryAddress;

    private OrderStatus status;

    @CreationTimestamp
    private LocalDateTime registrationDate;

    private LocalDateTime confirmationDate;

    private LocalDateTime cancellationDate;

    private LocalDateTime deliveryDate;

    @ManyToOne
    @JoinColumn(nullable = false)
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Identity customer;

    @OneToMany(mappedBy = "deliveryOrder")
    private List<OrderItem> items = new ArrayList<>();
}