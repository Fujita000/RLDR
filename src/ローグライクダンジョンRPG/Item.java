package ローグライクダンジョンRPG;

public class Item {
	//Player p = new Main().p;
	//Main main = new Main();

	int item_tip_map[][] =  {
			{0,0},
			{0,16}
	};
	String item_string(int no) {
		//System.out.println(p.HP);
		switch(no) {
		case 1:return "回復薬.小";		//HP回復アイテム
		case 2:return "回復薬.中";		//MP回復アイテム
		case 3:return "回復薬.大";		//攻撃上昇HP半減
		case 4:return "魔力薬.小";
		case 5:return "魔力薬.中";
		case 6:return "魔力薬.大";
		case 7:return "エリクサー";
		case 8:return "鬼薬";
		case 9:return "魔薬";
		case 10:return "酒";
		case 11:return "";
		case 12:return "";
		case 13:return "";
		case 14:return "";
		case 15:return "";
		case 16:return "";
		case 17:return "";
		case 18:return "";
		case 19:return "";
		case 20:return "";
		case 21:return "";
		case 22:return "";
		case 23:return "";
		case 24:return "";
		case 25:return "";
		case 26:return "";
		case 27:return "";
		case 28:return "";
		case 29:return "";
		case 30:return "";
		case 31:return "";
		case 32:return "";
		case 33:return "";
		case 34:return "";
		case 35:return "";
		case 36:return "";
		case 37:return "";
		case 38:return "";
		case 39:return "";
		case 40:return "";
		case 41:return "";
		case 42:return "";
		case 43:return "";
		case 44:return "";
		case 45:return "";
		case 46:return "";
		case 47:return "";
		case 48:return "";
		case 49:return "";
		case 50:return "";
		case 51:return "";
		case 52:return "";
		case 53:return "";
		case 54:return "";
		case 55:return "";
		case 56:return "";
		case 57:return "";
		case 58:return "";
		case 59:return "";
		case 60:return "";
		case 61:return "";
		case 62:return "";
		case 63:return "";
		case 64:return "";
		case 65:return "";
		case 66:return "";
		case 67:return "";
		case 68:return "";
		case 69:return "";
		case 70:return "";
		case 71:return "";
		case 72:return "";
		case 73:return "";
		case 74:return "";
		case 75:return "";
		case 76:return "";
		case 77:return "";
		case 78:return "";
		case 79:return "";
		case 80:return "";
		case 81:return "";
		case 82:return "";
		case 83:return "";
		case 84:return "";
		case 85:return "";
		case 86:return "";
		case 87:return "";
		case 88:return "";
		case 89:return "";
		case 90:return "";
		case 91:return "";
		case 92:return "";
		case 93:return "";
		case 94:return "";
		case 95:return "";
		case 96:return "";
		case 97:return "";
		case 98:return "";
		case 99:return "";
		case 100:return "";
		//-----------------------100-----魔法書
		case 101:return "火の書";
		case 102:return "炎の書";
		case 103:return "極炎の書";
		case 104:return "水の書";
		case 105:return "波の書";
		case 106:return "極海の書";
		case 107:return "風の書";
		case 108:return "嵐の書";
		case 109:return "風神の書";
		case 110:return "電の書";
		case 111:return "雷の書";
		case 112:return "雷神の書";
		case 113:return "雪の書";
		case 114:return "雹の書";
		case 115:return "吹雪の書";
		case 116:return "土の書";
		case 117:return "地の書";
		case 118:return "大地の書";
		case 119:return "回復の書";
		case 120:return "復活の書";
		case 121:return "命の書";
		case 122:return "隕石の書";
		case 123:return "彗星の書";
		case 124:return "流星群の書";
		case 125:return "";
		case 126:return "";
		case 127:return "";
		case 128:return "";
		case 129:return "";
		case 130:return "";
		case 131:return "";
		case 132:return "";
		case 133:return "";
		case 134:return "";
		case 135:return "";
		case 136:return "";
		case 137:return "";
		case 138:return "";
		case 139:return "";
		case 140:return "";
		case 141:return "";
		case 142:return "";
		case 143:return "";
		case 144:return "";
		case 145:return "";
		case 146:return "";
		case 147:return "";
		case 148:return "";
		case 149:return "";
		case 150:return "";
		}
		return "";
	}
	/*
	1.回復薬（小）
	2.回復薬（中）
	3.回復薬（大）
	4.魔力薬（小）
	5.魔力薬（中）
	6.魔力薬（大）
	7.エリクサー
	8.空腹度回復（小）
	9.空腹度回復（中）
	10.空腹度回復（大）
	ーーーーーーーーー
	11.火の書
	12.炎の書
	13.極炎の書
	14.水の書
	15.波の書
	16.極海の書
	17.風の書
	18.嵐の書
	19.風神の書
	20.電の書
	21.雷の書
	22.雷神の書
	23.雪の書
	24.雹の書
	25.吹雪の書
	26.土の書
	27.地の書
	28.大地の書
	29.回復の書
	30.復活の書
	31.命の書
	32.隕石の書
	33.彗星の書
	34.流星群の書
	ーーーーーーーーー
	35.飛道具（弱）
	36.飛道具（中）
	37.飛道具（大）
	ーーーーーーーーー
	38.剣
	39.大剣
	40.短剣
	41.打刀
	42.太刀
	43.大太刀
	44.
	45.槍
	46.ランス
	47.弓
	48.棍棒
	49.戦鎌
	50.戦斧
	51.
	52.スタッフ//長く強い魔法
	53.ワンド//短く弱い魔法
	54.
	55.
	56.
	57.
	58.
	59.
	60.
	61.
	62.
	63.
	ーーーーーーーーーー
	64.鬼薬
	65.僧薬
	66.酒
	ーーーーーーーーーー
	67.兜
	68.鎧
	69.具足
	70.籠手
	71.お守り
	72.護符
	73.宝玉
	ーーーーーーーーーー
	74.
	75.
	76.
	77.
	78.
	79.
	80.
	81.
	82.
	83.
	84.
	85.
	86.
	87.
	88.
	89.
	90.
	91.
	92.
	93.
	94.
	95.
	96.
	97.
	98.
	99.
	100.
	*/
}
