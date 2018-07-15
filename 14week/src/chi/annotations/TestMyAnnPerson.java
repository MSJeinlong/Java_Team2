package chi.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

//����Person���е�ע����Ϣ�������õ�ע����Ϣͨ��������ȡ����
public class TestMyAnnPerson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Class c1 = Person.class;
		Class c1 = null;
		try {
			c1 = Class.forName("chi.annotations.Person");
			PersonToTable ann1 = (PersonToTable) c1.getAnnotation(PersonToTable.class);
			System.out.println(ann1.tableName());
			//ͨ�����䣬ϵͳ�͵õ�����Person������Ӧ�ı�����table_per
			System.out.println("=====================================");
			//ͨ������õ��������ֵ
			Field[] fields = c1.getDeclaredFields();
			for(Field field: fields){
				System.out.println(field);
			}
			System.out.println("=====================================");
			//ͨ�����䣬�õ���name���Ե�ע����Ϣ��tname:varchar:10
			Field f1= c1.getDeclaredField("name");
			AnnPer_Field af = f1.getAnnotation(AnnPer_Field.class);
			System.out.println(af.column()+":"+af.type()+":"+af.length());
			
			//ͨ�����䣬�õ�id���Ե�ע����Ϣ:tid:int:10
			Field id = c1.getDeclaredField("id");
			AnnPer_Field af1 = id.getAnnotation(AnnPer_Field.class);
			System.out.println(af1.column()+":"+af1.type()+":"+af1.length());
			
			//ͨ�����䣬�õ�age���Ե�ע����Ϣ:tage:int:4
			Field age = c1.getDeclaredField("age");
			AnnPer_Field af2 = age.getAnnotation(AnnPer_Field.class);
			System.out.println(af2.column()+":"+af2.type()+":"+af2.length());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.print(c1);
	}

}
