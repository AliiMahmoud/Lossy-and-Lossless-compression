
public class Tag
{
	private int index;
	private String next;
	public Tag()
	{
		index = 0;
		next = "";
	}
	public Tag(Tag sec)
	{
		index = sec.index;
		next = sec.next;
	}
	public Tag(int p, char c)
	{
		index = p;
		next = c + "";
	}
	public void setIndex(int p)
	{
		index = p;
	}
	public void setNext(String n)
	{
		next = n;
	}
	public int getIndex()
	{
		return index;
	}
	public String getNext()
	{
		return next;
	}
	public String toString()
	{
		String taag = "";
		taag += "<" + index + "," + next + ">\n";
		return taag;
	}
}
