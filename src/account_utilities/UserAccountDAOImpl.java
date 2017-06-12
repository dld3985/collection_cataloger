package account_utilities;

import java.sql.*;
import java.util.ArrayList;

import catalog.CatalogImpl;
import catalog.CatalogItem;
import catalog.CatalogItemIterator;
import catalog.CatalogItemType;
import collection_cataloger.RequestObject;
import collection_cataloger.ResponseType;
import collection_cataloger.TransactionResponse;
import collection_cataloger.TransactionType;
import database_utilities.ConnectionProvider;
import item_transactions.ItemTransactions;
import item_transactions.LendItems;
import item_transactions.TradeItems;

public class UserAccountDAOImpl implements UserAccountDAO {

	/**
	 * This will validate the user logging in to the system. If successful, a
	 * user object will be created. User catalogs will be build using factory
	 * pattern. A clone of the user will be built
	 */
	@Override
	public TransactionResponse validateAccount(RequestObject requestObject, TransactionResponse transactionResponse) {
		boolean status = false;

		LoginBean loginBean = new LoginBean();
		loginBean.setUsername(requestObject.getUsername());
		loginBean.setPassword(requestObject.getPassword());
		System.out.println("GETTING USER " + requestObject.getUsername());
		try {
			Connection con = ConnectionProvider.getCon();

			PreparedStatement ps = con.prepareStatement("select * from user where username=? and password=?");

			ps.setString(1, loginBean.getUsername());
			ps.setString(2, loginBean.getPassword());

			ResultSet rs = ps.executeQuery();
			status = rs.next();
			System.out.println(status);
			if (status) {
				transactionResponse.setStatus(ResponseType.SUCCESS);
				transactionResponse.setIsLoggedIn(ResponseType.USER_LOGGED_IN);
				// Build User object on success
				createUserObject(requestObject.getUsername(), transactionResponse);
			} else {
				transactionResponse.setStatus(ResponseType.ERROR);
				transactionResponse.setMessage("Username or password incorrect, please try again.");
			}

		} catch (Exception e) {
			transactionResponse.setStatus(ResponseType.ERROR);
			transactionResponse.setMessage("An error has occured, please contact the administrator.");
		}

		return transactionResponse;

	}

