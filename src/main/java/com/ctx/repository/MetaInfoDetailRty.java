package com.ctx.repository;

import com.ctx.domain.MetaInfo;
import com.ctx.domain.MetaInfoDetail;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by chaester on 2017-06-14.
 */
public interface MetaInfoDetailRty extends CrudRepository<MetaInfoDetail, Long> {
    MetaInfo findByName(String name);
}
