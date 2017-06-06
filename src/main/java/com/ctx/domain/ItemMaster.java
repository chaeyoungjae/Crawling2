package com.ctx.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by chaester on 2017-05-25.
 */
@Entity
@Table(name="item_master")
public class ItemMaster {
    @Id
    @Column(name="item_master_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name="item_id")
    private Item main_item;

    @OneToMany(mappedBy = "master")
    private List<Item> sub_item;

    @OneToMany(mappedBy = "master")
    private List<MetaInfo> metaInfos;

    private String master_image_link;
    private String keyword;

    public Item getMain_item() {
        return main_item;
    }

    public void setMain_item(Item main_item) {
        this.main_item = main_item;
    }

    public List<Item> getSub_item() {
        return sub_item;
    }

    public void setSub_item(List<Item> sub_item) {
        this.sub_item = sub_item;
    }

    public List<MetaInfo> getMetaInfos() {
        return metaInfos;
    }

    public void setMetaInfos(List<MetaInfo> metaInfos) {
        this.metaInfos = metaInfos;
    }

    public String getMaster_image_link() {
        return master_image_link;
    }

    public void setMaster_image_link(String master_image_link) {
        this.master_image_link = master_image_link;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
