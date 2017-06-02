package collection_cataloger;

import catalog.CatalogItemType;

public class RequestObject {
 
	private String username; 
	private String password;
	private String request;
 
	private int catalogItemID; 
	private String itemName; 
	private String itemDescription;
	private CatalogItemType TYPE;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public int getCatalogItemID() {
		return catalogItemID;
	}

	public void setCatalogItemID(int catalogItemID) {
		this.catalogItemID = catalogItemID;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public CatalogItemType getTYPE() {
		return TYPE;
	}

	public void setTYPE(CatalogItemType tYPE) {
		TYPE = tYPE;
	}

}
