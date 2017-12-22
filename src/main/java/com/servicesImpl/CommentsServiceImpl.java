package com.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entities.Comments;
import com.repository.CommentsRepository;
import com.services.CommentsService;

@Service
public class CommentsServiceImpl implements CommentsService{
	@Autowired
	CommentsRepository commentsRepo;

	public List<Comments> listComments(Integer news_id) {
		return commentsRepo.listComments(news_id);
	}

	public boolean addComments(Integer news_id, Comments comments) {
		return commentsRepo.addComments(news_id, comments);
	}

	public boolean updateComments(Comments comments) {
		return commentsRepo.updateComments(comments);
	}

	public boolean deleteComments(Integer comments_id) {
		return commentsRepo.deleteComments(comments_id);
	}
	
	
	
	
}
