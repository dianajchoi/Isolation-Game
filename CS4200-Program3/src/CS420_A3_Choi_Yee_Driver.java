
public class CS420_A3_Choi_Yee_Driver
{
	public static void main(String[] args)
	{
		
		CS420_Game_Isolation_Board test=new CS420_Game_Isolation_Board();
		test.move('c', 1,2);
		test.printBoard();
		test.move('o', 1, 8);
		test.printBoard();
		test.move('o', 3, 7);
	}
}
