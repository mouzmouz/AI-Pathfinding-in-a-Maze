import java.util.ArrayList;
import java.util.Collections;

public class AStar {
	
	public AStar() {
		
	}
	
	public void A_Star(Labyrinth lab,Node S,int N) {
		initializeHeuristics(lab.getEndX(),lab.getEndY(),lab.getMaze(),N);
		int flag=0;
		int extensions=0;;
		ArrayList<Node> ns = new ArrayList<Node>();
		ns=lab.availableNodes(S,lab.legalMoves(S));
		while(flag==0 && ns.size()>0) {
			Node NN = minCostAStar(ns);
			if(NN.getVisited()==false) {
				if(NN.getGoal()==true) {
					System.out.print("reached goal "+ NN.getBlock());
					System.out.println(" node ["+NN.getPos()[0]+"]["+NN.getPos()[1]+"]");
					System.out.println("total extensions : "+extensions);
					System.out.println("total cost : "+NN.getDist());
					System.out.println();
					AStarPath(lab,NN);
					flag=1;
				}
				else {
					extensions++;
					ArrayList<Node> nss = new ArrayList<Node>();
					nss=lab.availableNodes(NN,lab.legalMoves(NN));
					ns.remove(NN);
					ns.addAll(nss);
				}
			}
			else {
				ns.remove(NN);
			}
		}
		if(flag==0) {
			System.out.println("Couldn't find a solution");
		}
	}
	
	public void AStarPath(Labyrinth lab,Node goal){
		ArrayList<Node> path=new ArrayList<Node>();
		Node start=goal;
		Node temp=null;
		while(!start.getBlock().equals("S")) {
			temp=start.getParent();
			start=temp;
			path.add(start);
		}
		Collections.reverse(path);
		for(Node x:path) {
			System.out.print("["+x.getPos()[0]+"]["+x.getPos()[1]+"]");
			System.out.print("-->");
			x.setBlock("*");
		}
		lab.getStart().setBlock("$");
		goal.setBlock("$");
		System.out.println("["+goal.getPos()[0]+"]["+goal.getPos()[1]+"]");
		System.out.println();
		lab.printLabyrinth();
		lab.resetLabyrinth(goal);
	}
	
	public void initializeHeuristics(Node endX, Node endY,Node[][] maze,int N) {
		int xx=endX.getPos()[0];
		int xy=endX.getPos()[1];
		int yx=endY.getPos()[0];
		int yy=endY.getPos()[1];
		
		int hx=0;
		int hy=0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				Node cell = maze[i][j];
				if(cell.getGoal()==true) {
					cell.setHs(0);
				}
				else {
					hx= Math.abs(i-xx)+Math.abs(j-xy);
					hy = Math.abs(i-yx)+Math.abs(j-yy);
					cell.setHs(Math.max(hx,hy));
				}
			}
		}
	}
	
	private Node minCostAStar(ArrayList<Node> allthenodes) {
		Node y = null;
		int minDist=Integer.MAX_VALUE;
		for(Node x:allthenodes) {
			updateDistAStar(x);
			int tempDist=x.getDist()+x.getHs()+x.getCost();
			if(tempDist<minDist) {
				minDist=tempDist;
				y=x;
			}
		}
		return y;
	}
	
	public void updateDistAStar(Node node) {
		int x=node.getPos()[0];
		int y=node.getPos()[1];
		if(x<y) {
			node.setDist(y);
		}
		else {
			node.setDist(x);
		}
	}
}
