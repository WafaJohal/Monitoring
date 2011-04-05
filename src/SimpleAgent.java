import javax.management.*;
import java.lang.management.*;

public class SimpleAgent {
   private MBeanServer mbs = null;
   private int id;
/*********************************/
   /*
    * method .getProcessCpuTime()
    * from class OperatingSystemMXBean
    * */
   public SimpleAgent() {

      // Get the platform MBeanServer
       mbs = ManagementFactory.getPlatformMBeanServer();

      // Unique identification of MBeans
      VirtualNode nodeBean = new VirtualNode(id);
      ObjectName nodeName = null;

      try {
         // Uniquely identify the MBeans and register them with the platform MBeanServer 
         nodeName = new ObjectName("SimpleAgent:name=nodethere");
         mbs.registerMBean(nodeBean, nodeName);
      } catch(Exception e) {
         e.printStackTrace();
      }
   }

   // Utility method: so that the application continues to run
   private static void waitForEnterPressed() {
      try {
         System.out.println("Press  to continue...");
         System.in.read();
      } catch (Exception e) {
         e.printStackTrace();
      }
    }

   public static void main(String argv[]) {
      SimpleAgent agent = new SimpleAgent();
      System.out.println("SimpleAgent is running...");
      SimpleAgent.waitForEnterPressed();
   }
}
