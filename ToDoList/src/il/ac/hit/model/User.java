package il.ac.hit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * User class represent user properties.
 * @author Shani Nemni,Shir Dagan,Sapir levy.
 *
 */
@Entity
@Table(name="user")
public class User
{
	@Id
	@Column(name="user_name")
	private String userName;
	
	@Column(name="password")
	private String password;
/**
 * Default C'tor.	
 */
	public User()
	{
	}
/**
 * C'tor that initialize this class members with given values.	
 * @param userName represnt user name.
 * @param password represnt password for user.
 */
	public User(String userName,String password)
	{
		this.userName=userName;
		this.password=password;
	}
/**
 * Get user name.
 * @return user name.
 */
	public String getUserName() {
		return userName;
	}
/**
 * Set user name.
 * @param userName to set.
 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
/**
 * Get password.
 * @return password for user.
 */
	public String getPassword() {
		return password;
	}
/**
 * Set password.
 * @param passWord to sat.
 */
	public void setPassword(String passWord) {
		this.password = passWord;
	}	
}
