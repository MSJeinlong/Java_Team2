package game.tank.ui;

import game.tank.entity.Blink;
import game.tank.entity.Boom;
import game.tank.entity.Cell;
import game.tank.ImageManager;
import game.tank.MapManager;
import game.tank.Scene;
import game.tank.entity.Tank;
import game.tank.util.Consts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TankPanel extends JPanel {
	
	public static final int WIDTH = 515; 
	public static final int HEIGHT = 514;
	private static int remainLives ;
    private ImageManager imageManager ; 
    private MapManager mapManager;
    private Scene scene;
    private int round ;
    //show game result: victory/game over
    private JLabel label;  
    private JLabel text;
    private JLabel roundflag;
    private JLabel roundtext;
    private JLabel hptext; 
    private static JLabel hpIcon;
    //start/continue game
    private JButton btn;
    private JLabel tankIcon;
    private TankPanel self;
    private JFrame frame;
    
	public TankPanel(){
		round = 1;
		self = this;
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		this.setLayout(null);
		init();		
	}
	
	public void init(){
		imageManager = ImageManager.getInstance();
		mapManager = MapManager.getInstance();
		mapManager.setPanel(this);
		mapManager.start();	
		scene = mapManager.getScene(1);
		remainLives = scene.getMainTank().getHp();
		initComponent();
		new Thread(){
			public void run(){
				while(true){
					try{
						Thread.sleep(10);
					}catch(Exception e){
						e.printStackTrace();
					}
					repaint();
				}
			}
		}.start();
	}
	
	private void initComponent(){
	
		label = new JLabel();	
		label.setBounds(new Rectangle(200,400,150,50));
		label.setOpaque(false);
		label.setText("");
		this.add(label);
		
		text = new JLabel();
		Font font = new Font("Dialog",1,20);	
		text.setFont(font);
		text.setForeground(Color.ORANGE);
		text.setBounds(new Rectangle(100,100,400,150));
		text.setOpaque(false);
		text.setText("<html>如发现bug,或有建议,请联系我：<br/>363811405@qq.com</html>");
		text.setVisible(false);
		this.add(text);
		
		int width = ImageManager.getInstance().getRoundflag().getWidth(null);
		int height = ImageManager.getInstance().getRoundflag().getHeight(null);
		roundflag = new JLabel();
		roundflag.setBounds(new Rectangle(464,416,width,height));		
		ImageIcon icon = new ImageIcon(getClass().getResource("../image/flag.png"));
		roundflag.setIcon(icon);
		this.add(roundflag);
		
		roundtext = new JLabel();
		roundtext.setBounds(new Rectangle(464,432,50,50));	
		roundtext.setOpaque(false);
		roundtext.setForeground(Color.BLACK);
		font = new Font("Dialog",1,20);
		roundtext.setFont(font);
		roundtext.setText(String.valueOf(round));
		this.add(roundtext);
		
		hptext = new JLabel();
		hptext.setBounds(new Rectangle(464,304,50,32));	
		hptext.setOpaque(false);
		hptext.setForeground(Color.BLACK);
		hptext.setFont(font);
		hptext.setText("HP");
		this.add(hptext);
		
		hpIcon = new JLabel();
		hpIcon.setBounds(new Rectangle(464,336,50,32));	
		hpIcon.setOpaque(false);
		hpIcon.setForeground(Color.BLACK);
		hpIcon.setFont(font);
		hpIcon.setText(String.valueOf(remainLives));
		ImageIcon selfIcon = new ImageIcon(getClass().getResource("../image/self_icon.png"));
		hpIcon.setIcon(selfIcon);
		this.add(hpIcon);
		
		btn = new JButton();
		btn.setBounds(new Rectangle(352,406,79,41));
		ImageIcon btnIcon = new ImageIcon(getClass().getResource("../image/button.png"));
		btn.setIcon(btnIcon);
		btn.setVisible(false);
		btn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mapManager.resumeGame();
				btn.setVisible(false);
				tankIcon.setVisible(false);
				text.setVisible(false);
				label.setVisible(false);
				text.setVisible(false);
				roundflag.setVisible(true);
				roundflag.setVisible(true);
				hptext.setVisible(true);
				hpIcon.setVisible(true);
				hpIcon.setVisible(true);
				frame.setFocusable(true);
			}
		});
		this.add(btn);
	
		tankIcon = new JLabel();
		tankIcon.setBounds(new Rectangle(310,412,32,32));
		icon = new ImageIcon(getClass().getResource("../image/myTank_r.png"));
		tankIcon.setIcon(icon);
		tankIcon.setVisible(false);
		this.add(tankIcon);

	}
	
	public void hideComponent(){
		label.setVisible(false);
		text.setVisible(false);
		roundflag.setVisible(false);
		roundflag.setVisible(false);
		hptext.setVisible(false);
		hpIcon.setVisible(false);
		hpIcon.setVisible(false);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
        drawMap(g);
        Graphics2D g2d = (Graphics2D) g;  
        for(int i=0; i<scene.getTankList().size();i++) {
        	Tank t = scene.getTankList().get(i);
        	if(t.isAlive() == true){
        		t.drawSelf(g2d);
        	}
        	if(t.getBullet()!=null) {
        		t.getBullet().drawSelf(g2d);
        	}
        }
        
        
        for(int i=0; i<scene.getBooms().size();i++){
        	Boom b = scene.getBooms().get(i);
        	drawObjects(g,b);
        }
        
        
        //draw mini tank icon 
        int x = 464;
        int y = 0;
        int n = 0;
        for(int i=1; i-1<scene.getWaitedTanks().size();i++){
        	n = (i-1)/2;
        	y = 48 + n*16;
        	if(i%2==0){
        		x = 480;
        	}else{
        		x = 464;
        	}
        	Cell c = new Cell(x,y,Consts.ELEMENT_TYPE_MINITANK);
        	drawObjects(g,c);
        }
        
       
        for(int i =0 ;i<scene.getBlinkList().size();i++){
        	Blink blink = scene.getBlinkList().get(i);
        	drawObjects(g,blink);
        }
	}
	
	private void drawMap(Graphics g){
		 int[][] map = scene.getMap();
		 Image img = null;
		 int type = 0;
		 for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
            	type = map[x][y];
            	img = imageManager.getImageByType(type);
            	g.drawImage(img, y * Consts.CS, x * Consts.CS, this);
            }
		 }
	}
	
	private void drawObjects(Graphics g,Cell c){
		g.drawImage(c.getImg(), c.getHorizon(), c.getVertical(), this);	
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public MapManager getMapManager() {
		return mapManager;
	}

	public void setMapManager(MapManager mapManager) {
		this.mapManager = mapManager;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public JLabel getText() {
		return text;
	}

	public void setText(JLabel text) {
		this.text = text;
	}

	public static void decreaseLives() {
		TankPanel.remainLives--;
		hpIcon.setText(String.valueOf(remainLives));
	}

	public JButton getBtn() {
		return btn;
	}

	public void setBtn(JButton btn) {
		this.btn = btn;
	}

	public JLabel getTankIcon() {
		return tankIcon;
	}

	public void setTankIcon(JLabel tankIcon) {
		this.tankIcon = tankIcon;
	}

	public static void setRemainLives(int remainLives) {
		TankPanel.remainLives = remainLives;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public static int getRemainLives() {
		return remainLives;
	}

	public static void setHpIcon(JLabel hpIcon) {
		TankPanel.hpIcon = hpIcon;
	}

	public static JLabel getHpIcon() {
		return hpIcon;
	}

	public JLabel getRoundtext() {
		return roundtext;
	}

	public void setRoundtext(JLabel roundtext) {
		this.roundtext = roundtext;
	}

	
}
