package com.definiClass;

public class MyGrade {

	int cno; // 课程号
	int id; // 学号
	String name; // 姓名
	String schoolYear; // 学年
	int team; // 学年
	String courseName; // 课程名称
	Integer totalGrade; // 总评成绩
	Integer makeUpGrade; // 补考成绩
	Integer retakeGrade1; // 重修成绩1
	Integer retakeGrade2; // 重修成绩2
	Integer retakeGrade3; // 重修成绩3
	Float gradePoint; // 绩点
	Float dueCredits; // 应得学分

	@Override
	public String toString() {
		return "MyGrade [cno=" + cno + ", id=" + id + ", name=" + name + ", schoolYear=" + schoolYear + ", team=" + team
				+ ", courseName=" + courseName + ", totalGrade=" + totalGrade + ", makeUpGrade=" + makeUpGrade
				+ ", retakeGrade1=" + retakeGrade1 + ", retakeGrade2=" + retakeGrade2 + ", retakeGrade3=" + retakeGrade3
				+ ", gradePoint=" + gradePoint + ", dueCredits=" + dueCredits + "]";
	}

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

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

	public String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Integer getTotalGrade() {
		return totalGrade;
	}

	public void setTotalGrade(Integer totalGrade) {
		this.totalGrade = totalGrade;
	}

	public Integer getMakeUpGrade() {
		return makeUpGrade;
	}

	public void setMakeUpGrade(Integer makeUpGrade) {
		this.makeUpGrade = makeUpGrade;
	}

	public Integer getRetakeGrade1() {
		return retakeGrade1;
	}

	public void setRetakeGrade1(Integer retakeGrade1) {
		this.retakeGrade1 = retakeGrade1;
	}

	public Integer getRetakeGrade2() {
		return retakeGrade2;
	}

	public void setRetakeGrade2(Integer retakeGrade2) {
		this.retakeGrade2 = retakeGrade2;
	}

	public Integer getRetakeGrade3() {
		return retakeGrade3;
	}

	public void setRetakeGrade3(Integer retakeGrade3) {
		this.retakeGrade3 = retakeGrade3;
	}

	public Float getGradePoint() {
		return gradePoint;
	}

	public void setGradePoint(Float gradePoint) {
		this.gradePoint = gradePoint;
	}

	public Float getDueCredits() {
		return dueCredits;
	}

	public void setDueCredits(Float dueCredits) {
		this.dueCredits = dueCredits;
	}

}
