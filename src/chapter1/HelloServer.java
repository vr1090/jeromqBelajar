package chapter1;

import org.zeromq.ZMQ;

public class HelloServer {
	
	public static void main(String[] args) throws Exception
	{
		//bikin context
		ZMQ.Context ctx = ZMQ.context(1);
		
		//bikin socket om
		ZMQ.Socket responder = ctx.socket(ZMQ.REP);
		
		
		System.out.println("server jalan..");
		
		//bind ah
		responder.bind("tcp://*:5555");
		
		System.out.println("bind serhasil neh");
		
		while( !Thread.currentThread().isInterrupted())
		{
			byte[] request = responder.recv(0);
			
			System.out.println("receiver hello..");
			
			Thread.sleep(1000);
			
			String reply ="world";
			responder.send( reply.getBytes(),0);
			
			
		}//end while
		
		responder.close();
		ctx.close();
		
		System.out.println("bye..");
		
	}//end of method
}//end of class
