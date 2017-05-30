package account_utilities;

import java.sql.*;
import java.util.ArrayList;

import beans.AddressBean;
import beans.Catalog;
import beans.CatalogItem;
import beans.CatalogItemType;
import beans.ItemFactory;
import beans.LoginBean;
import beans.User;
import collection_cataloger.RequestObject;
import collection_cataloger.ResponseType;
import collection_cataloger.TransactionResponse;
import collection_cataloger.TransactionType;
import database_utilities.ConnectionProvider;


public class UserAccountDAOImpl implements UserAccountDAO{

	/**
	 * This will validate the user logging in to the system. If successful, a user object will be created.
	 * User catalogs will be build using factory pattern.
	 * A clone of the user will be built
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
			if(status){
				transactionResponse.setStatus(ResponseType.SUCCESS);
				transactionResponse.setIsLoggedIn(ResponseType.USER_LOGGED_IN);
				//Build User object on success
				createUserObject(requestObject.getUsername());
			}else{
				transactionResponse.setStatus(ResponseType.ERROR);
				transactionResponse.setMessage("Username or password incorrect, please try again.");
			}

		} catch (Exception e) {
			transactionResponse.setStatus(ResponseType.ERROR);
			transactionResponse.setMessage("An error has occured, please contact the administrator.");
		}

		return transactionResponse;

	}

	 
	@Override
	public TransactionResponse viewCatalog(RequestObject requestObject, TransactionResponse transactionResponse) {
		
		TransactionResponse localResponse = validateAccount(requestObject, transactionResponse);
		if(localResponse.getStatus().equals(ResponseType.SUCCESS)){
			
		}else{
			transactionResponse.setStatus(ResponseType.ERROR);
			transactionResponse.setMessage("User is not logged in with proper credentials.");
		}
		return transactionResponse;
	}
	
	private void createUserObject(String username) throws CloneNotSupportedException{
		//Connect to database to get user information including address information for each of their addresses
		//Will just use mock data for now
		System.out.println("SIMULATING BUILDING USER OBJECT");
		String firstName = "Dan";
		String lastName = "Dumouchel";
		String email = "dld3985@gmail.com";
		
		String street = "14 Somewhere St.";
		String city = "Webster";
		String state = "MA";
		String country = "USA";
		String zipCode = "01570";
 
		AddressBean address = new AddressBean();
		address.setStreet(street);
		address.setCity(city);
		address.setState(state);
		address.setCountry(country);
		address.setZipCode(zipCode);
		
		System.out.println("GETTING ADDRESSES");
		ArrayList<AddressBean> addresses = new ArrayList<AddressBean>();
		addresses.add(address);
		
		//BUILD VIDEOGAME ITEM
		System.out.println("BUILD CATALOG ITEM USING FACTORY");
		ItemFactory factory = new ItemFactory();
		RequestObject requestObjectVideogame = new RequestObject();
		requestObjectVideogame.setCatalogItemID(1);
		requestObjectVideogame.setRequest(TransactionType.VIEW_ACCOUNT.toString());
		requestObjectVideogame.setItemName("Super Mario Bros.");
		requestObjectVideogame.setItemDescription("Classic Mario NES game");
		
		RequestObject requestObjecMovie = new RequestObject();
		requestObjecMovie.setCatalogItemID(2);
		requestObjecMovie.setRequest(TransactionType.VIEW_ACCOUNT.toString());
		requestObjecMovie.setItemName("Star Wars");
		requestObjecMovie.setItemDescription("Star Wars episode IV");
		
		 
		ArrayList<CatalogItem> currentCatalog = new ArrayList<CatalogItem>();
		
		System.out.println("ADDING VIDEOGAME...");
		currentCatalog.add(factory.getCatalogItem(CatalogItemType.VIDEOGAME, requestObjectVideogame));
		System.out.println("ADDING MOVIE...");
		currentCatalog.add(factory.getCatalogItem(CatalogItemType.MOVIE, requestObjecMovie));
		
		ArrayList<Catalog> userCatalogs = new ArrayList<Catalog>();
		Catalog userCatalog = new Catalog(1, "Dans collection", currentCatalog);
		userCatalogs.add(userCatalog);
		
		User dan = new User(1, firstName, lastName, email, addresses, userCatalogs);
		System.out.println(dan.toString());
		
		//Get user catalog information and build object using factory
		System.out.println("CLONING USER TO MOCK CHANGE TO E-MAIL...");
		User danEdits = (User)dan.clone();
		danEdits.setEmail("changedemail@gmail.com");
		
		System.out.println(danEdits);
		
	}
	
}