package il.ac.hit.model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import il.ac.hit.model.Item.Status;

import java.util.ArrayList;
import java.util.List;
/**
 * HibernateToDoListDAO - implements IDoToListDAO interface,represent the methods for users and items action.
 * @author Shani Nemni,Shir Dagan,Sapir Levy.
 */
public class HibernateToDoListDAO implements IToDoListDAO
{
	private SessionFactory factory = new AnnotationConfiguration().configure().buildSessionFactory();
	private static HibernateToDoListDAO  hibernateSingltone;
	
	private HibernateToDoListDAO() 
	{	
	}
/**
 * Singeltone pattern,create a instance  of ToDoListDao.
 * @return one show of HibernateToDoListDAO(instance of ToDoListDao).
 */
	public static HibernateToDoListDAO getInstance()
	{
		if(hibernateSingltone== null)
		{
			hibernateSingltone = new HibernateToDoListDAO();
		}
		 return hibernateSingltone;
	}
	
	/**
	 * delete item form todo list.
	 * @param itemToDelete , item to delete.
	 * @throws ToDoListException(ExceptionToDoListDAO) if there is hibernate error.
	 */
	@Override
	public void deleteItem(Item itemToDelete) throws ExceptionToDoListDAO
	{
		Session session = factory.openSession();
		try
		{
			session.beginTransaction();
			session.delete(itemToDelete);
			session.getTransaction().commit();
		}
		catch(HibernateException ex) 
		{
			session.getTransaction().rollback();
			throw new ExceptionToDoListDAO("(DeleteItem)sorry there was a problem",ex);
		}
		finally
		{
			session.close();
		}	
	}
	/**
	 * add item to todo list.
	 * @param itemToAdd, item to add to todo list item table.
	 * @throws ExceptionToDoListDAO if user is not exist and for hibernate exception.
	 */
	@Override
	public void addItem(Item itemToAdd) throws ExceptionToDoListDAO
	{
		Session session = factory.openSession();
		try
		{
			session.beginTransaction();
			User user = (User)session.get(User.class,itemToAdd.getUserName());
			if(user!=null)
			{
				session.save(itemToAdd);
				session.getTransaction().commit();
			}
			else
			{
				throw new ExceptionToDoListDAO("THE USER IS NOT EXIST");
			}
		}
		catch(HibernateException ex) 
		{
			session.getTransaction().rollback();
			throw new ExceptionToDoListDAO("(AddItem)sorry there was a problem",ex);
		}
		finally
		{
			session.close();
		}	
	}
	
	/**
	 * update item description in the to do list.
	 * @param itemToUpdate,change - itemToUpdate - item to update,change is a string that represent new description for item.
	 * @throws ToDOListException for hibernate error.
	 */
	@Override
	public void updateItemDescription(Item itemToUpdate,String change) throws ExceptionToDoListDAO
	{
		Session session = factory.openSession();
		try
		{
			session.beginTransaction();
			itemToUpdate.setDescriptionOfItem(change);
			session.update(itemToUpdate);
			session.getTransaction().commit();
		}
		catch(HibernateException ex) 
		{
			session.getTransaction().rollback();
			throw new ExceptionToDoListDAO("(UpdateItem)sorry there was a problem",ex);
		}
		finally
		{
			session.close();
		}	
		
	}
	/**
	 * register to the todo list User table - add new user.
	 * @param userToAdd - User to add to the to do list User table.
	 * @throws ExceptionToDoListDAO in case the user(user name) is already exist or hibernate error.
	 */
	@Override
	public void addUser(User userToAdd) throws ExceptionToDoListDAO
	{
		Session session = factory.openSession();
		try
		{
			session.beginTransaction();
			User user = (User)session.get(User.class,userToAdd.getUserName());
			if(user == null)
			{
			session.save(userToAdd);
			session.getTransaction().commit();
			}
			else
			{
				throw new ExceptionToDoListDAO("THE USER IS ALREADY EXIST");
			}
		}
		catch(HibernateException e) 
		{
			session.getTransaction().rollback();
			throw new ExceptionToDoListDAO("(AddUser)sorry there was a problem",e);
		}
		finally
		{
			session.close();
		}	
	}
	/**
	 * update user password in the todolist user table.
	 * @param userToUpdate,passwordToChange - userToUpdate - user that we want to update, and passwordToChange is a String with new password.
	 * @throws ExceptionToDoListDAO for hibernate error or if the user is not exist.
	 */
	
	@Override
	public void updateUserPassword(User userToUpdate,String passwordToChange) throws ExceptionToDoListDAO
	{
		Session session = factory.openSession();
		try
		{
			session.beginTransaction();
			User user = (User)session.get(User.class, userToUpdate.getUserName());
			if(user!=null)
			{
			user.setPassword(passwordToChange);
			session.update(user);
			session.getTransaction().commit();
			}
			else
			{
				throw new ExceptionToDoListDAO("THE USER IS NOT EXIST");
			}
		}
		catch(HibernateException ex) 
		{
			session.getTransaction().rollback();
			throw new ExceptionToDoListDAO("(UpdateUserPassword)sorry there was a problem",ex);
		}
		finally
		{
			session.close();
		}	
	}
	/**
	 * delete user form the todo list user table.
	 * @param UserNameToDelete is user to delete.
	 * @throws ExceptionToDoListDAO for hibernate error or if user is not exsit.
	 */
	@Override
	public void deleteUser(User UserNameToDelete) throws ExceptionToDoListDAO
	{
		Session session = factory.openSession();
		List<Item> items = findAllItem();
		try
		{
			session.beginTransaction();
			User user = (User)session.get(User.class,UserNameToDelete.getUserName());
			if(user!=null)
			{
				for(int i=0;i<items.size();i++)
				{
					if(items.get(i).getUserName().equals(UserNameToDelete.getUserName()))
					{
						System.out.println(items.get(i));
						session.delete(items.get(i));
					}
				}
				System.out.println(user);
				session.delete(user);
				session.getTransaction().commit();
			}
			else
			{
				throw new ExceptionToDoListDAO("THE USER IS NOT EXIST");
			}
		}
		catch(HibernateException ex) 
		{
			session.getTransaction().rollback();
			throw new ExceptionToDoListDAO("(DeleteUser)sorry there was a problem",ex);
		}
		finally
		{
			session.close();
		}	
	}
	
/**
 * find all user form the todo list user table and return a list..
 * @return list of all users in the todo list table.
 * @throws	
 */
	
