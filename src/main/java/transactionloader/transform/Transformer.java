package transactionloader.transform;

public interface Transformer<T, U> {

	U transform(T t);
	
}
