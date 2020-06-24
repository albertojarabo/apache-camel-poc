package com.albertojarabo.camel.processors;

import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Paths;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.albertojarabo.camel.model.DogResponse;
import com.albertojarabo.camel.util.DownloadFile;

public class MyDogProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {

		DogResponse dogResponse = exchange.getIn().getBody(DogResponse.class);

		try {
			URL url = new URL(dogResponse.getMessage());
			String fileName = DownloadFile.downloadFromURL(url);
			System.out.println("Downloaded random dog at " + fileName);
		} catch (Exception e) {
			System.err.println("Couldn't download this dog: " + e.getMessage());
		}
	}

}
