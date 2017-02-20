package il.ac.hit.model;
import java.util.List;

import il.ac.hit.model.Item.Status;
/**
 * IToDoListDAO is a interface represent the methods for Item and User.
 * @author Shani Nemni,,Shir Dagan,Sapir levy.
 *
 */
public interface IToDoListDAO
{
	/**
	 * Delete Item form the DataBase(item table).
	 * @param ItemToDelelte represent item that we want to delete.
	 * @throws ExceptionToDoListDAO if the delete failed.
	 */
	public void deleteItem(Item ItemToDelelte) throws ExceptionToDoListDAO;
	/**
	 * Add Item to the DataBase(item table).
	 * @param itemToAdd represent item that we want to add.
	 * @throws ExceptionToDoListDAO if adding failed.
	 */
	public void addItem(Item itemToAdd) throws ExceptionToDoListDAO;
	/**
	 * Update item description in the Item table.
	 * @param itemToUpdate represent item that we want to update.
	 * @param change is a string that represent the new description.
	 * @throws ExceptionToDoListDAO if update failed.
	 */
	public void updateItemDescription(Item itemToUpdate,String change) throws ExceptionToDoListDAO;
	/**
	 * Delete user form the DataBase(User table).
	 * @param UserNameToDelete represent user that we want to delete.
	 * @throws ExceptionToDoListDAO if delete falied.
	 */
	public void deleteUser(User UserNameToDelete) throws ExceptionToDoListDAO;
	/**
	 * Add user for DataBase(User table).
	 * @param userToAdd represent user to add to user table.
	 * @throws ExceptionToDoListDAO if adding failed.
	 */
	public void addUser(User userToAdd) throws ExceptionToDoListDAO;
	/**
	 * Update user password in the DataBase(User table).
	 * @param userToUpdate represent User that we want to update his password. 
	 * @param passwordToChange represent String with new password. 
	 * @throws ExceptionToDoListDAO if update failed.
	 */
	public void updateUserPassword(User userToUpdate,String passwordToChange) throws ExceptionToDoListDAO;
	/**
	 * Retrun all users form the DataBase(User table).
	 * @return list of all Users.
	 * @throws ExceptionToDoListDAO if getting the list from DataBase failed.
	 */
	public List<User> findAllUsers() throws ExceptionToDoListDAO;
	/**
	 * Find user from the DataBase(User table).
	 * @param userName represent user that we look foor.
	 * @return the User if he exsit.
	 * @throws ExceptionToDoListDAO if searching failed.
	 */
	public User searchUser(String userName) throws ExceptionToDoListDAO;
	/**
	 * Return true or false if user is exsit.
	 * @param userName represent user that we want to look for in the User table.
	 * @param password represent a password for user.
	 * @return True or False if item exsit.
	 * @throws ExceptionToDoListDAO if user dont found.
	 */
	public boolean searchUserbool(String userName,String password) throws ExceptionToDoListDAO;
	/**
	 * Return list from DataBase(Item table) with all items.
	 * @return list of all items.
	 * @throws ExceptionToDoListDAO if getting all items form DataBase failed.
	 */
	public List<Item> findAllItem() throws ExceptionToDoListDAO;
	/**
	 * Return list from DataBase(Item table) ,with user's item.
	 * @param user represent user that we want to get his items form the DataBase.
	 * @return list of user's item.
	 * @throws ExceptionToDoListDAO if getting user's item from DataBase failed.
	 */
	public List<Item> findUserItems(User user) throws ExceptionToDoListDAO;
	/**
	 * Search item in the DataBase(Item table).
	 * @param id represent id of item that we want to look for.
	 * @return item that we look for by id.
	 * @throws ExceptionToDoListDAO if searching failed.
	 */
	public Item searchItem(int id) throws ExceptionToDoListDAO;
	/**
	 * Update status for item in the DataBase(Item table).
	 * @param id represent id of item that we want to update.
	 * @param newStatusItem represent new staus for item.
	 * @param user represent the user name that the item belong to him.
	 * @throws ExceptionToDoListDAO if updating failed.
	 */
	public void updateStatusItem(int id,Status newStatusItem, User user) throws ExceptionToDoListDAO;
	
} 
