import java.util.Scanner;
public class CS420_A3_Choi_Yee_Driver {
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
		initial.printBoard();
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
				/*System.out.println("Xpponent's move is: ");
				moveInput=s.next();
				move=convertToCoordinates(moveInput);
				if(initial.move('x',move[0],move[1]))
				{
					currPlayer='o';
					moved+=1;
					initial.printBoard();
				}*/
				
				System.out.println("Computer1 choosing move...");
				initial = initial.findNextBestMove(7, -Integer.MAX_VALUE, Integer.MAX_VALUE, 'x').nodeBoard;
				currPlayer = 'o';
				moved += 1;
				initial.printBoard();
			}
			else if(currPlayer=='o')
			{
				/*System.out.println("Opponent's turn is: ");
				moveInput=s.next();
				move=convertToCoordinates(moveInput);
				if(initial.move('o', move[0], move[1]))
				{
					currPlayer='x';
					moved+=1;
					initial.printBoard();
				}*/
				
				System.out.println("Computer2 choosing move...");
				initial = initial.findNextBestMove(7, -Integer.MAX_VALUE, Integer.MAX_VALUE, 'o').nodeBoard;
				currPlayer = 'x';
				moved += 1;
				initial.printBoard();
			}
			
			if(initial.hasLost('x')) {
				System.out.println("O has won!");
				return;
			}else if(initial.hasLost('o')) {
				System.out.println("X has won!");
				return;
			}
				
		}
	}
	
	public static int[] convertToCoordinates(String coordinate)
	{
		int[] coordinates=new int[2];
		char row=Character.toLowerCase(coordinate.charAt(0));
		coordinates[1]=Integer.parseInt(Character.toString(coordinate.charAt(1)));
		switch(row) {
		case 'a':
			coordinates[0] = 1;
			break;
		case 'b':
			coordinates[0] = 2;
			break;
		case 'c':
			coordinates[0] = 3;
			break;
		case 'd':
			coordinates[0] = 4;
			break;
		case 'e':
			coordinates[0] = 5;
			break;
		case 'f':
			coordinates[0] = 6;
			break;
		case 'g':
			coordinates[0] = 7;
			break;
		case 'h':
			coordinates[0] = 8;
			break;
		default:
			break;
		}
		
		return coordinates;
	}
}
