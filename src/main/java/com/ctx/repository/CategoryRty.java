package com.ctx.repository;

import com.ctx.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by chaester on 2017-06-05.
 */
public interface CategoryRty extends CrudRepository<Category, String> {
    @Override
    Iterable<Category> findAll();
    Long countById(String id);
}
