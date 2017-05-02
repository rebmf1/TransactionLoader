package transactionloader.processor;

import transactionloader.domain.HSBCTransaction;

import java.util.List;

public interface TransactionProcessor {

	List<String> processTransaction(List<String> input);
	
}
