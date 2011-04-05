import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



import javax.jms.*;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;


/**
 * Use in conjunction with TopicPublisher to test the performance of ActiveMQ
 * Topics.
 */
public class TopicSub implements MessageListener {

	private Connection connection;
	private Session session;
	private Topic topic;
	private String url = "tcp://localhost:61616";
	private String jmxInfo;
	private int idSub;
	private String topicName;

	public TopicSub(String topicName) {
		this.idSub = idSub;
		this.topicName = topicName;
	}

	public void run() throws JMSException {
		// Connect to the provider and get the JMS connection
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(url);
		connection = factory.createConnection();
		// Create the JMS Session
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// Lookup the request and response queues
		topic = session.createTopic(topicName);
		jmxInfo = null;

		MessageConsumer consumer = session.createConsumer(topic);
		consumer.setMessageListener(this);

		connection.start();
		System.out.println("Waiting for messages...");
		try {
			// Run until enter is pressed
			BufferedReader stdin = new BufferedReader(new InputStreamReader(
					System.in));
			System.out.println("Subsciber application started");
			System.out.println("Press enter to quit application");
			stdin.readLine();
			this.exit();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

	private static boolean checkText(Message m, String s) {
		try {
			return m instanceof TextMessage
					&& ((TextMessage) m).getText().equals(s);
		} catch (JMSException e) {
			e.printStackTrace(System.out);
			return false;
		}
	}

	public void onMessage(Message message) {
		if (checkText(message, "SHUTDOWN")) {

			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace(System.out);
			}
		} else {
			// Get the data from the message
			TextMessage msg = (TextMessage) message;
			try {
				jmxInfo = msg.getText();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

	private void exit() {
		try {
			connection.close();
		} catch (JMSException jmse) {
			jmse.printStackTrace();
		}
		System.exit(0);
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getMessageJMX(){
		return jmxInfo;
	}

}