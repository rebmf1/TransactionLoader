package transactionloader.io;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CsvFileReader implements Reader {

	private final String fileLocation;

	public CsvFileReader(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	@Override
	public List<List<String>> readRows() throws IOException {

		try {
			return Files.lines(Paths.get(getClass().getResource(fileLocation).toURI()))
                    .map(l -> Arrays.asList(l.split(",")))
                    .collect(Collectors.toList());
		} catch (URISyntaxException e) {
			throw new IOException(e);
		}
	}

}
