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
}
