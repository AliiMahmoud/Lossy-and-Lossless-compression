
import java.util.Vector;
public class Algorithm
{
	private Vector<String> dictionary;
	
	public Algorithm() 
	{
		dictionary = new Vector<String>();
	}
	
	public Vector<Tag> compress(String data)
	{
		dictionary.clear();
		dictionary.add(""); /// add null
		Vector<Tag> tags = new Vector<Tag>();
		String CurrentLetters = "";
		for(int i = 0; i < data.length(); ++i)
		{
			 Tag NewTag = new Tag();
			 CurrentLetters += data.charAt(i); 
			 if(!dictionary.contains(CurrentLetters)) /// stop if the currentLetters isn't in the dictionary
			 {
				 int index = dictionary.indexOf(CurrentLetters.substring(0, CurrentLetters.length() - 1)); /// without the last symbol
				 NewTag.setIndex(index);
				 NewTag.setNext(CurrentLetters.charAt(CurrentLetters.length() - 1) + "");
				 dictionary.add(CurrentLetters);
				 tags.add(NewTag);
				 CurrentLetters = "";
			 }
			 else if(i == data.length() - 1)
			 {
				 NewTag.setIndex(dictionary.indexOf(CurrentLetters));
				 NewTag.setNext("NULL");
				 tags.add(NewTag);
			 }
		}
		return tags;
	}

	public String decompress(Vector<Tag> tags)
	{
		dictionary.clear();
		dictionary.add(""); // add NULL
		String data = ""; 
		for(Tag i : tags)
		{
			String temp = dictionary.get(i.getIndex());
			if(i.getNext().equals("NULL") == false)
			{
				temp += i.getNext();
			}
			if(dictionary.contains(temp) == false)
			{
				dictionary.add(temp);
			}
			data += temp;
		}
		return data;
	}
}
