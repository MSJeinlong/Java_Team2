package Medium;

public class L64_Minimum_Path_Sum {

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int[][] grid = new int[3][3];
//		minPathSum(grid);
	}

	private static int minPathSum(int[][] grid) {
		// TODO Auto-generated method stub
		int m = grid.length;
		int n = grid[0].length;
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; i++) {
				if( i == 0 && j == 0) {
					grid[i][j] = grid[i][j];
				}
				else if( i == 0 && j != 0) {
					grid[i][j] += grid[i][j - 1];
				}
				else if( j == 0 && i != 0) {
					grid[i][j] += grid[i - 1][j];
				}
				else {
					grid[i][j] += Math.min(grid[i][j - 1], grid[i - 1][j]);
				}
			}
		}
		return grid[m - 1][n - 1];
	}

}

//DFS±©Á¦ÆÆ·¨
class Solution1{
    public int minPathSum(int[][] grid) {
        if(grid == null)
            return 0;    
        int[] minSum = new int[1];
        int m = grid.length;
        int n= grid[0].length;
        if( m == 1){
            for(int j = 0; j < n; j++)
                minSum[0] += grid[0][j];
            return minSum[0];
        }
         if( n == 1)
        {
            for(int i = 0; i < m; i++)
                minSum[0] += grid[i][0];
            return minSum[0];
        }
        FindMinPathSum(grid, 0, 0, m, n, minSum, 0);
        return minSum[0];
    }
    
    public void FindMinPathSum(int[][] grid, int i, int j, int m, int n, int[] minSum, int currSum){
        currSum += grid[i][j];
        if( i == m - 1 && j == n - 1)
        {
            //System.out.println(currSum);
            if(minSum[0] <= 0)
                minSum[0] = currSum;
            else if(minSum[0] > currSum)
                minSum[0] = currSum;
        }
        if(i + 1 < m){
            //System.out.println("i + 1 = " +(i + 1));
            FindMinPathSum(grid, i + 1, j, m, n, minSum, currSum);
        }
        if(j + 1 < n){     
             //System.out.println("j + 1 = "+(j + 1));
            FindMinPathSum(grid, i, j + 1, m, n, minSum, currSum);
        }
    }
}


