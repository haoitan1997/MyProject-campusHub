package com.haotian.campuslifehub.util;

public class ThreadLocalUtil {
    private static  ThreadLocal threadLocal;
    private ThreadLocalUtil(){};
    public static ThreadLocal getThreadLocal(){
        if(threadLocal == null){
            threadLocal = new ThreadLocal();
        }
        return threadLocal;
    }
//    public static ThreadLocal getThreadLocal(){
//        return threadLocal;
//    }
//    public static void setValue(Object o){
//        threadLocal.set(o);
//    }
//
//    public static Object getValue(){
//        return threadLocal.get();
//    }
//
//    public static void remove(){
//        threadLocal.remove();
//    }
}
