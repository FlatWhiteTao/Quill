package com.cache;
import java.util.Map;
import java.util.HashMap;
import com.entities.*;

public class NewsIdentityMap {
	private static Map <String,News> newsIdentityMap = new HashMap <String, News>();
	
	public NewsIdentityMap(){
		
	}
	
	public static News getCachedNews (String news_title){
		News news = newsIdentityMap.get(news_title);
		return news;
	}
	
	public static boolean hasCachedNews(String news_title){
		return newsIdentityMap.containsKey(news_title);
	}
	
	public static void cacheNews(News news){
		newsIdentityMap.put(news.getNews_title(),news);
		boolean isContained = newsIdentityMap.containsKey(news.getNews_title());
		
		
	}
	
}
