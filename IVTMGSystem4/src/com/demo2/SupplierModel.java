package com.demo2;

import java.util.LinkedList;
import java.util.Vector;

public class SupplierModel extends Model {

	// supplierGroup�û��飬������й����̵ļ���
	private static LinkedList<Supplier> supplierGroup = null;

	// sellerGroup�û���
	public static LinkedList<Supplier> getSupplierGroup() {
		return supplierGroup;
	}

	public static void setSupplierGroup(LinkedList<Supplier> supplierGroup) {
		SupplierModel.supplierGroup = supplierGroup;
	}

	// supplierGroup�û��������ļ��ı���λ��
	private static String filePath = "G:/JavaTrain1/SupplierData.txt";

	public SupplierModel() {

	}

	// �Ӵ��̶�ȡ�û���Ϣ
	public static void readData() {
		supplierGroup = FileHelper.readData(filePath);

	}

	// ���û����ݱ��浽����
	public static void writeData() {

		FileHelper.writeData(supplierGroup, filePath); // �������

	}

	// ��ѯ�������Ǹ���JTable��ʾ�����ݣ���ÿ������ɾ����֮�󣬶�Ҫ���еĲ���
	public int query(String name) {
		this.setColumnNames(new Vector());
		// ����JTable����
		columnNames.add("���");
		columnNames.add("����������");
		columnNames.add("�绰");
		columnNames.add("��ַ");
		columnNames.add("�������");
		columnNames.add("��������");

		rowData = new Vector();
		// �ո���������ʱ����Ӧ�ôӴ��̴ζ�ȡ�û����ݵ�userGroup
		// ���ֻ�ǽ�����������ɾ���ĵȲ�����userGroup������ڿ��������ٴӴ��̶�ȡ��¼
		if (supplierGroup == null) {
			SupplierModel.readData();
		}
		// ˢ��JTable������
		// ��userGroup���û�������JTable��չ��
		for (Supplier sp : supplierGroup) {
			// JTable��һ�����ݣ�������user����Ϣ,��ÿһ��������JTable�ж�����Vector����
			Vector oneRow = new Vector();
			// ������name���в�ѯ����name == "" ʱ�����ǲ�ѯ���й�������Ϣ
			// ���򣬾���������ѯ���������ּ���
			if (sp.getName().startsWith(name)) {
				oneRow.add(sp.getId());
				oneRow.add(sp.getName());
				oneRow.add(sp.getPhone());
				oneRow.add(sp.getAddress());
				oneRow.add(sp.getGoodsType());
				oneRow.add(sp.getGoods());

				// ��JTable�м�����һ������
				rowData.add(oneRow);
			}

		}
		return rowData.size();
	}

	// (��SupplierModel���и��£���������ɾ����)
	public static int update(String[] paras, int rowNum) {
		// paras == null��˵��ִ�е���ɾ������
		if (paras == null) {
			System.out.println(supplierGroup.size());
			supplierGroup.remove(rowNum); // ɾ����Ӧ�Ĳ���
			SupplierModel.writeData(); // �������
			System.out.println(supplierGroup.size());
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
		// �ж��Ƿ������Ƿ��ظ�,����µĹ�����ʱ����Ҫ���
		if (rowNum < 0) { // rowNum < 0����ʾ������¹����̣��������Ƿ����ظ�

			for (Supplier sp : supplierGroup) {
				if (sp.getName().equals(paras[1])) {
					return -2;
				}
			}
		}

		Supplier sp = null;
		// rowNum < 0, ��ʾ��������û�
		if (rowNum < 0) {
			// �������û�
			sp = new Supplier();

			// ���ù����̸�����Ϣ
			sp.setId(Integer.parseInt(paras[0]));
			sp.setName(paras[1]);
			sp.setPhone(paras[2]);
			sp.setAddress(paras[3]);
			sp.setGoodsType(paras[4]);
			sp.setGoods(paras[5]);

			// ������û���supplierGroup
			supplierGroup.add(sp);
		} else {
			// ��ȡ��Ӧ�Ĺ�����
			// sp = UserModel.getUserGroup().get(rowNum);
			sp = supplierGroup.get(rowNum);
			// �޸Ĺ�������Ϣ
			sp.setName(paras[0]);
			sp.setPhone(paras[1]);
			sp.setAddress(paras[2]);
			sp.setGoodsType(paras[3]);
			sp.setGoods(paras[4]);
		}
		// �������ݵ�����
		SupplierModel.writeData();
		return 1;
	}

	// ����update�Ի���
	public void updateDialog(Model m, int rowNum) {
		// �ж�����ӻ����޸�
		if (m == null) {
			SupplierAdd_UpdateDialog sa = new SupplierAdd_UpdateDialog(null, "���", true, null, 0);
		} else {
			SupplierAdd_UpdateDialog sa = new SupplierAdd_UpdateDialog(null, "�޸�", true, m, rowNum);
		}
	}
}
