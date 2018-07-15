package com.gui.dialog;

import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JLabel;

import com.game.TankGame.MyTools;
import com.service.IGradeService;
import com.service.impl.GradeServiceImpl;

/**
 * 
 * @author JunLong
 *	关于成绩的弹出式对话框
 */
public class aboutGrade extends JDialog{

	//定义组件
	private JLabel jlb1, jlb2, jlb3, jlb4, jlb5, jlb6;
	private float avgPoint;
	private float avgGrade;
	private int maxGrade;
	private int minGrade;
	private int makeUpNum;
	private int retakeNum;
	private IGradeService igs;
	
	public aboutGrade() {
		igs = new GradeServiceImpl();
		this.avgPoint = igs.getAvgPoint();
		this.avgGrade = igs.getAvgGrade();
		this.maxGrade = igs.getMaxGrade();
		this.minGrade = igs.getMinGrade();
		this.makeUpNum = igs.getMakeUpCount();
		this.retakeNum = igs.getRetakeCount();
		jlb1 = new JLabel("平均成绩："+avgGrade);
		jlb2 = new JLabel("平均绩点："+avgPoint);
		jlb3 = new JLabel("最高分："+maxGrade);
		jlb4 = new JLabel("最低分："+minGrade);
		jlb5 = new JLabel("补考科目数："+makeUpNum);
		jlb6 = new JLabel("重修科目数："+retakeNum);
		//设置字体
		jlb1.setFont(MyTools.f3);
		jlb2.setFont(MyTools.f3);
		jlb3.setFont(MyTools.f3);
		jlb4.setFont(MyTools.f3);
		jlb5.setFont(MyTools.f3);
		jlb6.setFont(MyTools.f3);
		
		//设置布局
		this.setLayout(new GridLayout(6, 1));
		
		//添加组件
		this.add(jlb1);
		this.add(jlb2);
		this.add(jlb3);
		this.add(jlb4);
		this.add(jlb5);
		this.add(jlb6);
		
		this.setTitle("关于成绩");
		Image image = null;
		try {
			image = ImageIO.read(new File("image/client/GradeInfo/aboutGrade.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setIconImage(image);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setBounds(800, 400, 200, 300);
	}
}
