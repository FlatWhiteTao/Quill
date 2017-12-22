package com.services;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.entities.Tags;

public interface TagsService {
	public List<Tags> listTags(Integer news_id);
	
	@PreAuthorize("hasRole(Admin')")
	public boolean addTags(Integer news_id,Tags tags);
	@PreAuthorize("hasRole(Admin')")
	public boolean updateTags(Tags tags);
	@PreAuthorize("hasRole(Admin')")
	public boolean deleteTags(Integer tags_id);
}
