package transactionloader.io;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface Writer {

	void write(List<List<String>> rows) throws URISyntaxException, IOException;
	
}
