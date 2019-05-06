package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Formatter;
import java.util.Scanner;
import java.util.Vector;
import javax.imageio.ImageIO;

class Quantizer {
	
	public static Vector<Level> generateTable(Vector<Double> values) 
	{
		Vector<Level> table = new Vector<Level>();
		int low = 0;
		for (int i = 0; i < values.size(); ++i) 
		{
			Level newLevel = new Level();
			newLevel.low = low;
			if(i != values.size()- 1)
				newLevel.high = (int) (values.get(i) + values.get(i + 1)) / 2;
			else
				newLevel.high = -1;
			newLevel.q = (int) (double) values.get(i);
			table.add(newLevel);
			low = newLevel.high;
		}
		return table;
	}

	public static void compress(String inPath, String outPath, int lvls, boolean image) throws IOException 
	{
		Vector<Integer> data;
		int imgHeight = 0;
		int imgWidth = 0;
		if (image == true) 
		{
			// readImage puts the width and the height at index 0, 1
			data = readImage(inPath);
			imgHeight = data.remove(0);
			imgWidth = data.remove(0);
		} 
		else 
		{
			data = new Vector<Integer>();
			Scanner read = new Scanner(new File(inPath));
			while (read.hasNextInt()) 
			{
				data.add(read.nextInt());
			}
			read.close();
		}
		Vector<Double> averages = qunatize(data, lvls);
		Vector<Level> table = generateTable(averages);
		Vector<Integer> output = new Vector<>();
		for (int i = 0; i < data.size(); ++i) 
		{
			int position = itsLevel(table, data.get(i));
			output.add(position);
		}
		/// writing the table
		FileOutputStream fos = new FileOutputStream("overhead.txt");
		ObjectOutputStream out = new ObjectOutputStream(fos);
		out.writeObject(table);
		out.close();

		if (image) 
		{
			Quantizer.writeImage(output, outPath, imgWidth, imgHeight);
		}
		else 
		{
			fos = new FileOutputStream(outPath);
			out = new ObjectOutputStream(fos);
			out.writeObject(output);
			out.close();
		}
	}

	public static int itsLevel(Vector<Level> table, Integer value) 
	{
		int i = 0;
		for (; i < table.size() - 1; ++i) 
		{
			if (value >= table.get(i).low && value < table.get(i).high)
				return i;
		}
		return i;
	}

	public static Double average(Vector<Integer> data) 
	{
		Double sum = 0.0;
		for (Integer i : data) 
		{
			sum += i;
		}
		return sum / data.size();
	}

	public static int close(Integer element, Vector<Double> values) 
	{
		Double best = Math.abs(element - values.get(0));
		int index = 0;
		for (int i = 1; i < values.size(); ++i) 
		{
			if (Double.compare(best, Math.abs(element - values.get(i))) > 0) 
			{
				best = Math.abs(element - values.get(i));
				index = i;
			}
		}
		return index;
	}

	public static void associate(Vector<Integer> data, Vector<Double> averages) 
	{
		Vector<Integer> sums = new Vector<>();
		Vector<Integer> counters = new Vector<>();
		for (int i = 0; i < averages.size(); ++i) 
		{
			sums.add(0);
			counters.add(0);
		}
		for (int i = 0; i < data.size(); ++i) 
		{
			// to know its range
			int index = close(data.get(i), averages);
			// as sum[index] += data[i]
			sums.setElementAt(sums.get(index) + data.get(i), index);
			// counnter[index] += 1
			counters.setElementAt(counters.get(index) + 1, index);
		}
		// set the averages
		for (int i = 0; i < averages.size(); ++i) 
		{
			averages.setElementAt((double) sums.get(i) / counters.get(i), i);
		}
	}

	public static Vector<Double> qunatize(Vector<Integer> data, int levels) 
	{

		Double avg = average(data);
		Vector<Double> values = new Vector<>();
		values.add(avg);
		for (int i = 0; i < levels; ++i) 
		{
			int size = values.size();
			for (int j = 0; j < size; ++j) 
			{
				Double CurrentAverage = values.remove(0);
				Double low = Math.floor(CurrentAverage);
				Double high = Math.ceil(CurrentAverage);
				if (low.compareTo(high) == 0)
					high += 1;
				values.add(low);
				values.add(high);
			}
			associate(data, values);
		}
		return values;
	}

	public static Vector<Integer> readImage(String filePath)
	{
		File file = new File(filePath);
		BufferedImage image = null;
		try 
		{
			image = ImageIO.read(file);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
			return null;
		}
		int imgWidth = image.getWidth();
		int imgHeight = image.getHeight();
		int[][] pixels = new int[imgHeight][imgWidth];
		for (int x = 0; x < imgWidth; x++) 
		{
			for (int y = 0; y < imgHeight; y++) 
			{
				 int p = image.getRGB(x,y); 
	                int a = (p>>24)&0xff; 
	                int r = (p>>16)&0xff; 
	                int g = (p>>8)&0xff; 
	                int b = p&0xff; 
	                //because in gray image r=g=b  we will select r  
	               
	                pixels[y][x] = r;
	                
	                //set new RGB value 
	                p = (a<<24) | (r<<16) | (g<<8) | b; 
	                image.setRGB(x, y, p); 
			}
		}
		Vector<Integer> imgData = new Vector<>(imgWidth * imgHeight);
		imgData.add(imgHeight);
		imgData.add(imgWidth);
		for (int x = 0; x < imgHeight; x++) 
		{
			for (int y = 0; y < imgWidth; y++) 
			{
				imgData.add(pixels[x][y]);
			}
		}
		return imgData;
	}

	public static void writeImage(Vector<Integer> output, String outputFilePath, int imgWidth, int imgHeight) 
	{
		int[][] pixels = new int[imgHeight][imgWidth];
		
		for (int i = 0; i < output.size(); ++i) 
		{
			pixels[i / imgWidth][i % imgWidth] = output.get(i);
		}
		
		File fileout = new File(outputFilePath);
		BufferedImage image2 = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < imgWidth; x++) 
		{
			for (int y = 0; y < imgHeight; y++) 
			{
				
				int a = 255;
            	int pix = pixels[y][x];            			
            	int p = (a << 24)  | (pix << 16) | (pix << 8) | pix;
            	image2.setRGB(x, y, p); 
				
			}
		}
		try 
		{
			ImageIO.write(image2, "jpg", fileout);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void decompress(String inPath, String outPath, boolean image)
			throws IOException, ClassNotFoundException {

		FileInputStream fos = new FileInputStream("overhead.txt");
		ObjectInputStream out = new ObjectInputStream(fos);
		Vector<Level> table = (Vector<Level>) out.readObject();
		out.close();
		int imgHeight = 0;
		int imgWidth = 0;
		Vector<Integer> data;
		if (image) 
		{
			data = readImage(inPath);
			imgHeight = data.remove(0);
			imgWidth = data.remove(0);
		}
		else 
		{
			fos = new FileInputStream(inPath);
			out = new ObjectInputStream(fos);
			data = (Vector<Integer>) out.readObject();
			out.close();
		}
		for (int i = 0; i < data.size(); ++i) 
		{
			if (data.get(i) >= table.size())
				data.setElementAt(table.get(table.size() - 1).q, i);
			else
				data.setElementAt(table.get(data.get(i)).q, i);
		}
		if (image) 
		{
			Quantizer.writeImage(data, outPath, imgWidth, imgHeight);
		} 
		else 
		{
			Formatter write = new Formatter(outPath);
			for (Integer i : data) 
			{
				write.format("%s ", i);
			}
			write.close();
		}
	}
}
