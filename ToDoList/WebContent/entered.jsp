
<%@ page language="java" import="il.ac.hit.model.*,il.ac.hit.controller.*,java.util.*" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<html>
<head>
 <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>HOME PAGE</title>
</head>
<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <ul class="nav navbar-nav">
        <ul class="dropdown-menu">
        </ul>
    </ul>
    
    <ul class="nav navbar-nav navbar-right">
      <li><a href="updatePassword.jsp"><span class="glyphicon glyphicon-user"></span> privacy</a></li>
      <li> <action="ToDoListController" method="post"> <input type="hidden" name="command" value="logout">
      <span class="glyphicon glyphicon-log-out"> </span> Logout</a></li>
    </ul>
    
  </div>
</nav>

<div class="jumbotron text-center">
 <h2>ToDOList Table</h2>
  <p>Hello <%User user = (User)session.getAttribute("loginUser");
  out.println(user.getUserName()); %> ,Your Items are:</p> 
 </div>

<div class="container">
  <table class="table">
  
   <thead>
  <tr>
  <th>Task decription</th>
  <th>Task status</th>
  <th></th>
  <th></th>
  </tr> 
   </thead>
<%
List<Item> userItems = (List<Item>)session.getAttribute("userItems");

request.setAttribute("itemList", userItems);
%>

<%
for(Item item : userItems)
{
	out.println("<tr><td>"+item.getDescriptionOfItem()+
			"</td><td>"+item.geteStatus()+
			"</td><td>"+"<form action='ToDoListController' method='get'><input type='hidden' name='command' value='delete'><input type ='hidden' value='"+item.getItemId()+"' name='itemId'> <input type ='submit' value ='delete' class='btn btn-primary'></form>"+"</td><td>"+"<form action='ToDoListController' method='get'><input type='hidden' name='command' value='sendToUpdate'><input type ='hidden' value='"+item.getItemId()+"' name='itemId'> <input type ='submit' value ='update' class='btn btn-primary'></form>"+"</td></tr>");
}

out.println("Number of sessions are: " +ServletListenerCounter.getSessionCounter()); 
%>

  
</table>
<input type="button" value="Add Item"  class="btn btn-info" onclick="window.location.href='addItem.jsp'"> 
<br/>
<div>	
	<%@ taglib uri ="/WEB-INF/tag.tld" prefix="test" %>
	<test:helloTag nameOfUser="shir-shani-sapir"/>
	<br/>
</div>

<div class="jumbotron text-center">
<form action="ToDoListController" method="get">
	<input type="hidden" name="command" value="logout" >
	<input type="submit" value="logout"  class="btn btn-danger">
</form>
</div>
</body>
</html>