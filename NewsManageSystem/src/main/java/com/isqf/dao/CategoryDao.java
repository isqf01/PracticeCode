package com.isqf.dao;

import com.isqf.domain.Category;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CategoryDao {
    /**
     * 查询新闻类别集合列表
     * @return
     */
    @Select("select * from t_category")
    public List<Category> selectCategoryList();

    /**
     * 通过categoryId查询新闻类别
     * @param categoryId
     * @return
     */
    @Select("select * from t_category where categoryId = #{categoryId}")
    public Category getCategoryById(Integer categoryId);
}
