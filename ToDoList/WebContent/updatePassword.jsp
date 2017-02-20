<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>update password</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
  <h2>update your password:</h2>
<form action="ToDoListController" method="post">

<%if(request.getAttribute("message") != null)
out.write((String)request.getAttribute("message")); %>

<input type="hidden" name="command" value="changePassword"> 
<br/>
<div class="form-group">
<label for="pwd">Old password:</label>
<input type="password" class="form-control" name="oldPassword" placeholder="Enter old password">
</div>

<div class="form-group">
 <label for="pwd">new Password:</label>
 <input type="password" class="form-control" name="newPassword" placeholder="Enter password">
 </div>
 
 <div class="form-group">
 <label for="pwd">repat your new Password:</label>
 <input type="password" class="form-control" name="rePassword" placeholder="Enter your password again">
</div> 
</br>
</br>
</br>

<input type="submit" value="update password"  class="btn btn-primary" >

</form>
</br>
<a href="entered.jsp"> Return back</a>
</body>
</html>