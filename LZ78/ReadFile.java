
import java.util.*;
import java.io.*;;

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
		catch(Exception E)
		{
			System.out.println("can't open the read file");
		}
	}
	public String read()
	{
		String data = "";
		data = read.next();		
		return data;
	}
	public Vector<Tag> readtags()
	{
		Vector<Tag> LastTags = new Vector<Tag>();
		String line = read.nextLine();
		String[] SeparatedTags = line.split(", ");
		for(int i = 0; i < SeparatedTags.length; ++i)
		{
			String tagElements[] = SeparatedTags[i].split(" ");
			Tag NewTag = new Tag();
			int index = Integer.parseInt(tagElements[0]);
			NewTag.setIndex(index);
			NewTag.setNext(tagElements[1]);
			LastTags.add(NewTag);
		}		
		return LastTags;
	}
	public void closeFile()
	{
		read.close();
	}
	
}
