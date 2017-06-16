package com.ctx.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chaester on 2017-06-10.
 */
@Entity
public class ItemPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long  id;
    @ManyToOne
    private Item item;
    private Integer slePc;     // 공급 가격
    private Integer pricePc ;  // 판매 가격
    private Date    startDate; // 시작 일자
    private Date    endDate;   // 종료 일자

    public Item getItem() {
        return item;
    }
}
