package com.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import com.Class.User;
import com.itvm.tools.FileHelper;
import com.ivtm.Dialog.UserAddDialog;
import com.ivtm.Dialog.UserUpdateDialog;

/**
 * 
 * @author JunLong 这是我的一个 User的模型 可以把对User数据的各种操作封装到该类中
 */
public class UserModel extends Model {

	// userGroup用户组，存放所有用户的集合
	private static LinkedList<User> userGroup = null;
	// userGroup用户组数据文件的保存位置
	private static String filePath = "G:/JavaTrain1/UsersData.txt";

	public UserModel() {

	}

	// 从磁盘读取用户信息
	public void readData() {
		UserModel.userGroup = FileHelper.readData(filePath);
	}

	// 将用户数据保存到磁盘
	public static void writeData() {
		FileHelper.writeData(userGroup, filePath); // 保存操作
	}

	// 检查登录用户的账号和密码是否正确
	public boolean checkUser(String uid, String pwd) {
		boolean b = false;
		// 先读取磁盘里的用户数据
		this.readData();
		for (User user : userGroup) {
			// 1、判断用户id和pwd是否一致
			if (user.getID().equals(uid) && user.getPasswd().equals(pwd)) {
				b = true; // 设置 b 为 true
				break;
			}
		}
		return b;
	}

	// 找回密码时验证账号和手机号码
	// 检查登录用户的账号和密码是否正确
	public boolean findPwd(String uid, String Phone) {
		boolean b = false;
		// 先读取磁盘里的用户数据
		this.readData();
		for (User user : userGroup) {
			// 1、判断用户id和pwd是否一致
			if (user.getID().equals(uid) && user.getPhoneNum().equals(Phone)) {
				b = true; // 设置 b 为 true
				break;
			}
		}
		return b;
	}

	// 重设密码
	public void resetPasswd(String uid, String pwd) {
		// 根据uid修改相应的用户密码
		for (User user : userGroup) {
			if (user.getID().equals(uid)) {
				user.setPasswd(pwd);
				break;
			}
		}
		// 保存修改后的密码到磁盘
		UserModel.writeData();
	}

	// 查询，本质是更新JTable显示的内容，即每次增、删、改之后，都要进行的操作
	public int query(String name) {
		this.setColumnNames(new Vector());
		// 设置JTable列名
		columnNames.add("帐号");
		columnNames.add("密码");
		columnNames.add("姓名");
		columnNames.add("性别");
		columnNames.add("年龄");
		columnNames.add("手机号码");
		columnNames.add("电子邮箱");

		// rowData = new Vector();
		// 刚刚启动程序时，则应该从磁盘次读取用户数据到userGroup
		// 如果只是进行了如增、删、改等操作，userGroup不会等于空则无需再从磁盘读取记录
		if (userGroup == null) {
			this.readData();
		}
		// 刷新JTable的数据
		// 将userGroup的用户数据在JTable中展现
		for (User user : UserModel.userGroup) {
			// JTable的一行数据，来自于user的信息,且每一行数据在JTable中都是以Vector保存
			Vector oneRow = new Vector();
			// 按参数name进行查询，当name == "" 时，即是查询所有用户信息
			// 否则，就是条件查询，即按名字检索
			if (user.getName().startsWith(name)) {
				oneRow.add(user.getID());
				oneRow.add(user.getPasswd());
				oneRow.add(user.getName());
				oneRow.add(user.getSex());
				oneRow.add(user.getAge());
				oneRow.add(user.getPhoneNum());
				oneRow.add(user.getEmail());

				// 向JTable中加入这一行数据
				rowData.add(oneRow);
			}
		}
		return rowData.size();
	}

