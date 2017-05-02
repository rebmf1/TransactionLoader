package transactionloader.categorisation;

import com.google.common.collect.Lists;
import transactionloader.io.Reader;
import transactionloader.io.Writer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public final class Cache {

    private static Map<String, String> categoryMap;
    private static final AtomicBoolean INITIALISED = new AtomicBoolean(false);
    private Reader fileReader;
    private Writer fileWriter;

    public Cache(Reader fileReader, Writer fileWriter) {
        this.fileReader = fileReader;
        this.fileWriter = fileWriter;
        initialise();
    }

    private synchronized void initialise() {
        if (INITIALISED.compareAndSet(false, true)) {
            categoryMap = new ConcurrentHashMap<>();
            try {
                List<List<String>> fileContents = fileReader.readRows();
                for (List<String> row : fileContents) {
                    categoryMap.put(row.get(0), row.get(1));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Optional<String> getCategory(String description) {
        return Optional.ofNullable(categoryMap.get(description));
    }

    public List<String> getAllCategories() {
        return Lists.newArrayList(categoryMap.values());
    }

    public void storeAllCategories() throws IOException, URISyntaxException {
        List<List<String>> rows = categoryMap
                .entrySet()
                .stream()
                .map(e -> Lists.newArrayList(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
        fileWriter.write(rows);
    }

    public void addCategory(String description, String category) {
        categoryMap.put(description, category);
    }
}
