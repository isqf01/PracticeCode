package com.isqf.service;

import com.isqf.domain.News;
import com.isqf.utils.PageBean;

public interface NewsService {
    PageBean<News> findNewsByPage(String keywords, Integer newsListCategoryId, Integer currentPage, Integer pageSize);
    News getNewsByNewsId(Integer newsId);
    int setNewsPublishStatus(News news);
    int addNews(News news);
    int editNews(News news);
    int delNews(Integer newsId);
}
