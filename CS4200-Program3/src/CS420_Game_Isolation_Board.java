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
		System.out.println();
		for(int i=0;i<board.length;i++)
		{
			for(int j=0;j<board.length;j++)
			{
				System.out.print(" "+board[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
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
	public boolean move(char player, int newRow, int newColumn)
	{
		if(newRow<1 || newRow>8 || newColumn<1 ||newColumn>8)
		{
			System.out.println("The specified move is not within the board indexes.");
			return false;
		}
				
		boolean hasMoved=false;
		int rowPlacement,columnPlacement,rowDistance,columnDistance;
		if(player=='c')
		{
			rowPlacement=cPlayer[0];columnPlacement=cPlayer[1];
		}
		else
		{
			rowPlacement=oPlayer[0];columnPlacement=oPlayer[1];
		}
		
		rowDistance=Math.abs(newRow-rowPlacement);columnDistance=Math.abs(newColumn-columnPlacement);
		if(rowPlacement==newRow && columnPlacement==newColumn) //if in same position, return not moved
		{
			return false;
		}
		
		else if(rowPlacement<newRow && columnPlacement==newColumn) //moving player up, checking path till reach up
		{
			for(int i=1;i<=rowDistance;i++)
			{
				if(board[newRow-i][columnPlacement]!='-')
				{
					System.out.println("Path obstructed, cant move up");
					return false;
				}
			}
		}
		
		else if(rowPlacement>newRow && columnPlacement==newColumn) //moving player down, so check path till reach down 
		{
			for(int i=1;i<rowDistance;i++)
			{
				if(board[newRow+i][columnPlacement]!='-')
				{
					System.out.println("Path obstructed, cant move down");
					return false;
				}
			}
		}
		
		else if(rowPlacement==newRow && columnPlacement>newColumn)  //moving player left...
		{
			for(int i=1;i<columnDistance;i++)
			{
				if(board[rowPlacement][newColumn+i]!='-')
				{
					System.out.println("Path obstructed, cant move left");
					return false;
				}
			}
		}
		
		else if(rowPlacement==newRow && columnPlacement<newColumn) //moving right...
		{
			for(int i=1;i<columnDistance;i++)
			{
				if(board[rowPlacement][newColumn-i]!='-')
				{
					System.out.println("Path obstructed, cant move right");
					return false;
				}
			}
		}
		else if(rowDistance==columnDistance) 
		{
			if(newRow<rowPlacement && newColumn<columnPlacement)  //checking diagnal up left 
			{
				for(int i=1;i<=rowDistance;i++)
				{
					if(board[rowPlacement-i][columnPlacement-i]!='-')
					{
						System.out.println("Path obstructed, cant move diagnal up left");
						return false;
					}
				}
			}
			else if(newRow > rowPlacement && newColumn < columnPlacement) //checking diagnal down left
			{
				for(int i=1;i<=rowDistance;i++)
				{
					if(board[rowPlacement+i][columnPlacement-i]!='-')
					{
						System.out.println("Path obstructed, cant move diaganal down left");
						return false;
					}
				}
			}
			else if(newRow<rowPlacement && newColumn>columnPlacement) //checking diagnal up right
			{
				for(int i=1;i<=rowDistance;i++)
				{
					if(board[rowPlacement-i][columnPlacement+i]!='-')
					{
						System.out.println("Path obstructed, cant move diagnal up right");
						return false;
					}
				}
			}
			else if(newRow>rowPlacement && newColumn>columnPlacement)  //checking down right diagnal
			{
				for(int i=1;i<=rowDistance;i++)
				{
					if(board[rowPlacement+i][columnPlacement+i]!='-')
					{
						System.out.println("Path obstructed, cant move diagnal down right");
						return false;
					}
				}
			}
			
		}
		else
		{
			System.out.println("invalid move at:    ROW: "+newRow+"  COLUMN: "+newColumn);
			return false;
		}
		if(player=='c') //if here that means have passed all checks for a valid move. 
		{
			board[cPlayer[0]][cPlayer[1]]='#';   //updating old spot with a # 
			cPlayer[0]=newRow;cPlayer[1]=newColumn;     
			board[cPlayer[0]][cPlayer[1]]='C';     //updating boad to show new spot for C 
			hasMoved=true;
		}
		else if(player=='o')
		{
			board[oPlayer[0]][oPlayer[1]]='#';
			oPlayer[0]=newRow;oPlayer[1]=newColumn;
			board[oPlayer[0]][oPlayer[1]]='O';
			hasMoved=true;
			
		}
		else
		{
			System.out.println("invalid player.");
			
		}
		return hasMoved;
	}
}