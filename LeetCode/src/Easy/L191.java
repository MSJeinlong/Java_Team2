package Easy;

public class L191 {

    public static int hammingWeight(int n) {
        int bits = 0;
        
        for(int i = 0; i < 32; i++)
        {
            if((n & 1) == 1)
                bits++;
            n >>= 1;
        }
        return bits;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(hammingWeight(2147483647));
	}

}
