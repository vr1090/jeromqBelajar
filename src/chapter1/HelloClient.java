package chapter1;

import org.zeromq.*;

public class HelloClient {
	public static void main(String[] args)
	{
		ZMQ.Context ctx = ZMQ.context(1);
		
		ZMQ.Socket requester = ctx.socket(ZMQ.REQ);
		
		System.out.println("connecting to server..");
		
		requester.connect("tcp://localhost:5555");
		
		for( int req=0; req <10; req++)
		{
			String request ="hello";
			
			requester.send( request.getBytes(),0);
			
			byte[] reply = requester.recv(0);
			
			System.out.println("received.."+ new String(reply)+" "+ (req+1) );
		}//end for
		
		requester.close();
		ctx.close();
		
		
		
	}//end of method
}//end of class
