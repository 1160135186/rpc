package com.wangruiguang.client.channel;

import com.wangruiguang.client.RequestFuture;
import io.netty.channel.Channel;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ClientChannel {

    private boolean acceptNewRequest = true;

    private Channel channel;

    private Set<RequestFuture> requestFutureSet = new HashSet<>();

    private Lock lock = new ReentrantLock();

    private Condition idleCondition = lock.newCondition();

    public ClientChannel(Channel channel) {
        this.channel = channel;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Set<RequestFuture> getRequestFutureSet() {
        return requestFutureSet;
    }

    public void addRequestFuture(RequestFuture requestFuture) {
        requestFutureSet.add(requestFuture);
    }

    public void close() {
        if (channel.isOpen()) {
            channel.close();
        }
    }

    public boolean waitChannelToBeIdle() {
        // 将标志位设置成false,不在接受新的请求
        acceptNewRequest = false;

        // 等待旧请求处理完毕 关闭channel
        lock.lock();
        while (requestFutureSet.size() != 0) {
            try {
                idleCondition.await(100, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                return false;
            } finally {
                lock.unlock();
            }
        }
        return true;
    }
}
