package chapter1;

import org.zeromq.*;
import java.util.*;

public class WuServer {
	
	public static void main(String[] args)
	{
		ZMQ.Context ctx = ZMQ.context(1);
		
		ZMQ.Socket publisher = ctx.socket(ZMQ.PUB);
		
		publisher.bind("tcp://*:5556");
		publisher.bind("ipc://weather.ipc");
		
		Random rand = new Random();
		
		while(! Thread.currentThread().isInterrupted())
		{
			int zipcode, temperature, relhumidity;
			
			zipcode = rand.nextInt(5);
			temperature = rand.nextInt(215)-80;
			relhumidity = rand.nextInt(50) + 10;
			
			String send = String.format("%d %d %d", zipcode,temperature, relhumidity);
			
			//System.out.println("kirim cuy.."+ send);
			
			publisher.send(send.getBytes() );
		}//end while
		
		publisher.close();
		ctx.close();
		
	}//end of method
}//end of class
