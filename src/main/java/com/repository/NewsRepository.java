package com.repository;

import java.util.List;

import com.entities.News;


public interface NewsRepository {
	public boolean saveOrUpdate(News news);
	public List<News> list();
	public boolean delete(Integer news_id);
	public List<News> listWithCriteria(String news_title);
	public List<News> listWithCriteriaByAuthor(String news_author);
}
