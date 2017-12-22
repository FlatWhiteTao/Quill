package com.repositoryImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.entities.News;
import com.entities.Tags;
import com.repository.TagsRepository;

@Repository
@Transactional
public class TagsRepositoryImpl implements TagsRepository {

	@Autowired
	SessionFactory session;
	
	public List<Tags> listTags(Integer news_id) {
		
		Query query = session.getCurrentSession().createQuery("FROM News as n LEFT JOIN FETCH  n.tags WHERE n.id="+news_id);
		
		News news = (News) query.uniqueResult();
		return new ArrayList<Tags>(news.getTags());
	}

	public boolean addTags(Integer news_id, Tags tags) {
		session.getCurrentSession().save(tags);
		News existingNews = (News) session.getCurrentSession().get(News.class,news_id);
		existingNews.getTags().add(tags);
		session.getCurrentSession().save(existingNews);
		return true;
	}

	public boolean updateTags(Tags tags) {
		Tags existingTags = (Tags) session.getCurrentSession().get(Tags.class, tags.getTag_id());
		existingTags.setTag_content(tags.getTag_content());
		return true;
	}

	public boolean deleteTags(Integer tags_id) {
		Query query = session.getCurrentSession().createSQLQuery("DELETE FROM news_tags " + "WHERE tags_id="+tags_id);
		
    	
    	query.executeUpdate();
    	
		Tags tags = (Tags) session.getCurrentSession().get(Tags.class, tags_id);
		
		
		session.getCurrentSession().delete(tags);
		return true;
	}

}
