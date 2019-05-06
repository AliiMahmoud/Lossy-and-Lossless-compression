
import java.util.Scanner;
import java.util.Vector;
import java.io.File;

public class ReadFile 
{
	private Scanner read;
	
	public ReadFile()
	{		
	}
	
	public ReadFile(String path)
	{
		openFile(path);
	}
	
	public void openFile(String path)
	{
		try
		{
			read = new Scanner(new File(path));
		}
		catch(Exception e)
		{
			System.out.println("Error while opening the read file");
		}
	}
	public String readData()
	{
		String t = read.nextLine();
		return t;
	}
	public Vector<Integer> readTags()
	{
		Vector<Integer> tags = new Vector<Integer>();
		while(read.hasNextInt())
		{
			int temp = read.nextInt();
			tags.add(temp);
		}
		return tags;
	}

	public void closeFile() 
	{
		read.close();
	}

}
