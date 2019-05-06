import java.util.ArrayList;
import java.util.Scanner;
public class Driver {
	public static void main(String[] args)
	{
		ArrayList<turn> history=new ArrayList<turn>();
		int counterTurn=1;
		int moved=0;
		Scanner s=new Scanner(System.in);
		char currPlayer;
		String moveInput="";
		int[] move;
		System.out.println("Computer (X)  vs.  Opponent (O) ISOLATION ");
		Board initial=new Board();
		currPlayer=initial.currPlayer;
		initial.printBoard();
		int[] cpuMove=new int[2];
		String cpuMoveConvert="";
		turn node;
		System.out.println("INITIAL BOARD");
		initial.printBoard();
		System.out.println();
		while(true)
		{
			if(moved==2)
			{
				history.add(new turn(counterTurn,cpuMoveConvert,moveInput));
				System.out.println();
				initial.printBoard();
				System.out.println("Computer vs Opponent");
				System.out.println("====================");
				for(turn thisTurn: history)
				{
					System.out.println("Turn: "+thisTurn.turn+ "  CPU move: "+thisTurn.cpuMove+"  Opponent move: "+thisTurn.opponentMove+"\n");
				}
				System.out.println();
				counterTurn+=1;
				moved=0;
			}
			//System.out.println("TURN: "+counterTurn);
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
				
				cpuMove[0]=initial.xPlayer[0];cpuMove[1]=initial.xPlayer[1];
				cpuMoveConvert=convertMoveToString(cpuMove);
				initial.printBoard();
				System.out.println("Computer's move is: "+cpuMoveConvert+"\n");
				currPlayer = 'o';
				moved += 1;
				
			}
			else if(currPlayer=='o')
			{
				System.out.println("Enter opponent's move: ");
				moveInput=s.next();
				move=convertToCoordinates(moveInput);
				if(initial.move('o', move[0], move[1]))
				{
					currPlayer='x';
					moved+=1;
					initial.printBoard();
					System.out.println();
				}
				
//				System.out.println("Computer2 choosing move...");
//				initial = initial.findNextBestMove(7, -Integer.MAX_VALUE, Integer.MAX_VALUE, 'o').nodeBoard;
//				currPlayer = 'x';
//				moved += 1;
//				initial.printBoard();
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
	public static String convertMoveToString(int[] move)
	{
		
		String result="";
		switch(move[0]) {
		case 1:
			result+="A";
			break;
		case 2:
			result+="B";
			break;
		case 3:
			result+="C";
			break;
		case 4:
			result+="D";
			break;
		case 5:
			result+="E";
			break;
		case 6:
			result+="F";
			break;
		case 7:
			result+="G";
			break;
		case 8:
			result+="H";
			break;
		default:
			break;
		}
		result+=move[1];
		return result;
	}
	private static class turn
	{
		int turn;
		String cpuMove;
		String opponentMove;
		public turn(int turnNumber, String cpu, String opponent)
		{
			turn=turnNumber;
			cpuMove=cpu;
			opponentMove=opponent;
		}
	}
}
