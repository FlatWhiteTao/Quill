package com.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entities.Comments;
import com.entities.Tags;
import com.services.TagsService;

@Controller
@RequestMapping(value="news/tags")

public class TagsController {

	@Autowired
	TagsService tagsService;
	
	@RequestMapping(value="/list", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> listTags(Integer news_id){
		Map<String,Object> map = new HashMap<String,Object>();
	
			List<Tags> list = tagsService.listTags(news_id);
			
			if (list.size()!=0 ){
				map.put("status","200");
				map.put("message","Tags found");
				map.put("data", list);
			}else{
				map.put("status","404");
				map.put("message","Tags not found");
				
			}
		
		return map;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> addTags(Integer news_id,Tags tags){
		Map<String,Object> map = new HashMap<String,Object>();
		
		if(tagsService.addTags(news_id, tags)){
			
			map.put("status","200");
			map.put("message","Your tags have been saved successfully");
		}
		
		return map;
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> deleteTag(Integer tag_id){
		
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		if(tagsService.deleteTags(tag_id)){
			map.put("status","200");
			map.put("message","Your Comments have been deleted successfully");
		}
		
		return map;
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateComment(Tags tags){
		
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		if(tagsService.updateTags(tags)){
			map.put("status","200");
			map.put("message","Your Comments have been updated successfully");
		}
		
		return map;
	}
}
