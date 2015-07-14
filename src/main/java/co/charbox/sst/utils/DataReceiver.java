package co.charbox.sst.utils;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataReceiver implements Runnable {

	private MyIOHAndler io;
	private long size;
	private int duration;
	
	public DataReceiver(MyIOHAndler io, long size) {
		super();
		this.io = io;
		this.size = size;
	}

	public int getDuration() {
		return duration;
	}

	public void run() {
		log.debug("receiving " + size);
		long bytesLeft = size;
		long startTime = System.currentTimeMillis();
		long loopCount = 0;
		long bytesReceived;
		while (bytesLeft > 0) {
			if (loopCount++ >= 1000000) {
				loopCount = 0;
				log.warn("Wheels Spinning! bytesLeft: " + bytesLeft + " expectedSize: " + size);
				if (System.currentTimeMillis() - startTime > 5000) {
					log.error("Failing data receiver! bytesLeft: " + bytesLeft + " expectedSize: " + size);
					this.duration = -1;
					return;
				}
			}
			if (bytesLeft < 0 || bytesLeft > size) {
				throw new RuntimeException("Something went wrong. bytesLeft: " + bytesLeft + " expectedSize: " + size);
			}
			try {
				bytesReceived = io.skip(bytesLeft);
				bytesLeft -= bytesReceived;
				if (bytesReceived > 0) {
					loopCount = 0;
				}
			} catch (IOException e) {
				log.error(e.getMessage());
				return;
			}
		}
		this.duration = (int)(System.currentTimeMillis() - startTime);
	}
}
