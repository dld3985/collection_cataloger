package catalog;

public interface CatalogItemIterator {

	// true if iterator "points" past the last element:
	public boolean hasNext();

	// Causes the iterator to point to its next element:
	public CatalogItem next();

}
