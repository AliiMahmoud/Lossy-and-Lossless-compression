
import java.util.Vector;

public class Algorithm 
{
	private Vector<String> dictionary;
	public Algorithm()
	{
		dictionary = new Vector<String>();
	}
	public void buildDictionary()
	{
		dictionary.clear();
		for(int i = 0; i < 128; ++i)
			dictionary.add((char)i + "");
	}
	/**
	 * 
	 * @param data to compress it
	 * @return the tags (vector of integers)
	 */
	public Vector<Integer> compress(String data)
	{
		buildDictionary();
		Vector<Integer> tags = new Vector<Integer>();
		String word = "";
		int lastTag = 0;
		for(int i = 0; i < data.length(); ++i)
		{
			word += data.charAt(i);
			if(!dictionary.contains(word))
			{
				tags.add(lastTag);
				dictionary.add(word);
				word = "";
				i--;
			}
			else
			{
				lastTag = dictionary.indexOf(word);
				if(i == data.length() - 1)
					tags.add(lastTag);
			}
		}
		return tags;
	}
	/**
	 * 
	 * @param tags to decompress it
	 * @return the data after decompression
	 */
	public String decompress(Vector<Integer> tags)
	{
		buildDictionary();
		String data = "";
		String previous = "";
		for(int i = 0; i < tags.size(); ++i)
		{
			if(tags.get(i) >= dictionary.size())
			{
				dictionary.add(previous + previous.charAt(0));
				i--;
			}
			else
			{
				String s = dictionary.get(tags.get(i));
				data += s;
				if(!dictionary.contains(previous + s.charAt(0)))
					dictionary.add(previous + s.charAt(0));
				previous = s;
			}
		}
		return data;
	}
	
}
