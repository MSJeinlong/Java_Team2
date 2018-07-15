package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionTest {
    public static void main(String[] args) {
        Class c = null;
        try {
            // ���ݴ�������ȫ��������Class����,ע�������ȫ��
            c = Class.forName("test.Person");
            // �õ���·��
            System.out.println("package " + c.getPackage().getName() + ";\n");
            // �õ������η�
            System.out.print(Modifier.toString(c.getModifiers()));
            //�õ�����
            System.out.print(" class " + c.getSimpleName());
            //�õ�������
            System.out.print(" extends " + c.getSuperclass().getSimpleName());
            //�õ���ʵ�ֵĽӿ�����
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
            // ��ȡ������
            printField(c);
            // ��ȡ�๹����
            printConstructor(c);
            // ��ȡ�෽��
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
            // �õ�������ʽ���캯�����η���ʹ��Modifier���н���
            System.out.print(Modifier.toString(cs[i].getModifiers()) + " ");
            // �õ�������
            System.out.print(cs[i].getName() + "(");
            // �õ�������������
            Class[] paras = cs[i].getParameterTypes();
            for (int j = 0; j < paras.length; j++) {
                System.out.print(paras[j].getSimpleName() + " arg" + j);
                if (j < paras.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.print(")");
            System.out.println(" {");
            System.out.println("\t\t\\\\������");
            System.out.println("\t}");
        }
    }

    private static void printField(Class c) {
        // �õ���������
        Field[] fs = c.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            System.out.print("\t");
            // �õ�������ʽ�������η���ʹ��Modifier���н���
            System.out.print(Modifier.toString(fs[i].getModifiers()) + " ");
            // �õ���������
            System.out.print(fs[i].getType().getSimpleName() + " ");
            // �õ�������
            System.out.println(fs[i].getName() + ";");
        }
    }

    public static void printMethods(Class c) {
        // �õ���������
    	//ֻ�ܵõ����е�public����
        //Method[] md = c.getMethods();
        //�ܵõ��������еķ�����������public ���� private
        Method[] md = c.getDeclaredMethods();
/*		for(Method method:md) {
			System.out.println(method);
		}*/
        for (int i = 0; i < md.length; i++) {
            System.out.println();
            System.out.print("\t");
            // �õ�������ʽ�������η���ʹ��Modifier���н���
            System.out.print(Modifier.toString(md[i].getModifiers()) + " ");
            // �õ�������������
            System.out.print(md[i].getGenericReturnType() + " ");
            // �õ�������
            System.out.print(md[i].getName() + "(");
            // �õ�������������
            Class[] paras = md[i].getParameterTypes();
            for (int j = 0; j < paras.length; j++) {
                System.out.print(paras[j].getSimpleName() + " arg" + j);
                if (j < paras.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.print(")");
            // �õ��׳��쳣�������
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
            System.out.println("\t\t\\\\������");
            System.out.println("\t}");
        }
    }
}