package chi.annotations;
import java.lang.annotation.*;

//Ԫע��(mata_annotation):@target @retention
//����Ŀ�꣺�࣬���ԣ����Ƿ���
@Target(ElementType.METHOD)
//�������ԣ�����֮ǰ��VM ���������ʱ
@Retention(RetentionPolicy.RUNTIME)
public @interface Ann1{
	//������Ĳ�����һ����ʱ��һ��д��value����ô��ʹ��ʱ���Ϳ��Բ�д��������
	String value() default "";		//String �����ͣ�value�ǲ���
	int x();
	String [] a();
	Color color();	//������ö������
}