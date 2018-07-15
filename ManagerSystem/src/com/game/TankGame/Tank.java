package com.game.TankGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

//̹����--����
class Tank {
	// ����̹��ʱ�Ĳ��յ㣬����̨��Բ��
	int x = 0; // ̹�˵ĺ�����
	int y = 0; // ̹�˵�������

	// ̹�˷���
	// 0��ʾ�� 1��ʾ�� 2 ��ʾ�� 3��
	int direct = 0;
	int color; // ̹�˵���ɫ

	// ��ʶ̹��ʱ���̹�˻��ǵ���̹��
	int type;

	// ̹�˵��ӵ�����
	Vector<Shot> ss = new Vector<Shot>();

	// ̹�˵��ٶ�
	int speed = 2;
	boolean isLive = true; // ̹���Ƿ����
	boolean isPause = false; // ��Ϸ�Ƿ���ͣ������Ϸ��ͣ��̹��ֹͣ�˶�

	// ��Ϸ����Ĵ�С�������ж��Ƿ������˱߽�
	int width;
	int height;

	// ����һ�����������Դ�ŵ��˵�̹��
	Vector<EnemyTank> ets = new Vector<EnemyTank>();
	// ����һ�����̹�ˣ������ж��Ƿ���ײ
	Player player = null;

	// ����һ���ϰ�������ж��Ƿ��������ϰ���
	Barriers br = null;

	public void setBarrier(Barriers br) {
		this.br = br;
	}

