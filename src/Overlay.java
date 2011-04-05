import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author wafa
 *handle the structure of the tree by calling the node and join them
 *starter of the program
 *create node ojbcte and channel and then connect 
 */
public class Overlay {
	      
	int N ; 
	
	
	public void init(){	
		
	Properties configFile = new Properties();
	      try {
			configFile.load(this.getClass().getClassLoader().getResourceAsStream("/config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		N = Integer.parseInt(configFile.getProperty("N"));
	}

	public void main(String[] args){
		// 1° build communication network over the nodes
		//create N instances of the nodes and connect them in a network
	}
}