	@Override
	public List<User> findAllUsers() throws ExceptionToDoListDAO
	{ 
		Session session = factory.openSession();
		List users = new ArrayList<User>();
		try
		{
			session.beginTransaction();
			users = session.createQuery("from User").list();
			session.getTransaction().commit();
		}
		catch(HibernateException e)
		{
			session.getTransaction().rollback();
			throw new ExceptionToDoListDAO("(FindAllUsers)sorry there was a problem",e);
		}
		finally
		{
			session.close();
		}
		return users;
	}
/**
 * find all item,and return all item in table.
 * @throws ExceptionToDoListDAO for hibernate error.
 * @return list of all items.
 */
	
	@Override
	public List<Item>findAllItem() throws ExceptionToDoListDAO
	{
		Session session = factory.openSession();
		List items = new ArrayList<Item>();
		try
		{
			session.beginTransaction();
			items = session.createQuery("from Item").list();
			session.getTransaction().commit();
		}
		catch(HibernateException e)
		{
			session.getTransaction().rollback();
			throw new ExceptionToDoListDAO("(FindAllItems)sorry there was a problem",e);
		}
		finally
		{
			session.close();
		}
		return items;
	}
/**
 * find all items for user,and return list of item for specific user.
 * @param user, user to find all his items.
 * @throws ExceptionToDoListDAO for hibernate error.
 */
	
	@Override
	public List<Item> findUserItems(User user) throws ExceptionToDoListDAO
	{
		Session session = factory.openSession();
		List items = new ArrayList<Item>();
		try
		{
			session.beginTransaction();
			items = session.createQuery("from Item where userName ='"+user.getUserName()+"'").list();
			session.getTransaction().commit();
		}
		catch(HibernateException e)
		{
			session.getTransaction().rollback();
			throw new ExceptionToDoListDAO("(FindUserlItems)sorry there was a problem",e);
		}
		finally
		{
			session.close();
		}
		return items;
	}
/**
 * Search user in the todo list user table.
 * @param userName is a string that represent user name that we search.
 * @throws ExceptionToDoListDAO for hibernate error.
 */

@Override
	public User searchUser(String userName) throws ExceptionToDoListDAO 
	{
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		User user;
		try
		{
			session.beginTransaction();
			user =(User)session.get(User.class,userName);
			if(user!=null)
			{
				session.getTransaction().commit();
			}
		}
		catch(HibernateException ex) 
		{
			session.getTransaction().rollback();
			throw new ExceptionToDoListDAO("(searchUser)sorry there was a problem",ex);
		}
		finally
		{
			session.close();
		}
		return user;
	}

/**
 * Search item in the todo list item table.
 * @param id is a integer that represnt id of item that we want to search.
 * @throws ExceptionToDoListDAO for hibernate error.
 */
@Override
	public Item searchItem(int id) throws ExceptionToDoListDAO
	{
		Session session = factory.openSession();
		Item item;
		try
		{
			session.beginTransaction();
			item = (Item)session.get(Item.class, id);
		}
		catch(HibernateException ex) 
		{
			session.getTransaction().rollback();
			throw new ExceptionToDoListDAO("(searchUser)sorry there was a problem",ex);
		}
		finally
		{
			session.close();
		}
		return item;
	}
/**
 * return true or false if user is exsit in the todo list user table.
 * @param userName,password - userName is a string that represent user name, password is a string that represent password.
 * @throws 
 * 
 */
@Override
public boolean searchUserbool(String userName,String password) throws ExceptionToDoListDAO
{
	//Session session = factory.openSession();
	boolean userExist = false;
	User user = searchUser(userName);
	if(user!=null)
		{
			if(user.getPassword().equals(password))
			{
				userExist = true;
			}
		}
		return userExist;
}

/**
 * update item status in the todo list item table.
 * @param id,newStatusItem,user - id represent id for item,newStatusItem represent a new status for item, user that represent the user,to update.
 * @throws ExceptionToDoListDAO for hibernate error, or no id that belong to the todolist item table,or user is not exsit.
 */
@Override
public void updateStatusItem(int id, Status newStatusItem, User user) throws ExceptionToDoListDAO

{	
	
	Session session = factory.openSession();
	try
	{
		session.beginTransaction();
		User userExsit = searchUser(user.getUserName());
		if(userExsit != null)
		{	
			Item itemExsit = searchItem(id);
			if(itemExsit != null )
			{
				itemExsit.seteStatus(newStatusItem);
				session.update(itemExsit);
				session.getTransaction().commit();
			}	
			else
			{
				throw new ExceptionToDoListDAO("NO ITEM FOR THIS USER");
			}
		}
		else
		{
			throw new ExceptionToDoListDAO("ERROR,USER ISNT ON THE LIST FOR UPDATE");
		}
	}
	catch(HibernateException e)
	{
		session.getTransaction().rollback();
		throw new ExceptionToDoListDAO("HIBERNATE ERROR WHEN WE TRY TO UPDATE USER INFORMATION",e);
	}
	finally
	{
		session.close();
	}
}

}
