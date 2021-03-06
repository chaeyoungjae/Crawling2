package com.ctx.repository;

import com.ctx.domain.Category;
import com.ctx.domain.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by chaester on 2017-06-14.
 */
public interface ItemRty extends CrudRepository<Item, Long> {
    @Override
    Iterable<Item> findAll();
    Long countById(Long id);
    List<Item> findByTopImageUrlAndMasterNull(String topImageUrl);
    List<Item> findByTopImageUrlAndMasterNotNull(String topImageUrl);
}
