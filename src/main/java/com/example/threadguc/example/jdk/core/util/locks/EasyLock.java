package com.example.threadguc.example.jdk.core.util.locks;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:  重写 lock
 *   参考
 *  https://www.cnblogs.com/yanlong300/p/9772271.html
 * @date 2024/9/18 11:58
 */
public class EasyLock extends AbstractQueuedSynchronizer {
    /**
     * 锁定
     */
    public void lock(){
        acquire(1);
    }
    /**
     * 尝试锁定
     */
    public boolean tryLock(){
        return tryAcquire(1);
    }


    /**
     * 解锁
     */
    public void unlock(){
        release(1);
    }


    /**
     * 是否为锁定
     */
    public boolean isLocked(){
        return isHeldExclusively();
    }

    /**
     * 尝试获取锁
     */
    @Override
    protected boolean tryAcquire(int arg) {
        if(compareAndSetState(0,1)){
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }
        return false;
    }

    /**
     * 尝试释放锁
     */
    @Override
    protected boolean tryRelease(int arg) {
        setExclusiveOwnerThread(null);
        setState(0);
        return true;
    }



}
