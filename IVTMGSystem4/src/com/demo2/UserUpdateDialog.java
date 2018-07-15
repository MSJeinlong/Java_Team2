package com.demo2;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * 
 * @author JunLong �޸�User��Ϣ�ĶԻ���
 * 
 */
public class UserUpdateDialog extends JDialog implements ActionListener {

	// ��������Ҫ�����
	JLabel[] jlb = new JLabel[7];
	JButton jb1, jb2;
	JTextField[] jtf = new JTextField[6];
	JPanel jp1, jp2, jp3;
	JRadioButton jrb1, jrb2;
	Box box = null;
	String sex = null;
	int rowNum; // Ҫ������һ�е�����

	// owner���ĸ�����
	// title ������
	// model ָ����ģʽ���ڣ����Ƿ�ģʽ�Ĵ���
	public UserUpdateDialog(Frame owner, String title, boolean model, Model um, int rowNum) {
		// ���ø��๹�췽�����ﵽģʽ�Ի���Ч��
		super(owner, title, model);

		// ��������
		this.rowNum = rowNum;

		jlb[0] = new JLabel("�˺ţ�");
		jlb[1] = new JLabel("���룺");
		jlb[2] = new JLabel("������");
		jlb[3] = new JLabel("�Ա�");
		jlb[4] = new JLabel("���䣺");
		jlb[5] = new JLabel("���᣺");
		jlb[6] = new JLabel("ϵ��");

		jtf[0] = new JTextField();
		// ��ʼ������
		jtf[0].setText((String) um.getValueAt(rowNum, 0));
		// ��jtf[0]�����޸�
		jtf[0].setEditable(false);
		jtf[1] = new JTextField();
		jtf[1].setText((String) um.getValueAt(rowNum, 1));
		jtf[2] = new JTextField();
		jtf[2].setText((String) um.getValueAt(rowNum, 2));
		jtf[3] = new JTextField();
		jtf[3].setText((String) um.getValueAt(rowNum, 4).toString());
		jtf[4] = new JTextField();
		jtf[4].setText((String) um.getValueAt(rowNum, 5));
		jtf[5] = new JTextField();
		jtf[5].setText((String) um.getValueAt(rowNum, 6));

		// ������ѡ��
		sex = (String) um.getValueAt(rowNum, 3); // ��ȡԭ�����Ա���Ϣ
		jrb1 = new JRadioButton("��");
		jrb2 = new JRadioButton("Ů");
		// ��ԭ��ѡ��ť��ѡ��״̬
		if (sex.equals("��")) {
			jrb1.setSelected(true);
		} else {
			jrb2.setSelected(true);
		}

		jb1 = new JButton("ȷ��");
		jb2 = new JButton("ȡ��");

		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();

		// ���ò���
		jp1.setLayout(new GridLayout(7, 1));
		jp2.setLayout(new GridLayout(7, 1));

		// ��Ϊ��ѡ��������ͬʱѡ�����мӵ�һ��������ԴﵽЧ��
		ButtonGroup group = new ButtonGroup();
		group.add(jrb1);
		group.add(jrb2);
		box = Box.createHorizontalBox();
		box.add(Box.createHorizontalStrut(2));
		box.add(jrb1);
		box.add(jrb2);

		// ������
		jp1.add(jlb[0]);
		jp1.add(jlb[1]);
		jp1.add(jlb[2]);
		jp1.add(jlb[3]);
		jp1.add(jlb[4]);
		jp1.add(jlb[5]);
		jp1.add(jlb[6]);

		jp2.add(jtf[0]);
		jp2.add(jtf[1]);
		jp2.add(jtf[2]);
		jp2.add(box);
		jp2.add(jtf[3]);
		jp2.add(jtf[4]);
		jp2.add(jtf[5]);

		jp3.add(jb1);
		jp3.add(jb2);

		// ע�����
		jb1.addActionListener(this);
		jb1.setActionCommand("confirm");
		jb2.addActionListener(this);
		jb2.setActionCommand("canel");

		// JRadioButtonע�����
		// jrb1.addItemListener(this);
		jrb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sex = jrb1.getText();
				System.out.println("sex:" + sex);
			}

		});

		jrb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sex = jrb2.getText();
				System.out.println("sex:" + sex);
			};

		});

		// System.out.println("sex:"+sex);
		// ���ò���
		this.add(jp1, BorderLayout.WEST);
		this.add(jp2, BorderLayout.CENTER);
		this.add(jp3, BorderLayout.SOUTH);

		// չ��
		this.setBounds(1000, 400, 300, 300);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (e.getActionCommand()) {
		case "confirm":
			String[] paras = { jtf[1].getText(), jtf[2].getText(), sex, jtf[3].getText(), jtf[4].getText(),
					jtf[5].getText(), jtf[0].getText() };
			int res = UserModel.update(paras, rowNum);
			// �ж��޸Ľ������������Ӧ����ʾ
			if (res == 1) {
				// �޸ĳɹ�ʱ
				JOptionPane.showMessageDialog(this, "�޸ĳɹ�!", "��Ϣ", JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
			} else {
				// �޸�ʧ��ʱ
				JOptionPane.showMessageDialog(this, "�޸�ʧ��!\nԭ��������һ�������ϢΪ�գ�", "����", JOptionPane.ERROR_MESSAGE);
			}
			break;
		case "canel":
			this.dispose();
			break;
		}
	}

}
