package catalog; 

/**
 * Catalog interface
 * @author Dan
 *
 */
public interface Catalog {
	public void addCatalogItem(CatalogItem catalogItem);
	
	public void removeCatalogItem(CatalogItem catalogItem);
	
	public CatalogItem getCatalogItem(int arrayIndex);
	
	public CatalogItemIterator iterator(CatalogItemType TYPE);

}
