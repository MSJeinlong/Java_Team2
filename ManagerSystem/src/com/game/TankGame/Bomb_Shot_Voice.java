package com.game.TankGame;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

// 爆炸类
class Bomb {
	// 定义爆炸的坐标
	int x, y;
	// 炸弹的生命
	int life = 12;
	boolean isLive = true;

	public Bomb(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// 减少生命值
	public void lifeDown() {
		if (life > 0) {
			life--;
		} else {
			this.isLive = false;
		}
	}
}

// 播放声音的类
class PlayVoice extends Thread {
	private String filename;

	public PlayVoice(String wavfile) {
		filename = wavfile;
	}

	public void run() {
		File voicefile = new File(filename);

		AudioInputStream audiois = null;
		try {
			audiois = AudioSystem.getAudioInputStream(voicefile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}

		AudioFormat format = audiois.getFormat();
		SourceDataLine auline = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

		try {
			auline = (SourceDataLine) AudioSystem.getLine(info);
			auline.open();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}

		auline.start();
		int n = 0;
		byte[] abData = new byte[1024];

		try {
			while (n != -1) {
				n = audiois.read(abData, 0, abData.length);
				if (n > 0)
					auline.write(abData, 0, n);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			auline.drain();
			auline.close();
		}
	}
}

// 炮弹类
class Shot implements Runnable {
	int x; // 炮弹的坐标
	int y;
	int direct; // 炮弹的方向
	int speed = 9; // 炮弹速度
	boolean isLive = true;
	boolean isPause = false;
	// 游戏区域的大小，用于判断是否触碰到了边界
	int width;
	int height;

	public Shot(int x, int y, int direct) {
		this.x = x;
		this.y = y;
		this.direct = direct;
	}

	// 得到游戏的暂停状态
	public void getPause(boolean pause) {
		this.isPause = pause;
	}

	// 得到游戏边界的大小
	public void getWidth_height(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(50);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (this.isPause)
				continue;

			// 炮弹的移动
			switch (direct) {
			case 0:
				y -= speed;
				break;
			case 1:
				x += speed;
				break;
			case 2:
				y += speed;
				break;
			case 3:
				x -= speed;
				break;
			}
			// 炮弹何时消失
			//

			// 判断炮弹是否碰到边缘
			if (x < 0 || x > width || y < 0 || y > height) {
				// System.out.println("炮弹坐标 x=" + x + ", y=" + y);
				this.isLive = false;
				break;
			}
		}
	}
}
