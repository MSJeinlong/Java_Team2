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
 *         �����û���ʾ�ɼ���Ϣ�Ľ���
 *         </p>
 */
public class GradeInfo extends JPanel implements ActionListener {

	// ������Ҫ��JPanel
	private JPanel p1, p2, p3, p4, p5;
	private JLabel p1_lab1, p3_lab1, p1_lab2, p1_lab3, p1_lab4, p1_lab5, p1_lab6, p3_lab2, p4_lab1;
	private JComboBox p4_jcb, p1_jcb1, p1_jcb2, p1_jcb3, p1_jcb4;
	private JTextField p1_jtf1;
	private JButton p1_jb1, p4_jb1, p3_jb1, p4_jb2, p4_jb3, p4_jb4, p4_jb5, p4_jb6, p4_jb7;
	private JTable jt;
	private JScrollPane jsp;
	private GradeModel gm;
	private int rows; // ��ǰ����¼ʱ

	// ���췽��
	public GradeInfo() {

		// ������Ҫ�����
		p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p1_lab1 = new JLabel("������γ����ƣ�");
		p1_lab1.setFont(MyTools.f3);
		p1_jtf1 = new JTextField(20);
		// ��p1_jtf1�����س���
		p1_jtf1.addActionListener(this);

		p1_jb1 = new JButton("��ѯ", new ImageIcon("image/client/GradeInfo/query.png"));
		p1_lab2 = new JLabel("��ѯ����(��ѡ) =>  ѧ��:");
		p1_lab3 = new JLabel("  ѧ��:");
		String[] str1 = {"", "2016-2017","2017-2018", "2018-2019","2019-2020"};
		p1_jcb1 = new JComboBox(str1);
		String[] str2 = {"", "1", "2"};
		p1_jcb2 = new JComboBox(str2);
		p1_lab4 = new JLabel("    �ɼ�����(��ѡ)��");
		p1_lab5 = new JLabel("��  ���� ");
		p1_lab6 = new JLabel("��");
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
		// ��������뵽p1
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

		// �����в���p2
		p2 = new JPanel(new BorderLayout());
		gm = new GradeModel();
		gm.query("");
		jt = new JTable(gm);
		jt.setFont(MyTools.f1);
		jsp = new JScrollPane(jt);
		// jsp.add(jt);
		p2.add(jsp);
		// ��ȡ��������
		rows = gm.getRowCount();

		// �����ϲ���p3,p4,p5
		p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p3_lab1 = new JLabel("�ܹ���" + rows + "����¼");
		//p3_jb1 = new JButton("������Ϣ");
		//p3_jb1.setFont(MyTools.f3);
		p3_lab1.setFont(MyTools.f3);
	//	p3.add(p3_jb1);
		p3.add(p3_lab1);
		p4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		p4_lab1 = new JLabel("�������:");
		// ����������Ŀ�ѡ��
		String[] str = { "�����ɼ�", "����", "Ӧ��ѧ��" };
		p4_jcb = new JComboBox(str);
		p4_jb1 = new JButton("��ϸ��Ϣ", new ImageIcon("image/client/GradeInfo/moreInfo.png"));
		p4_jb2 = new JButton("���", new ImageIcon("image/client/GradeInfo/insert.png"));
		p4_jb3 = new JButton("�޸�", new ImageIcon("image/client/GradeInfo/update.png"));
		p4_jb4 = new JButton("ɾ��", new ImageIcon("image/client/GradeInfo/delete.png"));
		p4_jb5 = new JButton("����", new ImageIcon("image/client/GradeInfo/asc.png"));
		p4_jb6 = new JButton("����", new ImageIcon("image/client/GradeInfo/desc.png"));
		p4_jb7 = new JButton("���ڳɼ�", new ImageIcon("image/client/GradeInfo/aboutGrade.png"));
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

		// Ϊ������ťע�����
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

		// �Ѹ���JPanel���뵽this
		this.setLayout(new BorderLayout());
		this.add(p1, "North");
		this.add(p2, "Center");
		this.add(p5, "South");
		// this.setBackground(Color.pink);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// ��Ӧ���ְ�ť�¼�
		String action = e.getActionCommand();
		//��ȡ��ѯ����
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
			JOptionPane.showMessageDialog(null, "�ɼ�������ֵ����������ֵ", "����", JOptionPane.WARNING_MESSAGE);
			return;
		}
		// ��ѯ
		// �û������������ؼ��ֲ����»س������ߵ������ѯ����ťʱ��ִ�в�ѯ
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
				JOptionPane.showMessageDialog(null, "�ɼ�������ֵ����������ֵ", "����", JOptionPane.WARNING_MESSAGE);
				return;
			}*/
			//System.out.println(leftVal+" "+rightVal);
			gm = new GradeModel();
			gm.critQuery(name, syear, team, leftVal, rightVal);
			jt.setModel(gm);
			// ���»�ȡ����
			rows = gm.getRowCount();
			p3_lab1.setText("�ܹ���" + rows + "����¼");
			// System.out.println("��ѯ");
		} else if (action.equals("allInfo")) { // ȫ����ѯ
			this.allQuery();
		} else if (action.equals("insert")) { // ���
			new GradeDialog(null, "��ӳɼ���¼", true, "���", null);
			this.allQuery();
		} else if (action.equals("update")) { // �޸�
			int rowIndex = this.jt.getSelectedRow();
			if (rowIndex == -1) {
				JOptionPane.showMessageDialog(null, "��ѡ��һ��", "����", JOptionPane.WARNING_MESSAGE);
				return;
			}
			// �����û���ѡ��rowNum��ȡcourseName
			// �ڸ���courseName��ȡ��Ӧ��grade����
			String courseName = gm.getValueAt(rowIndex, 2).toString();

			MyGrade grade = new GradeServiceImpl().get(courseName);
			System.out.println("id:" + grade.getCno() + ", �γ���:" + courseName);

			new GradeDialog(null, "�޸ĳɼ���¼", true, "�޸�", grade);
			this.allQuery();
		} else if (action.equals("delete")) { // ɾ��
			int[] rows = this.jt.getSelectedRows(); // ��ȡ�û�ѡ����кţ�һ�л��߶��У�
			// �ж��û��Ƿ�ѡ������
			if (rows == null || rows.length == 0) {
				JOptionPane.showMessageDialog(null, "��ѡ��һ�л��߶���", "����", JOptionPane.WARNING_MESSAGE);
				return;
			} 
			// �����кŻ�ȡ����Ӧ�Ŀγ�����
			// ��ſγ����Ƶ�HashSet
			Set<String> names = new HashSet();
			for (int i : rows) {
				String name = "'" + gm.getValueAt(i, 2).toString() + "'";
				names.add(name); // ��ȡ�� i �е�3�е�ֵ�����γ����ƣ�
			}
			for (String i : names) {
				System.out.println(i);
			}
			int n = JOptionPane.showConfirmDialog(null, "ȷ��Ҫɾ������ѡ�������?", "ɾ��", JOptionPane.YES_NO_OPTION);
			// n == 0,˵���û�ѡ���ˡ��ǡ���ִ��ɾ������
			if (n == 0) {
				gm.delete(names);
				this.allQuery();
			}
		} else if(action.equals("aboutGrade")) {
			//�������ڳɼ��Ի���
			new aboutGrade();
		}

		// �������
		// ��ȡ�������
		String choose = p4_jcb.getSelectedItem().toString();
		String field = null;
		// �����û�ѡ��Ĳ�����������
		switch (choose) {
		case "�����ɼ�":
			field = "totalGrade";
			break;
		case "����":
			field = "gradePoint";
			break;
		case "Ӧ��ѧ��":
			field = "dueCredits";
			break;
		}
		if (action.equals("asc")) {	//�������
			//System.out.println("����:"+field);
			gm = new GradeModel();
			gm.asc(field, syear, team, leftVal, rightVal);
			jt.setModel(gm);
			// ���»�ȡ����
			rows = gm.getRowCount();
			p3_lab1.setText("�ܹ���" + rows + "����¼");
		} else if (action.equals("desc")) {	//�������
			//System.out.println("����"+field);
			gm = new GradeModel();
			gm.desc(field, syear, team, leftVal, rightVal);
			jt.setModel(gm);
			// ���»�ȡ����
			rows = gm.getRowCount();
			p3_lab1.setText("�ܹ���" + rows + "����¼");
		}

	}

	// ȫ����ѯ
	public void allQuery() {
		gm = new GradeModel();
		// ��ղ�ѯ�����Ϣ
		p1_jtf1.setText("");
		gm.query("");
		jt.setModel(gm);
		// ���»�ȡ����
		rows = gm.getRowCount();
		p3_lab1.setText("�ܹ���" + rows + "����¼");
	}

}
