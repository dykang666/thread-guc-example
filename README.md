1 、多线程的一些例子，比较全面。

2 、例子主要在test文件夹下。

3、重写AQS的测试在source\code\test文件夹下

4、 ThreadLocal 是通过线程本地存储机制实现的，每个线程拥有自己的 ThreadLocalMap，它用于存储该线程的本地变量副本。存在内存泄漏问题，显式调用 remove() 方法可避免泄漏。

5、Future：适用于基本的异步任务执行，只关心任务是否完成以及获取结果。FutureTask：适用于需要结合 Runnable 和 Future 功能的场景，可以将任务作为一个可执行的对象提交到线程池。CompletableFuture：适用于复杂的异步编程，特别是涉及多个异步任务的组合、回调和异常处理的场景。

![image](https://github.com/user-attachments/assets/efffec1e-4bed-4b2a-a572-f7d35c556d69)
