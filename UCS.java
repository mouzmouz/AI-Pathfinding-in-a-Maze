import java.util.ArrayList;
import java.util.Collections;

public class UCS {
	
	public UCS() {
		
	}
	
	public void uniformCostSearch(Labyrinth lab,Node S,int N) {
		int flag=0;
		int extensions=0;;
		ArrayList<Node> ns = new ArrayList<Node>();
		ns=lab.availableNodes(S,lab.legalMoves(S));
		while(flag==0 && ns.size()>0) {
			Node NN = minCostUcs(ns);
			if(NN.getVisited()==false) {
				if(NN.getGoal()==true) {
					System.out.print("reached goal "+ NN.getBlock());
					System.out.println(" node ["+NN.getPos()[0]+"]["+NN.getPos()[1]+"]");
					System.out.println("total extensions : "+extensions);
					System.out.println("total cost : "+NN.getDist());
					System.out.println();
					ucsPath(lab, NN);
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
	
	public void ucsPath(Labyrinth lab,Node goal){
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
	
	public Node minCostUcs(ArrayList<Node> allthenodes) {
		Node y = null;
		int minDist=Integer.MAX_VALUE;
		for(Node x:allthenodes) {
			updateDistUcs(x);
			int tempDist=x.getDist()+x.getCost();
			if(tempDist<minDist) {
				minDist=tempDist;
				y=x;
			}
		}
		return y;
	}
	
	public void updateDistUcs(Node node) {
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
