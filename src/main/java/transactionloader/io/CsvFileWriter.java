package transactionloader.io;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by rebeccafulton on 02/05/2017.
 */
public class CsvFileWriter implements Writer {

    String fileLocation;

    public CsvFileWriter (String fileLocation) {
        this.fileLocation = fileLocation;
    }

    @Override
    public void write(List<List<String>> rows) throws URISyntaxException, IOException {
        List<String> lines = rows.stream()
                .map(r -> r.stream().collect(Collectors.joining(",")))
                .collect(Collectors.toList());
        Files.write(Paths.get(getClass().getResource(fileLocation).toURI()), lines, Charset.defaultCharset());
    }
}
