package chapter1;

import org.zeromq.*;

public class WuClient {
	public static void main(String[] args)
	{
		ZMQ.Context ctx = ZMQ.context(1);
		
		ZMQ.Socket subscriber = ctx.socket(ZMQ.SUB);
		
		subscriber.connect("tcp://localhost:5556");
		
		System.out.println("nymabung ke server om..");
		
		subscriber.subscribe("4".getBytes());
		
		int maxUpdates=100;
		
		for( int i=0; i< maxUpdates;i++)
		{
			String data = subscriber.recvStr();
			System.out.println("dari server.."+data);
		}//end for
		
		subscriber.close();
		ctx.close();
	}//end of main
}//end of class
