<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>


<html>
<body>
<h2>Welcome to Quill <sec:authentication property="name"/> </h2>


<br/><br />
<sec:authentication property="authorities"/>
<br />


<sec:authorize access="hasRole('Admin')">
	Editor, Please click <a href= "http://localhost:8080/quill/news/manage#">here </a> to manage news
</sec:authorize>

<sec:authorize access="hasRole('User')">
	Non-Editor, Please Click <a href= "http://localhost:8080/quill/news/manage#">here </a> to write or update your own news
</sec:authorize>

</body>


<a href="logout">Log Out</a>