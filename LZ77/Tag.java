public class Tag
{
	private int position;
	private int length;
	private String next;
	public Tag()
	{
		position = length = 0;
		next = "";
	}
	public Tag(Tag sec)
	{
		position = sec.position;
		length = sec.length;
		next = sec.next;
	}
	public void setPosition(int p)
	{
		position = p;
	}
	public void setLength(int l)
	{
		length = l;
	}
	public void setNext(String n)
	{
		next = n;
	}
	public int getPosition()
	{
		return position;
	}
	public int getLength()
	{
		return length;
	}
	public String getNext()
	{
		return next;
	}
	public String toString()
	{
		String taag = "";
		taag += "<" + position + ", " + length + ", " + next + ">";
		return taag;
	}
}
