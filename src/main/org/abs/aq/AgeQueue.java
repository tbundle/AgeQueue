package org.abs.aq;


import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of {@link Queue} interface which flushes objects out after the
 * object has spent a certain duration in queue. This can be compared with human
 * aging process. As soon as an object is placed into the queue, it starts
 * monitoring its time on the queue. The object will be removed from the queue
 * once it has spent a defined {@link Duration} known as life.
 * <p>
 * {@code AgeQueue} accepts {@link DeathHandler} as callback mechanism which is
 * invoked for each of the outgoing object. The {@link DeathHandler}
 * implementation should not be very time consuming task. If so, applications
 * are advised to execute such tasks asynchronously.
 * </p>
 * <p>
 * {@code AgeQueue} implementation is thread-safe
 * </p>
 * 
 * @author Yogendra Sharma
 *
 */
public class AgeQueue<OBJECT> implements Queue<OBJECT> {
	private static final Logger LOGGER = Logger.getLogger(AgeQueue.class.getSimpleName());

	private static final Duration DEFAULT_QUEUE_LIFE = Duration.ofSeconds(10);

	private List<TimeSlice<OBJECT>> timeSlices;
	private Duration queueLife;
	private DeathHandler<OBJECT> deathHandler;
	private TimeSliceGenerator timeSlicerThread;

	private ReadWriteLock lock;

	public AgeQueue() {
		this(DEFAULT_QUEUE_LIFE, null);
	}

	public AgeQueue(DeathHandler<OBJECT> deathHandler) {
		this(DEFAULT_QUEUE_LIFE, deathHandler);
	}

	public AgeQueue(Duration life, DeathHandler<OBJECT> deathHandler) {
		this.timeSlices = new ArrayList<>();
		this.queueLife = life;
		this.deathHandler = deathHandler;
		this.lock = new ReentrantReadWriteLock();

		prepareTimeSlicerThread();
	}

	private void prepareTimeSlicerThread() {
		LOGGER.log(Level.FINE, "Preparing TimeSlicer thread");
		this.timeSlicerThread = new TimeSliceGenerator(this);

		LOGGER.log(Level.FINE, "Starting TimeSlicer thread");
		this.timeSlicerThread.start();
	}

	// public OBJECT add(OBJECT o) {
	// return this.shaft.getLast().add(o);
	// }

	/**
	 * Push a new {@link TimeSlice} instance on to the queue. This is likely to
	 * push another time slice out of the queue due to aging; and if so,
	 * {@link DeathHandler} will be invoked for all the objects being dropped
	 * from the queue.
	 * <p>
	 * The method has default access and is expected to be used by
	 * {@link TimeSliceGenerator} only.
	 * </p>
	 */
	void pushTimeSlice() {
		this.timeSlices.add(new TimeSlice<>());
	}

	@Override
	public int size() {
		try {
			this.lock.writeLock().lock();
			return this.timeSlices.size() > 0 ? this.timeSlices.stream().mapToInt(v -> v.size()).sum() : 0;

		} finally {
			this.lock.writeLock().unlock();
		}
	}

	@Override
	public synchronized boolean isEmpty() {
		try {
			this.lock.writeLock().lock();

			if (this.timeSlices.size() > 0) {
				for (TimeSlice<OBJECT> ts : this.timeSlices) {
					if (ts.size() > 0) {
						return false;
					}
				}
			}

			return true;

		} finally {
			this.lock.writeLock().unlock();
		}
	}

	@Override
	public boolean contains(Object o) {
		for (TimeSlice<OBJECT> ts : this.timeSlices) {

		}
		return false;
	}

	@Override
	public Iterator<OBJECT> iterator() {
		return null;
	}

	@Override
	public Object[] toArray() {
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return null;
	}

	@Override
	public boolean remove(Object o) {
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends OBJECT> c) {
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return false;
	}

	@Override
	public void clear() {

	}

	@Override
	public boolean add(OBJECT e) {
		return false;
	}

	@Override
	public boolean offer(OBJECT e) {
		return false;
	}

	@Override
	public OBJECT remove() {
		return null;
	}

	@Override
	public OBJECT poll() {
		return null;
	}

	@Override
	public OBJECT element() {
		return null;
	}

	@Override
	public OBJECT peek() {
		return null;
	}

}
