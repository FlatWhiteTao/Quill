package com.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repository.NewsRepository;
import com.entities.News;
import com.services.NewsService;

@Service
public class NewsServiceImpl implements NewsService {
	@Autowired
	NewsRepository newsRepo;
	
	public boolean saveOrUpdate(News news){
		return newsRepo.saveOrUpdate(news);
	}
	
	public List<News> list() {
		// TODO Auto-generated method stub
		return newsRepo.list();
	}

	public boolean delete(Integer news_id) {
		// TODO Auto-generated method stub
		return newsRepo.delete(news_id);
	}

	public List<News> findNewsByTitle(String news_title) {
		// TODO Auto-generated method stub
		return newsRepo.listWithCriteria(news_title);
	}
	
	public List<News> findNewsByAuthor(String news_author){
		return newsRepo.listWithCriteriaByAuthor(news_author);
	}

	
	
}
