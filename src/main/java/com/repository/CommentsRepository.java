package com.repository;

import java.util.List;

import com.entities.Comments;

public interface CommentsRepository {

	public List<Comments> listComments(Integer news_id);
	public boolean addComments(Integer news_id,Comments comments);
	public boolean updateComments(Comments comments);
	public boolean deleteComments(Integer comments_id);
	
}
