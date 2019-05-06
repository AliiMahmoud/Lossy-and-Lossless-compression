
import java.util.Vector;

public class Main 
{
	public static void main(String[] args)
	{
		String data = "";
		ReadFile read = new ReadFile();
		read.openFile("data.txt");
		data = read.readData();
		read.closeFile();

		Algorithm lzw = new Algorithm();
		Vector<Integer> tags = lzw.compress(data);

		WriteFile out = new WriteFile();
		out.openFile("Tags.txt");
		out.writeTags(tags);
		out.closeFile();

		read = new ReadFile("Tags.txt");
		tags = read.readTags();
		read.closeFile();

		out = new WriteFile("Last.txt");
		out.writeData(lzw.decompress(tags));
		out.closeFile();
	}
}
