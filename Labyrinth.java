import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Labyrinth {
	
	private Node[][] maze;
	private int N;
	private Node startS;
	private Node endX;
	private Node endY;
	
	public Labyrinth() {
		
	}
	
	public Labyrinth(int N,int p) {
		this.N=N;
		this.maze = new Node[N][N];
		Random seed = new Random(p);
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				int[] pos = {i,j};
				if(seed.nextInt(101)<p) {
					//maze[i][j]=1;
					Node cell = new Node(pos,0,false,"1",false,0,null,0);
					maze[i][j]=cell;
				}
				else {
					//maze[i][j]=0;
					Node cell = new Node(pos,1,false,"0",false,0,null,0);
					maze[i][j]=cell;
				}
			}
		}
	}
	
	public static int[] parameters(Scanner input) {
		//Scanner input = new Scanner(System.in);
		int N = 0;
		int p = 0;
		String in;
		int flag=0;
		int[] values = new int[2];
		while(flag==0) {
			try {
				System.out.println("Enter the size N of the Labyrinth");
				in = input.nextLine();
				N = Integer.parseInt(in);
				flag=1;
				if(N<0) {
					flag=0;
					System.out.println("N shouldn't be negative");
				}
			}catch (NumberFormatException ex) {
				System.out.println("Please enter a number");
				flag=0;
			}
		}
		flag=0;
		while(flag==0) {
			try {
				System.out.println("Enter percentage of blocked cells (0-100)");
				in = input.nextLine();
				p = Integer.parseInt(in);
				flag=1;
				if(p<0 || p>100) {
					flag=0;
					System.out.println("between 0 and 100");
				}
			}catch (NumberFormatException ex) {
				System.out.println("Please enter a number");
				flag=0;
			}
		}
		//input.close();
		values[0]=N;
		values[1]=p;
		return values;
	}
	
	public  Node[][] labyrinthSetup(Scanner input) {
		//Scanner input = new Scanner(System.in);
		int flag;
		do {
			flag=1;
			System.out.println("Enter the start S of the Labyrinth (row,column)");
			String start = input.nextLine();
			String[] S = start.split(",");
			if(S.length==2) {
				int S1 = Integer.parseInt(S[0]);
				int S2 = Integer.parseInt(S[1]);
				if(((S1>=0 && S1<N) && (S2>=0 && S2<N)) && (maze[S1][S2].getBlock().equals("0"))) {
					//maze[S1][S2]=2;
					int[] pos = {S1,S2};
					Node cell = new Node(pos,0,false,"S",false,0,null,0);
					startS=cell;
					maze[S1][S2]=cell;
					break;
				}
			}
			System.out.println("False Input");
			flag=0;
		}while(flag==0);
		do {
			flag=1;
			System.out.println("Enter the first exit X of the Labyrinth (row,column)");
			String end1 = input.nextLine();
			String[] X = end1.split(",");
			if(X.length==2) {
				int X1 = Integer.parseInt(X[0]);
				int X2 = Integer.parseInt(X[1]);
				if(((X1>=0 && X1<N) && (X2>=0 && X2<N)) && (maze[X1][X2].getBlock().equals("0"))) {
					int[] pos = {X1,X2};
					Node cell = new Node(pos,1,false,"X",true,0,null,0);
					endX=cell;
					maze[X1][X2]=cell;
					break;
				}
			}
			System.out.println("False Input");
			flag=0;
		}while(flag==0);
		do {
			flag=1;
			System.out.println("Enter the second exit Y of the Labyrinth (row,column)");
			String end2 = input.nextLine();
			String[] Y = end2.split(",");
			if(Y.length==2) {
				int Y1 = Integer.parseInt(Y[0]);
				int Y2 = Integer.parseInt(Y[1]);
				if(((Y1>=0 && Y1<N) && (Y2>=0 && Y2<N)) && (maze[Y1][Y2].getBlock().equals("0"))) {
					int[] pos = {Y1,Y2};
					Node cell = new Node(pos,1,false,"Y",true,0,null,0);
					endY=cell;
					maze[Y1][Y2]=cell;
					break;
				}
			}
			System.out.println("False Input");
			flag=0;
		}while(flag==0);
		//input.close();
		return maze;
	}

	public void printLabyrinth() {
		System.out.print("        ");
		for(int i=0;i<N;i++) {
			System.out.print(i+"   ");
		}
		System.out.println();
	    System.out.println();
	    for(int i=0;i<N;i++) {
	    	System.out.print(i+"   |   ");
	    	for(int j=0;j<N;j++) {
	    		if(this.maze[i][j].getBlock().equals("S")) {
	    			System.out.print("S"+"   ");
	    		}
	    		else if(this.maze[i][j].getBlock().equals("X")) {
	    			System.out.print("X"+"   ");
	    		}
	    		else if(this.maze[i][j].getBlock().equals("Y")) {
	    			System.out.print("Y"+"   ");
	    		}
	    		else {
	    			System.out.print(this.maze[i][j].getBlock()+"   ");
	    		}
	    	}
	    	System.out.println("|");
	    	System.out.println();
	    }
	}
	
	public ArrayList<int[]> legalMoves(Node cell) {
		ArrayList<int[]> moves = new ArrayList<int[]>();
		int[] pos = cell.getPos();
		//UP
		if(pos[0]-1>=0 && !maze[pos[0]-1][pos[1]].getBlock().equals("1")) {
			int[] matrix = new int[2];
			matrix[0]= pos[0]-1;
			matrix[1]= pos[1];
			moves.add(matrix);
		}
		//UP-RIGHT
		if(pos[0]-1>=0 && pos[1]+1<N && !maze[pos[0]-1][pos[1]+1].getBlock().equals("1") ) {
			int[] matrix = new int[2];
			matrix[0]= pos[0]-1;
			matrix[1]= pos[1]+1;
			moves.add(matrix);
		}
		//RIGHT
		if(pos[1]+1<N && !maze[pos[0]][pos[1]+1].getBlock().equals("1")) {
			int[] matrix = new int[2];
			matrix[0]= pos[0];
			matrix[1]= pos[1]+1;
			moves.add(matrix);
		}
		//DOWN-RIGHT
		if(pos[0]+1<N && pos[1]+1<N && !maze[pos[0]+1][pos[1]+1].getBlock().equals("1")) {
			int[] matrix = new int[2];
			matrix[0]= pos[0]+1;
			matrix[1]= pos[1]+1;
			moves.add(matrix);
		}
		//DOWN
		if(pos[0]+1<N && !maze[pos[0]+1][pos[1]].getBlock().equals("1")) {
			int[] matrix = new int[2];
			matrix[0]= pos[0]+1;
			matrix[1]= pos[1];
			moves.add(matrix);
		}
		//DOWN-LEFT
		if(pos[0]+1<N && pos[1]-1>=0 && !maze[pos[0]+1][pos[1]-1].getBlock().equals("1")) {
			int[] matrix = new int[2];
			matrix[0]= pos[0]+1;
			matrix[1]= pos[1]-1;
			moves.add(matrix);
		}
		//LEFT
		if(pos[1]-1>=0 && !maze[pos[0]][pos[1]-1].getBlock().equals("1")) {
			int[] matrix = new int[2];
			matrix[0]= pos[0];
			matrix[1]= pos[1]-1;
			moves.add(matrix);
		}
		//UP-LEFT
		if(pos[0]-1>=0 && pos[1]-1>=0 && !maze[pos[0]-1][pos[1]-1].getBlock().equals("1")) {
			int[] matrix = new int[2];
			matrix[0]= pos[0]-1;
			matrix[1]= pos[1]-1;
			moves.add(matrix);
		}
		cell.setVisited(true);
		return moves;
	}
	
	public ArrayList<Node> availableNodes(Node node,ArrayList<int[]> moves) {
		ArrayList<Node> nodes=new ArrayList<Node>();
		for(int[] x : moves) {
			Node cell = maze[x[0]][x[1]];
			if(cell.getVisited()==false) {
				if(cell.getParent()==null) {
					cell.setParent(node);
				}
				nodes.add(cell);
			}
		}
		return nodes;
	}
	
	public void resetLabyrinth(Node goal) {
		startS.setBlock("S");
		endX.setBlock("X");
		endY.setBlock("Y");
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				maze[i][j].setVisited(false);
				if(maze[i][j].getBlock().equals("*")) {
					maze[i][j].setBlock("0");
				}
			}
		}
	}
	
	public Node getStart() {
		return startS;
	}
	
	public Node[][] getMaze(){
		return maze;
	}
	
	public Node getEndX() {
		return endX;
	}
	
	public Node getEndY() {
		return endY;
	}
}
