package com.demo2;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 * 
 * @author JunLong �����ҵ�һ�� User��ģ�� ���԰Ѷ�User���ݵĸ��ֲ�����װ��������
 */
public class UserModel extends Model {

	// userGroup�û��飬��������û��ļ���
	private static LinkedList<User> userGroup = null;
	// userGroup�û��������ļ��ı���λ��
	private static String filePath = "G:/JavaTrain1/UsersData.txt";

	public UserModel() {

	}

	// �Ӵ��̶�ȡ�û���Ϣ
	public static void readData() {
		UserModel.userGroup = FileHelper.readData(filePath);
	}

	// ���û����ݱ��浽����
	public static void writeData() {
		FileHelper.writeData(userGroup, filePath); // �������
	}

	// ����¼�û����˺ź������Ƿ���ȷ
	public static boolean checkUser(String uid, String pwd) {
		boolean b = false;
		// �ȶ�ȡ��������û�����
		UserModel.readData();
		for (User user : userGroup) {
			// 1���ж��û�id��pwd�Ƿ�һ��
			if (user.getID().equals(uid) && user.getPasswd().equals(pwd)) {
				b = true; // ���� b Ϊ true
				break;
			}
		}
		return b;
	}

	// �һ�����ʱ��֤�˺ź��ֻ�����
	// ����¼�û����˺ź������Ƿ���ȷ
	public static boolean findPwd(String uid, String Phone) {
		boolean b = false;
		// �ȶ�ȡ��������û�����
		UserModel.readData();
		for (User user : userGroup) {
			// 1���ж��û�id��pwd�Ƿ�һ��
			if (user.getID().equals(uid) && user.getPhoneNum().equals(Phone)) {
				b = true; // ���� b Ϊ true
				break;
			}
		}
		return b;
	}

	// ��������
	public static void resetPasswd(String uid, String pwd) {
		// ����uid�޸���Ӧ���û�����
		for (User user : userGroup) {
			if (user.getID().equals(uid)) {
				user.setPasswd(pwd);
				break;
			}
		}
		// �����޸ĺ�����뵽����
		UserModel.writeData();
	}

	// ��ѯ�������Ǹ���JTable��ʾ�����ݣ���ÿ������ɾ����֮�󣬶�Ҫ���еĲ���
	public int query(String name) {
		this.setColumnNames(new Vector());
		// ����JTable����
		columnNames.add("�ʺ�");
		columnNames.add("����");
		columnNames.add("����");
		columnNames.add("�Ա�");
		columnNames.add("����");
		columnNames.add("�ֻ�����");
		columnNames.add("��������");

		// rowData = new Vector();
		// �ո���������ʱ����Ӧ�ôӴ��̴ζ�ȡ�û����ݵ�userGroup
		// ���ֻ�ǽ�����������ɾ���ĵȲ�����userGroup������ڿ��������ٴӴ��̶�ȡ��¼
		if (userGroup == null) {
			UserModel.readData();
		}
		// ˢ��JTable������
		// ��userGroup���û�������JTable��չ��
		for (User user : UserModel.userGroup) {
			// JTable��һ�����ݣ�������user����Ϣ,��ÿһ��������JTable�ж�����Vector����
			Vector oneRow = new Vector();
			// ������name���в�ѯ����name == "" ʱ�����ǲ�ѯ�����û���Ϣ
			// ���򣬾���������ѯ���������ּ���
			if (user.getName().startsWith(name)) {
				oneRow.add(user.getID());
				oneRow.add(user.getPasswd());
				oneRow.add(user.getName());
				oneRow.add(user.getSex());
				oneRow.add(user.getAge());
				oneRow.add(user.getPhoneNum());
				oneRow.add(user.getEmail());

				// ��JTable�м�����һ������
				rowData.add(oneRow);
			}
		}
		return rowData.size();
	}

	// (��UserModel���и��£���������ɾ����)
	public static int update(String[] paras, int[] rowNums) {
		// paras == null��˵��ִ�е���ɾ������
		if (paras == null) {
			for (int i : rowNums) {
				userGroup.remove(rowNums[i]); // ɾ����Ӧ�Ĳ���
			}
			UserModel.writeData(); // �������
			return 1; // �˳�
		}

		// �����֮ǰҪȷ��paras��ÿһ������Ƿ���ȷ
		// ȷ������ӻ����޸ĵ�user�û���ÿһ����Ϣ��Ϊ��,�ұ�Ų��ظ�
		// �ж��Ƿ�����ϢΪ��
		for (String mess : paras) {
			if (mess == null || mess.equals("")) {
				return -1;
			}
		}
		// �ж��Ƿ����Ƿ��ظ�,������û�ʱ����Ҫ���
		if (rowNums == null) { // rowNum < 0����ʾ��������û����������Ƿ����ظ�
			for (User user : userGroup) {
				if (user.getID().equals(paras[0])) {
					return -2;
				}
			}
		}

		User user = null;
		// rowNum < 0, ��ʾ��������û�
		if (rowNum < 0) {
			// �������û�
			user = new User();

			// �����û�������Ϣ
			user.setID(paras[0]);
			user.setPasswd(paras[1]);
			user.setName(paras[2]);
			user.setSex(paras[3]);
			user.setAge(Integer.parseInt(paras[4]));
			user.setPhoneNum(paras[5]);
			user.setEmail(paras[6]);

			// ������û���userGroup
			userGroup.add(user);
		} else {
			// ��ȡ��Ӧ���û�
			user = UserModel.getUserGroup().get(rowNum);
			// �޸��û���Ϣ
			user.setPasswd(paras[0]);
			user.setName(paras[1]);
			user.setSex(paras[2]);
			user.setAge(Integer.parseInt(paras[3]));
			user.setPhoneNum(paras[4]);
			user.setEmail(paras[5]);
		}
		// �������ݵ�����
		UserModel.writeData();
		return 1;
	}

	// ����update�Ի���
	public void updateDialog() {
		UserAddDialog ua = new UserAddDialog(null, "���", true, "add");
	}

	public static List<User> getUserGroup() {
		return userGroup;
	}

	public static void setUserGroup(LinkedList<User> userGroup) {
		UserModel.userGroup = userGroup;
	}

}
