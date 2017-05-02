package transactionloader.processor;

import transactionloader.categorisation.Categoriser;
import transactionloader.domain.AccountTrackerTransaction;
import transactionloader.domain.HSBCTransaction;
import transactionloader.io.Reader;
import transactionloader.transform.RowToTransactionTransformer;
import transactionloader.transform.TransactionToRowTransformer;
import transactionloader.transform.TransactionToTransactionTransformer;
import transactionloader.transform.Transformer;

import java.io.IOException;
import java.util.List;

/**
 * Created by rebeccafulton on 02/05/2017.
 */
public class Processor implements TransactionProcessor {

    private Transformer<List<String>, HSBCTransaction> transformer1 = new RowToTransactionTransformer();
    private Transformer<HSBCTransaction, AccountTrackerTransaction> transformer2;
    private Transformer<AccountTrackerTransaction, List<String>> transformer3 = new TransactionToRowTransformer();
    private Categoriser categoriser;

    public Processor(Categoriser categoriser, String account) {
        this.categoriser = categoriser;
        transformer2 = new TransactionToTransactionTransformer(account);
    }

    @Override
    public List<String> processTransaction(List<String> input) {
        HSBCTransaction hsbcTransaction = transformer1.transform(input);
        AccountTrackerTransaction accountTrackerTransaction = transformer2.transform(hsbcTransaction);
        categoriser.categorise(accountTrackerTransaction);
        return transformer3.transform(accountTrackerTransaction);
    }
}
