package com.annotations;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//��������
@Target(ElementType.TYPE)
//��������
//JVM����ʱ
@Retention(RetentionPolicy.RUNTIME)
public @interface GradeToTable {
	String tableName();
}