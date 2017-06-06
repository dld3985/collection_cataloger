package item_transactions;

import java.util.ArrayList;

import account_utilities.User;
import catalog.CatalogItem;
import catalog.CatalogItemStates;

public class LendItems extends ItemTransactions{

	@Override
	void selectItems(ArrayList<CatalogItem> items) {
		System.out.println(items.get(0).getName() + " is going to be lended.");
		
	}

	@Override
	void selectUsers(ArrayList<User> users) {
		System.out.println("Item will be lended from " + users.get(0).getFirstName() + " to " + users.get(1).getFirstName());
		
	}

	@Override
	void updateItemState() {
		System.out.println("Item state has been set to " + CatalogItemStates.BORROWED);
		
	}

	@Override
	void updateUsers(ArrayList<User> users) {
		System.out.println(users.get(0).getFirstName() + " and " + users.get(1).getFirstName() + " Have been notified of the transaction.");
		
	} 

}
