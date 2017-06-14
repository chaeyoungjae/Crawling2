package com.ctx.domain;

import javax.persistence.*;

/**
 * Created by chaester on 2017-06-10.
 */
@Entity
@Table(name="meta_info_detail")
public class MetaInfoDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne
    private MetaInfo metaInfo;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MetaInfo getMetaInfo() {
        return metaInfo;
    }

    public void setMetaInfo(MetaInfo metaInfo) {
        this.metaInfo = metaInfo;
    }
}
