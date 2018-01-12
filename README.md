# smartcollection
Set of java collections which are not offered by JDK out of the box. The collections are unconventional and some of them are live in nature; meaning they engage CPU even when applications are not operating with them.

#AgeQueue
A java.util.Queue implementation which automatically flushes an object out when the object has spent a predefined life span in the queue. This is similar to mechanism which is implemented by in-memory caching solutions; however those mechanisms are bulky and are designed to work with cache data stores which are much larger than a collection.
