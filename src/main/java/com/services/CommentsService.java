package com.services;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.entities.Comments;



public interface CommentsService {

	public List<Comments> listComments(Integer news_id);
	
	@PreAuthorize("hasRole(Admin')")
	public boolean addComments(Integer news_id,Comments comments);
	@PreAuthorize("hasRole(Admin')")
	public boolean updateComments(Comments comments);
	@PreAuthorize("hasRole(Admin')")
	public boolean deleteComments(Integer comments_id);
	
}
