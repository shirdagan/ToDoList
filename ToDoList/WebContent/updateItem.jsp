<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>UPDATE ITEM</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <ul class="nav navbar-nav">
        <ul class="dropdown-menu">
        </ul>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="/ToDoList/ToDoListController/logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
    </ul>
  </div>
</nav>

<div class="container">
<h2>update item</h2>

<form action="ToDoListController" method="get">
<%if(request.getAttribute("message") != null)
out.write((String)request.getAttribute("message")); %>

<input type="hidden" name="command" value="updateItemDescriptionOrStatus">
<br/>

<div class="form-group">
  <label for="itemStatus">Select Your Status form the list:</label>
  
  <select class="form-control" id="itemStatus" name="itemStatus">
    <option value= "DONE">DONE</option>
    <option value = "IN_PROGRESS">IN PROGRESS</option>
    <option value = "NOT_DONE"">NOT DONE</option>
  </select>
</div>

 <div class="form-group">
      <label for="usr">item description:</label>
      <input type="text" class="form-control" name="itemDescription">	
    </div>

<input type="submit" value="update item" class="btn btn-primary">

<input type="button" value="return" onclick="window.location.href='entered.jsp'" class="btn btn-primary"> 
</form>
</body>
</html>