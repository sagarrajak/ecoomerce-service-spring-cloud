package com.fancystore.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Table(name = "t_order_line_items")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"order"})
public class OrderLineItem {
    @Id
    @SequenceGenerator(
            sequenceName = "order_line_sequence",
            name = "order_line_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_line_sequence"
    )
    private Long orderLineId;

    private String skuCode;
    private BigDecimal price;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;
}

