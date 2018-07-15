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
 *         ����һ������ʽ�ĶԻ������ڽ�����ӻ����޸�
 *         </p>
 */
public class GradeDialog extends JDialog implements ActionListener {

	// �������
	JLabel[] labs = new JLabel[10];
	JTextField[] jtfs = new JTextField[7];
	// ��������������
	JComboBox jcb1, jcb2, jcb3;
	JPanel jp1, jp2, jp3;
	// ������ť
	JButton jb1, jb2;
	MyGrade grade;
	String Command; // �û��ж�����ӻ����޸�
	GradeServiceImpl gsim; // ���ݿ�����������
	GradeModel gm; // �����޸Ĳ��������GradeModel
	private Image titleIcon;

	/**
	 * ���췽��
	 * 
	 * @param owner--���ĸ�����
	 * @param title--������
	 * @param model--ָ����ģʽ���ڣ����Ƿ�ģʽ�Ĵ���
	 * @param Command--����ָ��
	 * @param grade--�ɼ������������Ӳ�������Ϊ�գ�
	 */
	public GradeDialog(Frame owner, String title, boolean model, String Command, MyGrade grade) {
		// ���ø��෽����ʼ��
		super(owner, title, model);

		// ��ʼ����ز���
		this.Command = Command;
		this.grade = grade;
		gsim = new GradeServiceImpl();

		// �������
		// ���jp1
		labs[0] = new JLabel("ѧ��(*)��");
		labs[1] = new JLabel("ѧ��(*)��");
		labs[2] = new JLabel("�γ�����(*)��");
		labs[3] = new JLabel("�����ɼ���");
		labs[4] = new JLabel("�����ɼ���");
		labs[5] = new JLabel("���޳ɼ�1��");
		labs[6] = new JLabel("���޳ɼ�2��");
		labs[7] = new JLabel("���޳ɼ�3��");
		labs[8] = new JLabel("���㣺");
		labs[9] = new JLabel("Ӧ��ѧ�֣�");

		jp1 = new JPanel();
		// �������Ϊ���񲼾�
		jp1.setLayout(new GridLayout(labs.length, 1));
		// JLabel����JPanel
		for (JLabel lab : labs) {
			jp1.add(lab);
		}

		// ���jp2
		jp2 = new JPanel();
		// �������Ϊ���񲼾�
		jp2.setLayout(new GridLayout(labs.length, 1));
		jtfs[0] = new JTextField();
		jtfs[1] = new JTextField();
		jtfs[2] = new JTextField();
		jtfs[3] = new JTextField();
		jtfs[4] = new JTextField();
		jtfs[5] = new JTextField();
		jtfs[6] = new JTextField();

		// ����������Ŀ�ѡ��
		// ����ѧ��Ŀ�ѡ��
		String[] str1 = { "2016-2017", "2017-2018", "2018-2019", "2019-2020" };
		// ����ѧ�ڵĿ�ѡ��
		String[] str2 = { "1", "2" };
		// ����Ӧ��ѧ�ֿ�ѡ��
		String[] str3 = { "1", "1.5", "2", "2.5", "3", "3.5", "4", "4.5", "5", "5.5", "6" };
		// �ѿ�ѡ����뵽������,
		jcb1 = new JComboBox(str1);
		jcb2 = new JComboBox(str2);
		jcb3 = new JComboBox(str3);

		if (Command.equals("�޸�")) {
			// ����ѧ��
			jcb1.setSelectedItem(grade.getSchoolYear());
			// ����ѧ��
			jcb2.setSelectedItem(grade.getTeam());
			// �����ı�������
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

			// ����Ӧ��ѧ��
			jcb3.setSelectedItem(grade.getDueCredits());
		}

		// ��˳����jp2�������
		jp2.add(jcb1);
		jp2.add(jcb2);
		for (JTextField jtf : jtfs) {
			jp2.add(jtf);
		}
		jp2.add(jcb3);

		// ����jp3
		jp3 = new JPanel();
		jb1 = new JButton("ȷ��", new ImageIcon("image/client/Dialog/confirm.png"));
		jb2 = new JButton("ȡ��", new ImageIcon("image/client/Dialog/cancel.png"));

		// ��ťע�����
		jb1.addActionListener(this);
		jb2.addActionListener(this);

		jp3.add(jb1);
		jp3.add(jb2);

		// ����Dialog����
		// ���ò���
		this.add(jp1, BorderLayout.WEST);
		this.add(jp2, BorderLayout.CENTER);
		this.add(jp3, BorderLayout.SOUTH);

		// չ��
		this.setBounds(800, 400, 400, 400);
		// ����ͼ��
		try {
			if (Command.equals("�޸�")) {
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
	 * // ��ȡ���û�������������grade���� public MyGrade getGrade() { //
	 * �½�һ��grade�������ڴ���û���ӵ�grade���� grade = new MyGrade();
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
	 * ���int������
	 * 
	 * @param textInt
	 *            �û������int������
	 * @return ��ֵ������û�зǷ���ĸ���򷵻�true�����򷵻�false
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
	 * ����û���ӻ����޸�grade��Ϣʱ�������������������ɵĶ������Ƿ�Ϸ�
	 * 
	 * @param grade
	 *            ���û������������ɵĶ���
	 * @return 0 -- �����������ݺϷ�
	 * @return 1 -- �γ�����Ϊ�մ���
	 * @return 2 -- �����ɼ���ֵ����
	 * @return 3 -- �����ɼ���ֵ����
	 * @return 4 -- ���޳ɼ�1��ֵ����
	 * @return 5 -- ���޳ɼ�2��ֵ����
	 * @return 6 -- ���޳ɼ�3��ֵ����
	 * @return 7 -- ������ֵ����
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

		// ���ȼ��γ������Ƿ�Ϊ��
		if (courseName == null || courseName.equals("")) { // �γ�����Ϊ�մ���
			return 1;
		}
		//�ټ��γ������Ƿ��ظ�
		//���
		List<MyGrade> list = gsim.list();
		for(MyGrade mg:list) {
			if(this.Command.equals("���") && mg.getCourseName().equals(courseName) ) {
				return 1;
			} else if(this.Command.equals("�޸�") && mg.getCourseName().equals(courseName) && mg.equals(this.grade)) {
				return 1;
			}
		}
		
		// ����ı����Ƿ�Ϊ��
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

		if (totalGrade != null && (totalGrade < 0 || totalGrade > 100)) { // �����ɼ���ֵ����
			return 2;
		} else if (makeUpGrade != null && (makeUpGrade < 0 || makeUpGrade > 100)) { // �����ɼ���ֵ����
			return 3;
		} else if (retakeGrade1 != null && (retakeGrade1 < 0 || retakeGrade1 > 100)) { // ���޳ɼ�1��ֵ����
			return 4;
		} else if (retakeGrade2 != null && (retakeGrade2 < 0 || retakeGrade2 > 100)) { // ���޳ɼ�2��ֵ����
			return 5;
		} else if (retakeGrade3 != null && (retakeGrade3 < 0 || retakeGrade3 > 100)) { // ���޳ɼ�3��ֵ����
			return 6;
		} else if (gradePoint != null && (gradePoint < 0 || gradePoint > 10)) { // ������ֵ����
			return 7;
		}
		// ͨ�����м�飬˵�������ǺϷ����룬�������ɶ���
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
		return 0; // �������ݺϷ������򷵻�0
	}

	/**
	 * @return �����ɹ�����true�����򷵻�false;
	 */
	public boolean update() {
		// �ж�����ӻ���ɾ��
		if (this.Command.equals("���")) {
			return gsim.insert(grade);
		} else if (this.Command.equals("�޸�")) {
			System.out.println("id:" + grade.getCno());
			return gsim.update(grade);
		}
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// ���û�����ˡ�ȷ������ťʱ
		if (e.getSource() == jb1) {
			// ��ȡ����
			// ���
			int res = this.checkGrade();
			// ����res�ж������Ƿ�Ϸ�
			// ������ݲ��Ϸ�����ͨ����������ʾ�û�
			switch (res) {
			// ִ���޸Ļ�����Ӳ���
			case 0:
				if (this.update()) { // �����ɹ�
					JOptionPane.showMessageDialog(null, Command + "�ɹ�", "��Ϣ", JOptionPane.INFORMATION_MESSAGE);
					this.dispose();
				} else { // ����ʧ��
					JOptionPane.showMessageDialog(null, Command + "ʧ��", "����", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case 1:
				JOptionPane.showMessageDialog(null, "�γ�����Ϊ�ջ����ظ�", "����", JOptionPane.ERROR_MESSAGE);
				break;
			case 2:
				JOptionPane.showMessageDialog(null, "�����ɼ���ֵ����", "����", JOptionPane.ERROR_MESSAGE);
				break;
			case 3:
				JOptionPane.showMessageDialog(null, "�����ɼ���ֵ����", "����", JOptionPane.ERROR_MESSAGE);
				break;
			case 4:
				JOptionPane.showMessageDialog(null, "���޳ɼ�1��ֵ����", "����", JOptionPane.ERROR_MESSAGE);
				break;
			case 5:
				JOptionPane.showMessageDialog(null, "���޳ɼ�2��ֵ����", "����", JOptionPane.ERROR_MESSAGE);
				break;
			case 6:
				JOptionPane.showMessageDialog(null, "���޳ɼ�3��ֵ����", "����", JOptionPane.ERROR_MESSAGE);
				break;
			case 7:
				JOptionPane.showMessageDialog(null, "������ֵ����", "����", JOptionPane.ERROR_MESSAGE);
				break;
			}

		} else if (e.getSource() == jb2) { // �û������ȡ������ť���رնԻ���
			this.dispose();
		}
	}

}
