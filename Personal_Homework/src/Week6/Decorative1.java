package Week6;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Decorative1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 OutputStream os = null;  
	        try {  
	           // os = new DecoraOutputStream(new BufferedOutputStream(new FileOutputStream( new File("text.txt"))));  
	            os.write("qwerty".getBytes());  
	            os.flush();  
	        } catch (FileNotFoundException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } finally{  
	            try {  
	                os.close();  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            }  
	        }  
	    }  
	}


class DecoraOutputStream extends FilterOutputStream {  
    public DecoraOutputStream(OutputStream out) {  
        super(out);  
    }  
    public void write(int arg) throws IOException {  
        arg += 2;  
        if (arg >= 97 + 26) {  
            arg = arg - 26;  
        }  
        super.write(arg);  
    }  
}  
