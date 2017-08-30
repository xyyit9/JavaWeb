import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 这个类代表贪吃蛇的活动场所
 * @author bjsxt
 * @version 1.0
 */
public class Yard extends Frame {

	PaintThread paintThread = new PaintThread();
	private boolean gameOver = false; //游戏是否结束
	
	/**
	 * 行数
	 */
	// final 修饰的(成员)变量是常量,Java编码规范建议全部大写,方便阅读。一看即知道是个常量。
	public static final int ROWS = 30;
	public static final int COLS = 30;
	public static final int BLOCK_SIZE = 15;
	
	//Font(String name, int style, int size) 根据指定名称、样式和磅值大小，创建一个新 Font。
	private Font fontGameOver = new Font("宋体", Font.BOLD, 50);
	
	private int score = 0;
	
	Snake s = new Snake(this);
	Egg e = new Egg();
	
	//抽象类 Image 是表示图形图像的所有类的超类。必须以特定于平台的方式获取图像。
	Image offScreenImage = null;
	
	public void launch() {
		//类 Component的public void setLocation(int x,int y)将组件移到新位置。通过此组件父级坐标空间中的 x 和 y 参数来指定新位置的左上角。 
		this.setLocation(200, 200);
		//类 Component的public void setSize(int width,int height)调整组件的大小，使其宽度为 width，高度为 height。
		this.setSize(COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
		/* 类window的public void addWindowListener(WindowListener l)添加指定的窗口状态侦听器，以从此窗口接收窗口事件。如果 l 为 null，则不抛出任何异常，且不执行任何操作。
		windowAdapter实现了WindowListener接口。
		*/
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		//类 Component的setVisible(boolean b) 根据参数 b 的值显示或隐藏此组件。
		this.setVisible(true);
		//第一步：添加指定的按键监听器，以接收发自此组件的按键事件。
		this.addKeyListener(new KeyMonitor());
		//启动线程
		new Thread(paintThread).start();
	}
	
	public static void main(String[] args) {
		new Yard().launch();
	}
	
	public void stop() {
		//相当于设置了两个开关，当stop的时候一个开关关闭，显示出“游戏结束”的字样，并且关闭第二个开关pause
		//当关闭第二开关的时候，才真正停止paintThread线程
		gameOver = true;
	}
	
	//frame类中的paint(Graphics g)方法，snake和egg没有继承frame接口，所以没有paint方法，用的是自定义的draw
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.GRAY);
		//填充整个画面
		g.fillRect(0, 0, COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
		g.setColor(Color.DARK_GRAY);
		//画出横线
		for(int i=1; i<ROWS; i++) {
			g.drawLine(0, BLOCK_SIZE * i, COLS * BLOCK_SIZE, BLOCK_SIZE * i);
		}
		//画出竖线
		for(int i=1; i<COLS; i++) {
			g.drawLine(BLOCK_SIZE * i, 0, BLOCK_SIZE * i, BLOCK_SIZE * ROWS);
		}
		
		g.setColor(Color.YELLOW);
		g.drawString("score:" + score, 10, 60);
		
		if(gameOver) {
			g.setFont(fontGameOver);
			g.drawString("游戏结束", 120, 180);
			//在显示出“游戏结束”字样以后，再停止paintThread线程
			paintThread.pause();
		}
		
		g.setColor(c);
		
		//判断是否吃到蛋
		s.eat(e);
		//画出蛇
		e.draw(g);
		//画出蛋
		s.draw(g);
		
		
	}
	
	@Override
	public void update(Graphics g) {
		//offScreenImage在内存中分配一个图片和设置它的大小和游戏窗口一样（在内存中的空间我门是看不到的）
		if(offScreenImage == null) {
			offScreenImage = this.createImage(COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
		}
       //对象gOff=offScreenImage.getGraphics()去把要绘制的对象并存放到分配好的内存空间中．
		Graphics gOff = offScreenImage.getGraphics();
	   //利用paint(gOffScreen)将其全部绘制带内存之中
		paint(gOff);
	   //将offScreenImage全部一次性的绘制到我门的动画窗口
		//drawImage(Image img, int x, int y, ImageObserver observer) 绘制指定图像中当前可用的图像。
		g.drawImage(offScreenImage, 0, 0,  null);
	}
	
	//要想蛇可以移动，首先要写一个线程类，调用repaint方法，每隔100毫秒进行一次repaint
	private class PaintThread implements Runnable {
		//running变量的作用：如果没有它，按F2不会重新开始游戏。
		private boolean running = true;
		private boolean pause = false;
		public void run() {
			//如果pause是true则跳出循环
			while(running) {
				//此时pause若为true则会跳出当前循环，进行下一次循环，直到某一次pause成为false
				if(pause) continue; 
				else repaint();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		public void pause() {
			this.pause = true;
		}
		
		public void reStart() {
			this.pause = false;
			s = new Snake(Yard.this);
			gameOver = false;
		}
		
	}
	

	
	//第二步：在Yard中设置键盘监听，才可以移动起来，具体的过程交给Snake去处理。
	private class KeyMonitor extends KeyAdapter {

		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			//当游戏结束的时候，按F2重新开始
			if(key == KeyEvent.VK_F2) {
				paintThread.reStart();
			}
			s.keyPressed(e);
		}
		
	}
	
	/**
	 * 拿到所得的分数
	 * @return 分数
	 */
	
	public int getScore() {
		return score;
	}
	
	/**
	 * 设置所得的分数
	 * @param score 分数
	 */
	
	public void setScore(int score) {
		this.score = score;
	}

}
