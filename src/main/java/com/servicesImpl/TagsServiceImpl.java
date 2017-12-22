package com.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entities.Tags;
import com.repository.TagsRepository;
import com.services.TagsService;

@Service
public class TagsServiceImpl implements TagsService {

	@Autowired
	TagsRepository tagsRepo;
	
	public List<Tags> listTags(Integer news_id) {
		return tagsRepo.listTags(news_id);
	}

	public boolean addTags(Integer news_id, Tags tags) {
		return tagsRepo.addTags(news_id, tags);
	}

	public boolean updateTags(Tags tags) {
		return tagsRepo.updateTags(tags);
	}

	public boolean deleteTags(Integer tags_id) {
		return tagsRepo.deleteTags(tags_id);
	}

}
