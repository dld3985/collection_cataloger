package dao; 
  
import collection_cataloger.TransactionObject;
import collection_cataloger.TransactionResponse; 

public interface UserAccountDAO {

	public TransactionResponse validateAccount(TransactionObject transaction, TransactionResponse transactionResponse);
}