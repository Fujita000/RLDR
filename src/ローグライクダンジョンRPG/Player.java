package ローグライクダンジョンRPG;

public class Player{
	int px = 1;
	int py = 1;
	int ps = 32;

	int di = 0;//向き:0.上,1.右,2.下,3.左
	int mo = 0;

	 String NAME = "テスコ";
	int LV = 1;

	int LV_NEXT_EXP = 0;
	int EXP_X = 30;
	int EXP = 0;
	int SUM_EXP = 0;

	int MAX_HP = 33*LV;
	int HP = MAX_HP;
	int MAX_MP = 3;
	int MP = MAX_MP;

	int AT = 3*LV;//攻撃
	int MG = 3*LV;//魔力
	int SP = 3*LV;//速さ
	int DEF = 3*LV;//防御
	int DEX = 3*LV;//器用
	int LUC = 3*LV;//幸運

	int modifiers = 0;



	String statas_str[] = {
			"攻撃力："+ AT,
			"魔　力："+ MG,
			"速　さ："+ SP,
			"防　御："+ DEF,
			"器　用："+ DEX,
			"幸　運："+ LUC,
	};
	String equipment_str[] = {
			" 頭  ："+ "null",
			" 体  ："+ "null",
			" 腕  ："+ "null",
			" 足  ："+ "null",
			"武器："+ "null",
			"装備："+ "null",
	};

	int MAX_ITEM_NUM = 10;
	int my_item[] = {
			0,0,0,0,0,0,0,0,0,0
	};
	int my_skill[] = {
			1,2,3
	};
	int carsolx = 0;
	int carsoly = 0;

	String level_tra(int get_exp){
		String ret = null;
		EXP+=get_exp;
		SUM_EXP+=get_exp;
		LV_NEXT_EXP = EXP_X*LV-EXP;
		while(EXP_X*LV-EXP <= 0) {//レベルアップの条件
			EXP = (EXP_X*LV-EXP)*-1;
			LV++;
			LV_NEXT_EXP = EXP_X*LV-EXP;
			statas_update(LV,LV,LV,LV,LV,LV,MAX_HP/3,MAX_MP/3);
			ret = "LVが"+LV+"になった";
		}
		return ret;
	}
	void statas_update(int at,int mg,int sp,int df,int dx,int lu,int hp ,int mp) {
		MAX_HP+=hp;
		MAX_MP+=mp;
		AT+=at;
		MG+=mg;
		SP+=sp;
		DEF+=df;
		DEX+=dx;
		LUC+=lu;
		String new_statas_str[] = {
				"攻撃力："+ AT,
				"魔　力："+ MG,
				"速　さ："+ SP,
				"防　御："+ DEF,
				"器　用："+ DEX,
				"幸　運："+ LUC,
		};
		statas_str = new_statas_str;
	}
}
