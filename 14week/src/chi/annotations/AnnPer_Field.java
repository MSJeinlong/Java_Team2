package chi.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnPer_Field {
	String column();	//表示Person所对应的表中的列（字段）
	String type();		//对应将来表中字段的类型
	int length();	//表示将来表中字段的长度
}
