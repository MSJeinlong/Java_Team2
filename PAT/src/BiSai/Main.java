package BiSai;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        if(in.hasNext()){
            int a = Integer.parseInt(in.nextLine());
            String st;
            char[] cr;
            if(a<100) {
            st=in.nextLine();
            cr = st.toCharArray();
            for(int i=0;i<a;i++)
                {
                    for(int j=(st.length()/4);j>=0;j--){
                        if(j*4+i<st.length())
                          System.out.printf("%c",cr[j*4+i]);
                        else
                            System.out.printf("%c",' ');
                     if(j==0&&(a!=i))
                      System.out.println();
                }
                }

            }
        }
    }
}