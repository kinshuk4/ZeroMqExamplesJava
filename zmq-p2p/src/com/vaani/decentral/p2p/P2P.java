package com.vaani.decentral.p2p;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Socket;

public class P2P {

	public static void main(String[] args) {
		ZMQ.Context context = ZMQ.context(1);

		ZMQ.Socket responder = context.socket(ZMQ.REP);

		ZContext ctx = new ZContext();
		ZMQ.Socket subscriber = ctx.createSocket(ZMQ.SUB);

		int address;
		for (address = 1; address < 255; address++)
			zsocket_connect(subscriber, "tcp://10.0.0.%d:9000", address);

		ctx.close();

	}

	private static void zsocket_connect(Socket subscriber, String preAddress,
			int address) {

		String x = String.format(preAddress, address);
		System.out.println(x);
		subscriber.connect(x);

	}

}
