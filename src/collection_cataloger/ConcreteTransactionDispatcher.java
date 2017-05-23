package collection_cataloger;

import account_utilities.UserAccountDAOImpl;
import beans.LoginBean;
import dao.UserAccountDAO;

public class ConcreteTransactionDispatcher implements TransactionDispatcher{

	@Override
	public TransactionResponse handleTransaction(TransactionObject transaction, TransactionResponse transactionResponse) {
	
		//get first field for transaction
		String transactionType = transaction.getTransaction();
		
		//case of login, look for username and password
		if(transactionType.equals(TransactionType.LOGIN.toString())){
			//if exists, create login bean and send to user account DAO for verification
			 
			UserAccountDAO userUtil = new UserAccountDAOImpl();
			transactionResponse = userUtil.validateAccount(transaction, transactionResponse);
		}
		return transactionResponse;
	}

}
