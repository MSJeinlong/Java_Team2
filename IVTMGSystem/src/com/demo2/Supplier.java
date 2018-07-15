package com.demo2;

/**
 * 
 * @author JunLong 供货商类，描述供货商的具体信息
 */
public class Supplier {
	private int id;
	private String name;
	private int phone;
	private String address;
	private String goodsType; // 货物类别
	private String goods; // 货物名称

	public Supplier() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
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

}
