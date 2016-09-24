package solution;

import java.io.*;
import java.nio.charset.Charset;

public class DistributeMug {
// Assumption: the total num of mugs is <= number of people
	public static void main(String[] args) throws Exception {
		InputStream is = new FileInputStream("input");
		InputStreamReader isr = new InputStreamReader(is, Charset.forName("UTF-8"));
	    BufferedReader br = new BufferedReader(isr);
	    String line = br.readLine();
	    if (line != null) {
	    	int n = Integer.parseInt(line);
	    	String[] stringArray = br.readLine().split(" ");
	    	int[] array = new int[n];
	    	for (int i = 0; i < n; i++) {
	    		// fill array with the number of mugs for each person at start
	    		array[i] = Integer.parseInt(stringArray[i]);
	    	}  
	    	System.out.println("Final answer is: " + distribute(array, n));
	    } else {
	    	System.out.println("Input file error");
	    }
	}
	private static int distribute(int[] array, int n) {
		if (n < 2) {
			return 0;
		}
		// use recursive function to calculate count, count is an array size 1,
	    //so that in recursion it will be preserved
		int[] count = new int[1];
		helper(array, count);
		return count[0];
	}
	private static void helper(int[] array, int[] count) {
		// base case is when all number in array <2 , assume num of mugs are less than num of people
		int n = array.length;
		for (int i = 0; i < n; i++) {
			if (array[i] > 1) {
				array[i] -= 2;
				// now distribute to left and right,
				// add left, for first person, add to last num
				if (i == 0) {
					array[n - 1]++;
				} else {
					array[i - 1]++;
				}
				//add right, for last person, add to first num
				if (i == n - 1) {
					array[0]++;
				} else {
					array[i + 1]++;
				}
				count[0]++;
				// following with a distribution, recursive call
				helper(array, count);
			}
		}
	}
}
