package me.gamrboy4life.paradox.utils.timers;

public class Timer2 {
	
	public static short convert(float perSecond) {
		return (short)(1000/perSecond);
	}
	
	public static long getCurrentTime() {
		return System.nanoTime()/1000000;
	}
	
	private long previousTime;
	
	public Timer2() {
		previousTime=-1L;
	}
	
	public long get() {
		return previousTime;
	}
	
	public boolean check(float milliSeconds) {
		return Timer2.getCurrentTime()-previousTime>=milliSeconds;
	}
	
	public void reset() {
		previousTime=Timer2.getCurrentTime();
	}

}
