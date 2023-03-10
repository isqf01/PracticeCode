package com.isqf.service.impl;

import com.isqf.dao.CategoryDao;
import com.isqf.domain.Category;
import com.isqf.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoryService")
/*
 * 新闻类别Service接口实现类
 */
public class CategoryServiceImpl implements CategoryService {
    //注入RoleDao
    @Autowired
    private CategoryDao categoryDao;
    @Override
    public List<Category> findCategoryList() {
        return this.categoryDao.selectCategoryList();
    }
    @Override
    public Category findCategoryById(Integer categoryId) {
        return this.categoryDao.getCategoryById(categoryId);
    }
}
