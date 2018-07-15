package com.ivtm.Dialog;
/**
 * 用户忘记密码时，用于验证用户信息的对话框
 * 
 */
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.model.UserModel;

public class ForgetPwdDialog extends JDialog implements ActionListener {

	// 定义组件
	private JPanel jp1, jp2, jp3;
	private JLabel jlb1, jlb2;
	private JButton jb1, jb2, jb3;
	private JTextField jtf1, jtf2;

	public ForgetPwdDialog() {

		// 三个面板
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();

		// 两个标签
		jlb1 = new JLabel("账    号：");
		jlb2 = new JLabel("手机号码：");
		jlb1.setIcon(new ImageIcon("image/login/account1.png"));
		jlb2.setIcon(new ImageIcon("image/login/PhoneNum1.png"));
/*		int width = 20, height = 20;
		ImageIcon image1 = new ImageIcon("image/login/account.png");
		ImageIcon image2 = new ImageIcon("image/login/PhoneNum.png");
		image1.setImage(image1.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		image2.setImage(image2.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		jlb1.setIcon(image1);
		jlb1.setSize(width, height);
		jlb2.setIcon(image2);
		jlb2.setSize(width, height);*/

		// 文本框和密码框
		jtf1 = new JTextField(15);
		jtf2 = new JTextField(15);

		// 三个按钮
		jb1 = new JButton("确定");
		jb2 = new JButton("取消");

		// 为三个按钮注册监听
		jb1.addActionListener(this);
		jb1.setActionCommand("confirm");
		jb2.addActionListener(this);
		jb2.setActionCommand("canel");

		// 设置布局管理器
		this.setLayout(new GridLayout(3, 1));
		// jp2.setLayout(new GridLayout(2,1));

		// 添加组件到面板
		jp1.add(jlb1);
		jp1.add(jtf1);
		jp2.add(jlb2);
		jp2.add(jtf2);
		jp3.add(jb1);
		jp3.add(jb2);

		// 添加面板到JFrame
		// this.add(jlb3);
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);

		// 设置窗体属性
		this.setTitle("找回密码");
		this.setSize(300, 150);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width / 2 - 200, height / 2 - 150);
		this.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (e.getActionCommand()) {
		case "confirm":
			String uid = jtf1.getText().trim();
			String Phone = jtf2.getText().trim();
			//检查用户信息是否正确
			if(UserModel.findPwd(uid, Phone)) {
				//调出重设密码对话框
				new ResetPwdDialog(uid);
				this.dispose();//成功修改密码后关闭对话框
			} else {
				//用户信息不正确，提醒用户
				JOptionPane.showMessageDialog(this, "账号或手机号码错误！", "警告", JOptionPane.WARNING_MESSAGE);
			}
			break;
		case "canel":
			this.dispose();
			break;
		}
	}

}
