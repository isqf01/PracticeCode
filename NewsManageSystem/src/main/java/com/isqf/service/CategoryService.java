package com.isqf.service;

import com.isqf.domain.Category;

import java.util.List;

/*
 * 新闻类别Service层接口
 */
public interface CategoryService {
    public List<Category> findCategoryList();
    public Category findCategoryById(Integer categoryId);
}
