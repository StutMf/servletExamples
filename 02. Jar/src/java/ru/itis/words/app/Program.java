package ru.itis.words.app;

import com.beust.jcommander.JCommander;

import java.net.MalformedURLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Program { 
	public static void main(String[] argv) throws InterruptedException, MalformedURLException {
		int count = 1;

		Args args = new Args();

		JCommander.newBuilder()
		.addObject(args)
		.build()
		.parse(argv);

		if (!args.mode.equals("one-thread"))
			count = Integer.parseInt(args.count);
		String files = args.files;
		String folder = args.folder;
		String[] file = files.split(";");

		ExecutorService service = Executors.newFixedThreadPool(count);

		for (String name : file) {
			service.submit(new 	MyThread(name, folder));
		}
		service.shutdown();
		service.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
	}
}