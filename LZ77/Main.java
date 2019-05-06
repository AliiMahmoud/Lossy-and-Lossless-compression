
import java.util.Scanner;
import java.util.Vector;
public class Main 
{
	public static void main(String[] args) 
	{
		Scanner cin = new Scanner(System.in);
		String data = cin.nextLine();
		Vector<Tag> compressed = Algorithm.compress(data);
		System.out.println(compressed);
		String output = Algorithm.decompress(compressed);
		System.out.println(output);
		cin.close();
		
		
	}
}
