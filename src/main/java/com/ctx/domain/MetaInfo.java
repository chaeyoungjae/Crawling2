package com.ctx.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by chaester on 2017-05-12.
 */
@Entity
@Table(name="meta_info")
public class MetaInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Item item;
    private String name;

    public Long getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
