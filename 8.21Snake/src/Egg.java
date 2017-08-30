import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * 代表蛋
 * @author Administrator
 *
 */
public class Egg {
	int row, col;
	int w = Yard.BLOCK_SIZE;
	int h = Yard.BLOCK_SIZE;
	private static Random r = new Random();
	private Color color = Color.GREEN;

	public Egg(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public Egg() {
		// nextInt(int n) 返回一个伪随机数，它是取自此随机数生成器序列的、在 0（包括）和指定值（不包括）之间均匀分布的 int 值。
		//this调用另一个构造方法。-2再+2是为了避免出现在最上方的条框内		
		this(r.nextInt(Yard.ROWS-2) + 2, r.nextInt(Yard.COLS));
	}
	
	//重新出现在新的位置上
	public void reAppear() {
		this.row = r.nextInt(Yard.ROWS-2) + 2;
		this.col = r.nextInt(Yard.COLS);
	}
	
	public Rectangle getRect() {
		return new Rectangle(Yard.BLOCK_SIZE * col, Yard.BLOCK_SIZE * row, w, h);
	}
	
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(color);
		g.fillOval(Yard.BLOCK_SIZE * col, Yard.BLOCK_SIZE * row, w, h);
		g.setColor(c);
		if(color == Color.GREEN) color = Color.RED;
		else color = Color.GREEN;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}
	
}
