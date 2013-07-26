package com.method.index.analyzer.lucene;

import java.io.Reader;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;

public class ThesaurusAnalyzer extends Analyzer {

    public ThesaurusAnalyzer() {
    }

    public final TokenStream tokenStream(String fieldName, Reader reader) {
    	 TokenStream result = new ThesaurusTokenizer(reader);
         return result;
    }
}