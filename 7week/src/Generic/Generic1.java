package Generic;

//泛型
//引入多个对象，只是数据类型不一样，其他的结构完全一样，写很多个类
//Solution1，把类型都写成Object,在进行类型转换是会出错
//Solution2,泛型的实质就是将“数据类型参数化”，是类的设计中的一个范式， i 不让
public class Generic1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Fan1<String>  f1 = new Fan1<String>("huang");
		Fan1<Integer> f2 = new Fan1<Integer>(1);
		System.out.println(f1.getData());
	}

}

//设计泛型类
class Fan1<T> {
	private T data;

	public Fan1(T data) {
		this.data = data;
	}
	
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	} 
}