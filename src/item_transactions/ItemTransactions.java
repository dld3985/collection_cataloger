package item_transactions;

import java.util.ArrayList;

import account_utilities.User;
import catalog.CatalogItem;

public abstract class ItemTransactions {

	abstract void selectItems(ArrayList<CatalogItem> items);

	abstract void selectUsers(ArrayList<User> users);

	abstract void updateItemState();

	abstract void updateUsers(ArrayList<User> users);

	// template method
	public final void transaction(ArrayList<CatalogItem> items, ArrayList<User> users){
 
			selectItems(items);
 
			selectUsers(users);

			updateItemState();
			
			updateUsers(users);
	}

}
