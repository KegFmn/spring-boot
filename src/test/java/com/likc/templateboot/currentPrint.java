package com.likc.templateboot;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author likc
 * @date 2022/3/18
 * @description
 */
public class currentPrint {
    /**
     * 使用两个线程交替打印0~100 ReentrantLock+Condition
     */
    //private static int count = 0;
    //public static void main(String[] args) throws InterruptedException {
    //    Lock lock = new ReentrantLock();
    //    Condition evenCondition = lock.newCondition();
    //    Condition oldCondition = lock.newCondition();
    //    //偶数线程
    //    new Thread(()-> {
    //
    //        while (count <= 100) {
    //            try {
    //                lock.lock();
    //                System.out.println(Thread.currentThread().getName() + ":" + count);
    //                count++;
    //                //唤醒奇数线程
    //                oldCondition.signal();
    //
    //                //打印出100后，就不再等待
    //                if (count <= 100) {
    //                    evenCondition.await();
    //                }
    //
    //            } catch (Exception e) {
    //                e.printStackTrace();
    //            } finally {
    //                lock.unlock();
    //            }
    //        }
    //    }, "偶数").start();
    //
    //    Thread.sleep(10);
    //    //奇数线程
    //    new Thread(() -> {
    //        while (count <= 100) {
    //            try {
    //                lock.lock();
    //                System.out.println(Thread.currentThread().getName() + ":" + count);
    //                count++;
    //                //唤醒偶数线程
    //                evenCondition.signal();
    //
    //                oldCondition.await();
    //            } catch (Exception e) {
    //                e.printStackTrace();
    //            } finally {
    //                lock.unlock();
    //            }
    //        }
    //    }, "奇数").start();
    //}

    /**
     *   wait 与 notify 实现
     */
    //static class TurningRunner implements Runnable {
    //    private static int count = 0;
    //    private static final Object lock = new Object();
    //    private static final int num = 100;
    //
    //    @Override
    //    public void run() {
    //        while (count < num) {
    //            synchronized (lock) {
    //                System.out.println(Thread.currentThread().getName() + ":" + count);
    //                count ++;
    //                lock.notify(); // 唤醒另一个线程
    //                if (count <= num) {
    //                    try {
    //                        lock.wait(); // 释放锁，进入阻塞状态，等待被唤醒
    //                    } catch (InterruptedException e) {
    //                        e.printStackTrace();
    //                    }
    //                }
    //            }
    //        }
    //    }
    //}
    //
    //public static void main(String[] args) throws InterruptedException {
    //    new Thread(new TurningRunner(), "偶数").start();
    //    // 短暂休眠，确保线程执行顺序
    //    Thread.sleep(10);
    //    new Thread(new TurningRunner(), "奇数").start();
    //}

    static class Turning implements Runnable{
        private static int count = 0;
        private static final Object lock = new Object();
        private static final int num = 100;

        @Override
        public void run() {
            while (count < num){
                synchronized (lock){
                    System.out.println(Thread.currentThread().getName() + "：" + ++count);
                    lock.notify(); // 唤醒线程
                    if (count < num){
                        try {
                            lock.wait(); // 释放锁，进入阻塞状态，等待被唤醒
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Turning(), "线程1").start();

        Thread.sleep(10);
        new Thread(new Turning(), "线程2").start();
    }

}
