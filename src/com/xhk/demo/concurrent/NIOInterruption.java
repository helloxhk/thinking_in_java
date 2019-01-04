package com.xhk.demo.concurrent;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author xhk
 * @time 2019-01-02 10:49
 */
public class NIOInterruption {

	public static void main(String[] args) throws IOException, InterruptedException {
		ExecutorService executor = Executors.newCachedThreadPool();
		ServerSocket server = new ServerSocket(8080);
		InetSocketAddress isa = new InetSocketAddress("127.0.0.1", 8080);
		SocketChannel sc1 = SocketChannel.open(isa);
		SocketChannel sc2 = SocketChannel.open(isa);
		Future<?> f = executor.submit(new NIOBlocked(sc1));
		executor.execute(new NIOBlocked(sc2));
		executor.shutdown();
//		executor.shutdownNow();
		TimeUnit.SECONDS.sleep(1);
		f.cancel(true);
		TimeUnit.SECONDS.sleep(1);
		sc2.close();


	}
}

class NIOBlocked implements Runnable {

	private SocketChannel sc;

	public NIOBlocked(SocketChannel sc) {
		this.sc = sc;
	}

	@Override
	public void run() {
		System.out.println("Waiting for read() in " + this);
		try {
			sc.read(ByteBuffer.allocate(1));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Exiting NIOBlocked.run()" + this);
	}
}
