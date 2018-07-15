package com.annotations;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//修饰类型
@Target(ElementType.TYPE)
//保留策略
//JVM运行时
@Retention(RetentionPolicy.RUNTIME)
public @interface GradeToTable {
	String tableName();
}
