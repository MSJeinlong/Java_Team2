package chi.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnPer_Field {
	String column();	//��ʾPerson����Ӧ�ı��е��У��ֶΣ�
	String type();		//��Ӧ���������ֶε�����
	int length();	//��ʾ���������ֶεĳ���
}
