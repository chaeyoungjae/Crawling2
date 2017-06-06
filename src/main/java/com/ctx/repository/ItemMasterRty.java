package com.ctx.repository;

import com.ctx.domain.ItemMaster;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by chaester on 2017-06-06.
 */
public interface ItemMasterRty extends CrudRepository<ItemMaster, Long> {
    Long countByMaster_image_link(String master_image_link);
}
