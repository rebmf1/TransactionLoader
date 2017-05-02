package transactionloader.transform;

import transactionloader.domain.AccountTrackerTransaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rebeccafulton on 27/03/2017.
 */
public class TransactionToRowTransformer implements Transformer<AccountTrackerTransaction, List<String>> {

    @Override
    public List<String> transform(AccountTrackerTransaction accountTrackerTransaction) {
        List<String> row = new ArrayList<>();
        row.add(accountTrackerTransaction.getAccount());
        row.add(accountTrackerTransaction.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        row.add(accountTrackerTransaction.getDetails());
        row.add(accountTrackerTransaction.getCategory());
        row.add(accountTrackerTransaction.getNotes());
        row.add(accountTrackerTransaction.getChequeNumber());
        row.add(String.valueOf(accountTrackerTransaction.getAmount()));
        row.add(accountTrackerTransaction.isReconciled() ? "Y" : "N");
        return row;
    }
}