package com.ctx.repository;

import com.ctx.domain.Item;
import com.ctx.domain.ItemMaster;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by chaester on 2017-06-06.
 */
public interface ItemRty extends CrudRepository<Item, Long> {
    Long countByMaster(ItemMaster itemMaster);
}
