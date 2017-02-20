
package il.ac.hit.controller;

import java.io.IOException;
import java.util.*;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import antlr.collections.*;
import il.ac.hit.model.*;
import il.ac.hit.model.Item.Status;

/**
 * Servlet implementation class ToDoListController
 */
@WebServlet("/ToDoListController/*")
public class ToDoListController extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private IToDoListDAO todo = HibernateToDoListDAO.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToDoListController()
    {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String path = request.getPathInfo();
		String theCommand = request.getParameter("command");
		List<Item> userItems = null;
		RequestDispatcher dispatcher = null;
		User newUser = null;
		HttpSession session = request.getSession();
		switch(theCommand)
		{
		case "delete":
			String itemID = request.getParameter("itemId");
			int intItemID = Integer.parseInt(itemID);
			try
			{
				//newUser = (User)session.getAttribute("loginUser");
				Item itemToDelete = todo.searchItem(intItemID);
				todo.deleteItem(itemToDelete);
				newUser = (User)session.getAttribute("loginUser");
				userItems = todo.findUserItems(newUser);
				session.setAttribute("userItems",userItems);
				request.getRequestDispatcher("/entered.jsp").forward(request, response);
			} 
			catch (ExceptionToDoListDAO e)
			{	e.printStackTrace();
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
			
			break;
			
		case "sendToUpdate":
			itemID = request.getParameter("itemId");
			session.setAttribute("itemId", itemID);
			request.getRequestDispatcher("/updateItem.jsp").forward(request, response);
			break;
			
		case "updateItemDescriptionOrStatus":
			Status status = Status.valueOf(request.getParameter("itemStatus"));
			String itemDesc = request.getParameter("itemDescription");
			itemID= (String) session.getAttribute("itemId");
			intItemID = Integer.parseInt(itemID);
			if(itemDesc.equals(""))
			{
				newUser = (User)session.getAttribute("loginUser");
				try 
				{
					todo.updateStatusItem(intItemID, status, newUser);
					userItems = todo.findUserItems(newUser);
					session.setAttribute("userItems", userItems);	
					request.getRequestDispatcher("/entered.jsp").forward(request, response);
				} 
				catch (ExceptionToDoListDAO e)
				{
					e.printStackTrace();
					request.getRequestDispatcher("/error.jsp").forward(request, response);
				}
			}
			else
			{
				try 
				{
					newUser = (User) session.getAttribute("loginUser");
					Item itemToUpdate = todo.searchItem(intItemID);
					todo.updateItemDescription(itemToUpdate, itemDesc);
					todo.updateStatusItem(intItemID, status, newUser);
					userItems = todo.findUserItems(newUser);
					session.setAttribute("userItems", userItems);	
					request.getRequestDispatcher("/entered.jsp").forward(request, response);
				} 
				catch (ExceptionToDoListDAO e) 
				{	
					e.printStackTrace();
					request.getRequestDispatcher("/error.jsp").forward(request, response);
				}	
			}
			break;
			
		case "logout":
			session.invalidate();
			request.getRequestDispatcher("/index.html").forward(request, response);
			break;
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String username,password,rePassword;
		String newPassword,itemDesc,itemID;
		List<Item> userItems = null;
		RequestDispatcher dispatcher = null;
		User newUser = null;
		//int flag=0;
		String theCommand = request.getParameter("command");
		HttpSession session = request.getSession();
		switch(theCommand)
		{
		case "login":
			 username = request.getParameter("userName");
			 password = request.getParameter("password");
			try
			{
				if(username.equals("") || password.equals(""))
				{
					request.setAttribute("message","you forgot to fill password/username");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}
				
				boolean userExist = todo.searchUserbool(username, password);
					if(userExist)
					{
						User user = todo.searchUser(username);
						userItems = todo.findUserItems(user);
						session.setAttribute("userItems", userItems);
						session.setAttribute("loginUser", user);
						request.getRequestDispatcher("/entered.jsp").forward(request, response);
					}
					else
					{
						request.setAttribute("message","user/password incorrect");
						request.getRequestDispatcher("/login.jsp").forward(request, response);
					}

			}
			catch (ExceptionToDoListDAO e)
			{	
				e.printStackTrace();
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
			break;
			
		case "register" :
			Iterator<User> iterator = null;
			username = request.getParameter("userName");
			password = request.getParameter("password");
			rePassword = request.getParameter("repassword");
			if(!(password.equals(rePassword)))
			{
				request.setAttribute("message", "The password you've enterd are not the same, please try agian");
				request.getRequestDispatcher("/register.jsp").forward(request, response);
			}
			else
			{
				if (password.equals("") || username.equals("")) 
				{
					request.setAttribute("message", "You have to fill in both User name and Password");
					request.getRequestDispatcher("/register.jsp").forward(request, response);
				} 
				else
				{
					try
					{
						User user = todo.searchUser(username);
						if(user != null)
						{
							request.setAttribute("message","The username is already exist");
							request.getRequestDispatcher("/register.jsp").forward(request, response);
							//flag=1;	
						}
						else//if(flag==0)
						{
							user = new User(username,password);
							todo.addUser(user);
							session.setAttribute("loginUser", user); 
							request.setAttribute("message", "WELCOME TO OUR TODOLIST WEB! PLEASE ENTER YOUR DEATILS");
							request.getRequestDispatcher("/login.jsp").forward(request, response);
						}
					}
					catch (ExceptionToDoListDAO e)
					{
						e.printStackTrace();
						request.getRequestDispatcher("/error.jsp").forward(request, response);
					}		
				}
			}	
			break;
			
		case "addItem":
			itemDesc =  request.getParameter("itemDescription");
			newUser = (User)session.getAttribute("loginUser");
			if(itemDesc.equals(""))
			{
				request.setAttribute("message", "YOU HAVE TO FILL THE FIELD");
				request.getRequestDispatcher("/addItem.jsp").forward(request, response);
			}
			else
			{
				Item newItem = new Item(newUser.getUserName(),itemDesc);
				try
				{
					todo.addItem(newItem);
					userItems = todo.findUserItems(newUser);
					session.setAttribute("userItems", userItems);
					request.getRequestDispatcher("/entered.jsp").forward(request, response);	
				}
				
				catch (ExceptionToDoListDAO e)
				{
					e.printStackTrace();
					request.getRequestDispatcher("/error.jsp").forward(request, response);
				}
			}
			break;

		case "changePassword":
			String oldPassword,repPassword;
			newUser = (User) session.getAttribute("loginUser");
			oldPassword = request.getParameter("oldPassword");
			newPassword = request.getParameter("newPassword");
			repPassword = request.getParameter("rePassword");
		
			if((oldPassword == "") || (newPassword == "") || (repPassword == ""))
			{
				request.setAttribute("message", "your have to fill all fields");
				request.getRequestDispatcher("/updatePassword.jsp").forward(request, response);
			}
			else
			{
					if (!(oldPassword.equals(newUser.getPassword())))
					{
					request.setAttribute("message", "your old password is not correct");
					request.getRequestDispatcher("/updatePassword.jsp").forward(request, response);	
					}
					else
					{
						if(!(newPassword.equals(repPassword)))
						{
							request.setAttribute("message", "your password repeat dosent match to your new password");
							request.getRequestDispatcher("/updatePassword.jsp").forward(request, response);
						}
						else
						{
							if(newPassword.equals(newUser.getPassword()))
							{
								request.setAttribute("message", "you entered your current password, please enter a different one");
								request.getRequestDispatcher("/updatePassword.jsp").forward(request, response);
							}
							else
							{ 
								try
								{
									todo.updateUserPassword(newUser,newPassword);
									request.setAttribute("message", "YOUR PASSWORD CHANGED SUCCSEFULLY!");
									request.getRequestDispatcher("/entered.jsp").forward(request, response);
								}
								catch (ExceptionToDoListDAO e)
								{
									e.printStackTrace();
									request.getRequestDispatcher("/error.jsp").forward(request, response);						
								}
							}
						}
					}
			}
			break;
		}	
		
	}	
}