package transactionloader.transform;

import transactionloader.domain.AccountTrackerTransaction;
import transactionloader.domain.HSBCTransaction;

/**
 * Created by rebeccafulton on 27/03/2017.
 */
public class TransactionToTransactionTransformer implements Transformer<HSBCTransaction, AccountTrackerTransaction> {

    private String account;

    public TransactionToTransactionTransformer(String account) {
        this.account = account;
    }

    @Override
    public AccountTrackerTransaction transform(HSBCTransaction hsbcTransaction) {
        return new AccountTrackerTransaction(
                account,
                hsbcTransaction.getDate(),
                hsbcTransaction.getDescription(),
                "",
                "",
                hsbcTransaction.getAmount(),
                false
        );
    }
}
