import java.awt.Point;
// The Point class represents a location in a two-dimensional (x, y) coordinate space.
import java.util.*;

public class GridPath
{
	public static void main(String [] args)
	{
		Set<Point> path = new LinkedHashSet<>(); // stores the path
		boolean [][] grid = new boolean[6][6]; // 2D array
		
		grid[2][0] = true; // obstacle cells (do not step at)
		grid[3][0] = true;
		grid[3][1] = true;
		grid[3][2] = true;
		grid[3][3] = true;
		
		computePath(5, 5, grid, path);
		
		System.out.println("Compute path, using plain recursion: ");
		path.forEach(System.out::println); // display the path
		
		System.out.println("========================\nCompute Path Using Memoization:");
		Set<Point> visited = new HashSet<>();
		computePathMemo(5, 5, grid, path, visited);
		path.forEach(System.out::println); // display the path
	}
	// ComputePath() path, using plain recursion
	// runtime is (2^n)
	// space complexity is linear O(n)
	public static boolean computePath(int m, int n, boolean grid[][], Set<Point> path)
	{
		
		if(m < 0 || n < 0) // do not step off the grid
			return false;
		
		if(grid[m][n]) // return false for these cells
			return false;
		
		if((m == 0 && n == 0) // reaching the target
				|| computePath(m, n -1, grid, path) // try moving right
				|| computePath(m-1, n, grid, path)) // try moving down
		{
			path.add(new Point(m,n)); // add the cell to the path
			return true;
		}
		
		return false;
		
	}
	
	// ComputePathMeo path, using DP (Memoization)
	// runtime is O(mn)
	// space complexity is linear O(n)
	public static boolean computePathMemo(int m, int n, boolean grid[][], Set<Point> path, Set<Point> visitFailed)
	{
		
		if(m < 0 || n < 0) // do not step off the grid
			return false;
		
		if(grid[m][n]) // return false for these cells
			return false;
		Point cell = new Point(m, n);
		
		if(visitFailed.contains(cell)) // cache the overlapping subproblems
			return false;
		
		if((m == 0 && n == 0) // reaching the target
				|| computePathMemo(m, n -1, grid, path, visitFailed) // try moving right
				|| computePathMemo(m-1, n, grid, path, visitFailed)) // try moving down
		{
			path.add(cell); // add the cell to the path
			return true;
		}
		
		visitFailed.add(cell);
		return false;
		
	}
	
	
	
}