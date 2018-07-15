package com.ivtm.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.demo2.UserAddDialog;
import com.demo2.UserModel;
import com.itvm.tools.MyTools;

public class UserInfo extends JPanel implements ActionListener {

	// 定义需要的组件
	private JPanel jp1, jp2, jp3, jp4, jp5;
	private JLabel jp1_lab1, jp3_lab;
	private JTextField jp1_jtf;
	private JButton jp1_jb1, jp4_jb1, jp4_jb2, jp4_jb3, jp4_jb4;
	// 这个是用于显示信息的JTable
	private JTable jtb;
	// 构造方法
	private JScrollPane jsp;
	private int counts; // 记录条数
	UserModel um;

	public UserInfo() {

		// 处理北部的jp1
		// 创建需要的组件
		jp1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jp1_lab1 = new JLabel("请输入管理员姓名");
		jp1_jtf = new JTextField(20);
		jp1_lab1.setFont(MyTools.f1);
		jp1_jb1 = new JButton("查询");
		jp1_jb1.setFont(MyTools.f3);
		// 把他们加入到jp1
		jp1.add(jp1_lab1);
		jp1.add(jp1_jtf);
		jp1.add(jp1_jb1);

		// 处理中间的jp2
		um = new UserModel();
		counts = um.query("");
		jtb = new JTable();
		jtb.setModel(um);
		jp2 = new JPanel(new BorderLayout());
		jsp = new JScrollPane(jtb);
		jp2.add(jsp);

		// 处理南部的jp5
		jp3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jp3_lab = new JLabel("总共有 " + counts + " 条记录");
		jp3_lab.setFont(MyTools.f1);
		jp3.add(jp3_lab);
		jp4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jp4_jb1 = new JButton("详细信息");
		jp4_jb2 = new JButton("添加");
		jp4_jb3 = new JButton("修改");
		jp4_jb4 = new JButton("删除");
		jp4_jb1.setFont(MyTools.f3);
		jp4_jb2.setFont(MyTools.f3);
		jp4_jb3.setFont(MyTools.f3);
		jp4_jb4.setFont(MyTools.f3);
		jp4.add(jp4_jb1);
		jp4.add(jp4_jb2);
		jp4.add(jp4_jb3);
		jp4.add(jp4_jb4);
		jp5 = new JPanel(new BorderLayout());

		// 为按钮注册监听
		jp1_jb1.addActionListener(this);
		jp1_jb1.setActionCommand("query");
		jp4_jb1.addActionListener(this);
		jp4_jb1.setActionCommand("all");
		jp4_jb2.addActionListener(this);
		jp4_jb2.setActionCommand("add");
		jp4_jb3.addActionListener(this);
		jp4_jb3.setActionCommand("alter");
		jp4_jb4.addActionListener(this);
		jp4_jb4.setActionCommand("delete");

		// 为JTextField添加监听回车键的功能
		// 新建一个匿名内部类
		jp1_jtf.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) // 按回车键执行相应操作;
				{
					// 匿名内部类调用外部类的方法
					String name = UserInfo.this.jp1_jtf.getText().trim();
					UserInfo.this.query(name); // 用户输入关键字后，按下Enter键即可进行查询
				}
			}
		});

		jp5.add(jp3, "West");
		jp5.add(jp4, "East");

		// 把总的JPanel设置为边界布局
		this.setLayout(new BorderLayout());
		// 把jp1加入到总的JPanel
		this.add(jp1, "North");
		this.add(jp2, "Center");
		this.add(jp5, "South");
		// this.setBackground(Color.pink);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// 对用户点击增删改查等不同的按钮做出事件处理
		switch (e.getActionCommand()) {
		// 查询
		case "query":
			// 调用查询
			String name = this.jp1_jtf.getText().trim();
			this.query(name);
			break;
		// 添加
		case "add":
			UserAddDialog ua = new UserAddDialog(null, "添加", true, "add");
			// 弹出添加对话框
			um.updateDialog(null, 0);
			// 更新数据模型
			// model = new UserModel();
			um = MyTools.newTclass(um.getClass());
			// model.query("");
			this.query("");
			// 更新JTable
			jtb.setModel(um);
			break;
		// 修改
		case "alter":
			int rowNum = this.jtb.getSelectedRow();
			if (rowNum == -1) {
				// 用户没选择任何一行时提示
				JOptionPane.showMessageDialog(this, "请选择一行", "警告！", JOptionPane.WARNING_MESSAGE);
				return;
			}
			// 显示修改对话框
			um.updateDialog(um, rowNum);
			// 更新数据模型
			um = new UserModel();
			// 通过查询更新JTable
			this.query("");
			// 更新JTable
			jtb.setModel(um);
			break;
		// 删除
		case "delete":
			int rowNum1 = this.jtb.getSelectedRow();
			if (rowNum1 == -1) {
				// 用户没选择任何一行时提示
				JOptionPane.showMessageDialog(this, "请选择一行", "警告！", JOptionPane.WARNING_MESSAGE);
				return;
			} else { // 用户选择了其中一行时
				// 删除前，再次请求用户的确认
				int n = JOptionPane.showConfirmDialog(null, "您将要永久删除一行！确定删除吗？", "删除数据", JOptionPane.YES_NO_OPTION); // 返回值为0或1
				if (n == 0) { // n == 0，表示用户点击了"是"；
					UserModel.update(null, rowNum1);
					// 弹出提示信息
					JOptionPane.showMessageDialog(this, "删除成功!", "信息", JOptionPane.INFORMATION_MESSAGE);
					// 更新数据模型
					um = new UserModel();
					// model = MyTools.newTclass(model.getClass());
					// model.query("");
					this.query("");
					// 更新JTable
					jtb.setModel(um);
				}
			}
			break;
		case "all":
			// 调用查询
			this.query("");
			break;
		}
	}

	// 将查询功能进一步抽象
	public void query(String name) {

		// 构建新的User数据模型
		um = new UserModel();
		// um = MyTools.newTclass(um.getClass());
		// 执行查询操作
		counts = um.query(name);
		// 更新JTable
		jtb.setModel(um);
		// 更新记录显示条数
		jp3_lab.setText("总共有 " + counts + " 条记录");
	}

	public void setLabel(String text) {
		jp1_lab1.setText("请输入" + text);
	}
}
