package com.ctx.domain;

import com.ctx.enums.DeliveryType;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by chaester on 2017-06-15.
 */
@Entity
public class ItemDelivery implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.ORDINAL)
    private DeliveryType deliveryType;
    private Integer price;

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
