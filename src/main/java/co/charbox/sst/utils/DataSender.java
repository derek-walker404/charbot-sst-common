package co.charbox.sst.utils;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataSender implements Runnable {

	private MyIOHAndler io;
	private String data;
	private long size;
	
	public DataSender(MyIOHAndler io, String data, long size) {
		super();
		this.io = io;
		this.data = data;
		this.size = size;
	}

	public void run() {
//		log.debug("Sending " + size);
		try {
			long currSize = 0;
			byte[] rawData = data.getBytes();
			int length = rawData.length;
			while (currSize < size) {
					io.write(rawData, length);
					currSize += length;
//					log.debug("sent " + currSize);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.debug("Sent " + size);
	}

}
