<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
</head>
<body>

		<input type="hidden" id="user_id">
		Name: <input type="text" id="user_name" required="required" name="user_name"><br>
		Password: <input type="password" id="password" required="required" name="password"><br>
		Email: <input type="email" id="email" required="required" name="email"><br>
		<button onclick="submit();">Submit</button>
		<button onclick="load();">Load</button>
	
	

		<table id="table" border=1>
			<tr> <th> Name </th> <th> Password </th> <th> Email </th> <th> Edit </th> <th> Delete </th> </tr>
		
		</table>
			
	
	<script type="text/javascript">
	data = "";
	submit = function(){
		 
			$.ajax({
				url:'saveOrUpdate',
				type:'POST',
				data:{user_id:$("#user_id").val(),user_name:$('#user_name').val(),password:$('#password').val(),email:$("#email").val()},
				
				success: function(response){
						alert(response.message);
						load();
							
				}				
			});			
	}
	
	delete_ = function(id){		 
		 $.ajax({
			url:'delete',
			type:'POST',
			data:{user_id:id},
			success: function(response){
					alert(response.message);
					load();
			}				
		});
}
	
	edit = function (index){
		$("#user_id").val(data[index].user_id);
		$("#user_name").val(data[index].user_name);
		$("#password").val(data[index].password);
		$("#email").val(data[index].email);
		
	}
	
	
	load = function(){	
		$.ajax({
			url:'list',
			type:'POST',
			success: function(response){
					data = response.data;
					$('.tr').remove();
					for(i=0; i<response.data.length; i++){					
						$("#table").append("<tr class='tr'> <td> "+response.data[i].user_name+" </td> <td> "+response.data[i].password+" </td> <td> "+response.data[i].email+" </td> <td> <a href='#' onclick= edit("+i+");> Edit </a>  </td> </td> <td> <a href='#' onclick='delete_("+response.data[i].user_id+");'> Delete </a>  </td> </tr>");
					}			
			}				
		});
		
	}
		
	</script>
	
</body>
</html>