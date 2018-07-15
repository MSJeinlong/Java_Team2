package com.game.TankGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

//坦克类--父类
class Tank {
	// 画出坦克时的参照点，即炮台的圆心
	int x = 0; // 坦克的横坐标
	int y = 0; // 坦克的纵坐标

	// 坦克方法
	// 0表示上 1表示右 2 表示下 3左
	int direct = 0;
	int color; // 坦克的颜色

	// 标识坦克时玩家坦克还是敌人坦克
	int type;

	// 坦克的子弹集合
	Vector<Shot> ss = new Vector<Shot>();

	// 坦克的速度
	int speed = 2;
	boolean isLive = true; // 坦克是否存在
	boolean isPause = false; // 游戏是否暂停，若游戏暂停，坦克停止运动

	// 游戏区域的大小，用于判断是否触碰到了边界
	int width;
	int height;

	// 定义一个向量，可以存放敌人的坦克
	Vector<EnemyTank> ets = new Vector<EnemyTank>();
	// 定义一个玩家坦克，用于判断是否碰撞
	Player player = null;

	// 定义一个障碍物，用于判断是否碰到了障碍物
	Barriers br = null;

	public void setBarrier(Barriers br) {
		this.br = br;
	}

	public Tank(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// 获取坦克的坐标，设置坦克的坐标
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setDirect(int direct) {
		this.direct = direct;
	}

	public int getDirect() {
		return direct;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getColor() {
		return color;
	}

	public void getPause(boolean pause) {
		this.isPause = pause;
	}

	// 得到游戏边界的大小
	public void getWidth_height(int width, int height) {
		this.width = width;
		this.height = height;
	}

	// 得到MyPanel的敌人坦克向量
	public void getEts(Vector<EnemyTank> vv) {
		this.ets = vv;
	}

	// 得到我的坦克
	public void getPlayer(Player hero) {
		this.player = hero;
	}

	// 画出坦克
	// 画出坦克的方法
	public void drawTank(Graphics g) {
		// 判断坦克的类型（敌我），并给定不同的颜色
		switch (type) {
		case 0:
			g.setColor(Color.CYAN);
			break;
		case 1:
			g.setColor(Color.YELLOW);
			break;
		}

		// 判断方向
		switch (direct) {
		// 炮筒向上
		case 0:
			// 1、先画左边的矩形
			g.fill3DRect(x - 10, y - 15, 5, 30, false);
			// 2、画出右边矩形
			g.fill3DRect(x + 5, y - 15, 5, 30, false);
			// 3、画出中间的矩形
			g.fill3DRect(x - 5, y - 10, 10, 20, false);
			// 4、画出中间的炮台(圆形)
			g.fillOval(x - 5, y - 5, 10, 10);
			// 5、画出中间的炮筒（细长矩形）
			// g.drawLine(x, y - 15, x, y);
			g.fillRect(x - 1, y - 15, 2, 16);
			break;
		case 1: // 炮筒向右
			// 1、画出中间的矩形
			g.fill3DRect(x - 10, y - 5, 20, 10, false);
			// 2、画出上面的矩形
			g.fill3DRect(x - 15, y - 10, 30, 5, false);
			// 3、画出下面的矩形
			g.fill3DRect(x - 15, y + 5, 30, 5, false);
			// 4、先画出中间的炮筒
			// g.drawLine(x, y, x + 15, y);
			g.fillRect(x, y - 1, 16, 2);
			// 5、画出中间的炮台
			g.fillOval(x - 5, y - 5, 10, 10);
			break;
		case 2:// 向下（与向上画法差不多，继续修改炮筒方向为向下即可）
				// 1、先画左边的矩形
			g.fill3DRect(x - 10, y - 15, 5, 30, false);
			// 2、画出右边矩形
			g.fill3DRect(x + 5, y - 15, 5, 30, false);
			// 3、画出中间的矩形
			g.fill3DRect(x - 5, y - 10, 10, 20, false);
			// 4、画出中间的炮台(圆形)
			g.fillOval(x - 5, y - 5, 10, 10);
			// 5、画出中间的炮筒（直线）
			// g.drawLine(x, y + 15, x, y);
			g.fillRect(x - 1, y, 2, 16);
			break;
		case 3:// 向左(与炮筒向右差不多，修改炮筒方向为向左即可)
				// 1、画出中间的矩形
			g.fill3DRect(x - 10, y - 5, 20, 10, false);
			// 2、画出上面的矩形
			g.fill3DRect(x - 15, y - 10, 30, 5, false);
			// 3、画出下面的矩形
			g.fill3DRect(x - 15, y + 5, 30, 5, false);
			// 4、先画出中间的炮筒
			// g.drawLine(x, y, x - 15, y);
			g.fillRect(x - 15, y - 1, 16, 2);
			// 5、画出中间的炮台
			g.fillOval(x - 5, y - 5, 10, 10);
			break;
		}
	}

	// 判断是否碰到不可穿越的障碍物
	public boolean isTouchBarrier() {

		// 先判断该坦克的方向
		switch (this.direct) {
		case 0:
			// 在逐个取出障碍物，并与之做判断
			// 判断坦克是否与土墙碰撞
			for (int i = 0; i < br.soilwalls.size(); i++) {
				// 取出一个土墙对象，与之比较
				Barriers.SoilWall sw = br.soilwalls.get(i);
				if (this.x - 10 >= sw.x && this.x - 10 <= sw.x + sw.width && this.y - 15 >= sw.y
						&& this.y - 15 <= sw.y + sw.height)
					return true;
				if (this.x + 10 >= sw.x && this.x + 10 <= sw.x + sw.width && this.y - 15 >= sw.y
						&& this.y - 15 <= sw.y + sw.height)
					return true;
				if (this.x >= sw.x && this.x <= sw.x + sw.width && this.y - 15 >= sw.y
						&& this.y - 15 <= sw.y + sw.height)
					return true;
			}

			// 判断坦克是否与铁墙碰撞
			for (int i = 0; i < br.ironwalls.size(); i++) {
				// 取出一个铁墙对象，并与之比较
				Barriers.IronWall rw = br.ironwalls.get(i);
				if (this.x - 10 >= rw.x && this.x - 10 <= rw.x + rw.width && this.y - 15 >= rw.y
						&& this.y - 15 <= rw.y + rw.height)
					return true;
				if (this.x + 10 >= rw.x && this.x + 10 <= rw.x + rw.width && this.y - 15 >= rw.y
						&& this.y - 15 <= rw.y + rw.height)
					return true;
				if (this.x >= rw.x && this.x <= rw.x + rw.width && this.y - 15 >= rw.y
						&& this.y - 15 <= rw.y + rw.height)
					return true;
			}

			// 判断坦克是否碰撞水坑对象
			for (int i = 0; i < br.waters.size(); i++) {
				// 取出每一个水坑对象，并与之比较
				Barriers.Water wt = br.waters.get(i);
				if (this.x - 10 >= wt.x && this.x - 10 <= wt.x + wt.width && this.y - 15 >= wt.y
						&& this.y - 15 <= wt.y + wt.height)
					return true;
				if (this.x + 10 >= wt.x && this.x + 10 <= wt.x + wt.width && this.y - 15 >= wt.y
						&& this.y - 15 <= wt.y + wt.height)
					return true;
			}
			break;

		// 坦克向右
		case 1:
			// 在逐个取出障碍物，并与之做判断
			// 判断坦克是否与土墙碰撞
			for (int i = 0; i < br.soilwalls.size(); i++) {
				// 取出一个土墙对象，与之比较
				Barriers.SoilWall sw = br.soilwalls.get(i);
				// 判断是否碰到障碍物
				if (this.x + 15 >= sw.x && this.x + 15 <= sw.x + sw.width && this.y - 10 >= sw.y
						&& this.y - 10 < sw.y + sw.height)
					return true;
				if (this.x + 15 >= sw.x && this.x + 15 <= sw.x + sw.width && this.y + 10 >= sw.y
						&& this.y + 10 < sw.y + sw.height)
					return true;
			}

			// 判断坦克是否与铁墙碰撞
			for (int i = 0; i < br.ironwalls.size(); i++) {
				// 取出一个铁墙对象，并与之比较
				Barriers.IronWall rw = br.ironwalls.get(i);
				if (this.x + 15 >= rw.x && this.x + 15 <= rw.x + rw.width && this.y - 10 >= rw.y
						&& this.y - 10 < rw.y + rw.height)
					return true;
				if (this.x + 15 >= rw.x && this.x + 15 <= rw.x + rw.width && this.y + 10 >= rw.y
						&& this.y + 10 < rw.y + rw.height)
					return true;
			}

			// 判断坦克是否碰撞水坑对象
			for (int i = 0; i < br.waters.size(); i++) {
				// 取出每一个水坑对象，并与之比较
				Barriers.Water wt = br.waters.get(i);
				if (this.x + 15 >= wt.x && this.x + 15 <= wt.x + wt.width && this.y - 10 >= wt.y
						&& this.y - 10 < wt.y + wt.height)
					return true;
				if (this.x + 15 >= wt.x && this.x + 15 <= wt.x + wt.width && this.y + 10 >= wt.y
						&& this.y + 10 <= wt.y + wt.height)
					return true;
			}
			break;

		// 坦克向下
		case 2:
			// 在逐个取出障碍物，并与之做判断
			// 判断坦克是否与土墙碰撞
			for (int i = 0; i < br.soilwalls.size(); i++) {
				// 取出一个土墙对象，与之比较
				Barriers.SoilWall sw = br.soilwalls.get(i);
				// 判断是否碰到障碍物
				if (this.x - 10 >= sw.x && this.x - 10 <= sw.x + sw.width && this.y + 15 >= sw.y
						&& this.y + 15 <= sw.y + sw.height)
					return true;
				if (this.x + 10 >= sw.x && this.x + 10 <= sw.x + sw.width && this.y + 15 >= sw.y
						&& this.y + 15 <= sw.y + sw.height)
					return true;
				if (this.x >= sw.x && this.x <= sw.x + sw.width && this.y + 15 >= sw.y
						&& this.y + 15 <= sw.y + sw.height)
					return true;
			}
			// 判断坦克是否与铁墙碰撞
			for (int i = 0; i < br.ironwalls.size(); i++) {
				// 取出一个铁墙对象，并与之比较
				Barriers.IronWall rw = br.ironwalls.get(i);
				if (this.x - 10 >= rw.x && this.x - 10 <= rw.x + 10 && this.y + 15 >= rw.y && this.y + 15 < rw.y + 10)
					return true;
				if (this.x + 10 >= rw.x && this.x + 10 <= rw.x + 10 && this.y + 15 >= rw.y && this.y + 15 < rw.y + 10)
					return true;
				if (this.x >= rw.x && this.x <= rw.x + rw.width && this.y + 15 >= rw.y
						&& this.y + 15 <= rw.y + rw.height)
					return true;
			}

			// 判断坦克是否碰撞水坑对象
			for (int i = 0; i < br.waters.size(); i++) {
				// 取出每一个水坑对象，并与之比较
				Barriers.Water wt = br.waters.get(i);
				if (this.x - 10 >= wt.x && this.x - 10 <= wt.x + 20 && this.y + 15 >= wt.y && this.y + 15 < wt.y + 20)
					return true;
				if (this.x + 10 >= wt.x && this.x + 10 <= wt.x + 20 && this.y + 15 >= wt.y && this.y + 15 < wt.y + 20)
					return true;
			}
			break;
		// 坦克向左
		case 3:
			// 在逐个取出障碍物，并与之做判断
			// 判断坦克是否与土墙碰撞
			for (int i = 0; i < br.soilwalls.size(); i++) {
				// 取出一个土墙对象，与之比较
				Barriers.SoilWall sw = br.soilwalls.get(i);
				// 判断是否碰到障碍物
				if (this.x - 15 >= sw.x && this.x - 15 <= sw.x + 10 && this.y - 10 >= sw.y && this.y - 10 <= sw.y + 20)
					return true;
				if (this.x - 15 >= sw.x && this.x - 15 <= sw.x + 10 && this.y + 10 >= sw.y && this.y + 10 <= sw.y + 20)
					return true;
			}
			// 判断坦克是否与铁墙碰撞
			for (int i = 0; i < br.ironwalls.size(); i++) {
				// 取出一个铁墙对象，并与之比较
				Barriers.IronWall rw = br.ironwalls.get(i);
				if (this.x - 15 >= rw.x && this.x - 15 <= rw.x + 10 && this.y - 10 >= rw.y && this.y - 10 <= rw.y + 10)
					return true;
				if (this.x - 15 >= rw.x && this.x - 15 <= rw.x + 10 && this.y + 10 >= rw.y && this.y + 10 <= rw.y + 10)
					return true;
			}

			// 判断坦克是否碰撞水坑对象
			for (int i = 0; i < br.waters.size(); i++) {
				// 取出每一个水坑对象，并与之比较
				Barriers.Water wt = br.waters.get(i);
				if (this.x - 15 >= wt.x && this.x - 15 <= wt.x + 20 && this.y - 10 >= wt.y && this.y - 10 <= wt.y + 20)
					return true;
				if (this.x - 15 >= wt.x && this.x - 15 <= wt.x + 20 && this.y + 10 >= wt.y && this.y + 10 <= wt.y + 20)
					return true;
			}
			break;
		}
		return false;
	}

	// 判断是否碰到其他坦克(包括玩家坦克和敌人坦克)
	public boolean isTouchOtherTank() {
		switch (this.direct) {
		case 0:
			// 该坦克向上
			// 取出所有的敌人坦克
			for (int i = 0; i < ets.size(); i++) {
				// 取出一个坦克
				EnemyTank et = ets.get(i);
				// 如果该坦克不是取出来的敌人坦克
				if (et != this) {
					// 如果敌人坦克的方向是向上或者向下
					if (et.direct == 0 || et.direct == 2) {
						if (this.x - 10 >= et.x - 10 && this.x - 10 <= et.x + 10 && this.y - 15 >= et.y - 15
								&& this.y - 15 <= et.y + 15) {
							return true;
						}
						if (this.x + 10 >= et.x - 10 && this.x + 10 <= et.x + 10 && this.y - 15 >= et.y - 15
								&& this.y - 15 <= et.y + 15) {
							return true;
						}
					}
					if (et.direct == 1 || et.direct == 3) {
						if (this.x - 10 >= et.x - 15 && this.x - 10 <= et.x + 15 && this.y - 15 >= et.y - 10
								&& this.y - 15 <= et.y + 10) {
							return true;
						}
						if (this.x + 10 >= et.x - 15 && this.x + 10 <= et.x + 15 && this.y - 15 >= et.y - 10
								&& this.y - 15 <= et.y + 10) {
							return true;
						}
					}
				}
			}

			if (this != player) {
				if (player.direct == 0 || player.direct == 2) {
					if (this.x - 10 >= player.x - 10 && this.x - 10 <= player.x + 10 && this.y - 15 >= player.y - 15
							&& this.y - 15 <= player.y + 15) {
						return true;
					}
					if (this.x + 10 >= player.x - 10 && this.x + 10 <= player.x + 10 && this.y - 15 >= player.y - 15
							&& this.y - 15 <= player.y + 15) {
						return true;
					}
				}
				if (player.direct == 1 || player.direct == 3) {
					if (this.x - 10 >= player.x - 15 && this.x - 10 <= player.x + 15 && this.y - 15 >= player.y - 10
							&& this.y - 15 <= player.y + 10) {
						return true;
					}
					if (this.x + 10 >= player.x - 15 && this.x + 10 <= player.x + 15 && this.y - 15 >= player.y - 10
							&& this.y - 15 <= player.y + 10) {
						return true;
					}
				}
			}
			break;
		case 1:
			// 该坦克向右
			// 取出所有的敌人坦克
			for (int i = 0; i < ets.size(); i++) {
				// 取出一个坦克
				EnemyTank et = ets.get(i);
				// 如果该坦克不是取出来的敌人坦克
				if (et != this) {
					// 如果敌人坦克的方向是向上或者向下
					if (et.direct == 0 || et.direct == 2) {
						if (this.x + 15 >= et.x - 10 && this.x + 15 <= et.x + 10 && this.y - 10 >= et.y - 15
								&& this.y - 10 <= et.y + 15) {
							return true;
						}
						if (this.x + 15 >= et.x - 10 && this.x + 15 <= et.x + 10 && this.y + 10 >= et.y - 15
								&& this.y + 10 <= et.y + 15) {
							return true;
						}
					}
					if (et.direct == 1 || et.direct == 3) {
						if (this.x + 15 >= et.x - 15 && this.x + 15 <= et.x + 15 && this.y - 10 >= et.y - 10
								&& this.y - 10 <= et.y + 10) {
							return true;
						}
						if (this.x + 15 >= et.x - 15 && this.x + 15 <= et.x + 15 && this.y + 10 >= et.y - 10
								&& this.y + 10 <= et.y + 10) {
							return true;
						}
					}
				}
			}

			// 判断是否与我的坦克发生碰撞
			if (this != player) {
				if (player.direct == 0 || player.direct == 2) {
					if (this.x + 15 >= player.x - 10 && this.x + 15 <= player.x + 10 && this.y - 10 >= player.y - 15
							&& this.y - 10 <= player.y + 15) {
						return true;
					}
					if (this.x + 15 >= player.x - 10 && this.x + 15 <= player.x + 10 && this.y + 10 >= player.y - 15
							&& this.y + 10 <= player.y + 15) {
						return true;
					}
				}

				if (player.direct == 1 || player.direct == 3) {
					if (this.x + 15 >= player.x - 15 && this.x + 15 <= player.x + 15 && this.y - 10 >= player.y - 10
							&& this.y - 10 <= player.y + 10) {
						return true;
					}
					if (this.x + 15 >= player.x - 15 && this.x + 15 <= player.x + 15 && this.y + 10 >= player.y - 10
							&& this.y + 10 <= player.y + 10) {
						return true;
					}
				}
			}
			break;
		case 2:
			// 该坦克向下
			// 取出所有的敌人坦克
			for (int i = 0; i < ets.size(); i++) {
				// 取出一个坦克
				EnemyTank et = ets.get(i);
				// 如果该坦克不是取出来的敌人坦克
				if (et != this) {
					// 如果敌人坦克的方向是向上或者向下
					if (et.direct == 0 || et.direct == 2) {
						if (this.x - 10 >= et.x - 10 && this.x - 10 <= et.x + 10 && this.y + 15 >= et.y - 15
								&& this.y + 15 <= et.y + 15) {
							return true;
						}
						if (this.x + 10 >= et.x - 10 && this.x + 10 <= et.x + 10 && this.y + 15 >= et.y - 15
								&& this.y + 15 <= et.y + 15) {
							return true;
						}
					}
					if (et.direct == 1 || et.direct == 3) {
						if (this.x - 10 >= et.x - 15 && this.x - 10 <= et.x + 15 && this.y + 15 >= et.y - 10
								&& this.y + 15 <= et.y + 10) {
							return true;
						}
						if (this.x + 10 >= et.x - 15 && this.x + 10 <= et.x + 15 && this.y + 15 >= et.y - 10
								&& this.y + 15 <= et.y + 10) {
							return true;
						}
					}
				}
			}

			if (this != player) {
				if (player.direct == 0 || player.direct == 2) {
					if (this.x - 10 >= player.x - 10 && this.x - 10 <= player.x + 10 && this.y + 15 >= player.y - 15
							&& this.y + 15 <= player.y + 15) {
						return true;
					}
					if (this.x + 10 >= player.x - 10 && this.x + 10 <= player.x + 10 && this.y + 15 >= player.y - 15
							&& this.y + 15 <= player.y + 15) {
						return true;
					}
				}
				if (player.direct == 1 || player.direct == 3) {
					if (this.x - 10 >= player.x - 15 && this.x - 10 <= player.x + 15 && this.y + 15 >= player.y - 10
							&& this.y + 15 <= player.y + 10) {
						return true;
					}
					if (this.x + 10 >= player.x - 15 && this.x + 10 <= player.x + 15 && this.y + 15 >= player.y - 10
							&& this.y + 15 <= player.y + 10) {
						return true;
					}
				}
			}
			break;
		case 3:
			// 该坦克向左
			// 取出每一个敌人坦克
			for (int i = 0; i < ets.size(); i++) {
				EnemyTank et = ets.get(i);
				if (et != this) {
					if (et.direct == 0 || et.direct == 2) {
						if (this.x - 15 >= et.x - 10 && this.x - 15 <= et.x + 10 && this.y - 10 >= et.y - 15
								&& this.y - 10 <= et.y + 15) {
							return true;
						}
						if (this.x - 15 >= et.x - 10 && this.x - 15 <= et.x + 10 && this.y + 10 >= et.y - 15
								&& this.y + 10 <= et.y + 15) {
							return true;
						}
					}
					if (et.direct == 1 || et.direct == 3) {
						if (this.x - 15 >= et.x - 15 && this.x - 15 <= et.x + 15 && this.y - 10 >= et.y - 10
								&& this.y - 10 <= et.y + 10) {
							return true;
						}
						if (this.x - 15 >= et.x - 15 && this.x - 15 <= et.x + 15 && this.y + 10 >= et.y - 10
								&& this.y + 10 <= et.y + 10) {
							return true;
						}
					}
				}
			}

			// 判断是否与我的坦克相撞
			if (this != player) {
				if (player.direct == 0 || player.direct == 2) {
					if (this.x - 15 >= player.x - 10 && this.x - 15 <= player.x + 10 && this.y - 10 >= player.y - 15
							&& this.y - 10 <= player.y + 15) {
						return true;
					}
					if (this.x - 15 >= player.x - 10 && this.x - 15 <= player.x + 10 && this.y + 10 >= player.y - 15
							&& this.y + 10 <= player.y + 15) {
						return true;
					}
				}
				if (player.direct == 1 || player.direct == 3) {
					if (this.x - 15 >= player.x - 15 && this.x - 15 <= player.x + 15 && this.y - 10 >= player.y - 10
							&& this.y - 10 <= player.y + 10) {
						return true;
					}
					if (this.x - 15 >= player.x - 15 && this.x - 15 <= player.x + 15 && this.y + 10 >= player.y - 10
							&& this.y + 10 <= player.y + 10) {
						return true;
					}
				}
			}
			break;
		}

		return false;
	}

}

// 我的坦克
class Player extends Tank {

	// 坦克的炮弹
	Shot s = null;

	// 玩家坦克的生命条数，默认为3条命
	int life = 3;

	public Player(int x, int y) {
		super(x, y); // 调用父类的构造方法进行初始化
		// 设置玩家坦克的速度
		this.speed = 4;
		this.type = 1;
	}

	// 开火
	public void shotEnemy() {

		switch (this.direct) {
		case 0:
			s = new Shot(x - 2, y - 15, 0);
			ss.add(s);
			break;
		case 1:
			s = new Shot(x + 15, y - 2, 1);
			ss.add(s);
			break;
		case 2:
			s = new Shot(x - 2, y + 15, 2);
			ss.add(s);
			break;
		case 3:
			s = new Shot(x - 15, y - 2, 3);
			ss.add(s);
			break;
		}

		s.getWidth_height(width, height);
		// 启动炮弹线程
		Thread t = new Thread(s);
		t.start();
	}

	// 坦克向上移动
	public void moveUp() {
		y -= speed;
	}

	// 坦克向右移动
	public void moveRight() {
		x += speed;
	}

	// 坦克向下移动
	public void moveDown() {
		y += speed;
	}

	// 坦克向左移动
	public void moveLeft() {
		x -= speed;
	}
}

// 敌人坦克
class EnemyTank extends Tank implements Runnable {

	// 敌人添加子弹，应当在刚刚创建坦克时就给它一颗子弹
	int times = 0;

	public EnemyTank(int x, int y) {
		super(x, y);
		this.type = 0;
	}

	// 敌人坦克的移动方法
	public void Move() {
		switch (this.direct) {
		case 0:
			// 说明坦克正在向上
			for (int i = 0; i < 30; i++) {
				if (y - 15 < 0 || this.isTouchOtherTank() || this.isTouchBarrier())
					break;
				if (!this.isPause) {// 坦克连续平滑移动时，需要时刻判断游戏是否暂停了
					y -= speed;
				}
				try {
					Thread.sleep(50);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			break;
		case 1:
			// 向右
			for (int i = 0; i < 30; i++) {
				if (x + 15 > width || this.isTouchOtherTank() || this.isTouchBarrier())
					break;
				if (!this.isPause) {
					x += speed;
				}
				try {
					Thread.sleep(50);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;
		case 2:
			// 向下
			for (int i = 0; i < 30; i++) {
				if (y + 15 > height || this.isTouchOtherTank() || this.isTouchBarrier())
					break;
				if (!this.isPause) {
					y += speed;
				}
				try {
					Thread.sleep(50);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;
		case 3:
			// 向左
			for (int i = 0; i < 30; i++) {
				if (x - 15 < 0 || this.isTouchOtherTank() || this.isTouchBarrier())
					break;
				if (!this.isPause) {
					x -= speed;
				}
				try {
					Thread.sleep(50);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;
		}
	}

	// 为敌人坦克添加子弹
	public void addShot() {
		this.times++;
		if (times % 2 == 0) {
			if (isLive) {
				if (ss.size() < 2) {
					Shot s = null;
					// 没有子弹，添加
					switch (direct) {
					case 0:
						s = new Shot(x - 2, y - 15, 0);

						ss.add(s);
						break;
					case 1:
						s = new Shot(x + 15, y - 2, 1);
						ss.add(s);
						break;
					case 2:
						s = new Shot(x - 2, y + 15, 2);
						ss.add(s);
						break;
					case 3:
						s = new Shot(x - 15, y - 2, 3);
						ss.add(s);
						break;
					}

					// 得到游戏边界的信息
					s.getWidth_height(width, height);
					// 启动子弹线程
					Thread t = new Thread(s);
					t.start();
				}
			}
		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {

			this.Move();// 坦克移动

			if (this.isPause) // 改变坦克方向或者添加子弹时，判断游戏是否暂停
				continue;

			this.addShot();// 为坦克添加子弹

			// 让坦克随机产生一个新的方向
			this.direct = (int) (Math.random() * 4);

			// 判断敌人坦克是否死亡
			if (this.isLive == false) {
				// 让坦克死亡后，退出线程
				// 应同时remove掉该坦克的所有子弹
				break;
			}

		}
	}
}
