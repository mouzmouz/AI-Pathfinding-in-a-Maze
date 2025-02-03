public class Node {
	
	private int[] pos;
	private int cost;
	private boolean visited;
	private String block;
	private boolean goal;
	private int dist;
	private Node parent;
	private int hs;

	public Node(int[]pos,int cost, boolean visited, String block, boolean goal,int dist,Node parent,int hs) {
		this.pos=pos;
		this.cost=cost;
		this.visited=visited;
		this.block=block;
		this.goal=goal;
		this.dist=dist;
		this.parent=parent;
		this.hs=hs;
	}
	
	public String getBlock() {
		return block;
	}
	
	public void setBlock(String newBlock) {
		this.block=newBlock;
	}
	
	public int[] getPos() {
		return pos;
	}
	
	public boolean getVisited() {
		return visited;
	}
	
	public void setVisited(boolean newCond) {
		this.visited=newCond;
	}
	
	public int getCost() {
		return cost;
	}
	
	public void setCost(int newCost) {
		this.cost=newCost;
	}

	public boolean getGoal() {
		return goal;
	}
	
	public int getDist() {
		return dist;
	}
	
	public void setDist(int newDist) {
		this.dist=newDist;
	}
	
	public Node getParent() {
		return parent;
	}
	
	public void setParent(Node pnode) {
		this.parent=pnode;
	}
	
	public int getHs() {
		return hs;
	}
	
	public void setHs(int newHs) {
		this.hs=newHs;
	}
}
