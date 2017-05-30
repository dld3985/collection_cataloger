package beans;

import collection_cataloger.RequestObject;

public class ItemFactory {

	public CatalogItem getCatalogItem(CatalogItemType catalogItemType, RequestObject requestObject){
		
		if(catalogItemType == CatalogItemType.MOVIE){
			return new Movie(requestObject.getCatalogItemID(), requestObject.getItemName(), requestObject.getItemDescription());
		}
		
		if(catalogItemType == CatalogItemType.VIDEOGAME){
			return new Videogame(requestObject.getCatalogItemID(), requestObject.getItemName(), requestObject.getItemDescription());
		}
		return null;
	}
}
