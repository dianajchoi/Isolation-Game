import java.util.ArrayList;
import java.util.Scanner;
public class CS420_Game_Isolation_Board

{
	public char[][] board;
	public int[] xPlayer,oPlayer;  // keep track of position of x and y. position is [row][column]
	//public CS420_Game_Isolation_Board[] parent;				//player[0]=row player[1]=column
	public char currPlayer;
	public ArrayList<state>children;
	//INITIAL BOARD CONSTRUCTOR 
	public CS420_Game_Isolation_Board() //take in lower case x for computer, lowercase o for oponent
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
		xPlayer=new int[2];
		oPlayer=new int[2];
		
		System.out.println("Please enter the character corresponding to who goes first: C for computer, or O for opponent");
		playerToGoFirst=Character.toLowerCase(s.next().charAt(0));
		if(playerToGoFirst=='x')
		{
			xPlayer[0]=1;xPlayer[1]=1; //intial start if c is first is 1,1 
			board[1][1]='X';
			oPlayer[0]=8;oPlayer[1]=8; //initial start is 8,8 
			board[8][8]='O';
			currPlayer='x';
		}
		else
		{
			oPlayer[0]=1;oPlayer[1]=1; //intial start if c is first is 1,1 
			board[1][1]='O';
			xPlayer[0]=8;xPlayer[1]=8; //initial start is 8,8 
			board[8][8]='X';
			currPlayer='o';
		}
	}
	public CS420_Game_Isolation_Board(CS420_Game_Isolation_Board anotherBoard) //take in lower case c for computer, lowercase o for oponent
	{
		board=new char[9][9];
		for(int i=0;i<anotherBoard.board.length;i++)
		{
			for(int j=0;j<anotherBoard.board.length;j++)
			{
				board[i][j]=anotherBoard.board[i][j];
			}
		}
		xPlayer[0]=anotherBoard.xPlayer[0];xPlayer[1]=anotherBoard.xPlayer[1];
		oPlayer[0]=anotherBoard.oPlayer[0];oPlayer[1]=anotherBoard.oPlayer[1];
	
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
		xPlayer[0]=row;
		xPlayer[1]=column;
	}
	public void setO(int row, int column)
	{
		oPlayer[0]=row;
		oPlayer[1]=column;
	}
	//only moves if the specified area has no # or if the other player is not occupying the space 
	public boolean move(char player, int newRow, int newColumn)
	{
		
		boolean hasMoved=false;
		boolean possibleMove=canMove(player,newRow,newColumn); 
		if(possibleMove && player=='x') //if here that means have passed all checks for a valid move. 
		{
			board[xPlayer[0]][xPlayer[1]]='#';   //updating old spot with a # 
			xPlayer[0]=newRow;xPlayer[1]=newColumn;     
			board[xPlayer[0]][xPlayer[1]]='X';     //updating boad to show new spot for C 
			hasMoved=true;
		}
		else if(player=='o' && possibleMove)
		{
			board[oPlayer[0]][oPlayer[1]]='#';
			oPlayer[0]=newRow;oPlayer[1]=newColumn;
			board[oPlayer[0]][oPlayer[1]]='O';
			hasMoved=true;
			
		}
		else
		{
			System.out.println("invalid move");
			
		}
		return hasMoved;
	}
	//this version of move can be used after checking if it CAN be moved so it skips the check!
	private boolean move(char player, int newRow, int newColumn,boolean can)
	{
		
		boolean hasMoved=false;
		if(player=='x') //if here that means have passed all checks for a valid move. 
		{
			board[xPlayer[0]][xPlayer[1]]='#';   //updating old spot with a # 
			xPlayer[0]=newRow;xPlayer[1]=newColumn;     
			board[xPlayer[0]][xPlayer[1]]='X';     //updating boad to show new spot for C 
			hasMoved=true;
		}
		else if(player=='o')
		{
			board[oPlayer[0]][oPlayer[1]]='#';
			oPlayer[0]=newRow;oPlayer[1]=newColumn;
			board[oPlayer[0]][oPlayer[1]]='O';
			hasMoved=true;
			
		}
		return hasMoved;
	}
	public boolean canMove(char player, int newRow,int newColumn)
	{
		if(newRow<1 || newRow>8 || newColumn<1 || newColumn<8) //CHECKING IF OUT OF BOUNDS
		{
			return false;
		}
		System.out.println("newRow: "+newRow);
		boolean hasMoved=true;
		int rowPlacement,columnPlacement,rowDistance,columnDistance;
		if(player=='x')
		{
			rowPlacement=xPlayer[0];columnPlacement=xPlayer[1];
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
		
		else if(rowPlacement<newRow && columnPlacement==newColumn) //moving player down, checking path till reach up
		{
			System.out.println("rowDistance: " + rowDistance);
			for(int i=1;i<rowDistance;i++)
			{
				if(board[newRow-i][columnPlacement]!='-')
				{
					System.out.println("Path obstructed, cant move down");
					return false;
				}
			}
		}
		
		else if(rowPlacement>newRow && columnPlacement==newColumn) //moving player up, so check path till reach down 
		{
			for(int i=1;i<rowDistance;i++)
			{
				if(board[newRow+i][columnPlacement]!='-')
				{
					System.out.println("Path obstructed, cant move up");
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
		return hasMoved;
	}
	
	public boolean hasLost(char player) 
	{
		int currRow;int currColumn;
		if(player=='x')
		{
			currRow=xPlayer[0];currColumn=xPlayer[1];
		}
		else
		{
			currRow=oPlayer[0];currColumn=xPlayer[1];
		}
		for(int i=-1;i<2;i++)
		{
			for(int j=-1;j<2;j++)
			{
				if(i!=0 || j!=0)  //we ignore curr placement so i and j cannot both be equal to 0
				{
					if(currRow+i<9 && currRow+i>0 && currColumn+j<9 && currColumn+j>0)//if within the boundaries of the playable board
					{
						if(board[currRow+i][currColumn+j]=='-')  //if there is empty space we still can move 
						{
							return false;
						}
					}
				}
			}
		}
		return true;  //if reach here then that means any surrounding square that we could be able to move to is used or occupied by opponent
	}
	
	//WILL GENERATE POTENTIAL MOVES AND RETURN IT AS ARRAYLIST OF OUR STATE NODES (the score parameter is empty for now) WE WILL USE EVAL FUNCTION TO FIX
	public ArrayList<state> genStates(char player, int row,int column, int depth)
	{
		ArrayList<state>offSpring=new ArrayList<state>();
		int rowPlacement,columnPlacement;
		if(player=='x')
		{
			rowPlacement=xPlayer[0];columnPlacement=xPlayer[1];
			
		}
		else
		{
			rowPlacement=oPlayer[0];columnPlacement=oPlayer[1];
		}
		rowPlacement+=1;columnPlacement+=1;
		CS420_Game_Isolation_Board newState=null;
		for(int i= -depth;i<depth+1;i++)
		{
			for(int j=-depth;j<depth+1;j++)
			{
				if(i!=0 || j!=0) //as long as not in same position
				{
					//GENERATING POSSIbLE STATES GIVEN DEPTH
					rowPlacement-=1;columnPlacement-=1;
					if(canMove(player,rowPlacement,columnPlacement))
					{
						newState=new CS420_Game_Isolation_Board(this); //make copy
						newState.move(player, rowPlacement+i+1,columnPlacement+j+1,true);
					}
					else
					{
						newState=null;
					}
					
					
					if(newState!=null)
					{
						offSpring.add(new state(newState));
					}
				}
			}
		}
		return offSpring;
		
	}
	//heuristic used will be avaialable white spaces
	public int heuristic(char player)
	{
		int empty=0;
		int rowPlacement,columnPlacement;
		if(player=='x')
		{
			rowPlacement=xPlayer[0];columnPlacement=xPlayer[1];
		}
		else
		{
			rowPlacement=oPlayer[0];columnPlacement=oPlayer[1];
		}
		
	}
	private class state  //will be like node 
	{
		CS420_Game_Isolation_Board nodeBoard;
		int score; 
		public state(CS420_Game_Isolation_Board board)
		{
			nodeBoard=new CS420_Game_Isolation_Board(board);
			score=0;
		}
	}
	
	
}

