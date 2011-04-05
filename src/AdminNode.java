
import javax.naming.*;
public class AdminNode extends VirtualNode implements AdminNodeInterface{

	int n; //number of nodes
	int a; // arity of the tree
	int d; //depth of the tree
	static String topicChildrens;
	
	public AdminNode(int n, int a, int d){
		super(0);
		this.n = n; 
		this.a = a;
		this.d = d;
	}
	public AdminNode(int n, int a){
		this(n, a,  n%a +1);
	}
	public AdminNode(int n){
		this(n, 2,n%2+1);
	}
	
	public void createChannels(){
		//create n-1 JMS channels
	}
	
	public void createNodes() {
		for(int i = 0; i<n; i++){
			new VirtualNode(n);
			
		}
		
	}
	
	/**
	 * @param args
	 */
	public void main(String[] args) {
		createChannels();
		createNodes();

	}


}
