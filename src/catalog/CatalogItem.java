package catalog;

public class CatalogItem {

	private int catalogItemID;
	private String name;
	private String description;
	private CatalogItemType TYPE;

	public CatalogItem(int catalogItemID, String name, String description, CatalogItemType TYPE) {
		setValues(catalogItemID, name, description, TYPE);
	}

	private void setValues(int catalogItemID, String name, String description, CatalogItemType TYPE) {
		setCatalogItemID(catalogItemID);
		setName(name);
		setDescription(description);
		setTYPE(TYPE);
	} 
	

	public int getCatalogItemID() {
		return catalogItemID;
	}

	public void setCatalogItemID(int catalogItemID) {
		this.catalogItemID = catalogItemID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	public CatalogItemType getTYPE() {
		return TYPE;
	}

	public void setTYPE(CatalogItemType tYPE) {
		TYPE = tYPE;
	}
	
	@Override
	public String toString() {
		return "'" + name + "' : " + "'" + description + "'" + "' : " + "'" + TYPE.toString() + "'";
	}
}
