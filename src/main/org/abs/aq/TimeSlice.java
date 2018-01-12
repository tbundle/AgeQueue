package org.abs.aq;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * {@link AgeQueue} is made of {@code TimeSlice}s. So if an {@link AgeQueue} has
 * 10 seconds of life, it is likely that it will have 10 {@link TimeSlice}s;
 * each representing one second.
 * <p>
 * An object added to the queue is added to the last {@link TimeSlice} of the
 * queue.
 * </p>
 * 
 * @author Yogendra SHarma
 *
 * @param <OBJECT>
 */
public class TimeSlice<OBJECT> {
	private long startTime;
	private int counter = 0;
	/**
	 * Use of {@link ConcurrentHashMap} is not a good idea here; however we
	 * don't have a suitable data structure offered by Java at this point (Until
	 * I write HashList :))
	 */
	private Map<Integer, OBJECT> store;

	public TimeSlice() {
		this.startTime = System.currentTimeMillis();
		this.counter = 0;
		this.store = new ConcurrentHashMap<>();
	}

	public OBJECT add(OBJECT o) {
		return this.store.put(counter++, o);
	}
	
	public int size(){
		return this.store.size();
	}

	@Override
	public String toString() {
		return "TimeSlice-" + this.startTime + "[" + this.store.size() + "]";
	}
}
