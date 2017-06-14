package com.ctx.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by chaester on 2017-05-12.
 */
@Entity
@Table(name="brand")
public class Brand {
    @Id
    private String brndCd;
    private String brndNm;
}
