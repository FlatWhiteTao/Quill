<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
</head>
<body>
<br/><br />
<sec:authentication property="name"/> <sec:authentication property="authorities"/>
<br />

		<input type="hidden" id="news_id">
		Title: <input type="text" id="news_title" required="required" name="news_title"><br>
		Content: <input  id="news_content" required="required" name="news_content"></input>
		Author: <input type="text" id="news_author" required="required" name="news_author" value = <sec:authentication property="name"/> ><br>
		AccessLevel: <input type="text" id="acl" required="required" name="acl" value = <sec:authentication property="authorities"/> ><br>
		Time:<input type="text" id="news_timestamp" required="required" name="news_timestamp"><br>
		News Title:<input type="text" id="news_title_for_search" required="required" name="news_title_for_search"><br>
		
		
		
		<button onclick="submit();">Submit</button>
        
        <sec:authorize access="hasRole('User')">
        	<button onclick="findByAuthor();">Load</button>
        </sec:authorize>
        
        <sec:authorize access="hasRole('Admin')">
        	<button onclick="load();">Load</button>
        </sec:authorize>
        
		<button onclick="findByTitle();">Search</button>
		
		
        	<table id="table" border=1>
			<tr> <th> NewsId </th> <th> Title </th> <th> Content </th> <th> Author </th> <th> TimeStamp </th> <th> Edit </th> <th> Delete </th> <th> Tags </th>  <th> Comments </th> </tr>
		</table>
       	
       	
       	<font size="5"> Check Comments </font><br>
       	
       	 <sec:authorize access="hasRole('User')">
        	<table id="CommentsTableForUser" border=1>
			<tr> <th> NewsId </th> <th> Comments_id </th> <th> Comments </th> </tr>
		</table>
         </sec:authorize>
       	
       	<sec:authorize access="hasRole('Admin')">
        <table id="CommentsTable" border=1>
			<tr> <th> NewsId </th> <th> Comments_id </th> <th> Comments </th> <th> Delete </th> <th> Edit </th>  </tr>
		</table>

		
		CommentId:<input id="update_comment_id">
		CommentContent: <input type="text" id="update_comment_content" required="required" name="update_comment_content"><br>
		<button onclick="updateComments();">Update Comment</button> <br>
		
		
		NewsId:<input id="added_news_id">
		NewComment: <input type="text" id="added_comment_content" required="required" name="added_comment_content"><br>
		<button onclick="addComments();">Add Comment</button> <br>
		</sec:authorize>
	    
	    <font size="5"> Check Tags </font><br>
		
		
		<sec:authorize access="hasRole('User')">
        <table id="TagsTableForUser" border=1>
			<tr> <th> NewsId </th> <th> Tags_id </th> <th> Tags </th>  </tr>
		</table>
		</sec:authorize>
		
		<sec:authorize access="hasRole('Admin')">
		<table id="TagsTable" border=1>
			<tr> <th> NewsId </th> <th> Tags_id </th> <th> Tags </th> <th> Delete </th> <th> Edit </th>  </tr>
		</table>
		
		TagId:<input id="update_tag_id">
		TagContent: <input type="text" id="update_tag_content" required="required" name="update_tag_content"><br>
		<button onclick="updateTags();">Update Tag</button> <br>
		
		
		NewsId:<input id="added_tag_news_id">
		NewTag: <input type="text" id="added_tag_content" required="required" name="added_tag_content"><br>
		<button onclick="addTags();">Add Tag</button> <br>
		</sec:authorize>
		 
	<script type="text/javascript">
	data = "";
	submit = function(){
		 
			$.ajax({
				url:'saveOrUpdate',
				type:'POST',
				data:{news_id:$("#news_id").val(),news_title:$('#news_title').val(),news_content:$('#news_content').val(),news_author:$("#news_author").val(),news_timestamp:$("#news_timestamp").val()},
				
				success: function(response){
						alert(response.message);
						if($('#acl').val()=="[Admin]") {
							load();
						}else{
							findByAuthor();
						}			
				}				
			});			
	}
	
	delete_ = function(id){		 
		 $.ajax({
			url:'delete',
			type:'POST',
			data:{news_id:id},
			success: function(response){
					alert(response.message);
					if($('#acl').val()=="[Admin]") {
						load();
					}else{
						findByAuthor();
					}	
			}				
		});
}
	
	findByTitle = function(){		 
		 $.ajax({
			url:'findByTitle',
			type:'POST',
			data:{news_title:$('#news_title_for_search').val()},
			success: function(response){
					alert(response.message);
					data = response.data;
					$('.tr').remove();
					for(i=0; i<response.data.length; i++){					
						$("#table").append("<tr class='tr'> <td> "+response.data[i].news_id+" </td> <td> "+response.data[i].news_title+" </td> <td> "+response.data[i].news_content+" </td> <td> "+response.data[i].news_author+" </td> <td> "+response.data[i].news_timestamp+" </td> <td> <a href='#' onclick= edit("+i+");> Edit </a>  </td> </td> <td> <a href='#' onclick='delete_("+response.data[i].news_id+");'> Delete </a>  </td> </tr>");
					}			
			}				
		});
}

	findByAuthor = function(){		 
		 $.ajax({
			url:'findByAuthor',
			type:'POST',
			data:{news_author:$('#news_author').val()},
			success: function(response){
					alert(response.message);
					data = response.data;
					$('.tr').remove();
					for(i=0; i<response.data.length; i++){					
						$("#table").append("<tr class='tr'> <td> "+response.data[i].news_id+" </td> <td> "+response.data[i].news_title+" </td> <td> "+response.data[i].news_content+" </td> <td> "+response.data[i].news_author+" </td> <td> "+response.data[i].news_timestamp+" </td> <td> <a href='#' onclick= edit("+i+");> Edit </a>  </td> </td> <td> <a href='#' onclick='delete_("+response.data[i].news_id+");'> Delete </a> </td> <td> <a href='#' onclick='listTags("+response.data[i].news_id+");'> ViewTags </a> </td> <td> <a href='#' onclick='listComments("+response.data[i].news_id+");'> ViewComments </a> </td> </tr>");
					}			
			}				
		});
}
	
	
	
	
	
	edit = function (index){
		$("#news_id").val(data[index].news_id);
		$("#news_title").val(data[index].news_title);
		$("#news_content").val(data[index].news_content);
		$("#news_author").val(data[index].news_author);
		$("#news_timestamp").val(data[index].news_timestamp);
		
	}
	
	
	load = function(){	
		$.ajax({
			url:'list',
			type:'POST',
			success: function(response){
					data = response.data;
					$('.tr').remove();
					for(i=0; i<response.data.length; i++){					
						$("#table").append("<tr class='tr'> <td> "+response.data[i].news_id+" </td> <td> "+response.data[i].news_title+" </td> <td> "+response.data[i].news_content+" </td> <td> "+response.data[i].news_author+" </td> <td> "+response.data[i].news_timestamp+" </td> <td> <a href='#' onclick= edit("+i+");> Edit </a>  </td> </td> <td> <a href='#' onclick='delete_("+response.data[i].news_id+");'> Delete </a>  </td> <td> <a href='#' onclick='listTags("+response.data[i].news_id+");'> ViewTags </a> </td>  <td> <a href='#' onclick='listComments("+response.data[i].news_id+");'> ViewComments </a> </td>    </tr>");
					}			
			}				
		});
		
	}
	
	addComments = function(){
		 
		$.ajax({
			url:'comments/add',
			type:'POST',
			data:{news_id:$('#added_news_id').val(),comment_content:$('#added_comment_content').val()},
			
			success: function(response){
					alert(response.message);
					listComments($('#added_news_id').val());
								
			}				
		});			
}
	
	listComments = function(id){
		
		$("#added_news_id").val(id);
		
		$.ajax({
			url:'comments/list',
			type:'POST',
			data:{news_id:id},
			
			success: function(response){
					alert(response.message);
					if($('#acl').val()=="[Admin]"){
						
					
					$('#CommentsTable tr').slice(1).remove();
					for(i=0; i<response.data.length; i++){					
						$("#CommentsTable").append("<tr class='tr'> <td> "+id+" </td> <td> "+response.data[i].comment_id+" </td> <td> "+response.data[i].comment_content+" </td> <td> <a href='#' onclick='deleteComments("+response.data[i].comment_id+");'> DeleteComment </a>  </td> <td> <a href='#' onclick = editComments("+response.data[i].comment_id+");> UpdateComment </a> </td> </tr>");
					}
				}else{
					$('#CommentsTableForUser tr').slice(1).remove();
					for(i=0; i<response.data.length; i++){					
						$("#CommentsTableForUser").append("<tr class='tr'> <td> "+id+" </td> <td> "+response.data[i].comment_id+" </td> <td>"+response.data[i].comment_content+" </td> </tr>");
					}
				}	
			}				
		});	
	}
	
	
	deleteComments = function(id){
		$.ajax({
			url:'comments/delete',
			type:'POST',
			data:{comment_id:id},
			success: function(response){
					alert(response.message);
					listComments($('#added_news_id').val());
			}				
		});
	}
	
	editComments = function(id){
		$("#update_comment_id").val(id);
	}
	
	updateComments = function(){
		$.ajax({
			url:'comments/update',
			type:'POST',
			data:{comment_id:$("#update_comment_id").val(),comment_content:$("#update_comment_content").val()},
			
			success: function(response){
					alert(response.message);
					listComments($('#added_news_id').val());	
			}				
		});			
	}
	
	
	listTags = function(id){
		
		$("#added_tag_news_id").val(id);
		
		$.ajax({
			url:'tags/list',
			type:'POST',
			data:{news_id:id},
			
			success: function(response){
					alert(response.message);
					if($('#acl').val()=="[Admin]"){
						
					
					$('#TagsTable tr').slice(1).remove();
					for(i=0; i<response.data.length; i++){					
						$("#TagsTable").append("<tr class='tr'> <td> "+id+" </td> <td> "+response.data[i].tag_id+" </td> <td> "+response.data[i].tag_content+" </td> <td> <a href='#' onclick='deleteTags("+response.data[i].tag_id+");'> DeleteTag </a>  </td> <td> <a href='#' onclick = editTags("+response.data[i].tag_id+");> UpdateTag </a> </td> </tr>");
					}
					}else{
						$('#TagsTableForUser tr').slice(1).remove();
						for(i=0; i<response.data.length; i++){					
							$("#TagsTableForUser").append("<tr class='tr'> <td> "+id+" </td> <td> "+response.data[i].tag_id+" </td> <td> "+response.data[i].tag_content+" </td> </tr>");
						}
					}			
			}				
		});	
	}
	
	
	addTags = function(){
		 
		$.ajax({
			url:'tags/add',
			type:'POST',
			data:{news_id:$('#added_tag_news_id').val(),tag_content:$('#added_tag_content').val()},
			
			success: function(response){
					alert(response.message);
					listTags($('#added_tag_news_id').val());
								
			}				
		});			
}
	
	
	editTags = function(id){
		$("#update_tag_id").val(id);
	}
	
	updateTags = function(){
		$.ajax({
			url:'tags/update',
			type:'POST',
			data:{tag_id:$("#update_tag_id").val(),tag_content:$("#update_tag_content").val()},
			
			success: function(response){
					alert(response.message);
					listTags($('#added_tag_news_id').val());	
			}				
		});			
	}
	
	deleteTags = function(id){
		$.ajax({
			url:'tags/delete',
			type:'POST',
			data:{tag_id:id},
			success: function(response){
					alert(response.message);
					listTags($('#added_tag_news_id').val());
			}				
		});
	}
		
	</script>
	
</body>
</html>