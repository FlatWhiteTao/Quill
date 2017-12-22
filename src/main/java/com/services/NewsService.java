package com.services;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.entities.News;

public interface NewsService {
	public boolean saveOrUpdate(News news);
	
	@PreAuthorize("hasRole(Admin')")
	public List<News> list();
	
	public boolean delete(Integer news_id);
	public List<News> findNewsByTitle(String news_title);
	public List<News> findNewsByAuthor(String news_author);
}
