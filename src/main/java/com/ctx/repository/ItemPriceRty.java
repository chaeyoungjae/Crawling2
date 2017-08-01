package com.ctx.repository;

import com.ctx.domain.Item;
import com.ctx.domain.ItemPrice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.NamedQueries;
import java.util.List;

/**
 * Created by chaester on 2017-06-14.
 */
public interface ItemPriceRty extends CrudRepository<ItemPrice, Long> {
    @Override
    Iterable<ItemPrice> findAll();

    Long countById(Long id);

    @Query("select b from ItemPrice b where b.item.id = ?1 and current_date between b.startDate and b.endDate")
    List<ItemPrice> findByItemAndStartDateBetween(Long item_id);
}
