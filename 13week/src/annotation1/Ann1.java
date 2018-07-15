package annotation1;
import java.lang.annotation.*;
import java.util.*;
@SuppressWarnings("unused")
public class Ann1 {
	public String toString(){
		return super.toString();
	}
	
	public void fun(){
		
	}
	
	@Annotation1(aa={""}, b = false)
	public void ffer(){}
	
	@SuppressWarnings("unchecked")
	List list = new ArrayList();
}
