package com.model;

import java.util.LinkedList;
import java.util.Vector;

import com.Class.Seller;
import com.itvm.tools.FileHelper;
import com.ivtm.Dialog.SupplierAdd_UpdateDialog;

public class SellerModel extends Model {

	// supplierGroup�û��飬������й����̵ļ���
	private static LinkedList<Seller> sellerGroup = null;

	// sellerGroup�û���
	public static LinkedList<Seller> getSupplierGroup() {
		return sellerGroup;
	}

	public static void setSupplierGroup(LinkedList<Seller> sellerGroup) {
		SellerModel.sellerGroup = sellerGroup;
	}

	// supplierGroup�û��������ļ��ı���λ��
	private static String filePath = "G:/JavaTrain1/SellerData.txt";

	public SellerModel() {

	}

	// �Ӵ��̶�ȡ�û���Ϣ
	public static void readData() {
		sellerGroup = FileHelper.readData(filePath);

	}

	// ���û����ݱ��浽����
	public static void writeData() {

		FileHelper.writeData(sellerGroup, filePath); // �������

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
		if (sellerGroup == null) {
			SellerModel.readData();
		}
		// ˢ��JTable������
		// ��userGroup���û�������JTable��չ��
		for (Seller sp : sellerGroup) {
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
			sellerGroup.remove(rowNum); // ɾ����Ӧ�Ĳ���
			SellerModel.writeData(); // �������
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

			for (Seller sl : sellerGroup) {
				if (sl.getName().equals(paras[0])) {
					return -2;
				}
			}
		}

		Seller sl = null;
		// rowNum < 0, ��ʾ��������û�
		if (rowNum < 0) {
			// �������û�
			sl = new Seller();

			// ���ù����̸�����Ϣ
			sl.setId(Integer.parseInt(paras[0]));
			sl.setName(paras[1]);
			sl.setPhone(paras[2]);
			sl.setAddress(paras[3]);
			sl.setGoodsType(paras[4]);
			sl.setGoods(paras[5]);

			// ������û���supplierGroup
			sellerGroup.add(sl);
		} else {
			// ��ȡ��Ӧ�Ĺ�����
			// sp = UserModel.getUserGroup().get(rowNum);
			sl = sellerGroup.get(rowNum);
			// �޸Ĺ�������Ϣ
			sl.setId(Integer.parseInt(paras[0]));
			sl.setName(paras[1]);
			sl.setPhone(paras[2]);
			sl.setAddress(paras[3]);
			sl.setGoodsType(paras[4]);
			sl.setGoods(paras[5]);
		}
		// �������ݵ�����
		SellerModel.writeData();
		return 1;
	}

	// ����update�Ի���
	public void updateDialog() {

		SupplierAdd_UpdateDialog sa = new SupplierAdd_UpdateDialog(null, "���", true, null, 0);
	}
}
