package il.ac.hit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Item class represnt item properties.
 * @author Shani Nemni,Shir Dagan,Sapir Levy.
 *
 */
@Entity
@Table(name="item")
public class Item
{
	/**
	 * Status represnt item status.
	 */
	public enum Status {DONE, IN_PROGRESS,NOT_DONE}
	@Id
	@GeneratedValue
	@Column(name="item_id")
	private int itemId; 
	
	@Column(name="user_name")
	private String userName;	
	
	@Column(name="description_of_task")
	private String descriptionOfItem;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status_of_task")
	private Status eStatus;
	
/**
 * Default C'tor.
 */
	public Item()
	{
		
	}
/**
 * C'tor that initialize this class members with given values.
 * @param userName of item's owner.
 * @param descriptionOfItem decription about the item.
 */
	public Item(String userName,String descriptionOfItem)
	{
		this.setUserName(userName);
		this.setDescriptionOfItem(descriptionOfItem);
		this.seteStatus(Status.NOT_DONE);
	}
	/**
	 * Get User Name
	 * @return user Name.
	 */
	public String getUserName()
	{
		return userName;
	}
	/**
	 * Set user name.
	 * @param userName to set.
	 */
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	/**
	 * Get item id.
	 * @return id.
	 */
	
	public int getItemId() {
		return itemId;
	}
/**
 * Set item id.
 * @param itemId to set.
 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	/**
	 * Get description about item.
	 * @return description about item.
	 */
	public String getDescriptionOfItem()
	{
		return descriptionOfItem;
	}
	/**
	 * Set description for item.
	 * @param descriptionOfItem to set.
	 */
	public void setDescriptionOfItem(String descriptionOfItem)
	{
		this.descriptionOfItem = descriptionOfItem;
	}
	/**
	 * Get status item.
	 * @return status item.
	 */
	public Status geteStatus()
	{
		return eStatus;
	}
	/**
	 * Set status for item.
	 * @param eStatus status to set.
	 */
	public void seteStatus(Status eStatus)
	{
		this.eStatus = eStatus;
	}	
}
