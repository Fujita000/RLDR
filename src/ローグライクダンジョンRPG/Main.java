package ローグライクダンジョンRPG;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Main extends JFrame implements Runnable ,KeyListener, InterFace{

	//enemy
	static int ENEMY_MAX_NUM = 5;
	static int EMN = ENEMY_MAX_NUM;

	static int ex[] = new int[EMN];
	static int ey[] = new int[EMN];
	static int edi[] = new int[EMN];//向き:0.上,1.右,2.下,3.左
	static int emo[] = new int[EMN];
	static int es = 32;

	static int enemy_char_id[] = new int[EMN];

	static boolean eflag[] = new boolean[EMN];

	static String ename[] = new String[EMN];;
	static int elv[] = new int[EMN];;
	static int exp[] = new int[EMN];;
	static int emaxhp[] = new int[EMN];;
	static int ehp[] = new int[EMN];;
	static int emaxmp[] = new int[EMN];;
	static int emp[] = new int[EMN];;

	static int enemy_attack[] = new int[EMN];;
	static int enemy_magic[] = new int[EMN];;
	static int enemy_speed[] = new int[EMN];;
	static int enemy_defense[] = new int[EMN];;
	static int enemy_dex[] = new int[EMN];;
	static int enemy_luc[] = new int[EMN];;

	//item
	int ITEM_MAX_NUM = 10;

	//map
	int MAP_SIZE = 16;
	int MONI_SIZE_X = 21;
	int MONI_SIZE_Y = 14;
	int TIP_SIZE = 32;




	String url = "images\\";

	Image player_img = Toolkit.getDefaultToolkit().getImage(url+"a.png");
	Image map_img = Toolkit.getDefaultToolkit().getImage(url+"b.png");
	Image enemies_img = Toolkit.getDefaultToolkit().getImage(url+"c.png");
	Image effect_img = Toolkit.getDefaultToolkit().getImage(url+"d.png");
	Image skill_img = Toolkit.getDefaultToolkit().getImage(url+"e.png");
	Image item_img = Toolkit.getDefaultToolkit().getImage(url+"f.png");
	Image equip_img = Toolkit.getDefaultToolkit().getImage(url+"g.png");
	Image enemies2_img = Toolkit.getDefaultToolkit().getImage(url+"h.png");
	Image item2_img = Toolkit.getDefaultToolkit().getImage(url+"r.png");

	static int effect_chip[][]= {
			{160,0, 191,31},
			{160,32, 191,63},
			{160,64, 191,95},
			{160,96, 191,127},
	};
	//その他
	int keycode;
	private JComponent label;
	boolean key_flag = false;

	Random r = new Random();

	static char key_char = ' ';

	static int moniter_mode = 0;//0.ダンジョン,1.ステータス・装備,2.アイテム一覧,3.スキル一覧

	int log_max_num = 5;
	String log_box[] = new String[log_max_num];

	int chip[][][] = {//[キャラの向き][キャラの動き][キャラの動きの座標]
			{
				{0,0,23,31},
				{24,0,47,31},
				{48,0,71,31},
			},
			{
				{0,32,23,63},
				{24,32,47,63},
				{48,32,71,63},
			},
			{
				{0,64,23,95},
				{24,64,47,95},
				{48,64,71,95},
			},
			{
				{0,96,23,127},
				{24,96,47,127},
				{48,96,71,127},
			},
	};

	Player p = new Player();
	Enemy e = new Enemy();
	Map map = new Map();
	Skill skill = new Skill();
	Item item = new Item();
	static JFrame win;

	public static void main(String[] args) {
		win = new Main();
		win.setBounds(10, 10, 688, 544+15);
		win.setVisible(true);

	}
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(this);
		label = new LabelEx();
		getContentPane().add(label);
		new Thread(this).start();
	}
	public Main(int a) {

	}
	class LabelEx extends JComponent {
		Graphics map_imager(Graphics g,int ox,int oy) {
			if(map.room_map[p.py][p.px] == 1) {
				for(int y = 0;y < MONI_SIZE_Y;y++) {
					for(int x = 0;x < MONI_SIZE_X;x++) {
						if(player_map_if_9x9(x,y,ox,oy)) {//ここらへん道用の絵
							switch(map.map[y+oy][x+ox]) {
							case 0:
							case 1:
							case 2:
							case 3:
								g.drawImage(map_img, x*TIP_SIZE, y*TIP_SIZE, x*TIP_SIZE+TIP_SIZE, y*TIP_SIZE+TIP_SIZE, 16*1, 16*11, 16*1+15, 16*11+15, this);
							break;
							case 8:
								g.drawImage(map_img, x*TIP_SIZE, y*TIP_SIZE, x*TIP_SIZE+TIP_SIZE, y*TIP_SIZE+TIP_SIZE, 16*21, 16*5, 16*21+15, 16*6+15, this);
							break;
							case 9:
								g.drawImage(map_img, x*TIP_SIZE, y*TIP_SIZE, x*TIP_SIZE+TIP_SIZE, y*TIP_SIZE+TIP_SIZE, 16*23, 16*3, 16*23+15, 16*3+15, this);
							break;
							}
						}else {
							g.setColor(Color.GRAY);
							g.fillRect(x*TIP_SIZE, y*TIP_SIZE,32,32);
						}
					}
				}
			}else {
				for(int y = 0;y < MONI_SIZE_Y;y++) {
					for(int x = 0;x < MONI_SIZE_X;x++) {
						if(map_if_9x9(x,y,ox,oy)){
							switch(map.map[y+oy][x+ox]) {
							case 0:
							case 1:
							case 2:
							case 3:
								g.drawImage(map_img, x*TIP_SIZE, y*TIP_SIZE, x*TIP_SIZE+TIP_SIZE, y*TIP_SIZE+TIP_SIZE, 16*1, 16*11, 16*1+15, 16*11+15, this);
							break;
							case 8:
								g.drawImage(map_img, x*TIP_SIZE, y*TIP_SIZE, x*TIP_SIZE+TIP_SIZE, y*TIP_SIZE+TIP_SIZE, 16*21, 16*5, 16*21+15, 16*6+15, this);
							break;
							case 9:
								g.drawImage(map_img, x*TIP_SIZE, y*TIP_SIZE, x*TIP_SIZE+TIP_SIZE, y*TIP_SIZE+TIP_SIZE, 16*23, 16*3, 16*23+15, 16*3+15, this);
							break;
							}
						}else {
							g.setColor(Color.GRAY);
							g.fillRect(x*TIP_SIZE, y*TIP_SIZE,32,32);
						}
					}
				}
			}
			return g;
		}
		boolean player_map_if_9x9(int x,int y,int ox,int oy) {
			for(int ay = -1;ay < 2;ay++) {
				for(int ax = -1;ax < 2;ax++) {
					if(y+oy+ay < 0 || y+oy+ay >= map.SIZE_Y || x+ox+ax < 0 || x+ox+ax >= map.SIZE_X)return false;
					if(map.player_map[y+oy+ay][x+ox+ax] == 1)return true;
				}
			}
			return false;
		}
		boolean map_if_9x9(int x,int y,int ox,int oy) {
			for(int ay = -1;ay < 2;ay++) {
				for(int ax = -1;ax < 2;ax++) {
				if(map_cloud_imager(x+ax,y+ay,ox,oy))return true;
				}
			}
			return false;
		}
		boolean map_cloud_imager(int x,int y,int ox,int oy) {
			if(y+oy < 0 || y+oy >= map.SIZE_Y || x+ox < 0 || x+ox >= map.SIZE_X)return false;
			if(map.room_map[y+oy][x+ox] == map.room_map[p.py][p.px])return true;
			return false;
		}

		Graphics player_imager(Graphics g,int ox,int oy) {
			for(int y = 0;y < MONI_SIZE_Y;y++) {
				for(int x = 0;x < MONI_SIZE_X;x++) {
					switch(map.player_map[y+oy][x+ox]) {
					case 1:
						g.drawImage(player_img, x*p.ps-4, y*p.ps-16, x*p.ps+32, y*p.ps+32, chip[p.di][p.mo][0], chip[p.di][p.mo][1], chip[p.di][p.mo][2], chip[p.di][p.mo][3], this);
						//player_hp_裏
						g.setColor(Color.red);
						g.fillRect(x*TIP_SIZE, y*TIP_SIZE, TIP_SIZE, 2);
						//player_hp_表
						g.setColor(Color.GREEN);
						g.fillRect(x*TIP_SIZE, y*TIP_SIZE, bar_per(TIP_SIZE,p.HP,p.MAX_HP), 2);
					break;

					}
				}
			}
			return g;
		}
		Graphics enemy_imager_switch(Graphics g,int ox,int oy,int x,int y) {
			if(map.enemy_map[y+oy][x+ox] != 0) {
				int i = map.enemy_map[y+oy][x+ox];
				switch(enemy_char_id[i]) {
				case 0:
					g.drawImage(enemies_img, x*es-4, y*es-16, x*es+32, y*es+32, chip[edi[i]][emo[i]][0], chip[edi[i]][emo[i]][1], chip[edi[i]][emo[i]][2], chip[edi[i]][emo[i]][3], this);
					break;
				case 1:
					g.drawImage(enemies2_img, x*es-4, y*es-16, x*es+32, y*es+32, chip[edi[i]][emo[i]][0], chip[edi[i]][emo[i]][1], chip[edi[i]][emo[i]][2], chip[edi[i]][emo[i]][3], this);
					break;
				case 2:
					g.drawImage(enemies2_img, x*es-4, y*es-16, x*es+32, y*es+32, chip[edi[i]][emo[i]][0]+24*6, chip[edi[i]][emo[i]][1], chip[edi[i]][emo[i]][2]+24*6, chip[edi[i]][emo[i]][3], this);
					break;
				}
				//player_hp_裏
				g.setColor(Color.red);
				g.fillRect(x*TIP_SIZE, y*TIP_SIZE, TIP_SIZE, 2);
				//player_hp_表
				g.setColor(Color.GREEN);
				g.fillRect(x*TIP_SIZE, y*TIP_SIZE,bar_per(TIP_SIZE,i), 2);
			}
			return g;
		}
		Graphics enemy_imager(Graphics g,int ox,int oy) {
			if(map.room_map[p.py][p.px] == 1) {
				for(int y = 0;y < MONI_SIZE_Y;y++) {
					for(int x = 0;x < MONI_SIZE_X;x++) {
						if(player_map_if_9x9(x,y,ox,oy)) {
							enemy_imager_switch(g,ox,oy,x,y);
						}
					}
				}
			}else {
				for(int y = 0;y < MONI_SIZE_Y;y++) {
					for(int x = 0;x < MONI_SIZE_X;x++) {
						if(map_if_9x9(x,y,ox,oy)) {
							enemy_imager_switch(g,ox,oy,x,y);
						}
					}
				}
			}
			return g;
		}
		Graphics item_imager_switch(Graphics g,int ox,int oy,int x,int y) {
			if(map.item_map[y+oy][x+ox] != 0) {
				switch(map.item_map[y+oy][x+ox]) {
				case 1:
					g.drawImage(item2_img, x*TIP_SIZE, y*TIP_SIZE, x*TIP_SIZE+TIP_SIZE, y*TIP_SIZE+TIP_SIZE, TIP_SIZE*0, TIP_SIZE*1, TIP_SIZE*0+TIP_SIZE-1, TIP_SIZE*1+TIP_SIZE-1, this);
					break;
				case 2:
					g.drawImage(item2_img, x*TIP_SIZE, y*TIP_SIZE, x*TIP_SIZE+TIP_SIZE, y*TIP_SIZE+TIP_SIZE, TIP_SIZE*1, TIP_SIZE*1, TIP_SIZE*1+TIP_SIZE-1, TIP_SIZE*1+TIP_SIZE-1, this);
					break;
				case 3:
					g.drawImage(item2_img, x*TIP_SIZE, y*TIP_SIZE, x*TIP_SIZE+TIP_SIZE, y*TIP_SIZE+TIP_SIZE, TIP_SIZE*2, TIP_SIZE*1, TIP_SIZE*2+TIP_SIZE-1, TIP_SIZE*1+TIP_SIZE-1, this);
					break;
				}
			}
			return g;
		}
		Graphics item_imager(Graphics g,int ox,int oy) {
			if(map.room_map[p.py][p.px] == 1) {
				for(int y = 0;y < MONI_SIZE_Y;y++) {
					for(int x = 0;x < MONI_SIZE_X;x++) {
						if(player_map_if_9x9(x,y,ox,oy)) {
							item_imager_switch(g,ox,oy,x,y);
						}
					}
				}
			}else {
				for(int y = 0;y < MONI_SIZE_Y;y++) {
					for(int x = 0;x < MONI_SIZE_X;x++) {
						if(map_if_9x9(x,y,ox,oy)) {
							item_imager_switch(g,ox,oy,x,y);
						}
					}
				}
			}
			return g;
		}
		Graphics imager_set(Graphics g,int ox,int oy) {
			g = map_imager(g,ox,oy);
			g = item_imager(g,ox,oy);
			g = player_imager(g,ox,oy);
			g = enemy_imager(g,ox,oy);
			return g;
		}
		Graphics imager_shoutcut(Graphics g,int ox,int oy,int i,String s) {
			g.drawImage(skill_img, ox+TIP_SIZE*17+i, oy+6, ox+TIP_SIZE*17+TIP_SIZE+i, oy+TIP_SIZE+6, 3*16+4, 11*16,3*16+21, 11*16+17, this);
			g.setColor(Color.black);
			g.setFont(new Font("ゴシック", Font.BOLD, 20));
			g.drawString(s, ox+TIP_SIZE*17+i, oy+20);
			return g;
		}
		public void paintComponent(Graphics g) {
			int cx = p.px;
			int cy = p.py;
			if(cx > map.SIZE_X-MONI_SIZE_X+1)cx=map.SIZE_X-MONI_SIZE_X+1;
			if(cy > map.SIZE_Y-MONI_SIZE_Y+1)cy=map.SIZE_Y-MONI_SIZE_Y+1;
			switch(moniter_mode) {
			case 0:
				if(cy <  MONI_SIZE_Y/2+1&& cx <  MONI_SIZE_X/2+1) {//左上
					g = imager_set(g,0,0);
				}else if(cx >  MONI_SIZE_X/2 && p.px < map.SIZE_X-MONI_SIZE_X/2 && cy >  MONI_SIZE_Y/2 && p.py < map.SIZE_Y-MONI_SIZE_Y/2){//真ん中
					g = imager_set(g,p.px-MONI_SIZE_X/2,p.py-MONI_SIZE_Y/2);
				}else if(cx >  MONI_SIZE_X/2 && p.px < map.SIZE_X-MONI_SIZE_X/2 && cy <  MONI_SIZE_Y/2+1 ){//上
					g = imager_set(g,p.px-MONI_SIZE_X/2,0);
				}else if(cy >  MONI_SIZE_Y/2 && p.py < map.SIZE_Y-MONI_SIZE_Y/2 && cx <  MONI_SIZE_X/2+1 ){//左
					g = imager_set(g,0,p.py-MONI_SIZE_Y/2);
				}else if(cy >  MONI_SIZE_Y/2 && p.py < map.SIZE_Y-MONI_SIZE_Y/2){//右
					g = imager_set(g,cx-1,p.py-MONI_SIZE_Y/2);
				}else if(cx >  MONI_SIZE_X/2 && p.px < map.SIZE_X-MONI_SIZE_X/2){//下
					g = imager_set(g,p.px-MONI_SIZE_X/2,cy-1);
				}else if(cy >  MONI_SIZE_Y/2+1 && cx >  MONI_SIZE_X/2+1){//右下
					g = imager_set(g,cx-1,cy-1);
				}else if(cx >  MONI_SIZE_X/2){//右上
					g = imager_set(g,cx-1,0);
				}else if(cy >  MONI_SIZE_Y/2){//左下
					g = imager_set(g,0,cy-1);
				}
				int ox = 0;
				int oy = 448;
				//w448+1:h0
				g.setColor(Color.white);

				for(int i = 0;i < 3;i++) {
					g.fillRect(ox+TIP_SIZE*(17+i)+(i+1)*8, oy, TIP_SIZE, TIP_SIZE);
				}
				g.fillRect(ox+TIP_SIZE*4+8, oy, TIP_SIZE*12+16, TIP_SIZE*2+8);
				g.setColor(Color.black);
				g.setFont(new Font("メイリオ", Font.PLAIN, 12));
				for(int i = 0;i < log_max_num;i++) {//ログ表示
					String str = "";
					if(log_box[i]!=null)str = log_box[i];
					if(str != "null")g.drawString(str, ox+TIP_SIZE*4+8, oy+13*(i+1));
				}
				g.setColor(Color.black);
				g.fillRect(ox, oy, TIP_SIZE*4, TIP_SIZE*2+8);

				for(int y = 0;y < map.SIZE_Y;y++) {//マップ
					for(int x = 0;x < map.SIZE_X;x++) {
						if(map.seen_map[y][x] == 1) {
							if(map.map[y][x] == 9)g.setColor(Color.black);
							else g.setColor(Color.white);
							g.fillRect(x+ox, y+oy, 1,1);
						}
					}
				}
				for(int y = 0;y < map.SIZE_Y;y++) {
					for(int x = 0;x < map.SIZE_X;x++) {
						if(map.seen_map[y][x] == 1) {
							if(map.map[y][x] == 8) {
								g.setColor(Color.PINK);
								g.fillRect(x+ox, y+oy, 3,3);
							}
							if(map.player_map[y][x] == 1) {
								g.setColor(Color.GRAY);
								g.fillRect(x+ox, y+oy, 3,3);
							}
							if(map.item_map[y][x] != 0) {
								g.setColor(Color.GREEN);
								g.fillRect(x+ox, y+oy, 3,3);
							}
						}
						if(map.room_map[p.py][p.px] == 1) {
							for(int y1 = -1;y1 < 2;y1++) {
								for(int x1 = -1;x1 < 2;x1++) {
									if(y+oy < 0 || y+oy >= map.SIZE_Y || x+ox < 0 || x+ox >= map.SIZE_X);
									else if(map.enemy_map[y1+y][x1+x] != 0) {
										g.setColor(Color.RED);
										g.fillRect(x+ox, y+oy, 3,3);
									}

								}
							}
						}else {
							if(map_if_9x9(x,y,0,0)) {
								if(map.enemy_map[y][x] != 0) {
									g.setColor(Color.RED);
									g.fillRect(x+ox, y+oy, 3,3);
								}
							}
						}
					}
				}

				imager_shoutcut(g,ox,oy,8,"Q");
				imager_shoutcut(g,ox,oy,16+TIP_SIZE,"W");
				imager_shoutcut(g,ox,oy,24+TIP_SIZE*2,"E");
				g.fillRect(ox+TIP_SIZE*17, oy+TIP_SIZE+8, TIP_SIZE*4, TIP_SIZE);
				g.setColor(Color.red);
				g.fillRect(ox+TIP_SIZE*18-8, oy+TIP_SIZE+12, TIP_SIZE*3, 7);
				g.setColor(Color.GREEN);
				g.fillRect(ox+TIP_SIZE*18-8, oy+TIP_SIZE+12, bar_per(TIP_SIZE*3,p.HP,p.MAX_HP), 7);
				g.setColor(Color.gray);
				g.fillRect(ox+TIP_SIZE*18-8, oy+TIP_SIZE+16+7, TIP_SIZE*3, 7);
				g.setColor(Color.blue);
				g.fillRect(ox+TIP_SIZE*18-8, oy+TIP_SIZE+16+7, bar_per(TIP_SIZE*3,p.MP,p.MAX_MP), 7);
				break;
			case 1:
				int font_size = 30;
				int fs = font_size;
				int qy = 10;
				//枠
				g.drawRect(20, 20, 200, 200);
				g.drawRect(20, 240, 200,250);
				g.drawRect(240, 20, 250,200);
				g.drawRect(240, 240, 250,250);
				g.setColor(Color.black);
				//ステータス
				g.setFont(new Font("メイリオ", Font.PLAIN, fs));
				for(int i = 0;i < p.statas_str.length;i++) {
					g.drawString(p.statas_str[i], 40, fs*(qy+i));
				}
				//装備
				g.setFont(new Font("メイリオ", Font.PLAIN, fs));
				for(int i = 0;i < p.equipment_str.length;i++) {
					g.drawString(p.equipment_str[i], 260, fs*(qy+i));
				}
				//名前とか
				g.drawString(p.NAME, 260, 60);

				g.setFont(new Font("ゴシック", Font.BOLD, 24));
				g.drawString("LV:"+p.LV, 370, 60);
				g.setFont(new Font("ゴシック", Font.BOLD, 16));
				g.drawString("next:"+p.LV_NEXT_EXP + "exp", 370, 80);

				g.setFont(new Font("ゴシック", Font.BOLD, 16));
				g.drawString("HP "+p.HP+" / "+p.MAX_HP, 260, 90);

				g.setColor(Color.red);
				g.fillRect(260, 96, TIP_SIZE*6, 20);
				g.setColor(Color.GREEN);
				g.fillRect(260, 96, bar_per(TIP_SIZE*6,p.HP,p.MAX_HP), 20);
				g.setColor(Color.black);
				g.drawString("MP "+p.MP+" / "+p.MAX_MP, 260, 136);
				g.setColor(Color.GRAY);
				g.fillRect(260, 142, TIP_SIZE*6, 20);
				g.setColor(Color.BLUE);
				g.fillRect(260, 142, bar_per(TIP_SIZE*6,p.MP,p.MAX_MP), 20);
				break;
			case 2:
				g.setFont(new Font("メイリオ", Font.PLAIN, 30));
				g.drawString("所持アイテム", 20, 50);
				for(int i = 0;i < p.MAX_ITEM_NUM/2;i++) {
					g.drawRect(20, i*70+70, 222, 50);
					g.drawRect(262, i*70+70, 222, 50);
					g.drawString(item.item_string(p.my_item[i*2]), 20, i*70+110);
					g.drawString(item.item_string(p.my_item[i*2+1]), 262, i*70+110);
					//p.my_item[i]
				}
				g.setColor(Color.RED);
				if(p.carsolx == 0)g.drawRect(20, p.carsoly*70+70, 222, 50);
				if(p.carsolx == 1)g.drawRect(262, p.carsoly*70+70, 222, 50);
				break;
			case 3:
				g.setFont(new Font("メイリオ", Font.PLAIN, 30));
				g.drawString("所持アイテム", 20, 50);
				if(p.my_skill.length%2==0) {
					for(int i = 0;i < (p.my_skill.length+1)/2;i++) {
						g.drawRect(20, i*70+70, 222, 50);
						g.drawRect(262, i*70+70, 222, 50);
						g.drawString(skill_string(p.my_skill[i*2]), 20, i*70+110);
						g.drawString(skill_string(p.my_skill[i*2+1]), 262, i*70+110);
					}
				}else {
					for(int i = 0;i < (p.my_skill.length+1)/2;i++) {
						if(i == (p.my_skill.length+1)/2-1) {
							g.drawRect(20, i*70+70, 222, 50);
							g.drawString(skill_string(p.my_skill[i*2]), 20, i*70+110);
						}else {
							g.drawRect(20, i*70+70, 222, 50);
							g.drawRect(262, i*70+70, 222, 50);
							g.drawString(skill_string(p.my_skill[i*2]), 20, i*70+110);
							g.drawString(skill_string(p.my_skill[i*2+1]), 262, i*70+110);
						}
					}
				}
				g.setColor(Color.RED);
				if(p.carsolx == 0)g.drawRect(20, p.carsoly*70+70, 222, 50);
				if(p.carsolx == 1)g.drawRect(262, p.carsoly*70+70, 222, 50);
				break;

			}
		}
	}
	void log_stack(String str) {
		for(int i = 1;i < 5;i++) {
			log_box[i-1] = log_box[i];
		}
		log_box[4] = str;
	}
	void start_load() {
		for(int y = 0;y < map.SIZE_Y;y++) {//
			for(int x = 0;x < map.SIZE_X;x++) {
				map.map[y][x] = 0;
				map.room_map[y][x] = 0;
				map.seen_map[y][x] = 0;
				map.enemy_map[y][x] = 0;
				map.player_map[y][x] = 0;
				map.item_map[y][x] = 0;
			}
		}
		map.waku(map.SIZE_X,map.SIZE_Y);//マップ作製
		for(int y = 0;y < map.SIZE_Y;y++) {
			for(int x = 0;x < map.SIZE_X;x++) {
				if(map.map[y][x] == 0)map.map[y][x] = 9;
				if(map.map[y][x] == 3)map.room_map[y][x] = 1;
			}
		}
		int qx = r.nextInt(map.SIZE_X);
		int qy = r.nextInt(map.SIZE_Y);
		while(map.map[qy][qx] != 2) {
			qx = r.nextInt(map.SIZE_X);
			qy = r.nextInt(map.SIZE_Y);
		}
		map.map[qy][qx] = 8;
		do{
			p.px = r.nextInt(map.SIZE_X);
			p.py = r.nextInt(map.SIZE_Y);
		}while(map.map[p.py][p.px] != 2);

		for(int i = 0;i < ITEM_MAX_NUM;i++) {
			while(true) {
				qx = r.nextInt(map.SIZE_X);
				qy = r.nextInt(map.SIZE_Y);
				if(map.map[qy][qx] == 2 && map.item_map[qy][qx] == 0) {//アイテム配置
					map.item_map[qy][qx] = r.nextInt(3)+1;
					break;
				}
			}
		}
		map.player_map[p.py][p.px] = 1;
		enemy_reseter();
	}
	void all_clear() {
		for(int y = 0;y < map.SIZE_Y;y++) {
			for(int x = 0;x< map.SIZE_X;x++) {
				map.map[y][x] = 0;
			}
		}
	}



	void enemy_damage_tra(int dam,int i) {
		ehp[i]-=dam;
		if(ehp[i] <= 0) {
			log_stack(enemy_name[enemy_char_id[i]]+"をたおした");
			map.enemy_map[ey[i]][ex[i]] = 0;
			ex[i] = 0;
			ey[i] = 0;
			eflag[i] = false;
			String l = p.level_tra(exp[i]);
			if(l!=null)log_stack(l);
		}
	}
	public String skill_string(int no){
		switch(no) {
		case 1:
			return "ファイヤー";
		case 2:
			return "サンダー";
		case 3:
			return "上段切り";
		}
		return " ";
	}
	char all_key_char = ' ';
	boolean all_key_flag = false;
	boolean player_comand_flag = false;
	@Override
	public void run() {
		start_load();
		while(true) {
			if(p.HP <= 0) {
				all_key_event(all_key_char);
				all_key_flag = true;
			}else {
				switch(moniter_mode) {
				case 0:
					try {
						Thread.sleep(33);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(!key_flag) {
						player_moov(key_char);
					}
					break;
				case 1:
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				case 2:
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(!key_flag) {
						item_carsol_moov();
					}
					break;
				case 3:
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(!key_flag) {
						skill_carsol_moov();
					}
					break;
				}
				if(player_comand_flag) {
					for(int i = 1;i < EMN;i++) {
						if(eflag[i])enemy_run(i);
					}
				}
				if(!key_flag) {
					key_flag = true;
				}
				if(!all_key_flag) {
					all_key_event(all_key_char);
					all_key_flag = true;
				}
				if(p.HP <= 0) {
					map.player_map[p.py][p.px] = 2;
					log_stack(" GAME OVER ");
					log_stack("-----------");
					log_stack("Qでやめる");
					log_stack("Wで復活");
					log_stack("Eで最初から");

				}
				player_comand_flag = false;
				map_vis();
				repaint();

			}
		}
	}
	void map_vis() {
		if(map.room_map[p.py][p.px] == 1) {
			for(int y = -1;y < 2;y++) {
				for(int x = -1;x < 2;x++) {
					map.seen_map[p.py+y][p.px+x] = 1;
				}
			}
		}else {
			for(int y = 0;y < map.SIZE_Y;y++){
				for(int x = 0;x < map.SIZE_X;x++){
					if(map_if_9x9(x,y,0,0)) {
						map.seen_map[y][x] = 1;
					}
				}
			}
		}
	}
	boolean map_if_9x9(int x,int y,int ox,int oy) {
		for(int ay = -1;ay < 2;ay++) {
			for(int ax = -1;ax < 2;ax++) {
			if(map_cloud_imager(x+ax,y+ay,ox,oy))return true;
			}
		}
		return false;
	}
	boolean map_cloud_imager(int x,int y,int ox,int oy) {
		if(y+oy < 0 || y+oy >= map.SIZE_Y || x+ox < 0 || x+ox >= map.SIZE_X)return false;
		if(map.room_map[y+oy][x+ox] == map.room_map[p.py][p.px])return true;
		return false;
	}
	int item_empty() {
		for(int i = 0;i < p.MAX_ITEM_NUM;i++) {
			if(p.my_item[i] == 0) {
				return i;
			}
		}
		return -1;
	}
	int get_item_no() {
		if(p.carsolx == 0)return p.my_item[p.carsoly*2];
		if(p.carsolx == 1)return p.my_item[p.carsoly*2+1];
		return -1;
	}
	void skill_ex(int no) {
		int id = 0;
		int damage = 0;
		id = player_see_enemy(p.di);
		switch(no) {
		case 3:
			damage=10;
			break;
		}
		if(no != 0) {
			log_stack(enemy_name[enemy_char_id[id]]+"に"+skill_string(no)+"で"+damage+"のダメージを与えた");
			enemy_damage_tra(damage,id);
		}
		player_comand_flag = true;
	}
	String enemy_name[] = {
		"スライム",
		"ミイラ",
		"ソルジャー"
	};
	void item_carsol_moov() {
		switch(keycode){
		case 37:
			p.carsolx--;
			break;
		case 39:
			p.carsolx++;
			break;
		case 38:
			p.carsoly--;
			break;
		case 40:
			p.carsoly++;
			break;
		}
		switch(key_char) {
		case 'j':
			int e = item_empty();
			if(e == -1) {

			}else {
				p.my_item[e] = 1;
			}
			break;
		case '\n':
			if(item.item_string(get_item_no())!="") {
				log_stack(item.item_string(get_item_no())+"を使った");
				item_ex(p.my_item[p.carsolx+p.carsoly*2]);
				moniter_mode=0;
			}
			break;
		}

		if(p.carsolx > 1)p.carsolx = 0;
		if(p.carsolx < 0)p.carsolx = 1;
		if(p.carsoly > 4)p.carsoly = 0;
		if(p.carsoly < 0)p.carsoly = 4;

	}
	void item_ex(int no) {
		switch(no) {
		case 1:
			player_hp_heal(20);
			break;
		case 2:
			player_hp_heal(40);
			break;
		case 3:
			player_hp_heal(60);
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		case 7:
			break;
		case 8:
			break;
		case 9:
			break;
		case 10:
			break;

		}
		player_comand_flag = true;
		p.my_item[p.carsolx+p.carsoly*2] = 0;
	}
	void player_hp_heal(int hp) {
		if(p.HP+hp > p.MAX_HP)p.HP=p.MAX_HP;
		else p.HP+= hp;
	}
	void skill_carsol_moov() {
		switch(keycode){
		case 37:
			p.carsolx--;
			break;
		case 39:
			p.carsolx++;
			break;
		case 38:
			p.carsoly--;
			break;
		case 40:
			p.carsoly++;
			break;
		}
		switch(key_char) {
		case 'j':
			int e = item_empty();
			if(e == -1) {

			}else {
				p.my_item[e] = 1;
			}
			break;
		case '\n':
			skill_ex(p.my_skill[p.carsolx+p.carsoly*2]);
			moniter_mode=0;
			break;
		}

		if(p.carsolx > 1)p.carsolx = 0;
		if(p.carsolx < 0)p.carsolx = 1;
		if(p.carsoly > (p.my_skill.length+1)/2-1) p.carsoly = 0;
		if(p.carsoly < 0) p.carsoly = (p.my_skill.length+1)/2-1;
		if(p.my_skill.length % 2 == 1 && (p.my_skill.length+1)/2-1 == p.carsoly) {
			p.carsolx = 0;
		}
	}

	//共通関連

	public int bar_per(int num,int i) {
		if((int) ((double)ehp[i]/emaxhp[i]*num) >= 0)num =(int) ((double)ehp[i]/emaxhp[i]*num);
		else num = 0;
		return num;
	}
	public int bar_per(int num, int hp, int mhp) {
		if((int) ((double)hp/mhp*num) >= 0)num =(int) ((double)hp/mhp*num);
		else num = 0;
		return num;
	}
	public int map_sercher(int x,int y, int mx,int my) {
		if(map.map[my+y][mx+x] != 9) {
			return 0;
		}else {
			return 9;
		}
	}
	public int map_obj_sercher(int x,int y, int mx,int my) {
		return map.map[my+y][mx+x];
	}
	public int enemy_map_sercher(int x,int y, int mx,int my) {
		return map.enemy_map[my+y][mx+x];
	}
	public int player_map_sercher(int x,int y, int mx,int my) {
		return map.player_map[my+y][mx+x];
	}
	int mr(int seed,int min) {
		return r.nextInt(seed-min)+min;
	}
	int mr(int seed,int min,int max) {
		return r.nextInt(max-min)+min;//40-20+20
	}
	boolean iaf = true;
	public int moov_effect(int a) {
		if(iaf) {
			a++;
			if(a == 2) {
				iaf=false;
			}
		}else {
			a--;
			if(a == 0) {
				iaf=true;
			}
		}

		return a;
	}
	//player関連
	public void player_moov(char key_char) {
		int ox = p.px;
		int oy = p.py;
		switch(keycode){
		case 37://-1,0
			if(p.modifiers == 0) {
				player_moov_prosecc(-1,0,p.px,p.py);
				p.mo = moov_effect(p.mo);
			}
			p.di=3;
			break;
		case 39://1,0
			if(p.modifiers == 0) {
				player_moov_prosecc(1,0,p.px,p.py);
				p.mo = moov_effect(p.mo);
			}
			p.di=1;
			break;
		case 38://0,-1
			if(p.modifiers == 0) {
				player_moov_prosecc(0,-1,p.px,p.py);
				p.mo = moov_effect(p.mo);
			}
			p.di=0;
			break;
		case 40://0,1
			if(p.modifiers == 0) {
				player_moov_prosecc(0,1,p.px,p.py);
				p.mo = moov_effect(p.mo);
			}
			p.di=2;
			break;
		}
		switch(key_char){
		case 'r':
			start_load();
			break;
		case 'z':
			enemy_all_down();
			break;
		case 'x':
			p.HP = 0;
			break;
		case 'f':
			String tes_str = p.level_tra(100);
			if(tes_str!=null)log_stack(tes_str);
			for(int i = 0;i < p.statas_str.length;i++) {
				System.out.println(p.statas_str[i]);
			}
			break;
		case 'g':
			item_get();
			break;
		case 'h':
			System.out.println("-----");
			for(int y = 0;y < map.SIZE_Y;y++) {
				for(int x = 0;x < map.SIZE_X;x++) {
					if(map.room_map[y][x] != 0)System.out.print(map.room_map[y][x]);
					else System.out.print(" ");
				}
				System.out.println();
			}
			System.out.println(map.room_map[p.py][p.px]);
			System.out.println("-----");
			break;
		case '\n':
			switch(map_obj_sercher(p.px,p.py,0,0)) {
			case 8:
				for(int y = 0;y < map.SIZE_Y;y++) {
					for(int x = 0;x < map.SIZE_X;x++) {
						map.map[y][x] = 0;
					}
				}
				start_load();
				break;
			default:
				player_comand_flag = true;
				break;
			}
			break;
		}

		map.player_map[oy][ox] = 0;
		map.player_map[p.py][p.px] = 1;
		p.modifiers = 0;
	}
	void item_get() {
		int no =map.item_map[p.py][p.px];
		System.out.println(no);
		if(no != 0) {
			int g = item_empty();
			if(g != -1) {
				p.my_item[g] = no;
				map.item_map[p.py][p.px] = 0;
				log_stack(item.item_string(no)+"を手に入れた");
			}else {
				log_stack("荷物はもう持てない");
			}

		}else {
			log_stack("何もない");
		}
	}
	int player_see_enemy(int di) {
		int x = 0;
		int y = 0;
		switch(di) {
		case 0:
			y = -1;
			break;
		case 1:
			x = 1;
			break;
		case 2:
			y = 1;
			break;
		case 3:
			x = -1;
			break;
		}
		return enemy_map_sercher(x,y,p.px,p.py);
	}

	public void all_key_event(char all_key_char) {
		if(p.HP <= 0) {
			switch(all_key_char) {
			case 'e':
				p = new Player();
				e = new Enemy();
				map = new Map();
				skill = new Skill();
				item = new Item();
				start_load();
				for(int i = 0;i < 5;i++) {
					log_stack("");
				}
				break;
			case 'q':
				System.exit(0);
				win.dispose();
				break;
			case 'w':
				p.HP=p.MAX_HP;
				map.player_map[p.py][p.px] = 1;
				log_stack("-------------------------");
				log_stack("");
				log_stack(p.NAME+"は這い上がった");
				log_stack("");
				log_stack("-------------------------");
				break;
			}

			key_char = ' ';
			all_key_char = ' ';
		}else {
			switch(all_key_char) {

			case 'q':
				p.carsolx = 0;
				p.carsoly = 0;
				if(moniter_mode != 1)moniter_mode = 1;
				else moniter_mode = 0;
				break;
			case 'w':
				p.carsolx = 0;
				p.carsoly = 0;
				if(moniter_mode != 3)moniter_mode = 3;
				else moniter_mode = 0;
				break;
			case 'e':
				p.carsolx = 0;
				p.carsoly = 0;
				if(moniter_mode != 2)moniter_mode = 2;
				else moniter_mode = 0;
				break;
			}
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {

	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(key_flag) {
			key_char = e.getKeyChar();
			keycode = e.getKeyCode();
			key_flag = false;
			p.modifiers = e.getModifiers();
		}
		if(all_key_flag) {
			all_key_char = e.getKeyChar();
			all_key_flag = false;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {

	}
	void player_moov_prosecc(int ax, int ay, int bx, int by) {
		switch(map_sercher(ax,ay,bx,by)) {
		case 0:
			switch(enemy_map_sercher(ax,ay,bx,by)) {
				case 0:
					p.px+=ax;
					p.py+=ay;
					break;
				default:
					log_stack(enemy_name[enemy_char_id[enemy_map_sercher(ax,ay,bx,by)]]+"に"+p.AT+"ダメージを与えた");
					enemy_damage_tra(p.AT,enemy_map_sercher(ax,ay,bx,by));
					break;
			}
			player_comand_flag = true;
			break;
		case 9:

			break;
		}
	}
	int player_object_sercher(int x,int y, int mx,int my) {//0.空,1.敵,-1.壁
		if(map_sercher(x,y,mx,my) == 0) {
			if(enemy_map_sercher(x,y,mx,my) == 0) {
				return 0;
			}else {
				return 1;
			}
		}else {
			return -1;
		}
	}
	//enemy関連
	public void enemy_reseter() {
		for(int i = 1; i < EMN;i++) {
			enemy_spawn(i);
		}
	}
	public void enemy_spawn(int i) {
		map.enemy_map[ey[i]][ex[i]] = 0;
		enemy_coordinate(i);
		while(map.enemy_map[ey[i]][ex[i]] != 0 || map.map[ey[i]][ex[i]] != 2 || map.player_map[ey[i]][ex[i]] != 0) {
			enemy_coordinate(i);
		}
		edi[i] = 0;//向き:0.上,1.右,2.下,3.左
		emo[i] = 0;
		map.enemy_map[ey[i]][ex[i]] = i;
		enemy_char_id[i] = r.nextInt(3);
		switch(enemy_char_id[i]) {
		case 0:
			emaxhp[i] = 10;
			enemy_attack[i] = 1;
			exp[i] = 10;
			break;
		case 1:
			emaxhp[i] = 20;
			enemy_attack[i] = 2;
			exp[i] = 20;
			break;
		case 2:
			emaxhp[i] = 30;
			enemy_attack[i] = 3;
			exp[i] = 30;
			break;
		}
		ehp[i] = emaxhp[i];
		eflag[i] = true;
	}
	void enemy_coordinate(int i) {
		int qx = mr(map.SIZE_X-1,1);
		int qy = mr(map.SIZE_Y-1,1);
		ex[i] = qx;
		ey[i] = qy;
	}
	public void enemy_all_down() {
		for(int i = 0;i < EMN;i++) {
			enemy_damage_tra(emaxhp[i],i);
		}
	}
	public void enemy_run(int i) {
		if(ehp[i] > 0) {
			enemies_moov(i);
		}
	}
	int enemy_object_sercher(int x,int y, int mx,int my) {//0.空,1.プレイヤー,-1.壁,2.エネミー
		if(map_sercher(x,y,mx,my) == 0) {
			if(enemy_map_sercher(x,y,mx,my) == 0) {
				if(player_map_sercher(x,y,mx,my) == 0) {
					return 0;
				}else {
					return 1;
				}
			}else {
				return 2;
			}
		}else {
			return -1;
		}
	}
	void enemy_moover(int x,int y, int mx,int my,int i) {
		int g = enemy_object_sercher(x,y,mx,my);
		switch(g) {
		case 0:
			ex[i]+=x;
			ey[i]+=y;
			break;
		case 1:
			log_stack(enemy_name[enemy_char_id[i]]+"はプレイヤーに"+enemy_attack[i]+"のダメージを与えた");
			player_to_damage(enemy_attack[i]);
			break;
		}
		emo[i] = emoov_effect(emo[i]);
	}
	public void enemies_moov(int i) {
		int old_no = map.enemy_map[ey[i]][ex[i]];
		int rand = r.nextInt(4);
		int range = 9;
		for(int y = -1*range/2-1;y < range-range/2;y++) {
			for(int x = -1*range/2-1;x < range-range/2;x++) {
				if(ey[i] + y >= 0 && ex[i] + x >= 0 && ey[i] + y < map.SIZE_Y && ex[i] + x < map.SIZE_X) {
					if(map.player_map[ey[i]+y][ex[i]+x] == 1) {
						if(p.py-ey[i] > 0)rand = 3;
						if(p.py-ey[i] < 0)rand = 2;
						if(p.px-ex[i] > 0)rand = 1;
						if(p.px-ex[i] < 0)rand = 0;
					}
				}
			}
		}
		int ox = ex[i];//古い座標
		int oy = ey[i];
		switch(rand) {
		case 0:
			enemy_moover(-1,0,ex[i],ey[i],i);
			edi[i]=3;
			break;
		case 1:
			enemy_moover(1,0,ex[i],ey[i],i);
			edi[i]=1;
			break;
		case 2:
			enemy_moover(0,-1,ex[i],ey[i],i);
			edi[i] = 0;
			break;
		case 3:
			enemy_moover(0,1,ex[i],ey[i],i);
			edi[i]=2;
			break;
		}
		map.enemy_map[oy][ox] = 0;
		map.enemy_map[ey[i]][ex[i]] = old_no;
		repaint();
	}
	public void player_to_damage(int a){
		p.HP-=a;
	}
	public int emoov_effect(int a) {
		a++;
		if(a > 2)a = 0;
		return a;
	}
}
