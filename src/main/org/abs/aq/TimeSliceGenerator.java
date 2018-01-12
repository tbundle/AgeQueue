package org.abs.aq;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A thread which continuously pushes {@link TimeSlice} on the queue at regular
 * intervals.
 * 
 * @author Yogendra Sharma
 *
 */
public class TimeSliceGenerator extends Thread {
	private static final Logger LOGGER = Logger.getLogger(TimeSliceGenerator.class.getSimpleName());

	private static final String THREAD_NAME = "AgeQueueTimeSlicer";

	private AgeQueue<?> ageQ;
	private volatile boolean running = false;

	public TimeSliceGenerator(AgeQueue<?> ageQ) {
		super(THREAD_NAME);
		this.ageQ = ageQ;
	}

	@Override
	public void run() {
		LOGGER.log(Level.INFO, "Running AgeQueue TimeSlicer Thread");

		this.running = true;

		while (Thread.interrupted() && this.running) {
			this.ageQ.pushTimeSlice();

			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// Ignore
			}
		}

		this.running = false;

		LOGGER.log(Level.INFO, "Exiting AgeQueue TimeSlicer Thread");
	}

	/**
	 * Turn off the {@link TimeSliceGenerator#running} flag which will result in
	 * {@code TimeSlicerThread} to quit running in next interactions.
	 */
	public void shutdown() {
		this.running = false;
	}

}
