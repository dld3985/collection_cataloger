package collection_cataloger;

public class TransactionResponse {
	private String status;
	private String message;
	//will be passed to track a user has successfully logged in. Insecure and just for ease in project implementation
	private String isLoggedIn;

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
}
