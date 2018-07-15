package com.gui.dialog;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.definiClass.MyGrade;
import com.model.GradeModel;
import com.service.impl.GradeServiceImpl;

/**
 * 
 * @author JunLong
 *         <p>
 *         这是一个弹出式的对话框，用于进行添加或者修改
 *         </p>
 */
public class GradeDialog extends JDialog implements ActionListener {

	// 定义组件
	JLabel[] labs = new JLabel[10];
	JTextField[] jtfs = new JTextField[7];
	// 定义两个下拉框
	JComboBox jcb1, jcb2, jcb3;
	JPanel jp1, jp2, jp3;
	// 两个按钮
	JButton jb1, jb2;
	MyGrade grade;
	String Command; // 用户判断是添加还是修改
	GradeServiceImpl gsim; // 数据库操作服务对象
	GradeModel gm; // 用于修改操作所需的GradeModel
	private Image titleIcon;

	/**
	 * 构造方法
	 * 
	 * @param owner--它的父窗口
	 * @param title--窗口名
	 * @param model--指定是模式窗口，还是非模式的窗口
	 * @param Command--操作指令
	 * @param grade--成绩对象（如果是添加操作，则为空）
	 */
	public GradeDialog(Frame owner, String title, boolean model, String Command, MyGrade grade) {
		// 调用父类方法初始化
		super(owner, title, model);

		// 初始化相关参数
		this.Command = Command;
		this.grade = grade;
		gsim = new GradeServiceImpl();

		// 创建组件
		// 设计jp1
		labs[0] = new JLabel("学年(*)：");
		labs[1] = new JLabel("学期(*)：");
		labs[2] = new JLabel("课程名称(*)：");
		labs[3] = new JLabel("总评成绩：");
		labs[4] = new JLabel("补考成绩：");
		labs[5] = new JLabel("重修成绩1：");
		labs[6] = new JLabel("重修成绩2：");
		labs[7] = new JLabel("重修成绩3：");
		labs[8] = new JLabel("绩点：");
		labs[9] = new JLabel("应的学分：");

		jp1 = new JPanel();
		// 设置面板为网格布局
		jp1.setLayout(new GridLayout(labs.length, 1));
		// JLabel加入JPanel
		for (JLabel lab : labs) {
			jp1.add(lab);
		}

		// 设计jp2
		jp2 = new JPanel();
		// 设置面板为网格布局
		jp2.setLayout(new GridLayout(labs.length, 1));
		jtfs[0] = new JTextField();
		jtfs[1] = new JTextField();
		jtfs[2] = new JTextField();
		jtfs[3] = new JTextField();
		jtfs[4] = new JTextField();
		jtfs[5] = new JTextField();
		jtfs[6] = new JTextField();

		// 设置下拉框的可选项
		// 设置学年的可选项
		String[] str1 = { "2016-2017", "2017-2018", "2018-2019", "2019-2020" };
		// 设置学期的可选项
		String[] str2 = { "1", "2" };
		// 设置应得学分可选项
		String[] str3 = { "1", "1.5", "2", "2.5", "3", "3.5", "4", "4.5", "5", "5.5", "6" };
		// 把可选项加入到下拉框,
		jcb1 = new JComboBox(str1);
		jcb2 = new JComboBox(str2);
		jcb3 = new JComboBox(str3);

		if (Command.equals("修改")) {
			// 设置学年
			jcb1.setSelectedItem(grade.getSchoolYear());
			// 设置学期
			jcb2.setSelectedItem(grade.getTeam());
			// 设置文本框内容
			jtfs[0].setText(grade.getCourseName());
			Integer tg = grade.getTotalGrade();
			if (tg == null) {
				jtfs[1].setText(null);
			} else {
				jtfs[1].setText(tg.toString());
			}
			Integer mg = grade.getMakeUpGrade();
			if (mg == null) {
				jtfs[2].setText(null);
			} else {
				jtfs[2].setText(mg.toString());
			}
			Integer rg1 = grade.getRetakeGrade1();
			if (rg1 == null) {
				jtfs[3].setText(null);
			} else {
				jtfs[3].setText(rg1.toString());
			}
			Integer rg2 = grade.getRetakeGrade2();
			if (rg2 == null) {
				jtfs[4].setText(null);
			} else {
				jtfs[4].setText(rg1.toString());
			}
			Integer rg3 = grade.getRetakeGrade3();
			if (rg3 == null) {
				jtfs[5].setText(null);
			} else {
				jtfs[5].setText(rg3.toString());
			}
			Float gp = grade.getGradePoint();
			if (gp == null) {
				jtfs[6].setText(null);
			} else {
				jtfs[6].setText(gp.toString());
			}

			// 设置应得学分
			jcb3.setSelectedItem(grade.getDueCredits());
		}

		// 按顺序向jp2加入组件
		jp2.add(jcb1);
		jp2.add(jcb2);
		for (JTextField jtf : jtfs) {
			jp2.add(jtf);
		}
		jp2.add(jcb3);

		// 处理jp3
		jp3 = new JPanel();
		jb1 = new JButton("确认", new ImageIcon("image/client/Dialog/confirm.png"));
		jb2 = new JButton("取消", new ImageIcon("image/client/Dialog/cancel.png"));

		// 按钮注册监听
		jb1.addActionListener(this);
		jb2.addActionListener(this);

		jp3.add(jb1);
		jp3.add(jb2);

		// 设置Dialog属性
		// 设置布局
		this.add(jp1, BorderLayout.WEST);
		this.add(jp2, BorderLayout.CENTER);
		this.add(jp3, BorderLayout.SOUTH);

		// 展现
		this.setBounds(800, 400, 400, 400);
		// 设置图标
		try {
			if (Command.equals("修改")) {
				titleIcon = ImageIO.read(new File("image/client/GradeInfo/update.png"));
			} else {
				titleIcon = ImageIO.read(new File("image/client/GradeInfo/insert.png"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setIconImage(titleIcon);
		this.setVisible(true);
	}

	/*
	 * // 获取由用户输入数据生成grade对象 public MyGrade getGrade() { //
	 * 新建一个grade对象，用于存放用户添加的grade数据 grade = new MyGrade();
	 * grade.setSchoolYear(jcb1.getSelectedItem().toString()); grade.setTeam(new
	 * Integer(jcb2.getSelectedItem().toString()));
	 * grade.setCourseName(jtfs[0].getText()); grade.setTotalGrade(new
	 * Integer(jtfs[1].getText().trim())); grade.setMakeUpGrade(new
	 * Integer(jtfs[2].getText().trim())); grade.setRetakeGrade1(new
	 * Integer(jtfs[3].getText().trim())); grade.setRetakeGrade2(new
	 * Integer(jtfs[4].getText().trim())); grade.setRetakeGrade3(new
	 * Integer(jtfs[5].getText().trim())); grade.setGradePoint(new
	 * Float(jtfs[6].getText().trim())); grade.setDueCredits(new
	 * Float(jtfs[7].getText().trim())); return grade; }
	 */

	/**
	 * 检查int型数据
	 * 
	 * @param textInt
	 *            用户输入的int型数据
	 * @return 数值型数中没有非法字母，则返回true，否则返回false
	 */
	public boolean checkInt(String textInt) {
		char[] val = textInt.toCharArray();
		for (int i : val) {
			if (i < '0' || i > '9') {
				return false;
			}
		}
		return true;
	}

	public boolean checkFloat(String textFloat) {
		char[] val = textFloat.toCharArray();
		for (int i : val) {
			if (i == '.') {
				continue;
			}
			if (i < '0' || i > '9') {
				return false;
			}
		}
		return true;
	}

	/**
	 * 检查用户添加或者修改grade信息时，根据其输入数据生成的对象检查是否合法
	 * 
	 * @param grade
	 *            由用户输入数据生成的对象
	 * @return 0 -- 所有输入数据合法
	 * @return 1 -- 课程名称为空错误
	 * @return 2 -- 总评成绩数值错误
	 * @return 3 -- 补考成绩数值错误
	 * @return 4 -- 重修成绩1数值错误
	 * @return 5 -- 重修成绩2数值错误
	 * @return 6 -- 重修成绩3数值错误
	 * @return 7 -- 绩点数值错误
	 */
	public int checkGrade() {
		String schoolYear = jcb1.getSelectedItem().toString();
		String team = jcb2.getSelectedItem().toString();
		Integer totalGrade = null;
		Integer makeUpGrade = null;
		Integer retakeGrade1 = null;
		Integer retakeGrade2 = null;
		Integer retakeGrade3 = null;
		Float gradePoint = null;
		Float dueCredits = null;
		String courseName = jtfs[0].getText().trim();
		String str1 = jtfs[1].getText().trim();
		String str2 = jtfs[2].getText().trim();
		String str3 = jtfs[3].getText().trim();
		String str4 = jtfs[4].getText().trim();
		String str5 = jtfs[5].getText().trim();
		String str6 = jtfs[6].getText().trim();

		// 首先检查课程名称是否为空
		if (courseName == null || courseName.equals("")) { // 课程名称为空错误
			return 1;
		}
		//再检查课程名称是否重复
		//添加
		List<MyGrade> list = gsim.list();
		for(MyGrade mg:list) {
			if(this.Command.equals("添加") && mg.getCourseName().equals(courseName) ) {
				return 1;
			} else if(this.Command.equals("修改") && mg.getCourseName().equals(courseName) && mg.equals(this.grade)) {
				return 1;
			}
		}
		
		// 检查文本框是否为空
		if (str1 != null && !str1.equals("")) {
			if (checkInt(str1)) {
				totalGrade = new Integer(str1);
			} else {
				return 2;
			}
		}
		if (str2 != null && !str2.equals("")) {
			if (checkInt(str2)) {
				makeUpGrade = new Integer(str2);
			} else {
				return 3;
			}
		}
		if (str3 != null && !str3.equals("")) {
			if (checkInt(str3)) {
				retakeGrade1 = new Integer(str3);
			} else {
				return 4;
			}
		}
		if (str4 != null && !str4.equals("")) {
			if (checkInt(str3)) {
				retakeGrade2 = new Integer(str4);
			} else {
				return 5;
			}
		}
		if (str5 != null && !str5.equals("")) {
			if (checkInt(str5)) {
				retakeGrade3 = new Integer(str5);
			} else {
				return 6;
			}
		}
		if (str6 != null && !str6.equals("")) {
			if (checkFloat(str6)) {
				dueCredits = new Float(str6);
			} else {
				return 7;
			}
		}

		if (totalGrade != null && (totalGrade < 0 || totalGrade > 100)) { // 总评成绩数值错误
			return 2;
		} else if (makeUpGrade != null && (makeUpGrade < 0 || makeUpGrade > 100)) { // 补考成绩数值错误
			return 3;
		} else if (retakeGrade1 != null && (retakeGrade1 < 0 || retakeGrade1 > 100)) { // 重修成绩1数值错误
			return 4;
		} else if (retakeGrade2 != null && (retakeGrade2 < 0 || retakeGrade2 > 100)) { // 重修成绩2数值错误
			return 5;
		} else if (retakeGrade3 != null && (retakeGrade3 < 0 || retakeGrade3 > 100)) { // 重修成绩3数值错误
			return 6;
		} else if (gradePoint != null && (gradePoint < 0 || gradePoint > 10)) { // 绩点数值错误
			return 7;
		}
		// 通过所有检查，说明数据是合法输入，可以生成对象
		if (grade == null) {
			grade = new MyGrade();
		}
		grade.setSchoolYear(schoolYear);
		grade.setTeam(Integer.parseInt(team));
		grade.setCourseName(courseName);
		grade.setTotalGrade(totalGrade);
		grade.setMakeUpGrade(makeUpGrade);
		grade.setRetakeGrade1(retakeGrade1);
		grade.setRetakeGrade2(retakeGrade2);
		grade.setRetakeGrade3(retakeGrade3);
		grade.setGradePoint(gradePoint);
		grade.setDueCredits(dueCredits);
		return 0; // 所有数据合法输入则返回0
	}

	/**
	 * @return 操作成功返回true，否则返回false;
	 */
	public boolean update() {
		// 判断是添加还是删除
		if (this.Command.equals("添加")) {
			return gsim.insert(grade);
		} else if (this.Command.equals("修改")) {
			System.out.println("id:" + grade.getCno());
			return gsim.update(grade);
		}
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// 当用户点击了“确定”按钮时
		if (e.getSource() == jb1) {
			// 获取对象
			// 查错
			int res = this.checkGrade();
			// 根据res判断数据是否合法
			// 如果数据不合法，则通过弹出框提示用户
			switch (res) {
			// 执行修改或者添加操作
			case 0:
				if (this.update()) { // 操作成功
					JOptionPane.showMessageDialog(null, Command + "成功", "信息", JOptionPane.INFORMATION_MESSAGE);
					this.dispose();
				} else { // 操作失败
					JOptionPane.showMessageDialog(null, Command + "失败", "错误", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case 1:
				JOptionPane.showMessageDialog(null, "课程名称为空或者重复", "错误", JOptionPane.ERROR_MESSAGE);
				break;
			case 2:
				JOptionPane.showMessageDialog(null, "总评成绩数值错误", "错误", JOptionPane.ERROR_MESSAGE);
				break;
			case 3:
				JOptionPane.showMessageDialog(null, "补考成绩数值错误", "错误", JOptionPane.ERROR_MESSAGE);
				break;
			case 4:
				JOptionPane.showMessageDialog(null, "重修成绩1数值错误", "错误", JOptionPane.ERROR_MESSAGE);
				break;
			case 5:
				JOptionPane.showMessageDialog(null, "重修成绩2数值错误", "错误", JOptionPane.ERROR_MESSAGE);
				break;
			case 6:
				JOptionPane.showMessageDialog(null, "重修成绩3数值错误", "错误", JOptionPane.ERROR_MESSAGE);
				break;
			case 7:
				JOptionPane.showMessageDialog(null, "绩点数值错误", "错误", JOptionPane.ERROR_MESSAGE);
				break;
			}

		} else if (e.getSource() == jb2) { // 用户点击“取消”按钮，关闭对话框
			this.dispose();
		}
	}

}
