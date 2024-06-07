package me.gamrboy4life.paradox.utils.timers;

public class Timer {
	
	private long prevTime;
	
	public Timer() {
		prevTime=0;
	}
	
	public boolean hasTimePassed(long milSec) {
		return (float)(getTime()-prevTime)>=milSec;
	}
	
	public void reset() {
		prevTime=getTime();
	}

	public static long getTime() {
		return System.nanoTime()/1000000;
	}
}
