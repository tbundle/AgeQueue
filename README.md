This project is under development and **is not usable as yet.**

# smartcollection

Set of java collections which are not offered by JDK out of the box. The collections are unconventional and some of them are live in nature; meaning they engage CPU even when applications are not operating with them.

## AgeQueue
A `java.util.Queue` implementation which automatically flushes an object out when the object has spent a predefined life span in the queue. As soon as an object is added to the queue, the aging process starts for the object. Unless the application removes the object from queue, queue automatically drops the object out once the object has spent it's predefined life in queue.

This is similar to mechanism which is implemented by in-memory caching solutions; however those mechanisms are bulky and are designed to work with cache data stores which are much larger than a collection and more often than not it involves locking the data store and scanning it periodically.

AgeQueue implementation is **thread-safe; yet it does not locks down the datastore at any given point.**
