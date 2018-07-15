package com.demo2;

import java.util.LinkedList;
import java.util.Vector;

public class SupplierModel extends Model {

	// supplierGroup用户组，存放所有供货商的集合
	private static LinkedList<Supplier> supplierGroup = null;

	// sellerGroup用户组
	public static LinkedList<Supplier> getSupplierGroup() {
		return supplierGroup;
	}

	public static void setSupplierGroup(LinkedList<Supplier> supplierGroup) {
		SupplierModel.supplierGroup = supplierGroup;
	}

	// supplierGroup用户组数据文件的保存位置
	private static String filePath = "G:/JavaTrain1/SupplierData.txt";

	public SupplierModel() {

	}

	// 从磁盘读取用户信息
	public static void readData() {
		supplierGroup = FileHelper.readData(filePath);

	}

	// 将用户数据保存到磁盘
	public static void writeData() {

		FileHelper.writeData(supplierGroup, filePath); // 保存操作

	}

	// 查询，本质是更新JTable显示的内容，即每次增、删、改之后，都要进行的操作
	public int query(String name) {
		this.setColumnNames(new Vector());
		// 设置JTable列名
		columnNames.add("编号");
		columnNames.add("供货商名称");
		columnNames.add("电话");
		columnNames.add("地址");
		columnNames.add("货物类别");
		columnNames.add("货物名称");

		rowData = new Vector();
		// 刚刚启动程序时，则应该从磁盘次读取用户数据到userGroup
		// 如果只是进行了如增、删、改等操作，userGroup不会等于空则无需再从磁盘读取记录
		if (supplierGroup == null) {
			SupplierModel.readData();
		}
		// 刷新JTable的数据
		// 将userGroup的用户数据在JTable中展现
		for (Supplier sp : supplierGroup) {
			// JTable的一行数据，来自于user的信息,且每一行数据在JTable中都是以Vector保存
			Vector oneRow = new Vector();
			// 按参数name进行查询，当name == "" 时，即是查询所有供货商信息
			// 否则，就是条件查询，即按名字检索
			if (sp.getName().startsWith(name)) {
				oneRow.add(sp.getId());
				oneRow.add(sp.getName());
				oneRow.add(sp.getPhone());
				oneRow.add(sp.getAddress());
				oneRow.add(sp.getGoodsType());
				oneRow.add(sp.getGoods());

				// 向JTable中加入这一行数据
				rowData.add(oneRow);
			}

		}
		return rowData.size();
	}

	// (对SupplierModel进行更新，包括增、删、改)
	public static int update(String[] paras, int rowNum) {
		// paras == null，说明执行的是删除操作
		if (paras == null) {
			System.out.println(supplierGroup.size());
			supplierGroup.remove(rowNum); // 删除对应的操作
			SupplierModel.writeData(); // 保存操作
			System.out.println(supplierGroup.size());
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
		// 判断是否名称是否重复,添加新的供货商时才需要检查
		if (rowNum < 0) { // rowNum < 0，表示是添加新供货商，需检查编号是否有重复

			for (Supplier sp : supplierGroup) {
				if (sp.getName().equals(paras[1])) {
					return -2;
				}
			}
		}

		Supplier sp = null;
		// rowNum < 0, 表示是添加新用户
		if (rowNum < 0) {
			// 创建新用户
			sp = new Supplier();

			// 设置供货商各项信息
			sp.setId(Integer.parseInt(paras[0]));
			sp.setName(paras[1]);
			sp.setPhone(paras[2]);
			sp.setAddress(paras[3]);
			sp.setGoodsType(paras[4]);
			sp.setGoods(paras[5]);

			// 添加新用户到supplierGroup
			supplierGroup.add(sp);
		} else {
			// 获取对应的供货商
			// sp = UserModel.getUserGroup().get(rowNum);
			sp = supplierGroup.get(rowNum);
			// 修改供货商信息
			sp.setName(paras[0]);
			sp.setPhone(paras[1]);
			sp.setAddress(paras[2]);
			sp.setGoodsType(paras[3]);
			sp.setGoods(paras[4]);
		}
		// 保存数据到磁盘
		SupplierModel.writeData();
		return 1;
	}

	// 弹出update对话框
	public void updateDialog(Model m, int rowNum) {
		// 判断是添加还是修改
		if (m == null) {
			SupplierAdd_UpdateDialog sa = new SupplierAdd_UpdateDialog(null, "添加", true, null, 0);
		} else {
			SupplierAdd_UpdateDialog sa = new SupplierAdd_UpdateDialog(null, "修改", true, m, rowNum);
		}
	}
}
