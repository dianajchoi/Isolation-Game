import java.util.Scanner;
public class CS420_A3_Choi_Yee_Driver
{
	public static void main(String[] args)
	{
		
		int counterTurn=1;
		int moved=0;
		Scanner s=new Scanner(System.in);
		char currPlayer;
		String moveInput;
		int[] move;
		CS420_Game_Isolation_Board initial=new CS420_Game_Isolation_Board();
		System.out.println("Xpponent vs.  Opponent");
		currPlayer=initial.currPlayer;
		while(true)
		{
			if(moved==2)
			{
				counterTurn+=1;
				moved=0;
			}
			System.out.println("TURN: "+counterTurn);
			if(currPlayer=='x')
			{
				System.out.println("Xpponent's move is: ");
				moveInput=s.next();
				move=convertToCoordinates(moveInput);
				if(initial.move('x',move[0],move[1]))
				{
					currPlayer='o';
					moved+=1;
					initial.printBoard();
				}
				
			}
			else if(currPlayer=='o')
			{
				System.out.println("Opponent's turn is: ");
				moveInput=s.next();
				move=convertToCoordinates(moveInput);
				if(initial.move('o', move[0], move[1]))
				{
					currPlayer='x';
					moved+=1;
					initial.printBoard();
				
				}
				
			}
				
		}
		
		
//		CS420_Game_Isolation_Board test=new CS420_Game_Isolation_Board();
//		initial.move('c', 1	,4);
//		initial.printBoard();
//		initial.move('o', 8,1);
//		initial.printBoard();
//		initial.move('c',7,4);
//		initial.printBoard();
	}
	public static int[] convertToCoordinates(String coordinate)
	{
		int[] coordinates=new int[2];
		char row=Character.toLowerCase(coordinate.charAt(0));
		coordinates[1]=Integer.parseInt(Character.toString(coordinate.charAt(1)));
		if(row=='a')
		{
			coordinates[0]=1;
		}
		if(row=='b')
		{
			coordinates[0]=2;
		}
		if(row=='c')
		{
			coordinates[0]=3;
		}
		if(row=='d')
		{
			coordinates[0]=4;
		}
		if(row=='e')
		{
			coordinates[0]=5;
		}
		if(row=='f')
		{
			coordinates[0]=6;
		}
		if(row=='g')
		{
			coordinates[0]=7;
		}
		if(row=='h')
		{
			coordinates[0]=8;
		}
		
		return coordinates;
		
		
	}
}
