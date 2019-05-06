
import java.util.Comparator;

class Compar implements Comparator<Node> 
{
	public int compare(Node a, Node b)
	{
		return Double.compare(a.getProb(), b.getProb());
	}
}
