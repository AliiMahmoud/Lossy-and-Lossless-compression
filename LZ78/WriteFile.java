
import java.util.*;
public class WriteFile
{
	private Formatter file;
	public WriteFile(String path) 
	{
		openFile(path);
	}	
	public WriteFile() 
	{
	}
	public void openFile(String path) 
	{
		try
		{
			file = new Formatter(path);
		}
		catch(Exception p)
		{
			System.out.println("Something went wrong !!");
		}
	}
	public void closeFile() 
	{
		file.close();
	}
	public void write(Vector<Tag> tags)
	{
		for(int i = 0; i < tags.size(); ++i)
		{
			file.format("%d %s, ", tags.get(i).getIndex(),tags.get(i).getNext());
		}
	}
	public void write(String data)
	{
			file.format("%s ", data);	
	}
	
}
