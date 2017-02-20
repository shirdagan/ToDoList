package il.ac.hit.tag;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;

public class HelloTag extends SimpleTagSupport 
{
	private String nameOfUser;
	
	public String getNameOfUser()
	{
		return nameOfUser;
	}

	public void setNameOfUser(String nameOfUser)
	{
		this.nameOfUser = nameOfUser;
	}

	public void doTag() throws JspException, IOException 
	{
		JspWriter out = getJspContext().getOut();
		if(nameOfUser != null)
		{
			out.println("Hello and welcome! from "+ nameOfUser);
		}
		else
		{
			out.println("Hello,you are not logged yet");
		}
		//JspWriter out = getJspContext().getOut();
		//out.print("HELLO!");
		
	}	
}
