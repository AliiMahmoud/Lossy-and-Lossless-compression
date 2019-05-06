package application;

import java.io.Serializable;

// class for matrix operations
public class AverageVector implements Serializable {
	private static final long serialVersionUID = 1L;
	private double[][] average;

	
	public AverageVector() 
	{

	}
	public AverageVector(int size) 
	{
		average = new double[size][size];
		for (int i = 0; i < size; ++i) 
		{
			for (int j = 0; j < size; ++j) 
			{
				average[i][j] = 0.0;
			}
		}
	}
	public AverageVector(AverageVector another) 
	{
		average = new double[another.getArr().length][another.getArr()[0].length];
		for (int i = 0; i < another.getArr().length; ++i) 
		{
			for (int j = 0; j < another.getArr()[0].length; ++j) 
			{
				average[i][j] = another.getArr()[i][j];
			}
		}
	}

	public double[][] getArr()
	{
		return average;
	}
	public AverageVector divide(int num) 
	{
		AverageVector result = new AverageVector(this.average.length);
		for (int i = 0; i < average.length; ++i) 
		{
			for (int j = 0; j < average[0].length; ++j) 
			{
				result.average[i][j] = average[i][j] / num;
			}
		}
		return result;
	}

	public AverageVector plus(int[][] vec) {
		AverageVector result = new AverageVector(this.average.length);
		for (int i = 0; i < average.length; ++i)
		{
			for (int j = 0; j < average[0].length; ++j) 
			{
				result.average[i][j] = average[i][j] + vec[i][j];
			}
		}
		return result;

	}
	
	public double distance(int[][] another) 
	{
		double value = 0;
		for (int i = 0; i < average.length; ++i) 
		{
			for (int j = 0; j < average[0].length; ++j) 
			{
				value += Math.abs(average[i][j] - another[i][j]);
			}
		}
		return value;

	}

	public AverageVector add(int num) {
		AverageVector result = new AverageVector(this.average.length);
		for (int i = 0; i < average.length; ++i) {
			for (int j = 0; j < average[0].length; ++j) {
				result.average[i][j] = average[i][j] + num;
			}
		}
		return result;
	}

	public AverageVector floor() {
		AverageVector result = new AverageVector(this.average.length);
		for (int i = 0; i < average.length; ++i) {
			for (int j = 0; j < average[0].length; ++j) {
				result.average[i][j] = Math.floor(average[i][j]);
			}
		}
		return result;
	}

	public String toString() 
	{
		String output = "";
		for (int i = 0; i < average.length; ++i) 
		{
			for (int j = 0; j < average[0].length; ++j) 
			{
				output += average[i][j] + " ";
			}
			output += '\n';
		}
		return output;
	}
}
