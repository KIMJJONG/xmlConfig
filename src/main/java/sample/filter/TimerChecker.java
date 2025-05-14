package sample.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimerChecker {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TimerChecker.class);
	
	private long currentTime;
	private long durationTime;
	private long terminatedTime;
	
	public TimerChecker() {
		LOGGER.info("TimerChecker 실행");
		start();
	}
	
	private void start() {
		this.currentTime = System.currentTimeMillis();
	}
	
	public long period() {
		start();
		this.durationTime = System.currentTimeMillis() - currentTime;
		return this.durationTime;
	}
	
	public long stop() {
		this.terminatedTime = System.currentTimeMillis() - currentTime;
		return this.terminatedTime;
	}
	
}