	public Tank(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// ��ȡ̹�˵����꣬����̹�˵�����
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

	// �õ���Ϸ�߽�Ĵ�С
	public void getWidth_height(int width, int height) {
		this.width = width;
		this.height = height;
	}

	// �õ�MyPanel�ĵ���̹������
	public void getEts(Vector<EnemyTank> vv) {
		this.ets = vv;
	}

	// �õ��ҵ�̹��
	public void getPlayer(Player hero) {
		this.player = hero;
	}

	// ����̹��
	// ����̹�˵ķ���
	public void drawTank(Graphics g) {
		// �ж�̹�˵����ͣ����ң�����������ͬ����ɫ
		switch (type) {
		case 0:
			g.setColor(Color.CYAN);
			break;
		case 1:
			g.setColor(Color.YELLOW);
			break;
		}

		// �жϷ���
		switch (direct) {
		// ��Ͳ����
		case 0:
			// 1���Ȼ���ߵľ���
			g.fill3DRect(x - 10, y - 15, 5, 30, false);
			// 2�������ұ߾���
			g.fill3DRect(x + 5, y - 15, 5, 30, false);
			// 3�������м�ľ���
			g.fill3DRect(x - 5, y - 10, 10, 20, false);
			// 4�������м����̨(Բ��)
			g.fillOval(x - 5, y - 5, 10, 10);
			// 5�������м����Ͳ��ϸ�����Σ�
			// g.drawLine(x, y - 15, x, y);
			g.fillRect(x - 1, y - 15, 2, 16);
			break;
		case 1: // ��Ͳ����
			// 1�������м�ľ���
			g.fill3DRect(x - 10, y - 5, 20, 10, false);
			// 2����������ľ���
			g.fill3DRect(x - 15, y - 10, 30, 5, false);
			// 3����������ľ���
			g.fill3DRect(x - 15, y + 5, 30, 5, false);
			// 4���Ȼ����м����Ͳ
			// g.drawLine(x, y, x + 15, y);
			g.fillRect(x, y - 1, 16, 2);
			// 5�������м����̨
			g.fillOval(x - 5, y - 5, 10, 10);
			break;
		case 2:// ���£������ϻ�����࣬�����޸���Ͳ����Ϊ���¼��ɣ�
				// 1���Ȼ���ߵľ���
			g.fill3DRect(x - 10, y - 15, 5, 30, false);
			// 2�������ұ߾���
			g.fill3DRect(x + 5, y - 15, 5, 30, false);
			// 3�������м�ľ���
			g.fill3DRect(x - 5, y - 10, 10, 20, false);
			// 4�������м����̨(Բ��)
			g.fillOval(x - 5, y - 5, 10, 10);
			// 5�������м����Ͳ��ֱ�ߣ�
			// g.drawLine(x, y + 15, x, y);
			g.fillRect(x - 1, y, 2, 16);
			break;
		case 3:// ����(����Ͳ���Ҳ�࣬�޸���Ͳ����Ϊ���󼴿�)
				// 1�������м�ľ���
			g.fill3DRect(x - 10, y - 5, 20, 10, false);
			// 2����������ľ���
			g.fill3DRect(x - 15, y - 10, 30, 5, false);
			// 3����������ľ���
			g.fill3DRect(x - 15, y + 5, 30, 5, false);
			// 4���Ȼ����м����Ͳ
			// g.drawLine(x, y, x - 15, y);
			g.fillRect(x - 15, y - 1, 16, 2);
			// 5�������м����̨
			g.fillOval(x - 5, y - 5, 10, 10);
			break;
		}
	}

	// �ж��Ƿ��������ɴ�Խ���ϰ���
	public boolean isTouchBarrier() {

		// ���жϸ�̹�˵ķ���
		switch (this.direct) {
		case 0:
			// �����ȡ���ϰ������֮���ж�
			// �ж�̹���Ƿ�����ǽ��ײ
			for (int i = 0; i < br.soilwalls.size(); i++) {
				// ȡ��һ����ǽ������֮�Ƚ�
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

			// �ж�̹���Ƿ�����ǽ��ײ
			for (int i = 0; i < br.ironwalls.size(); i++) {
				// ȡ��һ����ǽ���󣬲���֮�Ƚ�
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

			// �ж�̹���Ƿ���ײˮ�Ӷ���
			for (int i = 0; i < br.waters.size(); i++) {
				// ȡ��ÿһ��ˮ�Ӷ��󣬲���֮�Ƚ�
				Barriers.Water wt = br.waters.get(i);
				if (this.x - 10 >= wt.x && this.x - 10 <= wt.x + wt.width && this.y - 15 >= wt.y
						&& this.y - 15 <= wt.y + wt.height)
					return true;
				if (this.x + 10 >= wt.x && this.x + 10 <= wt.x + wt.width && this.y - 15 >= wt.y
						&& this.y - 15 <= wt.y + wt.height)
					return true;
			}
			break;

		// ̹������
		case 1:
			// �����ȡ���ϰ������֮���ж�
			// �ж�̹���Ƿ�����ǽ��ײ
			for (int i = 0; i < br.soilwalls.size(); i++) {
				// ȡ��һ����ǽ������֮�Ƚ�
				Barriers.SoilWall sw = br.soilwalls.get(i);
				// �ж��Ƿ������ϰ���
				if (this.x + 15 >= sw.x && this.x + 15 <= sw.x + sw.width && this.y - 10 >= sw.y
						&& this.y - 10 < sw.y + sw.height)
					return true;
				if (this.x + 15 >= sw.x && this.x + 15 <= sw.x + sw.width && this.y + 10 >= sw.y
						&& this.y + 10 < sw.y + sw.height)
					return true;
			}

			// �ж�̹���Ƿ�����ǽ��ײ
			for (int i = 0; i < br.ironwalls.size(); i++) {
				// ȡ��һ����ǽ���󣬲���֮�Ƚ�
				Barriers.IronWall rw = br.ironwalls.get(i);
				if (this.x + 15 >= rw.x && this.x + 15 <= rw.x + rw.width && this.y - 10 >= rw.y
						&& this.y - 10 < rw.y + rw.height)
					return true;
				if (this.x + 15 >= rw.x && this.x + 15 <= rw.x + rw.width && this.y + 10 >= rw.y
						&& this.y + 10 < rw.y + rw.height)
					return true;
			}

			// �ж�̹���Ƿ���ײˮ�Ӷ���
			for (int i = 0; i < br.waters.size(); i++) {
				// ȡ��ÿһ��ˮ�Ӷ��󣬲���֮�Ƚ�
				Barriers.Water wt = br.waters.get(i);
				if (this.x + 15 >= wt.x && this.x + 15 <= wt.x + wt.width && this.y - 10 >= wt.y
						&& this.y - 10 < wt.y + wt.height)
					return true;
				if (this.x + 15 >= wt.x && this.x + 15 <= wt.x + wt.width && this.y + 10 >= wt.y
						&& this.y + 10 <= wt.y + wt.height)
					return true;
			}
			break;

		// ̹������
		case 2:
			// �����ȡ���ϰ������֮���ж�
			// �ж�̹���Ƿ�����ǽ��ײ
			for (int i = 0; i < br.soilwalls.size(); i++) {
				// ȡ��һ����ǽ������֮�Ƚ�
				Barriers.SoilWall sw = br.soilwalls.get(i);
				// �ж��Ƿ������ϰ���
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
			// �ж�̹���Ƿ�����ǽ��ײ
			for (int i = 0; i < br.ironwalls.size(); i++) {
				// ȡ��һ����ǽ���󣬲���֮�Ƚ�
				Barriers.IronWall rw = br.ironwalls.get(i);
				if (this.x - 10 >= rw.x && this.x - 10 <= rw.x + 10 && this.y + 15 >= rw.y && this.y + 15 < rw.y + 10)
					return true;
				if (this.x + 10 >= rw.x && this.x + 10 <= rw.x + 10 && this.y + 15 >= rw.y && this.y + 15 < rw.y + 10)
					return true;
				if (this.x >= rw.x && this.x <= rw.x + rw.width && this.y + 15 >= rw.y
						&& this.y + 15 <= rw.y + rw.height)
					return true;
			}

			// �ж�̹���Ƿ���ײˮ�Ӷ���
			for (int i = 0; i < br.waters.size(); i++) {
				// ȡ��ÿһ��ˮ�Ӷ��󣬲���֮�Ƚ�
				Barriers.Water wt = br.waters.get(i);
				if (this.x - 10 >= wt.x && this.x - 10 <= wt.x + 20 && this.y + 15 >= wt.y && this.y + 15 < wt.y + 20)
					return true;
				if (this.x + 10 >= wt.x && this.x + 10 <= wt.x + 20 && this.y + 15 >= wt.y && this.y + 15 < wt.y + 20)
					return true;
			}
			break;
		// ̹������
		case 3:
			// �����ȡ���ϰ������֮���ж�
			// �ж�̹���Ƿ�����ǽ��ײ
			for (int i = 0; i < br.soilwalls.size(); i++) {
				// ȡ��һ����ǽ������֮�Ƚ�
				Barriers.SoilWall sw = br.soilwalls.get(i);
				// �ж��Ƿ������ϰ���
				if (this.x - 15 >= sw.x && this.x - 15 <= sw.x + 10 && this.y - 10 >= sw.y && this.y - 10 <= sw.y + 20)
					return true;
				if (this.x - 15 >= sw.x && this.x - 15 <= sw.x + 10 && this.y + 10 >= sw.y && this.y + 10 <= sw.y + 20)
					return true;
			}
			// �ж�̹���Ƿ�����ǽ��ײ
			for (int i = 0; i < br.ironwalls.size(); i++) {
				// ȡ��һ����ǽ���󣬲���֮�Ƚ�
				Barriers.IronWall rw = br.ironwalls.get(i);
				if (this.x - 15 >= rw.x && this.x - 15 <= rw.x + 10 && this.y - 10 >= rw.y && this.y - 10 <= rw.y + 10)
					return true;
				if (this.x - 15 >= rw.x && this.x - 15 <= rw.x + 10 && this.y + 10 >= rw.y && this.y + 10 <= rw.y + 10)
					return true;
			}

			// �ж�̹���Ƿ���ײˮ�Ӷ���
			for (int i = 0; i < br.waters.size(); i++) {
				// ȡ��ÿһ��ˮ�Ӷ��󣬲���֮�Ƚ�
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

	// �ж��Ƿ���������̹��(�������̹�˺͵���̹��)
	public boolean isTouchOtherTank() {
		switch (this.direct) {
		case 0:
			// ��̹������
			// ȡ�����еĵ���̹��
			for (int i = 0; i < ets.size(); i++) {
				// ȡ��һ��̹��
				EnemyTank et = ets.get(i);
				// �����̹�˲���ȡ�����ĵ���̹��
				if (et != this) {
					// �������̹�˵ķ��������ϻ�������
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
			// ��̹������
			// ȡ�����еĵ���̹��
			for (int i = 0; i < ets.size(); i++) {
				// ȡ��һ��̹��
				EnemyTank et = ets.get(i);
				// �����̹�˲���ȡ�����ĵ���̹��
				if (et != this) {
					// �������̹�˵ķ��������ϻ�������
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

			// �ж��Ƿ����ҵ�̹�˷�����ײ
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
			// ��̹������
			// ȡ�����еĵ���̹��
			for (int i = 0; i < ets.size(); i++) {
				// ȡ��һ��̹��
				EnemyTank et = ets.get(i);
				// �����̹�˲���ȡ�����ĵ���̹��
				if (et != this) {
					// �������̹�˵ķ��������ϻ�������
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
			// ��̹������
			// ȡ��ÿһ������̹��
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

			// �ж��Ƿ����ҵ�̹����ײ
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

// �ҵ�̹��
class Player extends Tank {

	// ̹�˵��ڵ�
	Shot s = null;

	// ���̹�˵�����������Ĭ��Ϊ3����
	int life = 3;

	public Player(int x, int y) {
		super(x, y); // ���ø���Ĺ��췽�����г�ʼ��
		// �������̹�˵��ٶ�
		this.speed = 4;
		this.type = 1;
	}

	// ����
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
		// �����ڵ��߳�
		Thread t = new Thread(s);
		t.start();
	}

	// ̹�������ƶ�
	public void moveUp() {
		y -= speed;
	}

	// ̹�������ƶ�
	public void moveRight() {
		x += speed;
	}

	// ̹�������ƶ�
	public void moveDown() {
		y += speed;
	}

	// ̹�������ƶ�
	public void moveLeft() {
		x -= speed;
	}
}

// ����̹��
class EnemyTank extends Tank implements Runnable {

	// ��������ӵ���Ӧ���ڸոմ���̹��ʱ�͸���һ���ӵ�
	int times = 0;

	public EnemyTank(int x, int y) {
		super(x, y);
		this.type = 0;
	}

	// ����̹�˵��ƶ�����
	public void Move() {
		switch (this.direct) {
		case 0:
			// ˵��̹����������
			for (int i = 0; i < 30; i++) {
				if (y - 15 < 0 || this.isTouchOtherTank() || this.isTouchBarrier())
					break;
				if (!this.isPause) {// ̹������ƽ���ƶ�ʱ����Ҫʱ���ж���Ϸ�Ƿ���ͣ��
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
			// ����
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
			// ����
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
			// ����
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

	// Ϊ����̹������ӵ�
	public void addShot() {
		this.times++;
		if (times % 2 == 0) {
			if (isLive) {
				if (ss.size() < 2) {
					Shot s = null;
					// û���ӵ������
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

					// �õ���Ϸ�߽����Ϣ
					s.getWidth_height(width, height);
					// �����ӵ��߳�
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

			this.Move();// ̹���ƶ�

			if (this.isPause) // �ı�̹�˷����������ӵ�ʱ���ж���Ϸ�Ƿ���ͣ
				continue;

			this.addShot();// Ϊ̹������ӵ�

			// ��̹���������һ���µķ���
			this.direct = (int) (Math.random() * 4);

			// �жϵ���̹���Ƿ�����
			if (this.isLive == false) {
				// ��̹���������˳��߳�
				// Ӧͬʱremove����̹�˵������ӵ�
				break;
			}

		}
	}
}
