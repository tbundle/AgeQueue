# Age Queue

A `java.util.Queue` implementation which automatically flushes an object out when the object has spent a predefined life span in the queue. As soon as an object is added to the queue, the aging process starts for the object. Unless the application removes the object from queue, queue automatically drops the object out once the object has spent it's predefined life in queue.

This is similar to mechanism which is implemented by in-memory caching solutions; however those mechanisms are bulky and are designed to work with cache data stores which are much larger than a collection and more often than not it involves locking the data store and scanning it periodically.

AgeQueue implementation is **thread-safe; yet it does not locks down the datastore at any given point.**

In order to work with AgeQueue, here are few points to keep in mind-

#### DeathHandler
There is an optional DeathHandler instance which AgeQueue accepts; any object which dropped out of the queue due to aging, AgeQueue invokes DeathHandler for that object instance. It is recommended that DeathHandler does not perform a heavy-lifting task; in such scnearios applications must execute such tasks in a asynchronously.

#### QueueLife
Currently AgeQueue supports a constant life  duration for all the objects (in future, application can attach a life to each object). As part of internal working of the queue, the whole life is divided into timeslices. So basically, an object will be flushed out of the queue once it spends the specified duaration (QueueLife) inside the queue.

#### TimeSlice
As stated above, the complete QueueLife is divided into TimeSlices. The Timeslice is the smallest unit of the time that queue can record and work with. Currently, the TimeSlice can not be smaller than a second.
