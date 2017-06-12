package collection_cataloger;

public class TransactionResponse {
	private String status;
	private String message;
	//will be passed to track a user has successfully logged in. Insecure and just for ease in project implementation
	private String isLoggedIn;
	private String catalogs;
	private String username;

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getIsLoggedIn() {
		return isLoggedIn;
	}
	public void setIsLoggedIn(String isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	public String getCatalogs() {
		return catalogs;
	}
	public void setCatalogs(String catalogs) {
		this.catalogs = catalogs;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
