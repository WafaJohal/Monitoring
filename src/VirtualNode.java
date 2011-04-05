import java.lang.management.ManagementFactory;



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
	public void getLocalJMXInfo(){
		/*com.sun.management.OperatingSystemMXBean mxbean =(com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

			// Get the number of processors
			int numProcessors = mxbean.getAvailableProcessors();

			// Get the Sun-specific attribute Process CPU time
			long cpuTime = mxbean.getProcessCpuTime();
	*/
	}
	
	
	/**every xsecond
	 * get its info
	 * get info from childern
	 * aggreagate
	 * send to parent
	 */
}
