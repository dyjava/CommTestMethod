package com.method.index.analyzer.lucene;

import java.io.Reader;
import org.apache.lucene.analysis.*;

import com.method.index.analyzer.thesaurus.AssociateStream;
import com.method.index.analyzer.thesaurus.WordTreeFactory;

public final class ThesaurusTokenizer extends Tokenizer {

	public ThesaurusTokenizer(Reader in) {
		input = in;
	}

	private int offset = 0, bufferIndex = 0, dataLen = 0;

	private final static int MAX_WORD_LEN = 24;

	private final static int IO_BUFFER_SIZE = 10240;

	/*
	 *将前一次io读取结果的最后 MAX_WORD_LEN 个字备份，以备回溯。
	 */
	private final char[] backBuffer = new char[MAX_WORD_LEN];

	private final char[] buffer = new char[MAX_WORD_LEN];

	private final char[] ioBuffer = new char[IO_BUFFER_SIZE];

	private int length;

	private int start;

	private final void push(char c) {
		if (length == 0)
			start = offset - 1; 
		buffer[length++] = Character.toLowerCase(c); 
	}

	private final Token flush() {
		if (length > 0) {
			Token token = new Token(new String(buffer, 0, length), start, start
					+ length);
			return token;
		} else {
			return null;
		}
	}

	public final Token next() throws java.io.IOException {
		length = 0;
		start = offset;
		
		//创建联想流.模仿输入法的联想词语的功能
		AssociateStream assoStream = new AssociateStream(WordTreeFactory.getInstance());
		while (true) {
			final char c;
			offset++;

//			ioBuffer中的数据处理完，重新取数据。
			if (bufferIndex >= dataLen) {
				System.arraycopy(ioBuffer, IO_BUFFER_SIZE - MAX_WORD_LEN,
						backBuffer, 0, MAX_WORD_LEN);
				dataLen = input.read(ioBuffer);
				bufferIndex = 0;
			}

			if (dataLen == -1){
//				字符串处理完，退出
				return flush();
			} else {
//				取一个字符
				if (bufferIndex < 0) {
					c = backBuffer[backBuffer.length + bufferIndex];
				} else {
					c = ioBuffer[bufferIndex];
				}
				bufferIndex++;
			}

			if (Character.isWhitespace(c)) {
//				空格
				if (!assoStream.isBegin()) {
					assoStream.reset();
					return flush();
				}
				if (length > 0)
					return flush();
			} else if (Character.isDigit(c)) {
//				数字
				if (!assoStream.isBegin()) {
					bufferIndex--;
					offset--;
					assoStream.reset();
					return flush();
				}
				push(c);
				if (length == MAX_WORD_LEN)
					return flush();

			} else if (Character.isLowerCase(c) || Character.isUpperCase(c)) {
//				英文字符
				if (!assoStream.isBegin()) {
					bufferIndex--;
					offset--;
					assoStream.reset();
					return flush();
				}
				push(c);
				if (length == MAX_WORD_LEN)
					return flush();

			} else if (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS) {
//				中文字符
				if (assoStream.isBegin() && length > 0) {
//					c为开始字符，输出已匹配字符
					bufferIndex--;
					offset--;
					return flush();
				}

//				c匹配成功
				if (assoStream.associate(c)) {
					push(c);
//					词已经结尾则结束，否则继续匹配
					if (!assoStream.canContinue()) {
						assoStream.reset();
						return flush();
					}
				} else {
//					c未匹配
					if (assoStream.isWordEnd()) {
//						匹配词完整结束
						assoStream.reset();
						bufferIndex--;
						offset--;
						return flush();
					} else if (assoStream.isOccurWord()) {
//						未完整匹配，倒退到最后一个完整匹配词
						assoStream.backToLastWordEnd();
						bufferIndex = bufferIndex
								- (length - assoStream.getSetp()) - 1;
						offset = offset - (length - assoStream.getSetp()) - 1;

						length = assoStream.getSetp();

						assoStream.reset();
						return flush();
					} else {
//						单字输出
						if (length > 0) {
							bufferIndex = bufferIndex - (length - 1) - 1;
							offset = offset - (length - 1) - 1;

							length = 1;

							assoStream.reset();
							return flush();
						}
						assoStream.reset();
						push(c);
						return flush();
					}
				}
			} else if (length > 0){
				if(Const.PUNCTUATION){
					if (length > 0) {
						bufferIndex--;
						offset--;
//						assoStream.reset();
						return flush();
					}
					push(c);
//					assoStream.reset();
					return flush();
				}
				return flush();
			}
		}
	}

}
