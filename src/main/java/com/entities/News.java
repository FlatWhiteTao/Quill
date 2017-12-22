package com.entities;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;



@Entity
@Table(name="news")
public class News {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="news_id")
	private Integer news_id;
	
	@Column(name="news_title")
	private String news_title;
	
	@Column(name="news_content")
	private String news_content;
	
	@Column(name="news_img")
	private String news_img;
	
	@Column(name="news_audio")
	private String news_audio;
	
	@Column(name="news_video")
	private String news_video;
	
	@Column(name="news_author")
	private String news_author;
	
	@Column(name="isApproved")
	private Boolean isApproved;
	
	@Column(name="isDeleted")
	private Boolean isDeleted;
	
	@Column(name="news_timestamp")
	private String news_timestamp;
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinTable(name="news_comments",
			joinColumns = @JoinColumn (name="news_id"),
			inverseJoinColumns = @JoinColumn(name="comments_id"))
	private List<Comments> comments;
	
	@OneToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name="news_tags",
			joinColumns = @JoinColumn (name="news_id"),
			inverseJoinColumns = @JoinColumn(name="tags_id"))
	private List<Tags> tags;
	
	@Version
	@Column(name="version")
    private Integer version;

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getNews_id() {
		return news_id;
	}

	public void setNews_id(Integer news_id) {
		this.news_id = news_id;
	}

	public String getNews_title() {
		return news_title;
	}

	public void setNews_title(String news_title) {
		this.news_title = news_title;
	}

	public String getNews_content() {
		return news_content;
	}

	public void setNews_content(String news_content) {
		this.news_content = news_content;
	}

	public String getNews_img() {
		return news_img;
	}

	public void setNews_img(String news_img) {
		this.news_img = news_img;
	}

	public String getNews_audio() {
		return news_audio;
	}

	public void setNews_audio(String news_audio) {
		this.news_audio = news_audio;
	}

	public String getNews_video() {
		return news_video;
	}

	public void setNews_video(String news_video) {
		this.news_video = news_video;
	}

	public Boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getNews_author() {
		return news_author;
	}

	public void setNews_author(String news_author) {
		this.news_author = news_author;
	}

	public String getNews_timestamp() {
		return news_timestamp;
	}

	public void setNews_timestamp(String news_timestamp) {
		this.news_timestamp = news_timestamp;
	}

	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

	public List<Tags> getTags() {
		return tags;
	}

	public void setTags(List<Tags> tags) {
		this.tags = tags;
	}

	

	
}
