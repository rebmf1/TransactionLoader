package transactionloader.io;

import java.util.List;

public interface Writer {

	void write(List<List<String>> rows);
	
}
