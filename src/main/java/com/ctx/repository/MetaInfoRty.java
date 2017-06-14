package com.ctx.repository;

import com.ctx.domain.MetaInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by chaester on 2017-06-14.
 */
public interface MetaInfoRty extends CrudRepository<MetaInfo, Long> {
    MetaInfo findByName(String name);
}
