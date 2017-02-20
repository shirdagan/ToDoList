package il.ac.hit.model;

import org.hibernate.HibernateException;

/**
 * 
 * Class that represent exception that belong to this project.
 *
 */
public class ExceptionToDoListDAO extends Exception
{
	/**
	 * Method that send a message that describe the exception to the user.
	 * @param msg is a string that represent the exception that occurred.
	 */
	public ExceptionToDoListDAO(String msg)
	{
		super(msg);
	}
/**
 * 
 * @param msg is a string that represent the exception that has just occurred.
 * @param arg1 is a Throwable that represent error stack.
 */
	public ExceptionToDoListDAO(String msg, Throwable arg1)
	{
		super(msg, arg1);
	}
}
