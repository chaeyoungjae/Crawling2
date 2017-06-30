package com.ctx.domain;

import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

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
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate;                    // 수정일

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
