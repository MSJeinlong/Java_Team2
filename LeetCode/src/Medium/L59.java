package Medium;

public class L59 {

    public static int[][] generateMatrix(int n) {
        int[][] a = new int[n][n];
        if(n == 0)
            return a;
        if( n == 1)
        {
            a[0][0] = 1;
            return a;
        }
        for(int i = 0; i < n; i++)
            a[0][i] = i + 1; 
        int direct = 0;
        int L = n - 1;
        int c = 0;
        boolean stop = false;
        int num = n + 1;
        int i = 0;
        int j = n - 1;
        while(!stop)
        {
        	int step = 0;
            switch(direct % 4)
            {
            case 0:
            	while(step < L)
            	{
            		a[++i][j]=num++;
            		step++;
            		if(num > n * n)
            		{
            			stop = true;
            			break;
            		}
            	}
            	break;//down
            case 1:
               	while(step < L)
            	{
            		a[i][--j]=num++;
            		step++;
            		if(num > n * n)
            		{
            			stop = true;
            			break;
            		}
            	}
            	break;//left
            case 2:
               	while(step < L)
            	{
            		a[--i][j]=num++;
            		step++;
            		if(num > n * n)
            		{
            			stop = true;
            			break;
            		}
            	}
            	break;//up
            case 3:
               	while(step < L)
            	{
            		a[i][++j]=num++;
            		step++;
            		if(num > n * n)
            		{
            			stop = true;
            			break;
            		}
            	}
            	break;
            }
            c++;
            direct++;
            if(c % 2 == 0)
            	L--;
        }
        return a;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 0;
		int[][] a = generateMatrix(n);
		for(int i = 0; i < n; i++)
		{
			for(int j = 0; j < n; j++)
			{
				System.out.print(a[i][j]+" ");
			}
			System.out.println();
		}
	}

}
