<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>Register page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>

<div class="container">
  <h2>Enter your details:</h2>
<form action="ToDoListController" method="post">

<%if(request.getAttribute("message") != null)
out.write((String)request.getAttribute("message")); %>

<input type="hidden" name="command" value="register"> 
<br/>
<div class="form-group">
<label for="userName">Username:</label>
<input type="username" class="form-control" name="userName" placeholder="Enter username">
</div>

<div class="form-group">
 <label for="pwd">Password:</label>
 <input type="password" class="form-control" name="password" placeholder="Enter password">
 </div>
 
 <div class="form-group">
 <label for="pwd">rePassword:</label>
 <input type="password" class="form-control" name="repassword" placeholder="Enter password again">
</div> 

<input type="submit" value="Register"  class="btn btn-primary" >

</form>
<%@ taglib uri ="/WEB-INF/tag.tld" prefix="test" %>
<test:helloTag/>
<br/>
<a href="index.html"> Return to home page</a>
</body>
</html>