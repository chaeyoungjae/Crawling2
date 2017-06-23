package com.ctx.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by chaester on 2017-05-12.
 */
@Entity
@Table(name="category")
public class Category implements Serializable {
    @Id
    private String id;

    @Column(name="cate_nm")
    private String  categoryNm;

    private Character useYn;

    @ManyToOne
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private Collection<Category> children;

    @OneToMany(mappedBy = "category")
    private List<Item> items = new ArrayList<>();

    public Category(String id) {
        this.id = id;
    }
    public Category() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryNm() {
        return categoryNm;
    }

    public void setCategoryNm(String categoryNm) {
        this.categoryNm = categoryNm;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public char getUseYn() {
        return useYn;
    }

    public void setUseYn(Character useYn) {
        this.useYn = useYn;
    }
}
