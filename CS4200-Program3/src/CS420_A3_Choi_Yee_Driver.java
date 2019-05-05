
public class CS420_A3_Choi_Yee_Driver
{
	public static void main(String[] args)
	{
		CS420_Game_Isolation_Board test=new CS420_Game_Isolation_Board();
		test.printBoard();
		test.move('c',3,3);
		test.printBoard();
	}
}
