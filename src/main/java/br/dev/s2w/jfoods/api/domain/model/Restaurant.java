package br.dev.s2w.jfoods.api.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurant {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "delivery_fee")
    private BigDecimal deliveryFee;

    @ManyToOne
    @JoinColumn(name = "cuisine_id")
    private Cuisine cuisine;
}
