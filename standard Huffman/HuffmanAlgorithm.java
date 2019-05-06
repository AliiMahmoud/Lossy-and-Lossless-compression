
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Formatter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Vector;

class HuffmanAlgorithm {
	private Node tree;
	private String distinctLetters;

	public HuffmanAlgorithm() 
	{
		distinctLetters = "";
		tree = new Node();
	}
	public Vector<Node> GeneratePropabilities(String data) {

		Vector<Node> symbolProb = new Vector<Node>();
		int[] asciiz = new int[130];
		for (int i = 0; i < data.length(); ++i)
			asciiz[data.charAt(i)]++;
		for (int i = 0; i < asciiz.length; ++i)
			if (asciiz[i] != 0) 
			{
				double prob = (double) asciiz[i] / data.length();
				Node newOne = new Node(prob, (char)i + "");
				symbolProb.add(newOne);
				distinctLetters += (char) i;
			}
		return symbolProb;
	}
	void generateCode(Node t, String s) {
		if (t == null)
			return;
		generateCode(t.right, s + "1");
		generateCode(t.left, s + "0");
		t.setCode(s);
	}
	void treeConstruction(Vector<Node> arrayOfLetters) {
		while (arrayOfLetters.size() > 1) {
			Collections.sort(arrayOfLetters, new Compar());
			Node parent = new Node();
			parent.right = arrayOfLetters.remove(0);
			parent.left = arrayOfLetters.remove(0);
			parent.setProb(parent.right.getProb() + parent.left.getProb());
			parent.setSymbol(parent.right.getSymbol() + parent.left.getSymbol());
			arrayOfLetters.add(parent);
		}
		tree = arrayOfLetters.remove(0);
		generateCode(tree, "");
	}
	public String compress(String data) {
		String compressed = "";
		Vector<Node> propabilities = GeneratePropabilities(data);
		treeConstruction(propabilities);
		for (int i = 0; i < data.length(); ++i) 
		{
			Node curr = search(data.charAt(i) + "");
			compressed += curr.getCode();
		}
		return compressed;
	}
	public Node search(String letter) {
		Node temp = tree;
		while (temp.right != null && temp.left != null) {
			if (temp.getSymbol().equals(letter))
				return temp;
			else if (temp.right.getSymbol().equals(letter))
				return temp.right;
			else if (temp.left.getSymbol().equals(letter))
				return temp.left;
			else {
				if (temp.right.getSymbol().contains(letter))
					temp = temp.right;
				else if (temp.left.getSymbol().contains(letter))
					temp = temp.left;
				else
					return null;
			}
		}
		return temp;
	}
	public String decompress(String bits) {
		String data = "";
		String temp = "";
		for (int i = 0; i < bits.length(); ++i) {
			temp += bits.charAt(i);
			Node found = moveByCode(temp);
			if (found.right == null && found.left == null) {
				data += found.getSymbol();
				temp = "";
			}
		}
		return data;
	}
	public void addToTree(String letter, String letterCode) {
		if (tree == null)
			tree = new Node();
		Node temp = tree;
		for (int i = 0; i < letterCode.length(); ++i) {
			if (letterCode.charAt(i) == '1') {
				if (temp.right == null)
					temp.right = new Node();
				temp = temp.right;
			} else {
				if (temp.left == null)
					temp.left = new Node();
				temp = temp.left;
			}
		}
		temp.setCode(letterCode);
		temp.setSymbol(letter);
	}
	public void decompressFromFile(String file1, String file2) throws IOException {
		Scanner read = new Scanner(new File(file1));
		String bits = read.next();
		while (read.hasNext()) {
			String letter = read.next();
			if(letter.equals("NULL"))
				letter = "\n";
			else if(letter.equals("null"))
				letter = " ";
			String letterCode = read.next();
			addToTree(letter, letterCode);
		}
		read.close();
		String decompressed = decompress(bits);
		Formatter write = new Formatter(file2);
		write.format("%s ", decompressed);
		write.close();
		tree = null;
	}
	public Node moveByCode(String code) {
		Node temp = tree;
		for (int i = 0; i < code.length(); ++i) {
			if (code.charAt(i) == '1')
				temp = temp.right;
			else
				temp = temp.left;
		}
		return temp;
	}
	public void compressWithFiles(String file1, String file2) throws IOException {
		/** read from file the data */
		Scanner read = new Scanner(new File(file1));
		String data = "";
		while (read.hasNextLine()) {
			data += read.nextLine();
			data += '\n'; /// ignore it
		}
		read.close();
		
		
		/** compress it*/
		String compressed = compress(data);
		
		
		/** write the output */
		Formatter write = new Formatter(file2);
		write.format("%s ", compressed);
		boolean line = false;
		boolean space = false;
		if(distinctLetters.contains("\n"))
		{
			distinctLetters =  distinctLetters.substring(1, distinctLetters.length());
			line = true;
		}
		if(distinctLetters.contains(" "))
		{
			distinctLetters = distinctLetters.substring(1, distinctLetters.length());
			space = true;
		}
		if(line == true)
		{
			Node found = search("\n");
			write.format("%s %s ", "NULL", found.getCode());				
		}
		if(space == true)
		{
			Node found = search(" ");
			write.format("%s %s ", "null", found.getCode());				
		}
		for (int i = 0; i < distinctLetters.length(); ++i) 
		{
			Node found = search(distinctLetters.charAt(i) + "");
			write.format("%s %s ", found.getSymbol(), found.getCode());
		}
		write.close();
		tree = null;
 	}
	public final void print() {
		Queue<Node> q = new LinkedList<Node>();
		q.add(tree);
		while (q.size() != 0) {
			Node temp = q.remove();
			System.out.println(temp);
			if (temp.left != null)
				q.add(temp.left);
			if (temp.right != null)
				q.add(temp.right);
		}
	}
}