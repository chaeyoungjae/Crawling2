package com.ctx.domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * Created by chaester on 2017-05-25.
 */
@Entity
@Table(name="item_master")
public class ItemMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Item main_item;

    @OneToMany(mappedBy = "master")
    private List<Item> sub_item;

    @OneToMany(mappedBy = "master")
    private List<MetaInfo> metaInfos;

    @CollectionTable
    private Collection<String> keyword;

    public List<Item> getSub_item() {
        return sub_item;
    }

    public void setSub_item(List<Item> sub_item) {
        this.sub_item = sub_item;
    }
}
