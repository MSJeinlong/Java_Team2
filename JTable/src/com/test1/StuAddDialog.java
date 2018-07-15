package com.test1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StuAddDialog extends JDialog implements ActionListener{
	
	//��������Ҫ�����
	JLabel[] jlb = new JLabel[5];
	JButton jb1, jb2;
	JTextField[] jtf = new JTextField[5];
	JPanel jp1, jp2, jp3;
	JRadioButton jrb1, jrb2;
	Box box = null;
	
	//owner���ĸ�����
	//title ������
	//model ָ����ģʽ���ڣ����Ƿ�ģʽ�Ĵ���
	public StuAddDialog(Frame owner, String title, boolean model) {
		//���ø��๹�췽�����ﵽģʽ�Ի���Ч��
		super(owner,title,model);
		jlb[0] = new JLabel("ѧ�ţ�");
		jlb[1] = new JLabel("������");
		jlb[2] = new JLabel("�Ա�");
		jlb[3] = new JLabel("���գ�");
		jlb[4] = new JLabel("�ǹ�������");
		
		jtf[0] = new JTextField();
		jtf[1] = new JTextField();
		jtf[2] = new JTextField();
		jtf[3] = new JTextField();
		jtf[4] = new JTextField();
		
		//������ѡ��
		jrb1 = new JRadioButton("��");
		jrb2 = new JRadioButton("Ů");
		
		jb1 = new JButton("���");
		jb2 = new JButton("ȡ��");
		
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		
		//���ò���
		jp1.setLayout(new GridLayout(5,1));
		jp2.setLayout(new GridLayout(5,1));
		
		//��Ϊ��ѡ��������ͬʱѡ�����мӵ�һ��������ԴﵽЧ��
		ButtonGroup group = new ButtonGroup();
		group.add(jrb1);  
        group.add(jrb2);
        box = Box.createHorizontalBox();
        box.add(Box.createHorizontalStrut(2));
        box.add(jrb1);
        box.add(jrb2);
	
		//������
		jp1.add(jlb[0]);
		jp1.add(jlb[1]);
		jp1.add(jlb[2]);
		jp1.add(jlb[3]);
		jp1.add(jlb[4]);
		
		jp2.add(jtf[0]);
		jp2.add(jtf[1]);
		jp2.add(box);
		jp2.add(jtf[2]);
		jp2.add(jtf[3]);
		//jp2.add(jtf[4]);
		
		jp3.add(jb1);
		jp3.add(jb2);
			
		this.add(jp1, BorderLayout.WEST);
		this.add(jp2, BorderLayout.CENTER);
		this.add(jp3, BorderLayout.SOUTH);
		
		jb1.addActionListener(this);
		
		//չ��
		this.setBounds(500, 400, 300, 200);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
