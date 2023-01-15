package com.isqf.dao;

import com.isqf.domain.News;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface NewsDao {

    /**
     * 查询新闻的集合
     * @param keywords
     * @param newsListCategoryId
     * @return
     */
    @Select("<script>select n.*,c.categoryName from t_news as n,t_category as c " +
            "<where>" +
            "  n.categoryId=c.categoryId" +
            "    <if test=\"keywords!=null and keywords!=''\" >" +
            "       and (n.title like CONCAT('%',#{keywords},'%') or n.keywords like CONCAT('%',#{keywords},'%'))" +
            "    </if>" +
            "   <if test=\"newsListCategoryId!=null and newsListCategoryId!=''\" >" +
            "      and (n.categoryId=#{newsListCategoryId})" +
            "    </if>" +
            "  </where>" +
            "order by publishTime desc" +
            "limit #{startRows},#{pageSize}</script>")
    int getNewsCount(@Param("keywords") String keywords, @Param("newsListCategoryId") Integer newsListCategoryId);

    /**
     * 查询新闻数量
     * @param keywords
     * @param newsListCategoryId
     * @param startRows
     * @param pageSize
     * @return
     */
    @Select("<script>select count(*) from t_news as n" +
            "<where>" +
            "<if test=\"keywords!=null and keywords!=''\" >" +
            "and (n.title like CONCAT('%',#{keywords},'%') or n.keywords like CONCAT('%',#{keywords},'%'))" +
            "</if>" +
            "<if test=\"newsListCategoryId!=null and newsListCategoryId!=''\" >" +
            "and (n.categoryId=#{newsListCategoryId})" +
            "</if>" +
            "</where></script>")
    List<News> findNewsList(@Param("keywords") String keywords, @Param("newsListCategoryId") Integer newsListCategoryId, @Param("startRows") Integer startRows, @Param("pageSize") Integer pageSize);

    /**
     * 通过newsId查询
     * @param newsId
     * @return
     */
    @Select("select *,categoryName from t_news as n,t_category as c where newsId=#{newsId} and n.categoryId=c.categoryId")
    News getNewsByNewsId(Integer newsId);

    /**
     * 添加新闻
     * @param news
     * @return
     */
    @Insert("insert into t_news(title,contentTitle,content,contentAbstract,keywords,author,publishTime,publishStatus,categoryId)" +
            "values(#{title},#{contentTitle},#{content},#{contentAbstract},#{keywords},#{author},#{publishTime},#{publishStatus},#{categoryId})")
    int addNews(News news);

    /**
     * 更新新闻
     * @param news
     * @return
     */
    @Update("update t_news" +
            "<set>" +
            "publishTime=#{publishTime}," +
            "publishStatus=#{publishStatus}," +
            "title=#{title}," +
            "contentTitle=#{contentTitle}," +
            "content=#{content}," +
            "contentAbstract=#{contentAbstract}," +
            "keywords=#{keywords}," +
            "author=#{author}," +
            "categoryId=#{categoryId}" +
            "</set>" +
            "where newsId=#{newsId}")
    int updateNews(News news);

    /**
     * 设置新闻发布状态  status 1:已发布；2：被撤稿
     * @param news
     * @return
     */
    @Update("update t_news set publishStatus=#{publishStatus} where newsId=#{newsId}")
    int setNewsPublishStatus(News news);

    /**
     * 删除新闻
     * @param newsId
     * @return
     */
    @Delete("delete from t_news where newsId=#{newsId}")
    int delNews(Integer newsId);
}
