package com.game.TankGame;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

// ��ը��
class Bomb {
	// ���屬ը������
	int x, y;
	// ը��������
	int life = 12;
	boolean isLive = true;

	public Bomb(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// ��������ֵ
	public void lifeDown() {
		if (life > 0) {
			life--;
		} else {
			this.isLive = false;
		}
	}
}

// ������������
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

// �ڵ���
class Shot implements Runnable {
	int x; // �ڵ�������
	int y;
	int direct; // �ڵ��ķ���
	int speed = 9; // �ڵ��ٶ�
	boolean isLive = true;
	boolean isPause = false;
	// ��Ϸ����Ĵ�С�������ж��Ƿ������˱߽�
	int width;
	int height;

	public Shot(int x, int y, int direct) {
		this.x = x;
		this.y = y;
		this.direct = direct;
	}

	// �õ���Ϸ����ͣ״̬
	public void getPause(boolean pause) {
		this.isPause = pause;
	}

	// �õ���Ϸ�߽�Ĵ�С
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

			// �ڵ����ƶ�
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
			// �ڵ���ʱ��ʧ
			//

			// �ж��ڵ��Ƿ�������Ե
			if (x < 0 || x > width || y < 0 || y > height) {
				// System.out.println("�ڵ����� x=" + x + ", y=" + y);
				this.isLive = false;
				break;
			}
		}
	}
}
