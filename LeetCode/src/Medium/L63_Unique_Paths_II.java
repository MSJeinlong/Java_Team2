package Medium;

public class L63_Unique_Paths_II {

	 public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
	        int m = obstacleGrid.length;
	        int n = obstacleGrid[0].length;
	        
	        if( m == 0 && n == 0)
	        {
	            return 0;
	        }

	        if( m == 1)
	        {
	            for(int j = 0; j < n; j++)
	            {
	                if(obstacleGrid[0][j] == 1)
	                    return 0;
	            }
	            return 1;
	        }
	     
	        if( n == 1)
	        {
	            for(int i = 0; i < m; i++)
	            {
	                if(obstacleGrid[i][0] == 1)
	                    return 0;
	            }
	            return 1;
	        }
	        
	        for(int i = 0; i < m; i++)
	            for(int j = 0; j < n;j++)
	            {
	                if( obstacleGrid[i][j] == 1)
	                {
	                    if(i == 0 && j == 0){

	                    }
	                    obstacleGrid[i][j] = 0;
	                }
	                else if( i == 0 || j == 0 )
	                {
	                    if(i == 0 && j == 0)
	                    {
	                        obstacleGrid[i][j] = 1;
	                    }
	                    else if( i == 0)
	                    {
	                        obstacleGrid[i][j] = obstacleGrid[i][j - 1];
	                    }
	                    else
	                    {
	                        obstacleGrid[i][j] = obstacleGrid[i - 1][j];
	                    }

	                }
	                else 
	                {
	                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];  
	                }
	            }

	        return obstacleGrid[m-1][n-1];
	    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
