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
 * @author JunLong 添加供货商信息 或者修改销售商信息的对话框
 * 
 */
public class SupplierAdd_UpdateDialog extends JDialog implements ActionListener {

	// 定义我需要的组件
	JLabel[] jlb = new JLabel[6];
	JButton jb1, jb2;
	JTextField[] jtf = new JTextField[6];
	JPanel jp1, jp2, jp3;
	String action;
	int rowNum = -1;

	// 构造方法
	// owner它的父窗口
	// title 窗口名
	// model 指定是模式窗口，还是非模式的窗口
	public SupplierAdd_UpdateDialog(Frame owner, String title, boolean model, Model sm, int rowNum) {
		// 调用父类构造方法，达到模式对话框效果
		super(owner, title, model);

		// 创建组件

		jlb[0] = new JLabel("编号");
		jlb[1] = new JLabel("名称");
		jlb[2] = new JLabel("电话");
		jlb[3] = new JLabel("地址");
		jlb[4] = new JLabel("货物类别");
		jlb[5] = new JLabel("货物");

		jtf[0] = new JTextField();
		jtf[1] = new JTextField(11);
		jtf[2] = new JTextField();
		jtf[3] = new JTextField();
		jtf[4] = new JTextField();
		jtf[5] = new JTextField();

		jb1 = new JButton("确认");
		jb2 = new JButton("取消");

		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();

		// 注册监听
		jb1.addActionListener(this);
		jb1.setActionCommand("confirm");
		jb2.addActionListener(this);
		jb2.setActionCommand("canel");

		// 设置jp1、jp2布局
		jp1.setLayout(new GridLayout(6, 1));
		jp2.setLayout(new GridLayout(6, 1));

		// 添加组件到JPanel
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

		if (title.equals("修改")) {
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

		// 设置布局
		this.add(jp1, BorderLayout.WEST);
		this.add(jp2, BorderLayout.CENTER);
		this.add(jp3, BorderLayout.SOUTH);

		// 设置Dialog属性
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
			if (action.equals("添加")) {
				String[] paras = { jtf[0].getText(), jtf[1].getText(), jtf[2].getText(), jtf[3].getText(),
						jtf[4].getText(), jtf[5].getText() };
				res = Model.update(paras, -1);
			} else {
				String[] paras = { jtf[1].getText(), jtf[2].getText(), jtf[3].getText(), jtf[4].getText(),
						jtf[5].getText() };
				res = Model.update(paras, rowNum);
			}
			if (res == 1) { // 返回结果为1,添加成功
				// 弹出信息对话框
				JOptionPane.showMessageDialog(this, action + "成功!", "信息", JOptionPane.INFORMATION_MESSAGE);
				// 关闭添加界面
				this.dispose();
			} else if (res == -1) { // 返回结果为 -1,添加失败，至少有一项必填信息为空
				// 弹出错误对话框
				JOptionPane.showMessageDialog(this, action + "失败!\n原因：至少有一项必填信息为空！", "错误", JOptionPane.ERROR_MESSAGE);
			} else { // 返回结果为 -2,添加失败，编号重复
				// 弹出错误对话框
				JOptionPane.showMessageDialog(this, action + "失败!\n原因：名称重复！", "错误", JOptionPane.ERROR_MESSAGE);
			}
			break;
		// 用户填完信息点击取消按钮时
		case "canel":
			// 关闭窗口
			this.dispose();
			break;
		}
	}

}
