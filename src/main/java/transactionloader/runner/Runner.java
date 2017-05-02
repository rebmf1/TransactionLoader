package transactionloader.runner;

import transactionloader.categorisation.Cache;
import transactionloader.categorisation.Categoriser;
import transactionloader.io.CsvFileReader;
import transactionloader.io.CsvFileWriter;
import transactionloader.io.Reader;
import transactionloader.io.Writer;
import transactionloader.processor.Processor;
import transactionloader.processor.TransactionProcessor;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Runner {

	private static final String CACHE_LOCATION = "../categorisation/cache.csv";
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		String inputFileLocation = args[0];

		Reader inputFileReader = new CsvFileReader(inputFileLocation);
		List<List<String>> fileContents = inputFileReader.readRows();

		List<List<String>> output = new ArrayList<>();

		Reader cacheReader = new CsvFileReader(CACHE_LOCATION);
		Writer cacheWriter = new CsvFileWriter(CACHE_LOCATION);

		Categoriser categoriser = new Categoriser(new Cache(cacheReader, cacheWriter));

		TransactionProcessor processor = new Processor(categoriser, args[1]);

		for (List<String> input : fileContents) {
			output.add(processor.processTransaction(input));
		}

		Writer outputFileWriter = new CsvFileWriter("../output/output.csv");

		outputFileWriter.write(output);

		categoriser.persistCache();
	}

}