	// (对UserModel进行更新，包括增、删、改)
	public static int update(String[] paras, int rowNum) {
		// paras == null，说明执行的是删除操作
		if (paras == null) {
			userGroup.remove(rowNum); // 删除对应的操作
			UserModel.writeData(); // 保存操作
			return 1; // 退出
		}

		// 在添加之前要确定paras的每一项参数是否正确
		// 确保新添加或者修改的user用户的每一项信息不为空,且编号不重复
		// 判断是否有信息为空
		for (String mess : paras) {
			if (mess == null || mess.equals("")) {
				return -1;
			}
		}
		// 判断是否编号是否重复,添加新用户时才需要检查
		if (rowNum < 0) { // rowNum < 0，表示是添加新用户，需检查编号是否有重复
			for (User user : userGroup) {
				if (user.getID().equals(paras[0])) {
					return -2;
				}
			}
		}

		User user = null;
		// rowNum < 0, 表示是添加新用户
		if (rowNum < 0) {
			// 创建新用户
			user = new User();

			// 设置用户各项信息
			user.setID(paras[0]);
			user.setPasswd(paras[1]);
			user.setName(paras[2]);
			user.setSex(paras[3]);
			user.setAge(Integer.parseInt(paras[4]));
			user.setPhoneNum(paras[5]);
			user.setEmail(paras[6]);

			// 添加新用户到userGroup
			userGroup.add(user);
		} else {
			// 获取对应的用户
			user = UserModel.getUserGroup().get(rowNum);
			// 修改用户信息
			user.setPasswd(paras[0]);
			user.setName(paras[1]);
			user.setSex(paras[2]);
			user.setAge(Integer.parseInt(paras[3]));
			user.setPhoneNum(paras[4]);
			user.setEmail(paras[5]);
		}
		// 保存数据到磁盘
		UserModel.writeData();
		return 1;
	}

	// 添加方法
	// 返回值解释
	// 返回 -1 表示其中一项必填信息为空
	public int add(String[] paras) {
		// 添加之前先检查用户输入是否有误
		int res = checkInput(paras, -1);
		// 结果不为1，说明检查不通过
		if (res != 1) {
			return res;
		}
		// 检查通过则可以添加
		String id = paras[0];
		String name = paras[1];
		String passwd = paras[2];
		String sex = paras[3];
		int age = Integer.parseInt(paras[4]);
		String phone = paras[5];
		String email = paras[6];
		User user = new User();

		user.setID(id);
		user.setName(name);
		user.setPasswd(passwd);
		user.setSex(sex);
		user.setPhoneNum(phone);
		user.setEmail(email);

		// 添加到用户组并保存
		userGroup.add(user);
		UserModel.writeData();
		return 1;
	}

	// 检查用户输入是否合理
	public static int checkInput(String[] paras, int rowNum) {
		// 检查是否有必填项为空
		for (int i = 0; i < paras.length; i++) {
			if (paras[i] == null) {
				return -1;
			}
		}
		String id = paras[0];
		String name = paras[1];
		String passwd = paras[2];
		String sex = paras[3];
		int age = Integer.parseInt(paras[4]);
		String phone = paras[5];
		String Email = paras[6];

		// 年龄有误
		if (age < 0 || age > 150) {
			return -2;
		}
		// 手机号码有误
		if (phone.length() != 0) {
			return -3;
		}
		for (int i = 0; i < phone.length(); i++) {
			if (phone.charAt(i) > '9' || phone.charAt(i) < '0') {
				return -3;
			}
		}

		// 电子邮件格式错误
		if (Email.endsWith(".com") || Email.contains("@")) {
			return -4;
		}

		// row < 0 ，表示是添加操作
		if (rowNum < 0) {
			// 检查用户ID是否重复
			for (User u : userGroup) {
				if (u.getID().equals(id)) {
					return -5;
				}
			}
		}
		return 1;
	}

	// 修改
	public int alter(String[] paras, int rowNum) {
		int res = checkInput(paras, rowNum);
		if (res != 1) {
			return res;
		}

		return 1;
	}

	// 弹出update对话框
	public void updateDialog(Model m, int rowNum) {
		if (m == null) {
			new UserAddDialog(null, "添加", true, "add");
		} else {
			new UserUpdateDialog(null, "修改", true, m, rowNum);
		}
	}

	public static List<User> getUserGroup() {
		return userGroup;
	}

	public static void setUserGroup(LinkedList<User> userGroup) {
		UserModel.userGroup = userGroup;
	}

}
