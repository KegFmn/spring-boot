package com.likc.templateboot;

/**
 * @author likc
 * @date 2022/3/17
 * @description
 */
public class Singleton {
    /**
     *   饿汉单例
     */
    //private Singleton(){
    //
    //}
    //
    //private static Singleton singleton = new Singleton();
    //
    //public static Singleton getSingleton(){
    //    return singleton;
    //}

    /**
     *   懒汉单例
     */
    //private Singleton(){
    //
    //}
    //
    //private static Singleton singleton;
    //
    //public static Singleton getSingleton(){
    //    if (singleton == null){
    //        singleton = new Singleton();
    //    }
    //    return singleton;
    //}

    /**
     *   懒汉单例(线程安全)
     */
    //private Singleton(){
    //
    //}
    //
    //private static Singleton singleton;
    //
    //public static synchronized Singleton getSingleton(){
    //    if (singleton == null){
    //        singleton = new Singleton();
    //    }
    //    return singleton;
    //}

    /**
     *   懒汉单例(双重校验锁)
     */
    //private Singleton(){
    //
    //}
    //
    //private volatile static Singleton singleton;
    //
    //public static Singleton getSingleton(){
    //    if (singleton == null){
    //        synchronized (Singleton.class){
    //            if (singleton == null){
    //                singleton = new Singleton();
    //            }
    //        }
    //    }
    //    return singleton;
    //}
}
