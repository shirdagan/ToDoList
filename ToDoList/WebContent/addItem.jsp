<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>ADD ITEM</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
<div class="container">
<h2>add new item</h2>
<form action="ToDoListController" method="post">
<%if(request.getAttribute("message") != null)
out.write((String)request.getAttribute("message")); %>

<input type="hidden" name="command" value="addItem">
<br/>

    <div class="form-group">
      <label for="pwd">Item description:</label>
      <input type="text" class="form-control" name="itemDescription">
    </div>
    
<input type="submit" value="add Item" class="btn btn-primary">

<input type="button" value="return" onclick="window.location.href='entered.jsp'" class="btn btn-primary"> 
</form>

</body>
</html>