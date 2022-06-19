package ローグライクダンジョンRPG;

import java.util.Random;

public class Map {
	int SIZE_X = 96;
	int SIZE_Y = 64;
	int map[][] = new int[SIZE_Y][SIZE_X];
	int player_map[][] = new int[SIZE_Y][SIZE_X];
	int enemy_map[][] = new int[SIZE_Y][SIZE_X];
	int room_map[][] = new int[SIZE_Y][SIZE_X];
	int seen_map[][] = new int[SIZE_Y][SIZE_X];
	int item_map[][] = new int[SIZE_Y][SIZE_X];
	Random r = new Random();
	void waku(int ax,int ay) {
		for(int i = 0;i < SIZE_Y;i++) {
			map[i][ax/3] = 1;
			map[i][ax/3*2] = 1;
		}
		for(int i = 0;i < SIZE_X;i++) {
			map[ay/3][i] = 1;
			map[ay/3*2][i] = 1;
		}

		int k = 1;
		room(1,1,ax/3-1,ay/3-1,k+=1);
		room(ax/3+2,1,ax/3*2-1,ay/3-1,k+=1);
		room(ax/3*2+2,1,ax-1,ay/3-1,k+=1);

		room(1,ay/3+2,ax/3-1,ay/3*2-1,k+=1);
		room(ax/3+2,ay/3+2,ax/3*2-1,ay/3*2-1,k+=1);
		room(ax/3*2+2,ay/3+2,ax-1,ay/3*2-1,k+=1);

		room(1,ay/3*2+2,ax/3-1,ay-1,k+=1);
		room(ax/3+2,ay/3*2+2,ax/3*2-1,ay-1,k+=1);
		room(ax/3*2+2,ay/3*2+2,ax-1,ay-1,k+=1);





		boolean flag = false;
		for(int i = 0;i < SIZE_Y;i++) {
			if(map[i][ax/3-1] == 3 && map[i][ax/3+1] == 3) {
				map[i][ax/3] = 3;
			}else if(map[i][ax/3] == 3 && !flag) {
				flag = true;
			}else if(map[i][ax/3] == 3 && flag){
				flag = false;
			}
			if(flag)map[i][ax/3] = 3;
			else if(!flag && map[i][ax/3] == 1) map[i][ax/3] = 0;
		}
		flag = false;
		for(int i = 0;i < SIZE_Y;i++) {
			if(map[i][ax/3*2-1] == 3 && map[i][ax/3*2+1] == 3) {
				map[i][ax/3*2] = 3;
			}else if(map[i][ax/3*2] == 3 && !flag) {
				flag = true;
			}else if(map[i][ax/3*2] == 3 && flag){
				flag = false;
			}
			if(flag)map[i][ax/3*2] = 3;
			else if(!flag && map[i][ax/3*2] == 1) map[i][ax/3*2] = 0;
		}
		flag = false;
		for(int i = 0;i < SIZE_X;i++) {
			if(map[ay/3-1][i] == 3 && map[ay/3+1][i] == 3) {
				map[ay/3][i] = 3;
			}
			else if(map[ay/3][i] == 3 && !flag) {
				flag = true;
			}else if(map[ay/3][i] == 3 && flag){
				flag = false;
			}
			if(flag)map[ay/3][i] = 3;
			else if(!flag && map[ay/3][i] == 1) map[ay/3][i] = 0;
		}
		flag = false;
		for(int i = 0;i < SIZE_X;i++) {
			if(map[ay/3*2-1][i] == 3 && map[ay/3*2+1][i] == 3) {
				map[ay/3*2][i] = 3;
			}
			else if(map[ay/3*2][i] == 3 && !flag) {
				flag = true;
			}else if(map[ay/3*2][i] == 3 && flag){
				flag = false;
			}
			if(flag)map[ay/3*2][i] = 3;
			else if(!flag && map[ay/3*2][i] == 1) map[ay/3*2][i] = 0;
		}

	}
	void room(int st_x,int st_y,int en_x, int en_y,int i) {
		st_x += r.nextInt(7);
		st_y += r.nextInt(7);
		en_x -= r.nextInt(7);
		en_y -= r.nextInt(7);

		for(int y = st_y;y < en_y;y++) {
			for(int x = st_x;x < en_x;x++) {
				map[y][x] = 2;
				room_map[y][x] = i;
			}
		}
		load(st_x,st_y,en_x,en_y);
	}
	void load(int st_x,int st_y,int en_x, int en_y) {
		int px = r.nextInt(en_x-st_x);
		int py = r.nextInt(en_y-st_y);
		int amx = r.nextInt(en_x-st_x);
		int amy = r.nextInt(en_y-st_y);

		boolean flag_px = false;
		boolean flag_py = false;
		boolean flag_mx = false;
		boolean flag_my = false;

		int gy = st_y-1;
		int gx = st_x-1;
		for(int i = st_y;i+1 > 0;i--) {
			if(map[i][st_x+px] == 1 || map[i][st_x+px] == 3) {
				flag_mx = true;
			}
		}
		while(flag_mx) {
			if(map[gy][st_x+px] == 1 || map[gy][st_x+px] == 3) {
				map[gy][st_x+px] = 3;
				break;
			}
			map[gy][st_x+px] = 3;
			gy--;
		}

		gy = en_y;
		for(int i = en_y;i < SIZE_Y;i++) {
			if(map[i][st_x+amx] == 1 || map[i][st_x+amx] == 3) {
				flag_px = true;
			}
		}
		while(flag_px) {
			if(map[gy][st_x+amx] == 1 || map[gy][st_x+amx] == 3) {
				map[gy][st_x+amx] = 3;
				break;
			}
			map[gy][st_x+amx] = 3;
			gy++;
		}
		for(int i = st_x;i+1 > 0;i--) {
			if(map[st_y+py][i] == 1 || map[st_y+py][i] == 3) {
				flag_my = true;
			}
		}
		while(flag_my) {
			if(map[st_y+py][gx] == 1 || map[st_y+py][gx] == 3) {
				map[st_y+py][gx] = 3;
				break;
			}
			map[st_y+py][gx] = 3;
			gx--;
		}

		gx = en_x;
		for(int i = en_x;i < SIZE_X;i++) {
			if(map[st_y+amy][i] == 1 || map[st_y+amy][i] == 3) {
				flag_py = true;
			}
		}
		while(flag_py) {
			if(map[st_y+amy][gx] == 1 || map[st_y+amy][gx] == 3) {
				map[st_y+amy][gx] = 3;
				break;
			}
			map[st_y+amy][gx] = 3;
			gx++;
		}
	}
}
