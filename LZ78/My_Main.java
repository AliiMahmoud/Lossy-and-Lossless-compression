
import java.util.*;

public class My_Main 
{
	public static void main(String[] args) 
	{
		/*
		 * At first, read data from file 
		 */
			String data = "";
			ReadFile dataFile = new ReadFile();
			dataFile.openFile("data.txt");
			data = dataFile.read();
			dataFile.closeFile();
		
		/* 
		 * then compress it 
		 */
			Algorithm lz78 = new Algorithm();
			Vector<Tag> tags = lz78.compress(data);
		
			
		/*
		 * its written in the format 0 A, 0 B, ...  
		 */
			WriteFile tagsFile = new WriteFile();
			tagsFile.openFile("Tags.txt");
			tagsFile.write(tags);
			tagsFile.closeFile();
			
			/*
			 * read tags from file
			 */
			ReadFile read = new ReadFile("Tags.txt");
			tags = read.readtags();
			read.closeFile();
		
			/*
			 * decompress the tags and write the output.
			 */
			tagsFile = new WriteFile("Last.txt");
			tagsFile.write(lz78.decompress(tags));
			tagsFile.closeFile();
	}
}
	