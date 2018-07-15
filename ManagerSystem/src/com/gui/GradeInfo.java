package com.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.definiClass.MyGrade;
import com.gui.dialog.GradeDialog;
import com.gui.dialog.aboutGrade;
import com.model.GradeModel;
import com.service.impl.GradeServiceImpl;
import com.tools.MyTools;

/**
 * 
 * @author JunLong
 *         <p>
 *         这是用户显示成绩信息的界面
 *         </p>
 */
public class GradeInfo extends JPanel implements ActionListener {

	// 定义需要的JPanel
	private JPanel p1, p2, p3, p4, p5;
	private JLabel p1_lab1, p3_lab1, p1_lab2, p1_lab3, p1_lab4, p1_lab5, p1_lab6, p3_lab2, p4_lab1;
	private JComboBox p4_jcb, p1_jcb1, p1_jcb2, p1_jcb3, p1_jcb4;
	private JTextField p1_jtf1;
	private JButton p1_jb1, p4_jb1, p3_jb1, p4_jb2, p4_jb3, p4_jb4, p4_jb5, p4_jb6, p4_jb7;
	private JTable jt;
	private JScrollPane jsp;
	private GradeModel gm;
	private int rows; // 当前表格记录时

	// 构造方法
	public GradeInfo() {

		// 创建需要的组件
		p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p1_lab1 = new JLabel("请输入课程名称：");
		p1_lab1.setFont(MyTools.f3);
		p1_jtf1 = new JTextField(20);
		// 让p1_jtf1监听回车键
		p1_jtf1.addActionListener(this);

		p1_jb1 = new JButton("查询", new ImageIcon("image/client/GradeInfo/query.png"));
		p1_lab2 = new JLabel("查询条件(可选) =>  学年:");
		p1_lab3 = new JLabel("  学期:");
		String[] str1 = {"", "2016-2017","2017-2018", "2018-2019","2019-2020"};
		p1_jcb1 = new JComboBox(str1);
		String[] str2 = {"", "1", "2"};
		p1_jcb2 = new JComboBox(str2);
		p1_lab4 = new JLabel("    成绩区间(可选)：");
		p1_lab5 = new JLabel("分  —— ");
		p1_lab6 = new JLabel("分");
		String[] str3 = {"", "0", "5", "10", "15", "20", "25", "30", "35", "40",
				"45", "50", "55", "60", "65", "70", "75", "80", "85", "90", "95", "100"};
		p1_jcb3 = new JComboBox(str3);
		String[] str4 = {"", "100", "95", "90", "85", "80", "75", "70", "65", 
				"60", "55", "50", "45", "40", "35", "30", "25", "20", "15", "10", "5"};
		p1_jcb4 = new JComboBox(str4);
		p1_lab2.setFont(MyTools.f3);
		p1_lab3.setFont(MyTools.f3);
		p1_lab4.setFont(MyTools.f3);
		p1_lab5.setFont(MyTools.f3);
		p1_lab6.setFont(MyTools.f3);
		p1_jcb1.setFont(MyTools.f3);
		p1_jcb2.setFont(MyTools.f3);
		p1_jb1.setFont(MyTools.f3);
		// 把组件加入到p1
		p1.add(p1_lab1);
		p1.add(p1_jtf1);
		p1.add(p1_jb1);
		p1.add(p1_lab2);
		p1.add(p1_jcb1);
		p1.add(p1_lab3);
		p1.add(p1_jcb2);
		p1.add(p1_lab4);
		p1.add(p1_jcb3);
		p1.add(p1_lab5);
		p1.add(p1_jcb4);
		p1.add(p1_lab6);

		// 处理中部的p2
		p2 = new JPanel(new BorderLayout());
		gm = new GradeModel();
		gm.query("");
		jt = new JTable(gm);
		jt.setFont(MyTools.f1);
		jsp = new JScrollPane(jt);
		// jsp.add(jt);
		p2.add(jsp);
		// 获取表格的行数
		rows = gm.getRowCount();

		// 处理南部的p3,p4,p5
		p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p3_lab1 = new JLabel("总共有" + rows + "条记录");
		//p3_jb1 = new JButton("更多信息");
		//p3_jb1.setFont(MyTools.f3);
		p3_lab1.setFont(MyTools.f3);
	//	p3.add(p3_jb1);
		p3.add(p3_lab1);
		p4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		p4_lab1 = new JLabel("排序参数:");
		// 定义下拉框的可选项
		String[] str = { "总评成绩", "绩点", "应得学分" };
		p4_jcb = new JComboBox(str);
		p4_jb1 = new JButton("详细信息", new ImageIcon("image/client/GradeInfo/moreInfo.png"));
		p4_jb2 = new JButton("添加", new ImageIcon("image/client/GradeInfo/insert.png"));
		p4_jb3 = new JButton("修改", new ImageIcon("image/client/GradeInfo/update.png"));
		p4_jb4 = new JButton("删除", new ImageIcon("image/client/GradeInfo/delete.png"));
		p4_jb5 = new JButton("升序", new ImageIcon("image/client/GradeInfo/asc.png"));
		p4_jb6 = new JButton("降序", new ImageIcon("image/client/GradeInfo/desc.png"));
		p4_jb7 = new JButton("关于成绩", new ImageIcon("image/client/GradeInfo/aboutGrade.png"));
		p4_jcb.setFont(MyTools.f3);
		p4_lab1.setFont(MyTools.f3);
		p4_jb1.setFont(MyTools.f3);
		p4_jb2.setFont(MyTools.f3);
		p4_jb3.setFont(MyTools.f3);
		p4_jb4.setFont(MyTools.f3);
		p4_jb5.setFont(MyTools.f3);
		p4_jb6.setFont(MyTools.f3);
		p4_jb7.setFont(MyTools.f3);
		p4.add(p4_jb7);
		p4.add(p4_lab1);
		p4.add(p4_jcb);
		p4.add(p4_jb5);
		p4.add(p4_jb6);
		p4.add(p4_jb1);
		p4.add(p4_jb2);
		p4.add(p4_jb3);
		p4.add(p4_jb4);

		// 为各个按钮注册监听
		p1_jb1.addActionListener(this);
		p1_jb1.setActionCommand("query");
		p4_jb1.addActionListener(this);
		p4_jb1.setActionCommand("allInfo");
		p4_jb2.addActionListener(this);
		p4_jb2.setActionCommand("insert");
		p4_jb3.addActionListener(this);
		p4_jb3.setActionCommand("update");
		p4_jb4.addActionListener(this);
		p4_jb4.setActionCommand("delete");
		p4_jb5.addActionListener(this);
		p4_jb5.setActionCommand("asc");
		p4_jb6.addActionListener(this);
		p4_jb6.setActionCommand("desc");
		p4_jb7.addActionListener(this);
		p4_jb7.setActionCommand("aboutGrade");

		p5 = new JPanel(new BorderLayout());
		p5.add(p3, "West");
		p5.add(p4, "East");

		// 把各个JPanel加入到this
		this.setLayout(new BorderLayout());
		this.add(p1, "North");
		this.add(p2, "Center");
		this.add(p5, "South");
		// this.setBackground(Color.pink);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 响应各种按钮事件
		String action = e.getActionCommand();
		//获取查询条件
		String syear = this.p1_jcb1.getSelectedItem().toString();
		String t = this.p1_jcb2.getSelectedItem().toString();
		String l = this.p1_jcb3.getSelectedItem().toString();
		String r = this.p1_jcb4.getSelectedItem().toString();
		int team = 0;
		int leftVal = -1;
		int rightVal = -1;
		if(!t.equals("")) {
			team = Integer.parseInt(t);
		}
		if(!l.equals("") && !r.equals("")) {
			leftVal = Integer.parseInt(l);
			rightVal = Integer.parseInt(r);
		}
		if(leftVal > rightVal) {
			JOptionPane.showMessageDialog(null, "成绩左区间值大于右区间值", "警告", JOptionPane.WARNING_MESSAGE);
			return;
		}
		// 查询
		// 用户在输入框输入关键字并按下回车键或者点击“查询”按钮时，执行查询
		if (action.equals("query") || e.getSource() == this.p1_jtf1) {
			String name = this.p1_jtf1.getText().trim();		
			/*if(!t.equals("")) {
				team = Integer.parseInt(t);
			}
			if(!l.equals("") && !r.equals("")) {
				leftVal = Integer.parseInt(l);
				rightVal = Integer.parseInt(r);
			}
			if(leftVal > rightVal) {
				JOptionPane.showMessageDialog(null, "成绩左区间值大于右区间值", "警告", JOptionPane.WARNING_MESSAGE);
				return;
			}*/
			//System.out.println(leftVal+" "+rightVal);
			gm = new GradeModel();
			gm.critQuery(name, syear, team, leftVal, rightVal);
			jt.setModel(gm);
			// 重新获取行数
			rows = gm.getRowCount();
			p3_lab1.setText("总共有" + rows + "条记录");
			// System.out.println("查询");
		} else if (action.equals("allInfo")) { // 全部查询
			this.allQuery();
		} else if (action.equals("insert")) { // 添加
			new GradeDialog(null, "添加成绩记录", true, "添加", null);
			this.allQuery();
		} else if (action.equals("update")) { // 修改
			int rowIndex = this.jt.getSelectedRow();
			if (rowIndex == -1) {
				JOptionPane.showMessageDialog(null, "请选择一行", "警告", JOptionPane.WARNING_MESSAGE);
				return;
			}
			// 根据用户所选的rowNum获取courseName
			// 在根据courseName获取相应的grade对象
			String courseName = gm.getValueAt(rowIndex, 2).toString();

			MyGrade grade = new GradeServiceImpl().get(courseName);
			System.out.println("id:" + grade.getCno() + ", 课程名:" + courseName);

			new GradeDialog(null, "修改成绩记录", true, "修改", grade);
			this.allQuery();
		} else if (action.equals("delete")) { // 删除
			int[] rows = this.jt.getSelectedRows(); // 获取用户选择的行号（一行或者多行）
			// 判断用户是否选择了行
			if (rows == null || rows.length == 0) {
				JOptionPane.showMessageDialog(null, "请选择一行或者多行", "警告", JOptionPane.WARNING_MESSAGE);
				return;
			} 
			// 根据行号获取所对应的课程名称
			// 存放课程名称的HashSet
			Set<String> names = new HashSet();
			for (int i : rows) {
				String name = "'" + gm.getValueAt(i, 2).toString() + "'";
				names.add(name); // 获取第 i 行第3列的值（即课程名称）
			}
			for (String i : names) {
				System.out.println(i);
			}
			int n = JOptionPane.showConfirmDialog(null, "确定要删除您所选择的行吗?", "删除", JOptionPane.YES_NO_OPTION);
			// n == 0,说明用户选择了“是”，执行删除操作
			if (n == 0) {
				gm.delete(names);
				this.allQuery();
			}
		} else if(action.equals("aboutGrade")) {
			//调出关于成绩对话框
			new aboutGrade();
		}

		// 排序操作
		// 获取排序参数
		String choose = p4_jcb.getSelectedItem().toString();
		String field = null;
		// 根据用户选择的参数进行排序
		switch (choose) {
		case "总评成绩":
			field = "totalGrade";
			break;
		case "绩点":
			field = "gradePoint";
			break;
		case "应得学分":
			field = "dueCredits";
			break;
		}
		if (action.equals("asc")) {	//升序操作
			//System.out.println("升序:"+field);
			gm = new GradeModel();
			gm.asc(field, syear, team, leftVal, rightVal);
			jt.setModel(gm);
			// 重新获取行数
			rows = gm.getRowCount();
			p3_lab1.setText("总共有" + rows + "条记录");
		} else if (action.equals("desc")) {	//降序操作
			//System.out.println("降序："+field);
			gm = new GradeModel();
			gm.desc(field, syear, team, leftVal, rightVal);
			jt.setModel(gm);
			// 重新获取行数
			rows = gm.getRowCount();
			p3_lab1.setText("总共有" + rows + "条记录");
		}

	}

	// 全部查询
	public void allQuery() {
		gm = new GradeModel();
		// 清空查询框的信息
		p1_jtf1.setText("");
		gm.query("");
		jt.setModel(gm);
		// 重新获取行数
		rows = gm.getRowCount();
		p3_lab1.setText("总共有" + rows + "条记录");
	}

}
