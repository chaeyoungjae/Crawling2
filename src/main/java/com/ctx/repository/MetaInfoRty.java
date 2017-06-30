package com.ctx.repository;

import com.ctx.domain.Item;
import com.ctx.domain.MetaInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by chaester on 2017-06-14.
 */
public interface MetaInfoRty extends CrudRepository<MetaInfo, Long> {
    MetaInfo findByNameAndItem(String name, Item item);
    List<MetaInfo> findAllByItem(Item item);
}
