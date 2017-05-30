package beans;

import java.util.ArrayList;

import collection_cataloger.RequestObject;

public class Catalog {
	private int catalogID;
	private String catalogName;
	private ArrayList<CatalogItem> catalogItems;
	
	public Catalog(int catalogID, String catalogName, ArrayList<CatalogItem> catalogItems) {
		setValues(catalogID, catalogName, catalogItems);
	}

	private void setValues(int catalogID, String catalogName, ArrayList<CatalogItem> catalogItems) {
		setCatalogID(catalogID);
		setCatalogName(catalogName);
		setCatalogItems(catalogItems);
	}

	public int getCatalogID() {
		return catalogID;
	}

	public void setCatalogID(int catalogID) {
		this.catalogID = catalogID;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public ArrayList<CatalogItem> getCatalogItems() {
		return catalogItems;
	}

	public void setCatalogItems(ArrayList<CatalogItem> catalogItems) {
		this.catalogItems = catalogItems;
	}
	
	public ArrayList<CatalogItem> addCatalogItems(ArrayList<CatalogItem> catalogItems, RequestObject requestObject){
		
		return catalogItems;
		
	}
	
	@Override
	public String toString() {
		return "'" + catalogName + "' : " + "'" + catalogItems.toString() + "'";
	}
}
