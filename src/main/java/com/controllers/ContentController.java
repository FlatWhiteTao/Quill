package com.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.entities.Comments;
import com.entities.News;
import com.entities.Tags;
import com.services.CommentsService;
import com.services.NewsService;
import com.services.TagsService;


@Controller
@RequestMapping(value="news")
public class ContentController {
	@Autowired
	NewsService newsService;
	@Autowired
	CommentsService commentsService;
	
	@RequestMapping(value="/manage",method = RequestMethod.GET)
	public ModelAndView getPageForEditor(){
		ModelAndView view = new ModelAndView("newsManage");
		return view;
	}
	
	@RequestMapping(value="/NonEditor",method = RequestMethod.GET)
	public ModelAndView getPageForNonEditor(){
		ModelAndView view = new ModelAndView("newsManage");
		return view;
	}
	
	@RequestMapping(value="/saveOrUpdate",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> getSaved(News news){
		Map<String,Object> map = new HashMap<String,Object>();
		
		if(newsService.saveOrUpdate(news)){
			
			map.put("status","200");
			map.put("message","Your news have been saved successfully");
		}
		
		return map;
	}
	
	@RequestMapping(value="/list", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> getAll(News news){
		Map<String,Object> map = new HashMap<String,Object>();
	
			List<News> list = newsService.list();
			
			if (list.size()!=0){
				map.put("status","200");
				map.put("message","Data found");
				map.put("data", list);
			}else{
				map.put("status","404");
				map.put("message","Data not found");
				
			}
		
		return map;
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> delete(Integer news_id){
		
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		if(newsService.delete(news_id)){
			map.put("status","200");
			map.put("message","Your news have been deleted successfully");
		}
		
		return map;
	}
	
	
	@RequestMapping(value="/findByTitle", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> getNewsByTitle(String news_title){
		Map<String,Object> map = new HashMap<String,Object>();
			System.out.println(news_title);
			List<News> list = newsService.findNewsByTitle(news_title);
			
			if (list.size()!= 0){
				map.put("status","200");
				map.put("message","News found");
				map.put("data", list);
			}else{
				map.put("status","404");
				map.put("message","News not found");
				
			}
		
		return map;
	}
	
	@RequestMapping(value="/findByAuthor", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> getNewsByAuthor(String news_author){
		Map<String,Object> map = new HashMap<String,Object>();
			System.out.println(news_author);
			List<News> list = newsService.findNewsByAuthor(news_author);
			
			if (list.size()!= 0){
				map.put("status","200");
				map.put("message","News found");
				map.put("data", list);
			}else{
				map.put("status","404");
				map.put("message","News not found");
				
			}
		
		return map;
	}
	
	/**
	@RequestMapping(value="/comments/add",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> addComments(Integer news_id,Comments comments){
		Map<String,Object> map = new HashMap<String,Object>();
		System.out.println(comments.getComment_content());
		if(commentsService.addComments(news_id, comments)){
			
			map.put("status","200");
			map.put("message","Your comments have been saved successfully");
		}
		
		return map;
	}
	
	@RequestMapping(value="/comments/list", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> listComments(Integer news_id){
		Map<String,Object> map = new HashMap<String,Object>();
	
			List<Comments> list = commentsService.listComments(news_id);
			
			if (list != null){
				map.put("status","200");
				map.put("message","Comments found");
				map.put("data", list);
			}else{
				map.put("status","404");
				map.put("message","Comments not found");
				
			}
		
		return map;
	}
	
	
	@RequestMapping(value="comments/delete", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> deleteComment(Integer comment_id){
		
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		if(commentsService.deleteComments(comment_id)){
			map.put("status","200");
			map.put("message","Your Comments have been deleted successfully");
		}
		
		return map;
	}
	
	@RequestMapping(value="comments/update", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateComment(Comments comments){
		
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		if(commentsService.updateComments(comments)){
			map.put("status","200");
			map.put("message","Your Comments have been updated successfully");
		}
		
		return map;
	}
	**/
	
	
	
	
}