	private TransactionResponse createUserObject(String username, TransactionResponse transactionResponse)
			throws CloneNotSupportedException {
		// Connect to database to get user information including address
		// information for each of their addresses
		// Will just use mock data for now. This will also create a cookie and a
		// user session
		System.out.println("SIMULATING BUILDING USER OBJECT");

		String street = "14 Somewhere St.";
		String city = "Webster";
		String state = "MA";
		String country = "USA";
		String zipCode = "01570";

		String street2 = "3 Elsewhere Ln.";
		String city2 = "Dudley";
		String state2 = "MA";
		String country2 = "USA";
		String zipCode2 = "01571";

		AddressBean address = new AddressBean();
		address.setStreet(street);
		address.setCity(city);
		address.setState(state);
		address.setCountry(country);
		address.setZipCode(zipCode);

		AddressBean address2 = new AddressBean();
		address2.setStreet(street2);
		address2.setCity(city2);
		address2.setState(state2);
		address2.setCountry(country2);
		address2.setZipCode(zipCode2);

		System.out.println("GETTING ADDRESSES");
		ArrayList<AddressBean> addresses = new ArrayList<AddressBean>();
		addresses.add(address);

		ArrayList<AddressBean> addresses2 = new ArrayList<AddressBean>();
		addresses2.add(address2);

		// BUILD TWO SEPERATE CATALOGS
		System.out.println("BUILD CATALOG...");

		CatalogItem item1 = new CatalogItem(1, "Super Mario Bros.", "Classic Mario NES Game",
				CatalogItemType.VIDEOGAME);
		CatalogItem item2 = new CatalogItem(2, "Star Wars", "Star Wars episode IV", CatalogItemType.MOVIE);
		CatalogItem item3 = new CatalogItem(3, "Legend of Zelda", "Classic LoZ NES Game", CatalogItemType.VIDEOGAME);
		CatalogImpl userCatalog = new CatalogImpl();
		userCatalog.addCatalogItem(item1);
		userCatalog.addCatalogItem(item2);
		userCatalog.addCatalogItem(item3);
		ArrayList<CatalogImpl> catalogs = new ArrayList<CatalogImpl>();
		catalogs.add(userCatalog);

		CatalogItem user2item1 = new CatalogItem(1, "Metroid", "Classic Metroid NES Game", CatalogItemType.VIDEOGAME);
		CatalogItem user2item2 = new CatalogItem(2, "Evil Dead", "Chaos within the cabin", CatalogItemType.MOVIE);
		CatalogItem user2item3 = new CatalogItem(3, "Rampage", "NES Game", CatalogItemType.VIDEOGAME);
		CatalogImpl user2Catalog = new CatalogImpl();
		user2Catalog.addCatalogItem(user2item1);
		user2Catalog.addCatalogItem(user2item2);
		user2Catalog.addCatalogItem(user2item3);
		ArrayList<CatalogImpl> catalogs2 = new ArrayList<CatalogImpl>();
		catalogs2.add(user2Catalog);

		// Build Users
		User user = new User(0, "Dan", "Dumouchel", "dld3985@gmail.com", addresses, catalogs);
		User user2 = new User(1, "Monty", "Python", "flyingcirucs@gmail.com", addresses2, catalogs2);

		// ITERATOR PATTERN CALLS
		System.out.println("ITERATE BY VIDEOGAME...");
		CatalogItemIterator videoGameIterator = userCatalog.iterator(CatalogItemType.VIDEOGAME);
		while (videoGameIterator.hasNext()) {
			CatalogItem catalogItem = videoGameIterator.next();
			System.out.println(catalogItem.toString());
		}

		System.out.println("ITERATE BY MOVIE...");
		CatalogItemIterator movieIterator = userCatalog.iterator(CatalogItemType.MOVIE);
		while (movieIterator.hasNext()) {
			CatalogItem catalogItem = movieIterator.next();
			System.out.println(catalogItem.toString());
		}

		System.out.println("ITERATE ALL...");
		CatalogItemIterator all = userCatalog.iterator(CatalogItemType.ALL);
		while (all.hasNext()) {
			CatalogItem catalogItem = all.next();
			System.out.println(catalogItem.toString());
		}

		// STATE PATTERN CALLS
		System.out.println("SET PROFILE TO PUBLIC...");
		PublicProfileState publicProfile = new PublicProfileState();
		publicProfile.doAction(user);

		System.out.println(user.getProfileState().toString());

		System.out.println("SET PROFILE TO PRIVATE...");
		PrivateProfileState privateProfileState = new PrivateProfileState();
		privateProfileState.doAction(user);
		System.out.println(user.getProfileState().toString());

		// TEMPLATE PATTERN FOR TRANSACTIONS
		ArrayList<CatalogItem> tradeItems = new ArrayList<CatalogItem>();
		tradeItems.add(userCatalog.getCatalogItem(2));
		tradeItems.add(user2Catalog.getCatalogItem(0));

		ArrayList<CatalogItem> lendItems = new ArrayList<CatalogItem>();
		lendItems.add(userCatalog.getCatalogItem(1));

		ArrayList<User> users = new ArrayList<User>();
		users.add(user);
		users.add(user2);
		System.out.println("TRADE ITEMS THROUGH TEMPLATE...");
		ItemTransactions tradeTransaction = new TradeItems();
		tradeTransaction.transaction(tradeItems, users);

		System.out.println("LEND ITEMS THROUGH TEMPLATE...");
		ItemTransactions lendTransaction = new LendItems();
		lendTransaction.transaction(lendItems, users);
		
		if(username.equals("dan")){
			transactionResponse.setUsername(username);
			transactionResponse.setCatalogs(catalogs.get(0).toString());
		}else{
			transactionResponse.setUsername(username);
			transactionResponse.setCatalogs(catalogs2.get(0).toString());
		}
	

		return transactionResponse;
	}

	@Override
	public TransactionResponse createAccount(RequestObject requestObject, TransactionResponse transactionResponse) {
		LoginBean loginBean = new LoginBean();
		loginBean.setUsername(requestObject.getUsername());
		loginBean.setPassword(requestObject.getPassword());
		System.out.println("GETTING USER " + requestObject.getUsername());
		try {
			Connection con = ConnectionProvider.getCon();

			String insertTableSQL = "INSERT INTO user" + "(username, password) VALUES" + "(?,?)";

			PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);

			preparedStatement.setString(1, loginBean.getUsername());
			preparedStatement.setString(2, loginBean.getPassword());

			// execute insert SQL stetement
			preparedStatement.executeUpdate();
			System.out.println("CREATING NEW ACCOUNT SUCCSSESSFUL");
			transactionResponse.setStatus(ResponseType.SUCCESS); 
			// Build User object on success 

		} catch (Exception e) {
			transactionResponse.setStatus(ResponseType.ERROR);
			transactionResponse.setMessage("An error has occured, please contact the administrator.");
		}

		return transactionResponse;
	}
}