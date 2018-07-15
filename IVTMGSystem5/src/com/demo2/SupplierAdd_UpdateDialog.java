package com.demo2;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * @author JunLong ��ӹ�������Ϣ �����޸���������Ϣ�ĶԻ���
 * 
 */
public class SupplierAdd_UpdateDialog extends JDialog implements ActionListener {

	// ��������Ҫ�����
	JLabel[] jlb = new JLabel[6];
	JButton jb1, jb2;
	JTextField[] jtf = new JTextField[6];
	JPanel jp1, jp2, jp3;
	String action;
	int rowNum = -1;

	// ���췽��
	// owner���ĸ�����
	// title ������
	// model ָ����ģʽ���ڣ����Ƿ�ģʽ�Ĵ���
	public SupplierAdd_UpdateDialog(Frame owner, String title, boolean model, Model sm, int rowNum) {
		// ���ø��๹�췽�����ﵽģʽ�Ի���Ч��
		super(owner, title, model);

		// �������

		jlb[0] = new JLabel("���");
		jlb[1] = new JLabel("����");
		jlb[2] = new JLabel("�绰");
		jlb[3] = new JLabel("��ַ");
		jlb[4] = new JLabel("�������");
		jlb[5] = new JLabel("����");

		jtf[0] = new JTextField();
		jtf[1] = new JTextField(11);
		jtf[2] = new JTextField();
		jtf[3] = new JTextField();
		jtf[4] = new JTextField();
		jtf[5] = new JTextField();

		jb1 = new JButton("ȷ��");
		jb2 = new JButton("ȡ��");

		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();

		// ע�����
		jb1.addActionListener(this);
		jb1.setActionCommand("confirm");
		jb2.addActionListener(this);
		jb2.setActionCommand("canel");

		// ����jp1��jp2����
		jp1.setLayout(new GridLayout(6, 1));
		jp2.setLayout(new GridLayout(6, 1));

		// ��������JPanel
		jp1.add(jlb[0]);
		jp1.add(jlb[1]);
		jp1.add(jlb[2]);
		jp1.add(jlb[3]);
		jp1.add(jlb[4]);
		jp1.add(jlb[5]);

		jp2.add(jtf[0]);
		jp2.add(jtf[1]);
		jp2.add(jtf[2]);
		jp2.add(jtf[3]);
		jp2.add(jtf[4]);
		jp2.add(jtf[5]);

		if (title.equals("�޸�")) {
			jtf[0].setText(sm.getValueAt(rowNum, 0).toString());
			jtf[0].setEditable(false);
			jtf[1].setText(sm.getValueAt(rowNum, 1).toString());
			jtf[2].setText(sm.getValueAt(rowNum, 2).toString());
			jtf[3].setText(sm.getValueAt(rowNum, 3).toString());
			jtf[4].setText(sm.getValueAt(rowNum, 4).toString());
			jtf[5].setText(sm.getValueAt(rowNum, 4).toString());
			action = title;
			this.rowNum = rowNum;
		} else {
			action = title;
		}

		jp3.add(jb1);
		jp3.add(jb2);

		// ���ò���
		this.add(jp1, BorderLayout.WEST);
		this.add(jp2, BorderLayout.CENTER);
		this.add(jp3, BorderLayout.SOUTH);

		// ����Dialog����
		this.setTitle(action);
		this.setBounds(1000, 400, 300, 220);
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (e.getActionCommand()) {
		case "confirm":

			int res = 0;
			if (action.equals("���")) {
				String[] paras = { jtf[0].getText(), jtf[1].getText(), jtf[2].getText(), jtf[3].getText(),
						jtf[4].getText(), jtf[5].getText() };
				res = Model.update(paras, -1);
			} else {
				String[] paras = { jtf[1].getText(), jtf[2].getText(), jtf[3].getText(), jtf[4].getText(),
						jtf[5].getText() };
				res = Model.update(paras, rowNum);
			}
			if (res == 1) { // ���ؽ��Ϊ1,��ӳɹ�
				// ������Ϣ�Ի���
				JOptionPane.showMessageDialog(this, action + "�ɹ�!", "��Ϣ", JOptionPane.INFORMATION_MESSAGE);
				// �ر���ӽ���
				this.dispose();
			} else if (res == -1) { // ���ؽ��Ϊ -1,���ʧ�ܣ�������һ�������ϢΪ��
				// ��������Ի���
				JOptionPane.showMessageDialog(this, action + "ʧ��!\nԭ��������һ�������ϢΪ�գ�", "����", JOptionPane.ERROR_MESSAGE);
			} else { // ���ؽ��Ϊ -2,���ʧ�ܣ�����ظ�
				// ��������Ի���
				JOptionPane.showMessageDialog(this, action + "ʧ��!\nԭ�������ظ���", "����", JOptionPane.ERROR_MESSAGE);
			}
			break;
		// �û�������Ϣ���ȡ����ťʱ
		case "canel":
			// �رմ���
			this.dispose();
			break;
		}
	}

}
