public class Node 
{
	private double prob;
	private String symbol;
	private String code;
	Node right;
	Node left;
	
	public Node()
	{
		code = "";
		right = left = null;
		prob = 0;
		symbol = "";
	}
	public Node(double p, String s)
	{
		prob = p;
		symbol = s;
	}
	public void setProb(double p)
	{
		prob = p;
	}
	public void setSymbol(String s)
	{
		symbol = s;
	}
	public final String getSymbol()
	{
		return symbol;
	}
	public void setCode(String s)
	{
		code = s;
	}
	public final String getCode()
	{
		return code;
	}
	public final double getProb()
	{
		return prob;
	}
	public String toString()
	{
		return "(" + prob + "," + symbol + ", " + code + ")";
	}
	
	

}
