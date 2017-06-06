package item_transactions;

import java.util.ArrayList;

import account_utilities.User;
import catalog.CatalogItem;
import catalog.CatalogItemStates;

public class TradeItems extends ItemTransactions {

	@Override
	void selectItems(ArrayList<CatalogItem> items) {
		System.out.println(items.get(0).getName() + " and " + items.get(1).getName() + " are going to be traded.");

	}

	@Override
	void selectUsers(ArrayList<User> users) {
		System.out.println(
				"Items will be traded between " + users.get(0).getFirstName() + " and " + users.get(1).getFirstName());

	}

	@Override
	void updateItemState() {
		System.out.println("Item state has been set to " + CatalogItemStates.USED);

	}

	@Override
	void updateUsers(ArrayList<User> users) {
		System.out.println(users.get(0).getFirstName() + " and " + users.get(1).getFirstName()
				+ " have been notified of the transaction.");

	}

}
