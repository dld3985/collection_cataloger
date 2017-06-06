package catalog;

import java.util.ArrayList;

/**
 * Implementation of the CatalogItemIterator for sorting by catalog item type.
 * 
 * @author Dan
 *
 */
public class CatalogItemIteratorImpl implements CatalogItemIterator{
	
	private CatalogItemType TYPE;
	private ArrayList<CatalogItem> catalogItems;
	private int position;
	
	public CatalogItemIteratorImpl(CatalogItemType TYPE,
			ArrayList<CatalogItem> catalogItems) {
		this.TYPE = TYPE;
		this.catalogItems = catalogItems;
	}

	@Override
	public boolean hasNext() {
		while (position < catalogItems.size()) {
			CatalogItem catalogItem = catalogItems.get(position);
			if (catalogItem.getTYPE().equals(TYPE) || TYPE.equals(CatalogItemType.ALL)) {
				return true;
			} else
				position++;
		}
		return false;
	}

	@Override
	public CatalogItem next() {
		CatalogItem catalogItem = catalogItems.get(position);
		position++;
		return catalogItem;
	}
 
 

}
