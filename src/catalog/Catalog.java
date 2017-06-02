package catalog;

 

public interface Catalog {
	public void addCatalogItem(CatalogItem catalogItem);
	
	public void removeCatalogItem(CatalogItem catalogItem);
	
	public CatalogItemIterator iterator(CatalogItemType TYPE);

}
