package org.abs.aq;

/**
 * A Callback handler interface for {@link AgeQueue}. The Handler is executed
 * for each of the objects which is is dropped from the queue after it has spent
 * it's life span in the queue.
 * <p>
 * Important to note that handler is invoked only for the object which are
 * dropped due to aging; a premature removal of the object from the queue will
 * not result in handler invocation for the object.
 * </p>
 * <p>
 * {@code DeathHandler} is a functional interface allowing developers to use
 * lambda expressions.
 * </p>
 * 
 * @author Yogendra Sharma
 *
 * @param <OBJECT>
 */
@FunctionalInterface
public interface DeathHandler<OBJECT> {
	public void onDeath(OBJECT o);
}
