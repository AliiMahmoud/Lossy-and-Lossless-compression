import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;


public class Tree 
{
	public class Node
	{
		Node left;
		Node right;
		Node parent;
		int count = 0;
		String code;
		String Data;
		public Node()
		{
			Data = code = "";
			left = right = parent = null;
		}
	}

	private Node root;
	private Vector<String> shortCodes;
	// distinct letters
	private String symbols;
	// final output
	private String compressed;
	public Tree()
	{
		symbols = compressed = "";
		root = new Node();
		shortCodes = new Vector<String>();
	}
	public String getSymbols()
	{
		return symbols;
	}
	public String deCompress(String bits, String letters)
	{
		root = new Node();
		// it returns the distinct letters after generating the short codes
		symbols = getShortCodes(letters);
		String data = "", curr = "";
		int numBits = shortCodes.get(0).length();
		// trying to add the to the tree the letter with the first (numbits) ie. get the index of 
		// the letter whose short code is the first n bits then add it to the tree
		String firstBits = bits.substring(0, numBits);
		String firstLetterIndex = shortCodes.indexOf(firstBits);
		add(symbols.charAt(firstLetterIndex) + "");
		data += symbols.charAt(firstLetterIndex);
		Node lastFound = null;
		for(int i = numBits; i < bits.length(); i++)
		{
			curr += bits.substring(i, i + 1);
			Node found = searchByCode(curr);
			if(found == null)
			{
				if(lastFound.Data.equals("NYT"))
				{
					String firstTime = bits.substring(i, i + numBits);
					data += symbols.charAt(shortCodes.indexOf(firstTime));
					add(symbols.charAt(shortCodes.indexOf(firstTime)) + "");
					i += numBits;
				}
				else
				{
					data += lastFound.Data;
					add(lastFound.Data);
				}
				i--;
				curr = "";
				continue;
			}
			else if(i == bits.length() - 1) 
			{
				data += found.Data;
				add(found.Data);
			}
			lastFound = found;
		}
		return data;
	}
	
	public String compress(String data)
	{
		root = new Node();
		symbols = getShortCodes(data);
		for(int i = 0; i < data.length(); ++i)
		{
			add(data.substring(i, i + 1));
		}
		return compressed;
	}
	public void add(String letter)
	{
			Node found = searchBySymbol(letter);
			if(found.Data.equals("NYT") || found.Data.equals("")) /// occurs for the first time
			{
				Node right = new Node();
				right.Data = letter;
				right.count = 1;
				right.parent = found;
				
				Node left = new Node();
				left.Data = "NYT";
				left.parent = found;

				found.left = left;
				found.right = right;
				found.count++;

				compressed += found.code;
				compressed += shortCodes.get(symbols.indexOf(letter));
				generateNodeCodes(root, "");
			}
			else
				compressed += found.code;
			update(found);
	}
	
	public Node searchBySymbol(String data)
	{
		Node temp = root;
		while(temp.right != null && temp.left != null)
		{
			if(temp.right.Data.equals(data))
			{
				return temp.right;
			}
			else if(temp.left.Data.equals(data))
			{
				return temp.left;
			}
			else
			{
				if(temp.right.Data.equals("NYT"))
					temp =  temp.right;
				else
					temp = temp.left; 
			}
		}
		return temp;
	}
	public Node searchByCode(String data)
	{
		Node temp = root;
		while(temp.right != null && temp.left != null)
		{
			if(temp.right.code.equals(data))
			{
				return temp.right;
			}
			else if(temp.left.code.equals(data))
			{
				return temp.left;
			}
			else
			{
				if(temp.right.Data.equals("NYT"))
					temp =  temp.right;
				else
					temp = temp.left; 
			}
		}
		return null;
	}
	
	public void generateNodeCodes(Node n, String s)
	{
		if(n.left == null && n.right == null)
		{
	 		n.code = s; //hut el code lly enta 3amltlu generate men abl keda                                  
			return;
		}
		generateNodeCodes(n.left, s + "0");
		generateNodeCodes(n.right, s + "1");
		n.code = s;
	}
	public void print()
	{
		Queue<Node> q = new LinkedList<Node>();
		q
		.add(root);
		while(q.size() != 0)
		{
			Node temp = q.remove();
			System.out.println(temp.Data + " " + temp.code + " " + temp.count);
			if(temp.left != null)
				q.add(temp.left);
			if(temp.right != null)
				q.add(temp.right);
		}
	}
	public void update(Node found) // el NYT aw el symbol
	{
		Node temp = found;
		if(temp  == root)
			return;
		else if(temp.Data.equals("NYT"))
			temp = temp.parent;
		
		while(temp != root)
		{
			Node toSwap = toSwapWith(temp);
			if(toSwap == null)
			{
				temp.count++;
				temp = temp.parent;
			}
			else
			{
				temp.count++;
				swap(temp, toSwap);
				temp = toSwap.parent;
			}
			generateNodeCodes(root, "");
		}
		temp.count++;
		generateNodeCodes(root, "");
	}

	public void generateShortCodes(int length, String s)
	{
		if(length == 0)
		{
			shortCodes.add(s);
			return;
		}
		generateShortCodes(length - 1, s + "0");
		generateShortCodes(length - 1, s + "1");
	}
	
	public String getShortCodes(String data)
	{	
	
		// clear if not empty
		shortCodes.clear();
		
		int[] power = {1, 2, 4, 8, 16, 32, 64, 128, 256};
		// ascii occurance frequencies 
		int[] arr = new int[200];		
		for(int i = 0; i < data.length(); ++i)
		{
			arr[data.charAt(i)]++;
		}
		String to = ""; 
		for(int i = 0; i < 200; ++i)
		{
			if(arr[i] != 0)
				to += (char)i;
		}
		int len = 1;
		while(to.length() > power[i])
			len++;
		generateShortCodes(len, "");
		return to;
	}
	public void swap(Node first, Node second)
	{
		if(!first.Data.equals("NYT") && !second.Data.equals("NYT"))
		{
			Node temp = new Node();
			temp.code = first.code;
			temp.Data = first.Data;
			temp.count = first.count;
			
			first.code = second.code;
			first.Data = second.Data;
			first.count = second.count;

			second.code = temp.code;
			second.Data = temp.Data;
			second.count = temp.count;
		}
		else 
		{
			if(first == root.left)
			{
				Node temp = root.right;
				root.right = root.left;
				root.left = temp; 
			}
			else
				return;
		}
	}
	public Node toSwapWith(Node current)
	{		
		Node temp = root;
		if(root.left == current)
		{
			if(root.left.count >= root.right.count)
				return root.right;
			else 
				return null;
		}
		while(current != temp.right)
		{
			Node symbol = temp.right.Data.equals("NYT") ? temp.left : temp.right;
			Node nyt = temp.right.Data.equals("NYT") ? temp.right : temp.left;
			
			if(current.count >= symbol.count)
			{
				return symbol;
			}
			temp = nyt;
		}
		return null;
	}
}
