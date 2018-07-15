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
	JLabel[] jlb = new JLabel[5];
	JButton jb1, jb2;
	JTextField[] jtf = new JTextField[5];
	JPanel jp1, jp2, jp3;
	String action;

	// ���췽��
	// owner���ĸ�����
	// title ������
	// model ָ����ģʽ���ڣ����Ƿ�ģʽ�Ĵ���
	public SupplierAdd_UpdateDialog(Frame owner, String title, boolean model, SupplierModel sp, int rowNum) {
		// ���ø��๹�췽�����ﵽģʽ�Ի���Ч��
		super(owner, title, model);

		// �������
		jlb[0] = new JLabel("����");
		jlb[1] = new JLabel("�绰");
		jlb[2] = new JLabel("��ַ");
		jlb[3] = new JLabel("�������");
		jlb[4] = new JLabel("����");

		jtf[0] = new JTextField();
		jtf[1] = new JTextField(11);
		jtf[2] = new JTextField();
		jtf[3] = new JTextField();
		jtf[4] = new JTextField();

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

		// ���ò���
		jp1.setLayout(new GridLayout(5, 1));
		jp2.setLayout(new GridLayout(5, 1));

		// ��������JPanel
		jp1.add(jlb[0]);
		jp1.add(jlb[1]);
		jp1.add(jlb[2]);
		jp1.add(jlb[3]);
		jp1.add(jlb[4]);

		jp2.add(jtf[0]);
		jp2.add(jtf[1]);
		jp2.add(jtf[2]);
		jp2.add(jtf[3]);
		jp2.add(jtf[4]);

		// sm != null����ʾ���޸�����
		if (sp != null) {
			jtf[0].setText(sp.getValueAt(rowNum, 0).toString());
			jtf[1].setText(sp.getValueAt(rowNum, 1).toString());
			jtf[2].setText(sp.getValueAt(rowNum, 2).toString());
			jtf[3].setText(sp.getValueAt(rowNum, 3).toString());
			jtf[4].setText(sp.getValueAt(rowNum, 4).toString());
			action = "���";
		} else {
			action = "�޸�";
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
		case "congfirm":
			String[] paras = { jtf[0].getText(), jtf[1].getText(), jtf[2].getText(), jtf[3].getText(),
					jtf[4].getText() };
			int res = SupplierModel.update(paras, -1);

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
