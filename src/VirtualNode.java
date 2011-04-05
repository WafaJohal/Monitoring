public class VirtualNode implements VirtualNodeInterface{

	int id;
	int id_parents;
	int[] id_childrens;
	TopicPub pub;
	TopicSub sub;
	String topicParent;
	String topicChildrens;

	public VirtualNode(int id){
		this.id = id;
	}
	
	public void init(){
		initJMSSub(); // init the channels and setting that this node is subscriber
		initJMSPub();// init the channels and setting that this node is publisher
		
	}
	public void initJMSSub(){
		pub = new TopicPub( topicParent);
	}
	public void initJMSPub(){
		sub = new TopicSub( topicChildrens);
	}
	public void main(String args[]) throws Exception{
		pub.run();
		
	}
}
