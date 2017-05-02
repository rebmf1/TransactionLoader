package transactionloader.categorisation;

import transactionloader.domain.AccountTrackerTransaction;
import transactionloader.ui.UserQuery;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

public class Categoriser {

    private static final String CATEGORY_QUERY_TEXT = "Please provide category for \n%s";

    private Cache cache;

    public Categoriser(Cache cache) {
        this.cache = cache;
    }

    public void categorise(AccountTrackerTransaction transaction) {
        Optional<String> cachedCategory = cache.getCategory(transaction.getDetails());
        if (cachedCategory.isPresent()) {
            transaction.setCategory(cachedCategory.get());
        } else {
            UserQuery query = new UserQuery(cache.getAllCategories());
            String category = query.query(String.format(CATEGORY_QUERY_TEXT, transaction.getDetails()));
            cache.addCategory(transaction.getDetails(), category);
            transaction.setCategory(category);
        }
    }

    public void persistCache() throws IOException, URISyntaxException {
        cache.storeAllCategories();
    }

}
