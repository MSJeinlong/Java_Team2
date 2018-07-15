package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionTest {
    public static void main(String[] args) {
        Class c = null;
        try {
            // 根据传入的类的全名来创建Class对象,注意必须是全名
            c = Class.forName("test.Person");
            // 得到包路径
            System.out.println("package " + c.getPackage().getName() + ";\n");
            // 得到类修饰符
            System.out.print(Modifier.toString(c.getModifiers()));
            //得到类名
            System.out.print(" class " + c.getSimpleName());
            //得到父类名
            System.out.print(" extends " + c.getSuperclass().getSimpleName());
            //得到类实现的接口数组
            Class[] inters = c.getInterfaces();
            if (inters.length > 0) {
                System.out.print(" implements ");
                for (int i = 0; i < inters.length; i++) {
                    System.out.print(inters[i].getSimpleName());
                    if (i < inters.length - 1) {
                        System.out.print(", ");
                    }
                }
            }
            System.out.println(" {");
            // 获取类属性
            printField(c);
            // 获取类构造器
            printConstructor(c);
            // 获取类方法
            printMethods(c);
            System.out.println(" }");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    private static void printConstructor(Class c){
        Constructor[] cs = c.getDeclaredConstructors();
        for (int i = 0; i < cs.length; i++) {
            System.out.println();
            System.out.print("\t");
            // 得到整数形式构造函数修饰符，使用Modifier进行解码
            System.out.print(Modifier.toString(cs[i].getModifiers()) + " ");
            // 得到方法名
            System.out.print(cs[i].getName() + "(");
            // 得到方法参数数组
            Class[] paras = cs[i].getParameterTypes();
            for (int j = 0; j < paras.length; j++) {
                System.out.print(paras[j].getSimpleName() + " arg" + j);
                if (j < paras.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.print(")");
            System.out.println(" {");
            System.out.println("\t\t\\\\方法体");
            System.out.println("\t}");
        }
    }

    private static void printField(Class c) {
        // 得到属性数组
        Field[] fs = c.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            System.out.print("\t");
            // 得到整数形式属性修饰符，使用Modifier进行解码
            System.out.print(Modifier.toString(fs[i].getModifiers()) + " ");
            // 得到属性类型
            System.out.print(fs[i].getType().getSimpleName() + " ");
            // 得到属性名
            System.out.println(fs[i].getName() + ";");
        }
    }

    public static void printMethods(Class c) {
        // 得到方法数组
    	//只能得到所有的public方法
        //Method[] md = c.getMethods();
        //能得到类中所有的方法，无论是public 还是 private
        Method[] md = c.getDeclaredMethods();
/*		for(Method method:md) {
			System.out.println(method);
		}*/
        for (int i = 0; i < md.length; i++) {
            System.out.println();
            System.out.print("\t");
            // 得到整数形式方法修饰符，使用Modifier进行解码
            System.out.print(Modifier.toString(md[i].getModifiers()) + " ");
            // 得到方法返回类型
            System.out.print(md[i].getGenericReturnType() + " ");
            // 得到方法名
            System.out.print(md[i].getName() + "(");
            // 得到方法参数数组
            Class[] paras = md[i].getParameterTypes();
            for (int j = 0; j < paras.length; j++) {
                System.out.print(paras[j].getSimpleName() + " arg" + j);
                if (j < paras.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.print(")");
            // 得到抛出异常类的数组
            Class[] exceps = md[i].getExceptionTypes();
            if (exceps.length > 0) {
                System.out.print(" throws ");
                for (int k = 0; k < exceps.length; k++) {
                    System.out.print(exceps[k].getSimpleName());
                    if (k < exceps.length - 1) {
                        System.out.print(", ");
                    }
                }
            }
            System.out.println(" {");
            System.out.println("\t\t\\\\方法体");
            System.out.println("\t}");
        }
    }
}