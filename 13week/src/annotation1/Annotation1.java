package annotation1;
/**
 * 
 * @author C506-user069
 *	×¢½â
 */
public @interface Annotation1 {
	String value() default "uncheck";
	int i() default 1;
	String[] aa();
	int[] array() default{7,8,9};
	boolean b();
}
