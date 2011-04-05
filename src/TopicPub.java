import java.util.Arrays;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.console.command.store.amq.CommandLineSupport;

/**
 * Use in conjunction with TopicListener to test the performance of ActiveMQ
 * Topics.
 */
public class TopicPub {

	private static final char[] DATA = "abcdefghijklmnopqrstuvwxyz"
			.toCharArray();

	private Connection connection;
	private Session session;
	private MessageProducer publisher;
	private Topic topic;
	private String topicName;
	private String url = "tcp://localhost:61616";
	
	private String jmxInfo;
	
	public TopicPub( String topicName){
		
		this.topicName = topicName;
	}
	


	public void run() throws Exception {

		// 1. Get an initial context to the JMS provider.
		// 2. Look up the connection factory.
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(url);
		// 3. Create a JMS connection.
		connection = factory.createConnection();
		// 4. Create a JMS session.
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		topic = session.createTopic(topicName);

		publisher = session.createProducer(topic);
		publisher.setDeliveryMode(DeliveryMode.PERSISTENT);

		// 5. Look up the destination.
		// 6. Start the connection.
		connection.start();
		publishJMXInfo();

		// request shutdown
		publisher.send(session.createTextMessage("SHUTDOWN"));

		connection.stop();
		connection.close();
	}

	private void publishJMXInfo() throws Exception {

		// send events
		TextMessage msg = session.createTextMessage();
		msg.setText(jmxInfo);
		// publish!!!!!
		publisher.send(msg);
		System.out.println("Sent the message");

	}



	public void setTopicName(String topicn) {
		this.topicName = topicn;
	}
	public String getTopicName() {
		return this.topicName;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
}