package beans;

public abstract class CatalogItem {

	private int catalogItemID;
	private String name;
	private String description;

	public CatalogItem(int catalogItemID, String name, String description) {
		setValues(catalogItemID, name, description);
	}

	private void setValues(int catalogItemID, String name, String description) {
		setCatalogItemID(catalogItemID);
		setName(name);
		setDescription(description);
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

	@Override
	public String toString() {
		return "'" + name + "' : " + "'" + description + "'";
	}
}
