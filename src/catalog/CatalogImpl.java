package catalog;

import java.util.ArrayList;

public class CatalogImpl implements Catalog{
	
	private ArrayList<CatalogItem> catalogItems;
	
	public CatalogImpl() {
		catalogItems = new ArrayList<CatalogItem>();
	}

	@Override
	public void addCatalogItem(CatalogItem catalogItem) {
		this.catalogItems.add(catalogItem);
		
	}

	@Override
	public void removeCatalogItem(CatalogItem catalogItem) {
		this.catalogItems.remove(catalogItem);
		
	}

	@Override
	public CatalogItemIterator iterator(CatalogItemType TYPE) { 
		return new CatalogItemIteratorImpl(TYPE, this.catalogItems);
	}
	 
}
