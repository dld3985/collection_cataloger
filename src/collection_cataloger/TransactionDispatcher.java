package collection_cataloger;

public interface TransactionDispatcher {

	public TransactionResponse handleTransaction(TransactionObject transaction, TransactionResponse transactionResponse);
}
