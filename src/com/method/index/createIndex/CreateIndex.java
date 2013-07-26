package com.method.index.createIndex;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.FSDirectory;

import com.method.index.Const;

public class CreateIndex {
	private static final Logger log = Logger.getLogger(CreateIndex.class);
	
	/**
	 * 创建索引
	 * @return
	 */
	public static boolean getIndex(){
		boolean bret = false;
		//判断是添加索引，还是增加索引
		File file = new File(Const.INDEX_DIR+"/segments");
		CreateIndex creat = new CreateIndex() ;
		if(file.exists()){
			bret = creat.addIndex();
		}else{
			bret = creat.createIndex();
		}
		return bret;
	}
	
	/**
	 * 删除索引
	 * @return
	 */
	public static boolean deleteIndex(){
		boolean bret = false;
		IndexReader reader = null;
		try {
			reader = IndexReader.open(FSDirectory.getDirectory(Const.INDEX_DIR,false));
			new Index().deleteData(reader);
			bret = true;
		} catch (Exception e){
			log.error("deleteIndex:"+e.getMessage());
		} finally{
			try {
				if(reader!=null){
					reader.close();
				}
			} catch (IOException e) {
				log.error("reader.close()");
			}
			IndexFactory.resetDeleteFlag();
		}
		return bret;
	}
	
	/**
	 * 索引增加
	 * @return
	 */
	private boolean addIndex(){
		boolean bret = false;
		IndexWriter writer = null;
		writer = IndexFactory.getIndexWriter();
		try {
			if(writer!=null){
				new Index().addData(writer);
				bret = true;
			}
			log.info("add Index success!");
		}catch (Exception e) {
			bret = false;
			log.error("addIndex:"+e.getMessage());
		}finally{
			IndexFactory.closeIndexWriter();			
		}
		return bret;
	}
	
	private boolean createIndex(){
		IndexWriter writer = null;
		try {
			writer = new IndexWriter(FSDirectory.getDirectory(Const.INDEX_DIR, true),
					new StandardAnalyzer(), true);
			writer.setMergeFactor(writer.getMergeFactor()*100);
			writer.setMaxMergeDocs(writer.getMaxMergeDocs()*100);
			
			new Index().addData(writer);
			log.info("create Index success!");
		} catch (Exception e) {
			log.error("createIndex:"+e.getMessage());
		} finally{
			try{
				if(writer!=null){
					writer.optimize();
					writer.close();
				}
			}catch(IOException e){
				log.error("writer close():"+e.getMessage());
			}
		}
		return true;
	}
	
}
