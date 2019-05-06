
import java.io.*;
class My_Main 
{
	public static void main(String[] args) throws IOException
	{
		HuffmanAlgorithm huffman = new HuffmanAlgorithm();
		huffman.compressWithFiles("data.txt", "binary.txt");
		huffman.decompressFromFile("binary.txt", "last.txt");	
	}

}
