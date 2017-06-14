package com.ctx.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by chaester on 2017-06-10.
 */
@Entity
@Table(name="meta_info_detail")
public class MetaInfoDetail {
    @Id
    private Long id;
    private String name;
    @ManyToOne
    private MetaInfo metaInfo;
}
