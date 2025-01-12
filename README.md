1 、多线程的一些例子，比较全面。

2 、例子主要在test文件夹下。

3、重写AQS的测试在source\code\test文件夹下

4、 ThreadLocal 是通过线程本地存储机制实现的，每个线程拥有自己的 ThreadLocalMap，它用于存储该线程的本地变量副本。存在内存泄漏问题，显式调用 remove() 方法可避免泄漏。

5、Future：适用于基本的异步任务执行，只关心任务是否完成以及获取结果。FutureTask：适用于需要结合 Runnable 和 Future 功能的场景，可以将任务作为一个可执行的对象提交到线程池。CompletableFuture：适用于复杂的异步编程，特别是涉及多个异步任务的组合、回调和异常处理的场景。

![image](https://github.com/user-attachments/assets/efffec1e-4bed-4b2a-a572-f7d35c556d69)

6、CountDownLatch其实可以把它看作一个计数器，可以向CountDownLatch对象设置一个初始的数字作为计数值，任何调用这个对象上的await()方法都会阻塞，直到这个计数器的计数值被其他的线程减为0为止，是一次性的不能循环使用。
主要方法 countDown计数-1;countDown.await()实现阻塞同步 使用场景：客户端一次请求5个统计数据，服务器需要全部统计完成后，才返回客户端;需要确保多个任务完成后再执行汇总操作的场景；是一次性的。

7、ForkJoinPool 分治算法，只要用于CPU的计算，只适合这种特定场景。

8、ReentrantReadWriteLock 可重入的读写锁；重入的实现逻辑是锁计数器（同一个线程可以多次获取，计数器不断+1），支持锁降级（写锁-》读锁），不支持锁升级（读锁-写锁）。

9、AQS 中的state非常重要，state 作为同步器的内部状态，扮演着非常重要的角色，表示锁的重入次数（ReentrantLock ）、信号量的计数（Semaphore 、CountDownLatch ）

![image](https://github.com/user-attachments/assets/642979f6-8641-4e71-b11e-0c63e6c36967)

10、Semaphore 信号量 （Semaphore semaphore = new Semaphore(3);// 型号灯,值是伸缩的，用满了0，空出来加，模拟3个停车位），核心方法 acquire、release。经典场景：线程池、停车场

11、StampedLock 冲压锁性能锁之王、适合读多写少的情况，不支持可重入。

12、synchronized（监视器锁（Monitor Lock） 锁住的是代码块或者方法；synchronized 可以保证 原子性 和 互斥性， ReentrantLock   锁住的对象是 ReentrantLock 实例本身；

![image](https://github.com/user-attachments/assets/d1e7fc72-f895-4652-82cb-e2c37c235156)

13 避免线程饥饿
![image](https://github.com/user-attachments/assets/554a07c1-3ba3-4d94-ab35-e5c1e019796c)

14 如何避免死锁

![image](https://github.com/user-attachments/assets/91f20cef-e307-44a6-b084-6c2df5ecd4eb)

15  volatile 只保持可见性和禁止指令重排序， 但不保证 原子性或互斥性，适用于仅仅用于共享的变量；适用简单的共享变量的场景；i++操作不是原子性的。





