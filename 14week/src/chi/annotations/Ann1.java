package chi.annotations;
import java.lang.annotation.*;

//元注解(mata_annotation):@target @retention
//修饰目标：类，属性，还是方法
@Target(ElementType.METHOD)
//保留策略：编译之前，VM 虚拟机运行时
@Retention(RetentionPolicy.RUNTIME)
public @interface Ann1{
	//当定义的参数是一个的时候，一般写成value，那么在使用时，就可以不写参数名字
	String value() default "";		//String 是类型，value是参数
	int x();
	String [] a();
	Color color();	//参数是枚举类型
}