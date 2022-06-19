package テスト用;

public class Test_main {

	public static void main(String[] args) {
		Test_player p = new Test_player();
		System.out.println(p.HP);
		p.HP--;
		System.out.println(p.HP);
	}
	void pl(String str) {
		System.out.println(str);
	}
	void pl(int str) {
		System.out.println(str);
	}
}
