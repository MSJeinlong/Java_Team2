package com.game.TankGame;

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

/**
 * 
 * @author JunLong 重设密码的对话框 用户在找回密码环节时，如果用户信息验证通过，即可重设密码
 */
public class ResetPwdDialog extends JDialog implements ActionListener {

	// 定义组件
	private JPanel jp1, jp2, jp3;
	private JLabel jlb1, jlb2;
	private JButton jb1, jb2, jb3;
	private JPasswordField jpf1, jpf2;
	private String uid;

	// 通过传参获得用户id
	public ResetPwdDialog(String uid) {

		this.uid = uid;

		// 三个面板
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();

		// 两个标签
		jlb1 = new JLabel("设置密码：");
		jlb2 = new JLabel("确认密码：");
		jlb1.setIcon(new ImageIcon("image/login/password3.png"));
		jlb2.setIcon(new ImageIcon("image/login/password3.png"));
/*		int width = 20, height = 20;
		ImageIcon image1 = new ImageIcon("image/login/password1.png");
		ImageIcon image2 = new ImageIcon("image/login/password1.png");
		image1.setImage(image1.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		image2.setImage(image2.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		jlb1.setIcon(image1);
		jlb1.setSize(width, height);
		jlb2.setIcon(image2);
		jlb2.setSize(width, height);*/

		// 文本框和密码框
		jpf1 = new JPasswordField(15);
		jpf2 = new JPasswordField(15);

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
		jp1.add(jpf1);
		jp2.add(jlb2);
		jp2.add(jpf2);
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
		this.setLocation(width / 2 - 170, height / 2 - 130);
		this.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (e.getActionCommand()) {
		case "confirm":
			// 先检验两次输入的密码是否一致
			// 获取两个密码框里的密码
			String pwd1 = new String(jpf1.getPassword());
			String pwd2 = new String(jpf2.getPassword());
			// 如果两次输入的密码一致
			if (pwd1.equals(pwd2)) {
				// 调用方法重设密码
				UserModel.resetPasswd(uid, pwd1);
				// 提示用户密码重设成功
				JOptionPane.showMessageDialog(this, "密码重设成功！", "信息", JOptionPane.INFORMATION_MESSAGE);
				this.dispose();// 退出
			} else {
				// 密码不一致，提示用户
				JOptionPane.showMessageDialog(this, "两次输入的密码不一致！", "警告", JOptionPane.WARNING_MESSAGE);
			}
			break;
		case "canel":
			// 用户点击取消时询问用户是否真的要放弃修改
			int n = JOptionPane.showConfirmDialog(this, "您确定要放弃本次修改（密码未修改）？", "放弃修改", JOptionPane.YES_NO_OPTION); // 返回值为0或1
			//用户点击是，放弃本次修改
			if (n == 0) {
				this.dispose();// 退出
			}
			break;
		}
	}
}
