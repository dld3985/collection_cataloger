package account_utilities;

import javax.servlet.http.HttpServletResponse;

import collection_cataloger.RequestObject;
import collection_cataloger.TransactionResponse;

/**
 * This interface will handle validating user actions. It will act as a facade
 * to all the user object creation within the account_utlities package.
 * 
 * @author Dan Dumouchel
 *
 */
public interface UserAccountDAO {

	public TransactionResponse validateAccount(RequestObject requestObject, TransactionResponse transactionResponse);

	public TransactionResponse createAccount(RequestObject requestObject, TransactionResponse transactionResponse);
	 
}