package transactionloader.io;

import java.io.IOException;
import java.util.List;

public interface Reader {

	List<List<String>> readRows() throws IOException;
	
}
