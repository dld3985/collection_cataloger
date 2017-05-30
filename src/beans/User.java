package beans;

import java.util.ArrayList;

public class User implements Cloneable{

	private String username;
	private String password;
	private String firstName;
	private int userID;
	private String lastName;
	private String email;

	private ArrayList<AddressBean> addresses;
	private ArrayList<Catalog> catalogs;
	
	private User(){
		
	}

	public User(int userID, String firstName, String lastName, String email, ArrayList<AddressBean> addresses,
			ArrayList<Catalog> catalogs) {
		setValues(userID, firstName, lastName, email, addresses, catalogs);
	}

	private void setValues(int userID, String firstName, String lastName, String email,
			ArrayList<AddressBean> addresses, ArrayList<Catalog> catalogs) {
		setUserID(userID);
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setAddresses(addresses);
		setCatalogs(catalogs);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<AddressBean> getAddresses() {
		return addresses;
	}

	public void setAddresses(ArrayList<AddressBean> addresses) {
		this.addresses = addresses;
	}

	public ArrayList<Catalog> getCatalogs() {
		return catalogs;
	}

	public void setCatalogs(ArrayList<Catalog> catalogs) {
		this.catalogs = catalogs;
	}
	
	@Override
	public String toString() {
		return firstName + " " + lastName + " " + email + " " + addresses.toString() + " " + catalogs.toString();
	}
	
	public Object clone()throws CloneNotSupportedException{  
		return super.clone();  
		}  
}

