package com.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
/**
 * 
 * @author JunLong ������Grade������
 */
public @interface AnnGrade_field {
	String column(); // ��ʾMyGrade����Ӧ�ı��е��У��ֶΣ�

	String type(); // ��Ӧ���������ֶε�����

	int length(); // ��ʾ���������ֶεĳ���
}
