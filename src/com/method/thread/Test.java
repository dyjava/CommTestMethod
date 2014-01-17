package com.method.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/** 
 * by dyong 2010-6-11
 */
public class Test {

	/**
	 * @param args
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		
		Test t = new Test() ;
		
//		t.testThread() ;
		
		t.testMethod() ;
		
	}
	
	/**
	 * 用线程池执行线程
	 */
	public void testThread(){
		CommonThread t = new CommonThread(5) ;
		
//		test 1
		Thread tt = new Thread(t) ;
		tt.start() ;
		
//		test thread pool
		AbstractExecutorService executors = (AbstractExecutorService) FetchThreadPoolManager.getInstance().getExecutorService();
		executors.submit(t) ;

	}

	/**
	 * 普通方法放到线程池执行
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public void testMethod() throws InterruptedException, ExecutionException{
		ArrayList<Callable<HashMap<String, Object>>> tasks=new ArrayList<Callable<HashMap<String, Object>>>();
		
		CommonMethod cm1 = new CommonMethod(2,6) ;
		tasks.add(cm1) ;
		CommonMethod cm2 = new CommonMethod(6,5) ;
		tasks.add(cm2) ;
		CommonMethod cm3 = new CommonMethod(12,3) ;
		tasks.add(cm3) ;
		
//		取线程池
		AbstractExecutorService es = (AbstractExecutorService) FetchThreadPoolManager.getInstance().getExecutorService();
		
//		执行any方法，从多任务中取执行最快的一个任务结果
		HashMap<String, Object> h = es.invokeAny(tasks) ;
		System.out.println(h.toString()) ;
		
//		执行完所有任务，返回所有结果
		List<Future<HashMap<String, Object>>> list = es.invokeAll(tasks) ;
		for(Future<HashMap<String, Object>> f :list){
			if(!f.isCancelled()){
				HashMap<String, Object> hash = f.get() ;
				System.out.println(hash.toString()) ;
			}
		}
	}
}
