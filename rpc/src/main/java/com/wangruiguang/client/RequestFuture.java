package com.wangruiguang.client;

import com.wangruiguang.common.exception.RpcErrorCode;
import com.wangruiguang.common.exception.RpcException;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RequestFuture {

    Lock lock = new ReentrantLock();

    Condition complete = lock.newCondition();

    private boolean success;

    private Object result;

    private Object error;

    public boolean await(long timeout, TimeUnit timeUnit) {
        lock.lock();
        try {
            return complete.await(timeout, timeUnit);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RpcException(RpcErrorCode.UN_DEFINED);
        } finally {
            lock.unlock();
        }
    }

    public void complete() {
        lock.lock();
        try {
            complete.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getResult() {
        return this.result;
    }

    public Object getError() {
        return this.error;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public void setError(Object error) {
        this.error = error;
    }
}
