package il.ac.hit.controller;

import javax.ws.rs.*;
import il.ac.hit.model.*;
@Path("/itemdescription/{itemid}")
public class RestfulController
{
	@GET
	@Produces("text/html")
	public String returnUser(@PathParam("itemid") int id)
	{
		IToDoListDAO todo = HibernateToDoListDAO.getInstance();
		String helloUser = null;
		try 
		{
			Item item = todo.searchItem(id);
			helloUser = ("THE DESCRIPTION OF THE ITEM IS: " + item.getDescriptionOfItem());
		} 
		catch (ExceptionToDoListDAO e) 
		{
			e.printStackTrace();
		}
		return helloUser;
	}
}

