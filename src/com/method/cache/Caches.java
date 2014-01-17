package com.method.cache;

import org.apache.log4j.Logger;
import org.shiftone.cache.policy.fifo.FifoCacheFactory;
import org.shiftone.cache.policy.lfu.LfuCacheFactory;
import org.shiftone.cache.policy.lru.LruCacheFactory;
import org.shiftone.cache.*;

public class Caches {
	private static Cache _cache = null ;
	private static long _TIMEOUT =  60*60*24 ;	//失效时间
	private static int _MAX_CACHE_NUM = 1000*10 ;	//cache中最大保存数量
	
	private static final Logger log = Logger.getLogger(Caches.class);
	
	/**
	 * 初始化Cache
	 */
	public static void initCache(){
//		LfuCacheFactory lfuFactory = new LfuCacheFactory();
//		_cache = lfuFactory.newInstance("cache", _TIMEOUT*1000, _MAX_CACHE_NUM);
		
//		FifoCacheFactory fifoFactory = new FifoCacheFactory();
//		_cache = fifoFactory.newInstance("cache", _TIMEOUT*1000, _MAX_CACHE_NUM);
		
		LruCacheFactory lruFactory = new LruCacheFactory();
		_cache = lruFactory.newInstance("cache", _TIMEOUT*1000, _MAX_CACHE_NUM);
		
		
//		if(Const.INIT_CACHE_AT_START){
//			CacheRingPage.setCacheDirectory(path);
//			CacheRingPage.restoreCache(_cache);
//		}
		log.info("initCache OK !");
	}
	public static void initCache(long timeout,int max_cache_num){
		_TIMEOUT = timeout;
		_MAX_CACHE_NUM = max_cache_num;
		initCache();
	}
	
	/**
	* 清除缓存
	*/
	public static void clearCache(){
		_cache.clear();
	}
	
	/**
	* 向缓存中增加关键词及其检索结果
	*/
	public static void putCache(String key, Object data){
		synchronized (_cache) {
			if(_cache.size()<_MAX_CACHE_NUM){
				_cache.addObject(key,data) ;
			}
		}
	}
	
	/**
	* 从缓存中得到缓存的查询结果
	*/
	public static Object getCache(String key){
		return _cache.getObject(key) ;
	}

	public static void main(String[] args) {
		initCache();
		CacheData cd = new CacheData();
		cd.setKey("key1") ;
		cd.setNum(2) ;
		Caches.putCache("key",cd ) ;
		
		CacheData cd2 = new CacheData();
		cd2.setKey("key2") ;
		cd2.setNum(22) ;
		Caches.putCache("key2",cd2 ) ;
		log.info("==="+((CacheData)Caches.getCache("key")).getKey()) ;
		log.info("==="+((CacheData)Caches.getCache("key2")).getKey()) ;

		try {
			Thread.sleep(6000);
			CacheData c = (CacheData)Caches.getCache("key");
			if(c!=null)
			log.info("==="+c.getKey()) ;
		}catch(Exception ex){
			log.error("err:"+ex.getMessage()) ;
		}
		log.info(""+_cache.size()) ;
	}

}
