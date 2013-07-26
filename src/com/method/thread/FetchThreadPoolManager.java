package com.method.thread;

import java.util.concurrent.*;

/**
 * thread pool manager
 * @author diyong
 *
 */
public class FetchThreadPoolManager{
	
	static final int threadPoolSize = 10 ;		//thread number
    static private FetchThreadPoolManager singletonObject = null; 
    private ExecutorService es = null;
    
    private FetchThreadPoolManager() {
       es = Executors.newFixedThreadPool(threadPoolSize);       
    }
    
    public static synchronized FetchThreadPoolManager getInstance(){
        if (singletonObject == null) {
            singletonObject = new FetchThreadPoolManager();
        }
        return singletonObject;
    }
    
    public ExecutorService getExecutorService() {
        return es;
    }
}
