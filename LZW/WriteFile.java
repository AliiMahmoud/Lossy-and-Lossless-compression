
package EL_LZW;
import java.util.Formatter;
import java.util.Vector;

public class WriteFile
{
	private Formatter write;
	
	public WriteFile() 
	{
	}
	public WriteFile(String path) 
	{
		openFile(path);
	}	
	public void openFile(String path) 
	{
		try
		{
			write = new Formatter(path);
		}
		catch(Exception p)
		{
			System.out.println("You have an error !!");
		}
	}
	public void writeTags(Vector<Integer> tags)
	{
		for(int i : tags)
		{
			write.format("%d ", i);
		}
	}
	public void writeData(String data)
	{
		write.format("%s ", data);
	}
	public void closeFile() 
	{
		write.close();
	}	
}
