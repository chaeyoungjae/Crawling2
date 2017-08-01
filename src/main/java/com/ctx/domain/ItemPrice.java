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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getSlePc() {
        return slePc;
    }

    public void setSlePc(Integer slePc) {
        this.slePc = slePc;
    }

    public Integer getPricePc() {
        return pricePc;
    }

    public void setPricePc(Integer pricePc) {
        this.pricePc = pricePc;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Item getItem() {
        return item;
    }
}
