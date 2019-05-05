import java.util.Scanner;
public class CS420_Game_Isolation_Board

{
	public char[][] board;
	public int[] cPlayer,oPlayer;  // keep track of position of x and y. position is [row][column]
									//player[0]=row player[1]=column
	public CS420_Game_Isolation_Board() //take in lower case c for computer, lowercase o for oponent
	{
		Scanner s=new Scanner(System.in);
		char playerToGoFirst;
		board=new char[9][9]; //9x9 because of titles (A-H column and 1-8 row)
		
		for(int i=0;i<board.length;i++)  //begin board initialization with headers
		{
			for(int j=0;j<board.length;j++)
			{
				board[i][j]='-';
			}
		}
		for(int i=1;i<9;i++)
		{
			board[0][i]=Integer.toString(i).charAt(0);
		}
		board[1][0]='A';
		board[2][0]='B';
		board[3][0]='C';
		board[4][0]='D';
		board[5][0]='E';
		board[6][0]='F';
		board[7][0]='G';
		board[8][0]='H';
		board[0][0]=' '; //end 
		cPlayer=new int[2];
		oPlayer=new int[2];
		
		System.out.println("Please enter the character corresponding to who goes first: C for computer, or O for opponent");
		playerToGoFirst=Character.toLowerCase(s.next().charAt(0));
		if(playerToGoFirst=='c')
		{
			cPlayer[0]=1;cPlayer[1]=1; //intial start if c is first is 1,1 
			board[1][1]='C';
			oPlayer[0]=8;oPlayer[1]=8; //initial start is 8,8 
			board[8][8]='O';
		}
		else
		{
			oPlayer[0]=1;oPlayer[1]=1; //intial start if c is first is 1,1 
			board[1][1]='O';
			cPlayer[0]=8;cPlayer[1]=8; //initial start is 8,8 
			board[8][8]='C';
		}
	}
	
	public void printBoard()
	{
		for(int i=0;i<board.length;i++)
		{
			for(int j=0;j<board.length;j++)
			{
				System.out.print(" "+board[i][j]+" ");
			}
			System.out.println();
		}
	}
	public void setC(int row, int column)
	{
		cPlayer[0]=row;
		cPlayer[1]=column;
	}
	public void setO(int row, int column)
	{
		oPlayer[0]=row;
		oPlayer[1]=column;
	}
	//only moves if the specified area has no # or if the other player is not occupying the space 
	public boolean move(char player, int row, int column)
	{
		boolean hasMoved=false;
		if(player=='c' && board[row][column]=='-')
		{
			board[cPlayer[0]][cPlayer[1]]='#';
			cPlayer[0]=row;cPlayer[1]=column;
			board[row][column]='C';
			hasMoved=true;
		}
		else if(player=='o' && board[row][column]=='-')
		{
			board[oPlayer[0]][oPlayer[1]]='#'; //replacing old position with #
			oPlayer[0]=row;oPlayer[1]=column;  //updating to new position
			hasMoved=true;
			board[row][column]='O';
		}
		return hasMoved;
	}
}
