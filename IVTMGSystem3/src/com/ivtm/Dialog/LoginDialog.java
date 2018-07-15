package com.ivtm.Dialog;

import java.awt.GridLayout;
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

import com.itvm.tools.MyTools;
import com.ivtm.view.ForgetPwdDialog;
import com.ivtm.view.Windows1;
import com.model.UserModel;

public class LoginDialog extends JDialog implements ActionListener {

	// 定义组件
	private JPanel jp1, jp2, jp3;
	private JLabel jlb1, jlb2;
	private JButton jb1, jb2, jb3;
	private JTextField jtf;
	private JPasswordField jpf;
	int width, height;

	public LoginDialog() {

		// 三个面板
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();

		// 两个标签
		jlb1 = new JLabel("账号：");
		jlb1.setIcon(new ImageIcon("image/login/account1.png"));
		jlb2 = new JLabel("密码：");
		jlb2.setIcon(new ImageIcon("image/login/password3.png"));
		jlb1.setFont(MyTools.f1);
		jlb2.setFont(MyTools.f1);
		/*
		 * ImageIcon image1 = new ImageIcon("image/login/account.png"; ImageIcon image2
		 * = new ImageIcon("image/login/password1.png"); //调用JLabel的方法，自定义image的大小 jlb1
		 * = MyTools.setImage(jlb1, image1, 20, 20); jlb2 = MyTools.setImage(jlb2,
		 * image2, 20, 20);
		 */

		// 文本框和密码框
		jtf = new JTextField(15);
		jpf = new JPasswordField(15);

		// 三个按钮
		jb1 = new JButton("登录");
		jb2 = new JButton("注册");
		jb3 = new JButton("忘记密码");

		// 为三个按钮注册监听
		jb1.addActionListener(this);
		jb1.setActionCommand("Login");
		jb2.addActionListener(this);
		jb2.setActionCommand("SignUp");
		jb3.addActionListener(this);
		jb3.setActionCommand("Forget");

		// 设置布局管理器
		this.setLayout(new GridLayout(3, 1));
		// jp2.setLayout(new GridLayout(2,1));

		// 添加组件到面板
		jp1.add(jlb1);
		jp1.add(jtf);
		jp2.add(jlb2);
		jp2.add(jpf);
		jp3.add(jb1);
		jp3.add(jb2);
		jp3.add(jb3);

		// 添加面板到JFrame
		// this.add(jlb3);
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);

		// 设置窗体属性
		this.setTitle("库存管理系统");
		this.setSize(300, 150);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		width = Toolkit.getDefaultToolkit().getScreenSize().width;
		height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width / 2 - 200, height / 2 - 150);
		this.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// 对用户不同的点击做出不同的反应
		// System.out.println("点击了");
		switch (e.getActionCommand()) {
		// 登录
		case "Login":
			UserModel um = new UserModel();
			um.readData();
			String uid = this.jtf.getText().trim();
			String pwd = new String(this.jpf.getPassword());
			pwd.trim();
			// 如果成功登录
			if (um.checkUser(uid, pwd)) {
				new Windows1(); // new主界面
				this.dispose(); // 关闭登录界面
			} else {
				// 登录失败
				JOptionPane.showMessageDialog(this, "登录失败!\n原因：账号或密码错误！", "错误", JOptionPane.ERROR_MESSAGE);
			}
			break;
		// 注册
		case "SignUp":
			System.out.println("注册");
			// 弹出注册界面
			new UserAddDialog(null, "用户注册", true, "SignUp");
			break;
		// 忘记密码
		case "Forget":
			new ForgetPwdDialog();
			break;
		}
	}
}
