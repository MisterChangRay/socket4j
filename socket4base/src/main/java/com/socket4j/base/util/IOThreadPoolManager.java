
package java.com.socket4j.base.util;


import java.util.concurrent.*;

public class IOThreadPoolManager {

    private static volatile IOThreadPoolManager _instance;

    private ExecutorService pool;
    private static int coreSize = Runtime.getRuntime().availableProcessors();
    private IOThreadPoolManager() {
        ThreadFactory factory = Executors.defaultThreadFactory();
        ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(10000);
        pool = new ThreadPoolExecutor(coreSize * 8, coreSize * 8, 30,
                TimeUnit.SECONDS, workQueue, new CustomThreadFactory("socket4j-io-pool"));
    }
    
    public static IOThreadPoolManager getInstance() {
        if (null == _instance) {
            synchronized (IOThreadPoolManager.class) {
                if (null == _instance) {
                    _instance = new IOThreadPoolManager();
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
