package com.Class;

import java.io.Serializable;

/**
 * 
 * @author JunLong �������࣬���������̵ľ�����Ϣ
 */
public class Supplier implements Serializable {

	private static final long serialVersionUID = 2976052444920965068L;

	private int id;
	private String name;
	private String phone;
	private String address;
	private String goodsType; // �������
	private String goods; // ��������

	public Supplier() {

	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getGoods() {
		return goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}

	@Override
	public String toString() {
		return "Supplier [id=" + id + ", name=" + name + ", phone=" + phone + ", address=" + address + ", goodsType="
				+ goodsType + ", goods=" + goods + "]";
	}
}
