package transactionloader.io;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CsvFileReaderTest {
	
	private CsvFileReader reader;
	
	@Test
	public void testReadEmptyFile() throws IOException {
		reader = new CsvFileReader("emptyFile.csv");
		List<List<String>> output = reader.readRows();
		assertEquals(0, output.size());
	}
	
	@Test
	public void testReadOneWordFile() throws IOException {
		reader = new CsvFileReader("oneWordFile.csv");
		List<List<String>> output = reader.readRows();
		assertEquals(1, output.size());
		assertEquals(1, output.get(0).size());
		assertEquals("word1", output.get(0).get(0));
	}

	@Test
	public void testReadOneLineFile() throws IOException {
		reader = new CsvFileReader("oneLineFile.csv");
		List<List<String>> output = reader.readRows();
		assertEquals(1, output.size());
		int index = 1;
		for (List<String> line : output) {
			assertEquals(3, line.size());
			for (String word : line) {
				assertEquals("word" + index++, word);
			}
		}
	}

	@Test
	public void testReadOneWordPerLineFile() throws IOException {
		reader = new CsvFileReader("oneWordPerLineFile.csv");
		List<List<String>> output = reader.readRows();
		assertEquals(3, output.size());
		int index = 1;
		for (List<String> line : output) {
			assertEquals(1, line.size());
			for (String word : line) {
				assertEquals("word" + index++, word);
			}
		}
	}

	@Test
	public void testReadFullFile() throws IOException {
		reader = new CsvFileReader("fullFile.csv");
		List<List<String>> output = reader.readRows();
		assertEquals(3, output.size());
		int index = 1;
		for (List<String> line : output) {
			assertEquals(2, line.size());
			for (String word : line) {
				assertEquals("word" + index++, word);
			}
		}
	}

}
