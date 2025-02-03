import java.util.Scanner;

public class LabyrinthSovler {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int[] values = new int[2];
		values=Labyrinth.parameters(input);
		int N=values[0];
		int p=values[1];
		Labyrinth lab = new Labyrinth(N,p);
		lab.printLabyrinth();
		lab.labyrinthSetup(input);
		input.close();
		lab.printLabyrinth();
		System.out.println("Solving using the UCS method...");
		UCS ucs = new UCS();
		ucs.uniformCostSearch(lab,lab.getStart(), N);
		System.out.println("Solving using the A_Star method...");
		AStar astar = new AStar();
		astar.A_Star(lab, lab.getStart(), N);
	}

}
