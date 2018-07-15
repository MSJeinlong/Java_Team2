package com.model;

import java.util.LinkedList;
import java.util.Vector;

import com.Class.Seller;
import com.itvm.tools.FileHelper;
import com.ivtm.Dialog.SupplierAdd_UpdateDialog;

public class SellerModel extends Model {

	// supplierGroup用户组，存放所有供货商的集合
	private static LinkedList<Seller> sellerGroup = null;

	// sellerGroup用户组
	public static LinkedList<Seller> getSupplierGroup() {
		return sellerGroup;
	}

	public static void setSupplierGroup(LinkedList<Seller> sellerGroup) {
		SellerModel.sellerGroup = sellerGroup;
	}

	// supplierGroup用户组数据文件的保存位置
	private static String filePath = "G:/JavaTrain1/SellerData.txt";

	public SellerModel() {

	}

	// 从磁盘读取用户信息
	public static void readData() {
		sellerGroup = FileHelper.readData(filePath);

	}

	// 将用户数据保存到磁盘
	public static void writeData() {

		FileHelper.writeData(sellerGroup, filePath); // 保存操作

	}

	// 查询，本质是更新JTable显示的内容，即每次增、删、改之后，都要进行的操作
	public int query(String name) {
		this.setColumnNames(new Vector());
		// 设置JTable列名
		columnNames.add("编号");
		columnNames.add("销售商名称");
		columnNames.add("电话");
		columnNames.add("地址");
		columnNames.add("货物类别");
		columnNames.add("货物名称");

		rowData = new Vector();
		// 刚刚启动程序时，则应该从磁盘次读取用户数据到userGroup
		// 如果只是进行了如增、删、改等操作，userGroup不会等于空则无需再从磁盘读取记录
		if (sellerGroup == null) {
			SellerModel.readData();
		}
		// 刷新JTable的数据
		// 将userGroup的用户数据在JTable中展现
		for (Seller sp : sellerGroup) {
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
			sellerGroup.remove(rowNum); // 删除对应的操作
			SellerModel.writeData(); // 保存操作
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

			for (Seller sl : sellerGroup) {
				if (sl.getName().equals(paras[0])) {
					return -2;
				}
			}
		}

		Seller sl = null;
		// rowNum < 0, 表示是添加新用户
		if (rowNum < 0) {
			// 创建新用户
			sl = new Seller();

			// 设置供货商各项信息
			sl.setId(Integer.parseInt(paras[0]));
			sl.setName(paras[1]);
			sl.setPhone(paras[2]);
			sl.setAddress(paras[3]);
			sl.setGoodsType(paras[4]);
			sl.setGoods(paras[5]);

			// 添加新用户到supplierGroup
			sellerGroup.add(sl);
		} else {
			// 获取对应的供货商
			// sp = UserModel.getUserGroup().get(rowNum);
			sl = sellerGroup.get(rowNum);
			// 修改供货商信息
			sl.setId(Integer.parseInt(paras[0]));
			sl.setName(paras[1]);
			sl.setPhone(paras[2]);
			sl.setAddress(paras[3]);
			sl.setGoodsType(paras[4]);
			sl.setGoods(paras[5]);
		}
		// 保存数据到磁盘
		SellerModel.writeData();
		return 1;
	}

	// 弹出update对话框
	public void updateDialog() {

		SupplierAdd_UpdateDialog sa = new SupplierAdd_UpdateDialog(null, "添加", true, null, 0);
	}
}
