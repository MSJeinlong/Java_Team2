package com.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
/**
 * 
 * @author JunLong 修饰类Grade的属性
 */
public @interface AnnGrade_field {
	String column(); // 表示MyGrade所对应的表中的列（字段）

	String type(); // 对应将来表中字段的类型

	int length(); // 表示将来表中字段的长度
}
