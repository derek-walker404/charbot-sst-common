package co.charbox.sst.utils;

import java.io.IOException;

import co.charbox.sst.SSTProperties;
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
	
	public DataSender(MyIOHAndler io, long size) {
		super();
		this.io = io;
		this.size = size;
	}

	public void run() {
		if (data != null) {
			runWithData();
		} else {
			runWithRandomData();
		}
	}
	
	private void runWithData() {
		try {
			long currSize = 0;
			byte[] rawData = data.getBytes();
			int length = rawData.length;
			while (currSize < size) {
				io.write(rawData, length);
				currSize += length;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.debug("Sent " + size);
	}
	
	private void runWithRandomData() {
		try {
			long currSize = 0;
			int length = SSTProperties.getRandomDataChunk().length;
			while (currSize < size) {
				io.write(SSTProperties.getRandomDataChunk(), length);
				currSize += length;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.debug("Sent " + size);
	}

}
