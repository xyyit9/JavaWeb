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
 * ��������̰���ߵĻ����
 * @author bjsxt
 * @version 1.0
 */
public class Yard extends Frame {

	PaintThread paintThread = new PaintThread();
	private boolean gameOver = false; //��Ϸ�Ƿ����
	
	/**
	 * ����
	 */
	// final ���ε�(��Ա)�����ǳ���,Java����淶����ȫ����д,�����Ķ���һ����֪���Ǹ�������
	public static final int ROWS = 30;
	public static final int COLS = 30;
	public static final int BLOCK_SIZE = 15;
	
	//Font(String name, int style, int size) ����ָ�����ơ���ʽ�Ͱ�ֵ��С������һ���� Font��
	private Font fontGameOver = new Font("����", Font.BOLD, 50);
	
	private int score = 0;
	
	Snake s = new Snake(this);
	Egg e = new Egg();
	
	//������ Image �Ǳ�ʾͼ��ͼ���������ĳ��ࡣ�������ض���ƽ̨�ķ�ʽ��ȡͼ��
	Image offScreenImage = null;
	
	public void launch() {
		//�� Component��public void setLocation(int x,int y)������Ƶ���λ�á�ͨ���������������ռ��е� x �� y ������ָ����λ�õ����Ͻǡ� 
		this.setLocation(200, 200);
		//�� Component��public void setSize(int width,int height)��������Ĵ�С��ʹ����Ϊ width���߶�Ϊ height��
		this.setSize(COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
		/* ��window��public void addWindowListener(WindowListener l)���ָ���Ĵ���״̬���������ԴӴ˴��ڽ��մ����¼������ l Ϊ null�����׳��κ��쳣���Ҳ�ִ���κβ�����
		windowAdapterʵ����WindowListener�ӿڡ�
		*/
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		//�� Component��setVisible(boolean b) ���ݲ��� b ��ֵ��ʾ�����ش������
		this.setVisible(true);
		//��һ�������ָ���İ������������Խ��շ��Դ�����İ����¼���
		this.addKeyListener(new KeyMonitor());
		//�����߳�
		new Thread(paintThread).start();
	}
	
	public static void main(String[] args) {
		new Yard().launch();
	}
	
	public void stop() {
		//�൱���������������أ���stop��ʱ��һ�����عرգ���ʾ������Ϸ�����������������ҹرյڶ�������pause
		//���رյڶ����ص�ʱ�򣬲�����ֹͣpaintThread�߳�
		gameOver = true;
	}
	
	//frame���е�paint(Graphics g)������snake��eggû�м̳�frame�ӿڣ�����û��paint�������õ����Զ����draw
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.GRAY);
		//�����������
		g.fillRect(0, 0, COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
		g.setColor(Color.DARK_GRAY);
		//��������
		for(int i=1; i<ROWS; i++) {
			g.drawLine(0, BLOCK_SIZE * i, COLS * BLOCK_SIZE, BLOCK_SIZE * i);
		}
		//��������
		for(int i=1; i<COLS; i++) {
			g.drawLine(BLOCK_SIZE * i, 0, BLOCK_SIZE * i, BLOCK_SIZE * ROWS);
		}
		
		g.setColor(Color.YELLOW);
		g.drawString("score:" + score, 10, 60);
		
		if(gameOver) {
			g.setFont(fontGameOver);
			g.drawString("��Ϸ����", 120, 180);
			//����ʾ������Ϸ�����������Ժ���ֹͣpaintThread�߳�
			paintThread.pause();
		}
		
		g.setColor(c);
		
		//�ж��Ƿ�Ե���
		s.eat(e);
		//������
		e.draw(g);
		//������
		s.draw(g);
		
		
	}
	
	@Override
	public void update(Graphics g) {
		//offScreenImage���ڴ��з���һ��ͼƬ���������Ĵ�С����Ϸ����һ�������ڴ��еĿռ������ǿ������ģ�
		if(offScreenImage == null) {
			offScreenImage = this.createImage(COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
		}
       //����gOff=offScreenImage.getGraphics()ȥ��Ҫ���ƵĶ��󲢴�ŵ�����õ��ڴ�ռ��У�
		Graphics gOff = offScreenImage.getGraphics();
	   //����paint(gOffScreen)����ȫ�����ƴ��ڴ�֮��
		paint(gOff);
	   //��offScreenImageȫ��һ���ԵĻ��Ƶ����ŵĶ�������
		//drawImage(Image img, int x, int y, ImageObserver observer) ����ָ��ͼ���е�ǰ���õ�ͼ��
		g.drawImage(offScreenImage, 0, 0,  null);
	}
	
	//Ҫ���߿����ƶ�������Ҫдһ���߳��࣬����repaint������ÿ��100�������һ��repaint
	private class PaintThread implements Runnable {
		//running���������ã����û��������F2�������¿�ʼ��Ϸ��
		private boolean running = true;
		private boolean pause = false;
		public void run() {
			//���pause��true������ѭ��
			while(running) {
				//��ʱpause��Ϊtrue���������ǰѭ����������һ��ѭ����ֱ��ĳһ��pause��Ϊfalse
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
	

	
	//�ڶ�������Yard�����ü��̼������ſ����ƶ�����������Ĺ��̽���Snakeȥ����
	private class KeyMonitor extends KeyAdapter {

		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			//����Ϸ������ʱ�򣬰�F2���¿�ʼ
			if(key == KeyEvent.VK_F2) {
				paintThread.reStart();
			}
			s.keyPressed(e);
		}
		
	}
	
	/**
	 * �õ����õķ���
	 * @return ����
	 */
	
	public int getScore() {
		return score;
	}
	
	/**
	 * �������õķ���
	 * @param score ����
	 */
	
	public void setScore(int score) {
		this.score = score;
	}

}
