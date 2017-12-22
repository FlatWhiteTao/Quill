package com.repository;

import java.util.List;

import com.entities.Tags;

public interface TagsRepository {
	public List<Tags> listTags(Integer news_id);
	public boolean addTags(Integer news_id,Tags tags);
	public boolean updateTags(Tags tags);
	public boolean deleteTags(Integer tags_id);
}
