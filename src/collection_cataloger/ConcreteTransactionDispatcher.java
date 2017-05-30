package collection_cataloger;

import account_utilities.UserAccountDAO;
import account_utilities.UserAccountDAOImpl;
import beans.LoginBean;

/**
 * Dispatcher to handle incoming requests. Requests are in JSON, and should
 * contain a transaction type in that object. This will pass the requests for
 * each user action and pass it to the corresponding interface to handle that
 * transaction. A response will return containing the success of error message of that transaction 
 * and this will be passed back to the main servlet. 
 * 
 * @author Dan Dumouchel
 *
 */
public class ConcreteTransactionDispatcher implements TransactionDispatcher {

	@Override
	public TransactionResponse getTransaction(RequestObject requestObject, TransactionResponse transactionResponse) {

		// get first field for transaction
		String transactionType = requestObject.getRequest();

		// case of login, look for username and password
		if (transactionType.equals(TransactionType.LOGIN.toString())) {
			// if exists, create login bean and send to user account DAO for
			// verification

			UserAccountDAO userUtil = new UserAccountDAOImpl();
			transactionResponse = userUtil.validateAccount(requestObject, transactionResponse);
		}
		return transactionResponse;
	}

}
