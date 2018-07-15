package com.tools;
/**
 * 
 * @author JunLong
 *	这个一个可以动态加载一个图片做背景的JPanel
 */
import javax.swing.*;
import javax.imageio.*;
import java.awt.*;

public class ImagePanel extends JPanel {
	Image im;
	
	//构造方法指的该Panel的大小
	public ImagePanel(Image im) {
		
		this.im = im;
		//希望ImagePanel大小自适应
		int w = Toolkit.getDefaultToolkit().getScreenSize().width;
		int h = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setSize(w, h);
	}
	
	//画出背景
	public void paintComponent(Graphics g) {
		//清屏
		super.paintComponent(g);
		g.drawImage(im, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}
