package com.albertojarabo.camel.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Paths;

public class DownloadFile {

	public static String downloadFromURL(URL url) throws IOException {
		
		ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
		String fileName = Paths.get(url.getFile()). getFileName().toString();
		FileOutputStream fileOutputStream = new FileOutputStream(fileName);
		FileChannel fileChannel = fileOutputStream.getChannel();
		
		fileOutputStream.getChannel()
		  .transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
		
		fileOutputStream.close();
		fileChannel.close();

		return fileName;
	}
}
