package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.messages.Message;
import no.hvl.dat110.messages.PublishMsg;
import no.hvl.dat110.common.TODO;

public class DisplayDevice {
	
	private static final int COUNT = 10;
		
	public static void main (String[] args) {
		
		System.out.println("Display starting ...");
		
		// TODO - START
				
		// create a client object and use it to
		// - connect to the broker - use "display" as the user name
		Client client = new Client("display", Common.BROKERHOST, Common.BROKERPORT);
		client.connect();
		
		// - create the temperature topic on the broker
		client.createTopic("temperature");
		
		// - subscribe to the topic
		client.subscribe("temperature");
		
		// - receive messages on the topic
		for (int i = 0; i < COUNT; i++) {
			
			PublishMsg msg = (PublishMsg) client.receive();
			System.out.println("DISPLAY: " + msg.getMessage());
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// - unsubscribe from the topic
		client.unsubscribe("temperature");

		// - disconnect from the broker
		client.disconnect();
		
		// TODO - END
		
		System.out.println("Display stopping ... ");
		
	}
}
