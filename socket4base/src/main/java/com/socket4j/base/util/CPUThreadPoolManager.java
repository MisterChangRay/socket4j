
package com.socket4j.base.util;


import java.util.concurrent.*;

public class CPUThreadPoolManager {
    
    private static volatile CPUThreadPoolManager _instance;
    
    private ExecutorService pool;
    private static int coreSize = Runtime.getRuntime().availableProcessors();
    private CPUThreadPoolManager() {
        ThreadFactory factory = Executors.defaultThreadFactory();
        ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(10000);
        pool = new ThreadPoolExecutor(coreSize, coreSize + 2, 30,
                TimeUnit.SECONDS, workQueue, new CustomThreadFactory("socket4j-cpu-pool"));
    }
    
    public static CPUThreadPoolManager getInstance() {
        if (null == _instance) {
            synchronized (CPUThreadPoolManager.class) {
                if (null == _instance) {
                    _instance = new CPUThreadPoolManager();
                }
            }
        }
        return _instance;
    }
    
    public void executeThread(Runnable run) {
        pool.execute(run);
    }
    
    public <V> Future<V> executeThread(Callable<V> call) throws Exception {
        Future<V> f = pool.submit(call);
        return f;
    }

}
