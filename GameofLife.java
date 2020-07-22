package test;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class GameofLife {
	public static void printWorld(char[][] world) {
		//prints 2d array
		for (char arr[] : world) {
			for (char c : arr) System.out.print(c+" ");
			System.out.print("\n");
		}
	}
	public static int adjCells(int y,int x, char[][] world) {
		//returns an int value that is the number of adjacent cells a certain cell of (x,y) has
		final int size = (world.length-1); 
		int count = 0;
		//BOTTOM
		if (y==size) {//bottom left
			if(x==size) {
				if (world[y-1][x] == 'X') {
					count++;
				}
				if (world[y-1][x-1] == 'X') 
					count++;		
							
				if (world[y][x-1] == 'X') {
					count++;
				} 
			}
			else if (x==0) { //bottom right
				if (world[y-1][x] == 'X') {
					count++;}
				if (world[y-1][x+1] == 'X') {
					count++;}
				if (world[y][x+1] == 'X') {
					count++;}
				
			}
			//bottom side
			else {
				if (world[y-1][x] == 'X') {
					count++;}
				if (world[y-1][x+1] == 'X') {
					count++;		}
				if (world[y-1][x-1] == 'X') {
					count++;}
				if (world[y][x-1] == 'X') {
					count++;}
				if (world[y][x+1] == 'X') {
					count++;}
			}
		}
		
		//TOP
		if (y==0) {
			if(x==size) {//top right
				if (world[y+1][x] == 'X') {
				count++;}
				if (world[y+1][x-1] == 'X') {
					count++;}
				if (world[y][x-1] == 'X') {
					count++;}
			} else if (x==0) { //top left
				if (world[y+1][x] == 'X') {
					count++;
				}
				if (world[y+1][x+1] == 'X') {
					count++;		
							}
				if (world[y][x+1] == 'X') {
					count++;
				}
			} 
			else { //top side
				
				if (world[y+1][x] == 'X') {
					count++;
				}
				if (world[y+1][x-1] == 'X') {
					count++;		
							}
				if (world[y+1][x+1] == 'X') {
					count++;
				}
				if (world[y][x-1] == 'X') {
					count++;		
				}
				if (world[y][x+1] == 'X') {
					count++;
				}
			} 
		} 
		//left side
		if (x==0 && y!=0 && y!=size) {
			if (world[y-1][x] == 'X') {
				count++;
			}
			if (world[y-1][x+1] == 'X') {
				count++;		
			}
			if (world[y+1][x] == 'X') {
				count++;
			}
			if (world[y+1][x+1] == 'X') {
				count++;
			}
			if (world[y][x+1] == 'X') {
				count++;
			}
		} 
		
		//right side
		if (x==size && y!=0 && y!=size) {
			if (world[y-1][x] == 'X') {
				count++;
			}
			if (world[y-1][x-1] == 'X') {
				count++;
			}
			if (world[y+1][x] == 'X') {
				count++;
			}
			if (world[y+1][x-1] == 'X') {
				count++;		
			}
			if (world[y][x-1] == 'X') {
				count++;		
			}
		}
		
	//INSIDE
		if (x<size && y<size && x>0 &&y>0) {
				if (world[y-1][x] == 'X') {
					count++;
				}
				if (world[y-1][x+1] == 'X') {
					count++;		
				}
				if (world[y-1][x-1] == 'X') {
					count++;
				}
				if (world[y+1][x] == 'X') {
					count++;
				}
				if (world[y+1][x-1] == 'X') {
					count++;		
				}
				if (world[y+1][x+1] == 'X') {
					count++;
				}
				if (world[y][x-1] == 'X') {
					count++;		
				}
				if (world[y][x+1] == 'X') {
					count++;
				}

			}
		return count;
	}
	public static void main(String[] args) {
		//creates world
		final int size = 20; //CHANGE HERE!
		char world[][] = new char[size][size];
		for (int x = 0; x<size; x++) {
			for (int y = 0; y<size; y++) {
				world[y][x] = '0';
			}
		}
		
		//user creates cells
		System.out.println("Welcome to Game of Life! \n You have a 20x20 grid that is your \"world\" and you input cells. \n  Cells will survive if they have exactly 2 or 3 adjacent cells. \n  Cells will become alive if they have exactly 3 adjacent cells. \n  Cells will die if they have an amount of adjacent cells not exactly 2 or 3.");
		System.out.println("");
		Scanner in = new Scanner(System.in);
		System.out.println("Number of starting cells:");
		int numcell = in.nextInt();
		System.out.println("Now, input your coordinates. (0,0) is in the top left corner.");
		for (int i = 1; i<=numcell;i++) {
			System.out.println("Cell " + i );
			System.out.println("x coordinate: ");
			int x = in.nextInt();
			System.out.println("y coordinate: ");
			int y = in.nextInt();
			world[y-1][x-1]= 'X';
		}
		printWorld(world);
		
		System.out.println();
		
		//start game
		int gameinprogress = 0;
		while (gameinprogress == 0) {
			System.out.print("\n Type 0 to continue. To stop, type 1.");
			gameinprogress=in.nextInt();
			if (gameinprogress == 1) {
				System.out.println("The game will now end. Thx for playing.");
			}
			else {
				//turns adjCells into an array
				int[] countarray = new int[size*size];
				int i =0;
				for (int y=0; y<size;y++) {
					for (int x =0; x<size;x++) {
						
						
				
						countarray[i] = adjCells(y,x,world);
						i++;
					}
				}
				System.out.println();
				//iterate once
				i = 0;
				for (int y=0; y<size;y++) {
					for (int x =0; x<size;x++) {
						
						if (world[y][x] == '0' && countarray[i] == 3) {
							world[y][x]= 'X';
						} 
						else if (world[y][x]=='X' && (countarray[i] == 2 || countarray[i] == 3)) {
							world[y][x]= 'X';
						}
						else if (world[y][x] == 'X' && (countarray[i] != 2 && countarray[i] != 3)) {
							world[y][x]= '0';
						}
						else { 
							world[y][x]= '0';
							}
						i++;
						}
					}			
				printWorld(world);

			}
	
				
		}
	
		in.close();
	}

}
