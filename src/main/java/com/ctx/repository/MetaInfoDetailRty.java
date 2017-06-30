package com.ctx.repository;

import com.ctx.domain.MetaInfo;
import com.ctx.domain.MetaInfoDetail;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by chaester on 2017-06-14.
 */
public interface MetaInfoDetailRty extends CrudRepository<MetaInfoDetail, Long> {
    MetaInfoDetail findByNameAndMetaInfo(String name, MetaInfo metaInfo);
    List<MetaInfoDetail> findAllByMetaInfo(MetaInfo metaInfo);
}
