import java.util.Vector;
public class Algorithm 
{
	public static Vector<Tag> compress(String data)
	{
		Vector<Tag> tags = new Vector<Tag>();
		Tag old = new Tag(), curTag;
		String curWord = "";
		int i = 0, len = 0;
		boolean done = false, ask = false;
		while (i < data.length()) 
		{
			curWord += data.substring(i, i + 1);
			if(ask == false)
				curTag = new Tag();
			else 
			{
				curTag = old;
				i -= len;
				ask = false;
			}
			for(int j = 0; (j + curTag.getLength()) < i; ++j)
			{
				if(curWord.equals(data.substring(j, j + curTag.getLength() + 1)))
				{
					done = true;
					if(i == data.length() - 2)
					{
						ask = false;
						curTag.setPosition(i - j);
						curTag.setLength(curTag.getLength() + 1);
						curTag.setNext(data.substring(data.length() - 1, data.length()));
						break;
					}
					else if(i == data.length() - 1)
					{
						ask = false;
						curTag.setLength(curTag.getLength() + 1);
						curTag.setPosition(i - j);
						curTag.setNext("");
						break;
					}
					else
					{
						ask = true;
						curTag.setLength(curTag.getLength() + 1);
						curTag.setPosition(i - j);
						if(i + len + 1 >= data.length())
						{
							curTag.setNext("");
							ask = false;
						}
						else
							curTag.setNext(data.substring(i + len + 1, i + len + 2));
						i += ++len;
						old = curTag;
						break;
					}
				}
			}
			if(ask)
				continue;
			if(done && ( i == data.length() - 1 || i == data.length() - 2))
			{
				tags.add(curTag);
				break;
			}
			else if(!done)
			{
				curTag.setNext(curWord);
				tags.add(curTag);
			}
			else
			{
				tags.add(curTag);
			}
			done = ask = false;
			curWord = "";
			len = 0;
			i += curTag.getLength() + 1;
		}
		return tags;
	}
	
	public static String decompress(Vector<Tag> tags)
	{
		String data = "";
		int counter = 0;
		for(Tag i : tags)
		{
			if(i.getLength() == 0)
			{
				data += i.getNext();
				counter++;
			}
			else
			{
				data += data.substring(counter - i.getPosition(), counter - i.getPosition() + i.getLength()) + i.getNext();
				counter += i.getLength() + 1;
			}
		}
		return data;
	}
}
