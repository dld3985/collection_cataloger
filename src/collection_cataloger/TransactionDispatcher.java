package collection_cataloger;

public interface TransactionDispatcher {

	public TransactionResponse getTransaction(RequestObject request, TransactionResponse transactionResponse);
}
