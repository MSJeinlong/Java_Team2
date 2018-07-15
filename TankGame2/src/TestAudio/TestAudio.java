package TestAudio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class TestAudio {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 1、创建一个PlayVoice的实例
		PlayVoice pv = new PlayVoice("F:\\JavaProject_Image\\TankGame\\5170.wav");
		pv.start();
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
		byte[] abData = new byte[512];

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
