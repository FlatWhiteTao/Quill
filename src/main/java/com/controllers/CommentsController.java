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
import com.services.CommentsService;

@Controller
@RequestMapping(value="news/comments")
public class CommentsController {
	
	@Autowired
	CommentsService commentsService;
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> addComments(Integer news_id,Comments comments){
		Map<String,Object> map = new HashMap<String,Object>();
		System.out.println(comments.getComment_content());
		if(commentsService.addComments(news_id, comments)){
			
			map.put("status","200");
			map.put("message","Your comments have been saved successfully");
		}
		
		return map;
	}
	
	@RequestMapping(value="/list", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> listComments(Integer news_id){
		Map<String,Object> map = new HashMap<String,Object>();
	
			List<Comments> list = commentsService.listComments(news_id);
			
			if (list.size() != 0){
				map.put("status","200");
				map.put("message","Comments found");
				map.put("data", list);
			}else{
				map.put("status","404");
				map.put("message","Comments not found");
				
			}
		
		return map;
	}
	
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> deleteComment(Integer comment_id){
		
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		if(commentsService.deleteComments(comment_id)){
			map.put("status","200");
			map.put("message","Your Comments have been deleted successfully");
		}
		
		return map;
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateComment(Comments comments){
		
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		if(commentsService.updateComments(comments)){
			map.put("status","200");
			map.put("message","Your Comments have been updated successfully");
		}
		
		return map;
	}

}
