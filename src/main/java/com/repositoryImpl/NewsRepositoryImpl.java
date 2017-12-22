package com.repositoryImpl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cache.NewsIdentityMap;
import com.repository.NewsRepository;
import com.entities.Comments;
import com.entities.News;
import com.entities.Tags;

@Repository
@Transactional
public class NewsRepositoryImpl implements NewsRepository {

	@Autowired
	SessionFactory session;
	
	
	public boolean saveOrUpdate(News news) {
		// TODO Auto-generated method stub
		session.getCurrentSession().saveOrUpdate(news);
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<News> list() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from News").list();
	}

	public boolean delete(Integer news_id) {
		
			
			// Create a Hibernate query (HQL)
			Query queryForComments = session.getCurrentSession().createQuery("FROM News as n LEFT JOIN FETCH  n.comments WHERE n.id="+news_id);
			
			// Retrieve record
			News news = (News) queryForComments.uniqueResult();
			
			List<Comments> comments =news.getComments();
			
			for (Comments comment : comments){
				session.getCurrentSession().delete(comment);
			}
			
			Query queryForTags = session.getCurrentSession().createQuery("FROM News as n LEFT JOIN FETCH  n.tags WHERE n.id="+news_id);
			
			news = (News) queryForTags.uniqueResult();
			List<Tags> tags = news.getTags();
			
			for (Tags tag:tags){
				session.getCurrentSession().delete(tag);
			}
			// Delete person
			session.getCurrentSession().delete(news);
			
		
		
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public List<News> listWithCriteria(String news_title){
		
		List<News> results = new ArrayList<News>();
		
			boolean isCached = NewsIdentityMap.hasCachedNews(news_title);
			
			if(isCached){
				results.add(NewsIdentityMap.getCachedNews(news_title));
				return results;
			}else{
				Criteria cr = session.getCurrentSession().createCriteria(News.class);
				cr.add(Restrictions.eq("news_title", news_title));
				if(!cr.list().isEmpty()){
					News cachedNews = (News) cr.list().get(0);
					NewsIdentityMap.cacheNews(cachedNews);
				}
				return cr.list();
			}
	}

	@SuppressWarnings("unchecked")
	public List<News> listWithCriteriaByAuthor(String news_author) {
		List<News> results = new ArrayList<News>();
		
		boolean isCached = NewsIdentityMap.hasCachedNews(news_author);
		
		if(isCached){
			results.add(NewsIdentityMap.getCachedNews(news_author));
			return results;
		}else{
			Criteria cr = session.getCurrentSession().createCriteria(News.class);
			cr.add(Restrictions.eq("news_author", news_author));
			if(!cr.list().isEmpty()){
				News cachedNews = (News) cr.list().get(0);
				NewsIdentityMap.cacheNews(cachedNews);
			}
			return cr.list();
		}
	}
	
	
}
