package transactionloader.transform;

import transactionloader.domain.HSBCTransaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by rebeccafulton on 27/03/2017.
 */
public class RowToTransactionTransformer implements Transformer<List<String>, HSBCTransaction> {

    private static final String DATE_TIME_FORMAT = "dd/MM/yyyy";

    @Override
    public HSBCTransaction transform(List<String> row) {
        return new HSBCTransaction(LocalDate.parse(
                row.get(0).replaceAll(" ", "").replaceAll("[^0-9/]", ""), DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)),
                row.get(1),
                Double.parseDouble(row.get(2).replaceAll("[ \",]", "")));
    }
}
