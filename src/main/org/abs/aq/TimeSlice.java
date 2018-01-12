package org.abs.aq;

import org.abs.hl.HashList;

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
	private HashList<OBJECT> store;

	public TimeSlice() {
		this.startTime = System.currentTimeMillis();
		this.store = new HashList<>();
	}

	public boolean add(OBJECT o) {
		return this.store.add(o);
	}

	public boolean remove(OBJECT o) {
		return this.store.remove(o);
	}

	public int size() {
		return this.store.size();
	}

	@Override
	public String toString() {
		return "TimeSlice-" + this.startTime + "[" + this.store.size() + "]";
	}
}
