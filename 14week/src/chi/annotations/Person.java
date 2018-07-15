package chi.annotations;

@PersonToTable(tableName = "table_per")
public class Person {
	
	@AnnPer_Field(column = "tid", type = "int", length = 10)
	private int id;
	
	@AnnPer_Field(column = "tname", type = "varchar", length = 10)
	private String name;
	
	@AnnPer_Field(column = "tage", type = "int", length = 4)
	private int age;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

}
