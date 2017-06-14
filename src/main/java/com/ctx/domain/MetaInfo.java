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

    @OneToMany(mappedBy = "metaInfo")
    private List<MetaInfoDetail> metaDetails;
}
