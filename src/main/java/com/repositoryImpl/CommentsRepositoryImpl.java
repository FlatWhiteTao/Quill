package com.repositoryImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entities.Comments;
import com.entities.News;
import com.repository.CommentsRepository;


@Repository
@Transactional
public class CommentsRepositoryImpl implements CommentsRepository {

	@Autowired
	SessionFactory session;
	
	public List<Comments> listComments(Integer news_id) {
		
		
		Query query = session.getCurrentSession().createQuery("FROM News as n LEFT JOIN FETCH  n.comments WHERE n.id="+news_id);
		
		News news = (News) query.uniqueResult();
		return new ArrayList<Comments>(news.getComments());
	}

	public boolean addComments(Integer news_id, Comments comments) {
		
		
		session.getCurrentSession().save(comments);
		News existingNews = (News) session.getCurrentSession().get(News.class,news_id);
		existingNews.getComments().add(comments);
		session.getCurrentSession().save(existingNews);
		return true;
	}

	public boolean updateComments(Comments comments) {
		Comments existingComments = (Comments) session.getCurrentSession().get(Comments.class, comments.getComment_id());
		existingComments.setComment_content(comments.getComment_content());
		return true;
	}

	public boolean deleteComments(Integer comments_id) {
		
		Query query = session.getCurrentSession().createSQLQuery("DELETE FROM news_comments " + "WHERE comments_id="+comments_id);
		
    	
    	query.executeUpdate();
    	
		Comments comments = (Comments) session.getCurrentSession().get(Comments.class, comments_id);
		
		
		session.getCurrentSession().delete(comments);
		return true;
	}
	
}
