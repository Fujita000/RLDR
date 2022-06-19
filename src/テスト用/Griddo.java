package テスト用;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Griddo extends JFrame implements Runnable{

	private JComponent label;
	String url = "images\\";
	
	
	Image img = Toolkit.getDefaultToolkit().getImage(url+"effect.png");
	int grid = 16;
	static int effect_chip[][]= {
			{160,0, 191,31},
			{160,32, 191,63},
			{160,64, 191,95},
			{160,96, 191,127},
	};
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		JFrame win = new Griddo();
		win.setBounds(10, 10, 524, 548);
		win.setVisible(true);
	}
	public Griddo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		label = new Label();
		getContentPane().add(label);
		new Thread(this).start();
	}
	int q = -1;
	class Label extends JComponent {
		public void paintComponent(Graphics g) {
			//g.drawImage(img, 0, 0, this);
			for(int y = 0;y < 548;y+=grid) {
				g.fillRect(0, y, 524, 1);
			}
			for(int x = 3;x < 524;x+=grid) {
				g.fillRect(x, 0, 1, 548);
			}
			g.drawImage(img, 0,0,32,32, 32*q, 0, 32*q+31, 31, this);
		}
	}

	@Override
	public void run() {
		while(true) {
			// TODO 自動生成されたメソッド・スタブ
			try {
				Thread.sleep(100);
				q++;
			} catch (InterruptedException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			if(q > 4)q=0;
			System.out.println(q);
			repaint();
		}
	}
}
