package assignment6;

public class RecursiveMethods {

	/**
	 * Recursively computes base ^ exponent
	 * 
	 * @param base the base - can be positive or negative
	 * @param exp  the exponent - can be positive or negative
	 * @return base ^ exponent
	 */
	public static double exponent(int base, int exp) {
		if (exp == 0) {
			return 1;
		}

		else if (exp > 0) {
			return base * exponent(base, exp - 1);
		}

		else {
			return (1.0 / base) * exponent(base, exp + 1);
		}
	}

	

	/**
	 * Recursively compute the sum of elements in an array
	 * 
	 * @param array an array of integers
	 * @return the sum of the elements in values
	 */
	public static int arraySum(int[] array) {
		if (array.length == 0) {
			return 0;
		}

		else {
			return arraySumHelper(array, 0, 0);
		}
			// FIXME: Recursively compute the sum of the values in an array
	}

	public static int arraySumHelper(int[] array, int index, int sum) {
		if (index == array.length) {
			return sum;
		}
		else {
			sum += array[index];
			return arraySumHelper(array, ++index, sum);
		}
	}

	/**
	 * Recursively computes string representations of dragon curves
	 * 
	 * @param n the desired degree of the dragon curve
	 * @return the nth dragon curve
	 */
	public static String dragon(int n) {
		if (n == 0) {
			return "F-H";
		}
		else {
			String prevDragon = dragon(n-1);
			String temp = prevDragon.replace("F", "F-X");
			temp = temp.replace("H", "Y+H");
			String newDragon = temp.replace("X", "H");
			newDragon = newDragon.replace("Y", "F");
			return newDragon;
		}
		
	}

	

	/**
	 * Finds the length of the longest path in the given 2D array from the specified
	 * start position.
	 * 
	 * @param chart 2D array of stones
	 * @param r     start position row
	 * @param c     start position column
	 * @return the length of the longest path that was found
	 */

	/** Recursively check if the index UP, DOWN, LEFT, RIGHT is TRUE
	 * Recursively Call to move if index is TRUE
	 * TEMPORARILY CONVERT THE INDEX YOU WERE ON TO FALSE
	 * SHOULD HAVE A COUNTER THAT COUNTS EACH TIME U TAKE A STEP
	 * TURN THE PREVIOUS PREVIOUS STEP BACK TO TRUE
	 * BASE CASE: ALL DIRECTIONS = FALSE
	*/ 	
	public static int maxPathLength(boolean[][] chart, int r, int c) {
	// --- BASE CASE 1 & 2 (Out of Bounds or Unsafe) ---
    // If the current position (r, c) is outside the chart boundaries OR
    // if the cell at (r, c) is false (unsafe/already visited), the path ends.
    // Length from here is 0.
	
		if (chart[r][c] == false || r >= chart.length || c >= chart[r].length || r < 0 || c < 0) {
			return 0;
		}
		// --- RECURSIVE STEP (Explore) ---

		// 1. Mark the current cell as visited (temporarily false) to avoid cycles.
		// This is the CRITICAL step that uses the chart itself for state.
		else {
			chart[r][c] = false;

			// 2. Dispatch the four "messengers" (recursive calls).
			// The result from each messenger is the longest path found starting from that neighbor.

			int upPath = maxPathLength(chart, r - 1, c);
			int downPath = maxPathLength(chart, r + 1, c);
			int rightPath = maxPathLength(chart, r, c+1);
			int leftPath = maxPathLength(chart, r, c - 1);

			// 3. Find the longest path among the four recursive results.
			// Use Math.max creatively, as you noted.

			int maxOfTwo = Math.max(upPath, downPath);
			int maxOfFour = Math.max(maxOfTwo, Math.max(rightPath, leftPath));

			// 4. Backtracking: RESTORE the current cell to true.
			// This allows other branches of the path-finding tree to use this cell.

			chart[r][c] = true;

			// 5. The total path length starting at (r, c) is 1 (for the current cell) 
			// plus the longest path found among its neighbors.

			return 1 + maxOfFour;
		}
		
	}
}
