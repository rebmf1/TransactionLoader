package transactionloader.processor;

import transactionloader.domain.Transaction;

public interface TransactionProcessor {

	Transaction processTransaction(Transaction input);
	
}
