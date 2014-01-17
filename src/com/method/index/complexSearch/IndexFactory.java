package com.method.index.complexSearch;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ParallelMultiSearcher;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.store.RAMDirectory;



/** 
 * 索引实时更新，索引刷新，索引固化，内存索引和硬盘索引同时提供。
 * by dyong 2010-8-10
 */
public class IndexFactory {
	private String _INDEX_DIR = "E:/java/applet/chinaPost";	//索引目录
	
	private RAMDirectory _RAM_DIR ;			//内存索引
	private RAMDirectory _RAM_DIR_BACK ;	//内存索引
	private IndexSearcher _DIR_SEARCHER ;	//硬盘索引
	
	private Searcher searcher ;				//IndexSearcher
	private Analyzer analyzer = new StandardAnalyzer() ;	//分词器
	
	private static IndexFactory indexSearch ;
	private IndexFactory() throws CorruptIndexException, IOException{
		_INDEX_DIR = ConstFinal.INDEX_DIR ;
		analyzer = ConstFinal.analyzer ;
		
		_DIR_SEARCHER = new IndexSearcher(_INDEX_DIR) ;
		_RAM_DIR = getRAMDirectory(null) ;
		_RAM_DIR_BACK = getRAMDirectory(null) ;
	}
	public static IndexFactory getInstence() throws IOException{
		if(indexSearch==null){
			indexSearch = new IndexFactory() ;
		}
		return indexSearch ;
	}
	
	/**
	 * getSearcher
	 * @return
	 * @throws IOException
	 */
	public Searcher getSearcher() throws IOException{
		if(searcher==null){
			reloadSearcher() ;
		}
		return searcher ;
	}

	/**
	 * 添加实时索引
	 * @throws IOException 
	 * @throws  
	 */
	public boolean addRAMIndex(List<Document> list) throws IOException {
		IndexWriter writer = new IndexWriter(_RAM_DIR,analyzer,false);
		for(int i=0;i<list.size();i++){
			writer.addDocument(list.get(i)) ;
		}
		writer.optimize() ;
//		writer.commit() ;
		writer.close() ;
		
//		重新加载
		reloadSearcher() ;
		return true ;
	}
	
	/**
	 * 将内存索引固化到硬盘索引中
	 * @throws IOException
	 */
	public void solidRAMIndex() throws IOException{
//		索引切换
		_RAM_DIR_BACK = _RAM_DIR ;
		_RAM_DIR = getRAMDirectory(null);
		reloadSearcher() ;
		
//		索引从内存中导入到硬盘中
		IndexReader reader = new IndexSearcher(_RAM_DIR_BACK).getIndexReader() ;
		IndexReader[] readers = {reader} ;
		IndexWriter writer = new IndexWriter(_INDEX_DIR,analyzer,false);
		writer.addIndexes(readers) ;
		writer.optimize();
        writer.close();

//        重载索引
        _DIR_SEARCHER.close() ;
        _DIR_SEARCHER = new IndexSearcher(_INDEX_DIR) ;
        _RAM_DIR_BACK = getRAMDirectory(null);
//        searcher.close() ;
        reloadSearcher() ;
	}
	
	/**
	 * 重新加载索引文件
	 * @throws IOException
	 */
	public void reloadIndex() throws IOException{
		_DIR_SEARCHER = new IndexSearcher(_INDEX_DIR) ;
		reloadSearcher() ;
	}
	
	/**
	 * 重新载入IndexSearcher
	 * @throws IOException
	 */
	private void reloadSearcher() throws IOException {
//		加载顺序有意义
		IndexSearcher[] searchers = {
				_DIR_SEARCHER,
        		new IndexSearcher(getRAMDirectory(_RAM_DIR_BACK)),
        		new IndexSearcher(getRAMDirectory(_RAM_DIR))
        		} ;
		searcher = new ParallelMultiSearcher(searchers);
	}
	
	private RAMDirectory getRAMDirectory(RAMDirectory ramd) throws IOException{
		if(ramd==null){
			ramd = new RAMDirectory();
			IndexWriter writer = new IndexWriter(ramd,analyzer,true);
	        writer.close();
		}
		return ramd ;
	}

	/**
	 * 取索引中最后一条数据
	 * 
	 * @return
	 * @throws IOException
	 */
	public Document getLastDocument() throws IOException {
		int maxId = searcher.maxDoc();
		if(maxId == 0){
			return null;
		}
		Document doc = searcher.doc(maxId-1);
		return doc ;
	}
	
}
