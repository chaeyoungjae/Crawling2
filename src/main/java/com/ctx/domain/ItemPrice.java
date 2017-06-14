package com.ctx.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by chaester on 2017-06-10.
 */
public class ItemPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String  id;
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
