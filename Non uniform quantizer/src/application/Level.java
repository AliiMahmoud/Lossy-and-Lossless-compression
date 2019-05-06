package application;
import java.io.Serializable;

public class Level implements Serializable {
	private static final long serialVersionUID = 1L;
	int low;
	int high;
	int q;

	public Level() {
		low = high = 0;
		q = 0;
	}

	public String toString() {
		return low + " " + high + " " + q;
	}
}
