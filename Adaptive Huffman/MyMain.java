
public class MyMain 
{
	public static void main(String[] args) 
	{
		Tree toCompress = new Tree();
		String bits = toCompress.compress("ABCCCAAAAA");
		System.out.println(bits);
		// in order to write it to a file you must write the distinct letters too 
		
		Tree toDecompress = new Tree();
		 // it takes stream of bits and the distinct letters ordered by ASCII 
		String data = toDecompress.deCompress(bits, "ABC");
		System.out.println(data);
	}
	
	
}
