package com.ivtm.Dialog;

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

import com.model.UserModel;

/**
 * 
 * @author JunLong 添加User的界面 实现添加User的功能
 *         UserAddDialog类继承了JDialog，还实现了ActionListener接口
 */
public class UserAddDialog extends JDialog implements ActionListener {

	// 定义我需要的组件
	JLabel[] jlb = new JLabel[8];
	JButton jb1, jb2;
	JTextField[] jtf = new JTextField[7];
	JPanel jp1, jp2, jp3;
	JRadioButton jrb1, jrb2;
	Box box = null;
	String sex = null;
	String action;

	// owner它的父窗口
	// title 窗口名
	// model 指定是模式窗口，还是非模式的窗口
	public UserAddDialog(Frame owner, String title, boolean model, String action) {
		// 调用父类构造方法，达到模式对话框效果
		super(owner, title, model);

		// 设置action
		this.action = action; // 用于判断是注册用户还是添加用户

		jlb[0] = new JLabel("账号：");
		jlb[1] = new JLabel("密码：");
		jlb[2] = new JLabel("姓名：");
		jlb[3] = new JLabel("性别：");
		jlb[4] = new JLabel("年龄：");
		jlb[5] = new JLabel("手机号码：");
		jlb[6] = new JLabel("电子邮箱：");

		// 如果是注册，还要确认密码
		if (action.equals("SignUp")) {
			jlb[7] = new JLabel("确认密码：");
			jtf[6] = new JTextField();
		}

		jtf[0] = new JTextField();
		jtf[1] = new JTextField();
		jtf[2] = new JTextField(3);
		jtf[3] = new JTextField(11);
		jtf[4] = new JTextField();
		jtf[5] = new JTextField();

		// 两个单选框
		jrb1 = new JRadioButton("男", true);
		sex = "男"; // sex默认为男
		jrb2 = new JRadioButton("女");

		jb1 = new JButton("确定");
		jb2 = new JButton("取消");

		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();

		// 设置布局
		if (action.equals("add")) {
			jp1.setLayout(new GridLayout(7, 1));
			jp2.setLayout(new GridLayout(7, 1));
		} else {
			jp1.setLayout(new GridLayout(8, 1));
			jp2.setLayout(new GridLayout(8, 1));
		}

		// 因为单选两个不能同时选择，所有加到一个组里可以达到效果
		ButtonGroup group = new ButtonGroup();
		group.add(jrb1);
		group.add(jrb2);
		box = Box.createHorizontalBox();
		box.add(Box.createHorizontalStrut(2));
		box.add(jrb1);
		box.add(jrb2);

		// 添加组件
		jp1.add(jlb[0]);
		jp1.add(jlb[1]);
		// 如果是注册用户，那么就要加上确认密码这一项
		if (action.equals("SignUp")) {
			jp1.add(jlb[7]);
		}
		jp1.add(jlb[2]);
		jp1.add(jlb[3]);
		jp1.add(jlb[4]);
		jp1.add(jlb[5]);
		jp1.add(jlb[6]);

		jp2.add(jtf[0]);
		// 如果是注册用户，那么就要加上密码
		if (action.equals("SignUp")) {
			jp2.add(jtf[6]);
		}
		jp2.add(jtf[1]);
		jp2.add(jtf[2]);
		jp2.add(box);
		jp2.add(jtf[3]);
		jp2.add(jtf[4]);
		jp2.add(jtf[5]);

		jp3.add(jb1);
		jp3.add(jb2);

		// 注册监听
		jb1.addActionListener(this);
		jb1.setActionCommand("confirm");
		jb2.addActionListener(this);
		jb2.setActionCommand("canel");

		// 用户选择不同的性别单选框时，sex也跟着改变
		jrb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 用户选择男单选框
				sex = jrb1.getText();
				// System.out.println("sex:"+sex);
			}

		});

		// 匿名内部类 ActionListener
		jrb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 用户选择女单选框
				sex = jrb2.getText();
				// System.out.println("sex:"+sex);
			};

		});

		// 设置布局
		this.add(jp1, BorderLayout.WEST);
		this.add(jp2, BorderLayout.CENTER);
		this.add(jp3, BorderLayout.SOUTH);

		// 展现
		this.setBounds(1000, 400, 300, 220);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// 用户点击确定进行添加时
		switch (e.getActionCommand()) {
		// 用户填完信息点击确定按钮时
		case "confirm":
			String mess = null;
			if (action.equals("add")) {
				mess = "添加";
			} else {
				mess = "注册";
				UserModel.readData();// 把User数据读取出来，防止编号重复
			}
			String[] paras = { jtf[0].getText(), jtf[1].getText(), jtf[2].getText(), sex, jtf[3].getText(),
					jtf[4].getText(), jtf[5].getText() };
			// 执行添加操作
			UserModel um = new UserModel();
			int res = um.add(paras);

			switch (res) {
			case 1:
				JOptionPane.showMessageDialog(this, mess + "成功!", "信息", JOptionPane.INFORMATION_MESSAGE);
				// 关闭添加界面
				this.dispose();
				break;
			case -1:
				// // 弹出错误对话框
				JOptionPane.showMessageDialog(this, mess + "失败!\n原因：至少有一项必填信息为空！", "错误", JOptionPane.ERROR_MESSAGE);
				break;
			case -2:
				JOptionPane.showMessageDialog(this, mess + "失败!\n原因：年龄有误！", "警告", JOptionPane.WARNING_MESSAGE);
				break;
			case -3:
				JOptionPane.showMessageDialog(this, mess + "失败!\n原因：手机号码有误！", "警告", JOptionPane.WARNING_MESSAGE);
				break;
			case -4:
				JOptionPane.showMessageDialog(this, mess + "失败!\n原因：电子邮箱有误！", "警告", JOptionPane.WARNING_MESSAGE);
				break;
			case -5:
				JOptionPane.showMessageDialog(this, mess + "失败!\n原因：用户账号重复！", "警告", JOptionPane.WARNING_MESSAGE);
				break;
			}
			break;
		// 用户填完信息点击取消按钮时
		case "canel":
			this.dispose(); // 关闭窗口
			break;
		}
	}
}
