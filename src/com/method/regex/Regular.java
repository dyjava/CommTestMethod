package com.method.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regular {
	
	/**
	 * 	按匹配的词分割字符串。
	 * @param str 字符串
	 * @param reg 正则表达式
	 * @param num 分割的字符串个数。
	 */
	public static String[] split(String content,String reg,int num){
        Pattern p = Pattern.compile(reg); 
//      用Pattern的split()方法把字符串按"/"分割 
        String[] result = p.split(content,num); 
        return result ;
	}
	
	public static String replace(String content,String reg,String reg2){
//		生成Pattern对象并且编译正则表达式 
		Pattern p = Pattern.compile(reg); 
//		用Pattern类的matcher()方法生成一个Matcher对象 
		Matcher m = p.matcher(content); 
		StringBuffer sb = new StringBuffer(); 

//		使用find()方法查找第一个匹配的对象
		while(m.find()){
//			使用循环将句子里所有的reg找出并替换再将内容加到sb里 
			m.appendReplacement(sb, reg2);
			System.out.println("sb的内容是："+sb);
		} 
//		最后调用appendTail()方法将最后一次匹配后的剩余字符串加到sb里； 
		m.appendTail(sb); 
		return sb.toString(); 
	}
	public static String replaceAdd(String content,String reg,String pre){
//		生成Pattern对象并且编译正则表达式 
		Pattern p = Pattern.compile(reg); 
//		用Pattern类的matcher()方法生成一个Matcher对象 
		Matcher m = p.matcher(content); 
		StringBuffer sb = new StringBuffer(); 

//		使用find()方法查找第一个匹配的对象
		while(m.find()){
//			使用循环将句子里所有的reg找出并替换再将内容加到sb里 
			m.appendReplacement(sb,pre+m.group());
//			System.out.println("sb的内容是："+sb);
		} 
//		最后调用appendTail()方法将最后一次匹配后的剩余字符串加到sb里； 
		m.appendTail(sb); 
		return sb.toString(); 
	}
	
	public String getInfoByRegex(String content, String pattern){
		return getInfoByRegex(content,pattern,1) ;
	}
	public String getInfoByRegex(String content, String pattern, int groupNo){
		Matcher matcher = Pattern.compile(pattern).matcher(content);
		if(matcher.find()){
			return matcher.group(groupNo);
		}
		return null;
	}
	
	public static List getInfoListByRegex(String content,String pattern){
		ArrayList<String> list = new ArrayList<String>() ;
		Matcher matcher = Pattern.compile(pattern).matcher(content);
		while(matcher.find()){
			String s = matcher.group();
			list.add(s) ;
		}
		return list;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String str = "Kevin has seen《LEON》seveal /times,because it is a good film." 
			+"//凯文已经看过《这个杀手不太冷》几次了，因为它是一部好电影。///名词:凯文。";
		String reg="[/]+";
		
//		String[] result = split(str,reg,4);
//        for (int i=0;i<result.length;i++){
//        	System.out.println(result[i]);
//        }
		
//		replace(str,reg,"@");
		System.out.println(replaceAdd(str,reg,"@"));
	}
	
	
//	　　常用的正则表达式主要有以下几种：
//	　　匹配中文字符的正则表达式： [\u4e00-\u9fa5]
//	　　评注：匹配中文还真是个头疼的事，有了这个表达式就好办了
//	　　匹配双字节字符(包括汉字在内)：[^\x00-\xff]
//	　　评注：可以用来计算字符串的长度（一个双字节字符长度计2，ASCII字符计1）
//	　　匹配空白行的正则表达式：\n\s*\r
//	　　评注：可以用来删除空白行
//	　　匹配HTML标记的正则表达式：<(\S*?)[^>]*>.*?</>|<.*? />
//	　　评注：网上流传的版本太糟糕，上面这个也仅仅能匹配部分，对于复杂的嵌套标记依旧无能为力
//	　　匹配首尾空白字符的正则表达式：^\s*|\s*$
//	　　评注：可以用来删除行首行尾的空白字符(包括空格、制表符、换页符等等)，非常有用的表达式
//	　　匹配Email地址的正则表达式：\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*
//	　　评注：表单验证时很实用
//	　　匹配网址URL的正则表达式：[a-zA-z]+://[^\s]*
//	　　评注：网上流传的版本功能很有限，上面这个基本可以满足需求
//	　　匹配帐号是否合法(字母开头，允许5-16字节，允许字母数字下划线)：^[a-zA-Z][a-zA-Z0-9_]{4,15}$
//	　　评注：表单验证时很实用
//	　　匹配国内电话号码：\d-\d|\d-\d
//	　　评注：匹配形式如 0511-4405222 或 021-87888822
//	　　匹配腾讯QQ号：[1-9][0-9]\{4,\}
//	　　评注：腾讯QQ号从10000开始
//	　　匹配中国邮政编码：[1-9]\d(?!\d)
//	　　评注：中国邮政编码为6位数字
//	　　匹配身份证：(^\d{15}|\d{18}$)
//	　　评注：中国的身份证为15位或18位
//	　　匹配ip地址：\d+\.\d+\.\d+\.\d+
//	　　评注：提取ip地址时有用
//	　　匹配特定数字：
//	　　^[1-9]\d*$　//匹配正整数
//	　　^-[1-9]\d*$ //匹配负整数
//	　　^-?[1-9]\d*$　//匹配整数
//	　　^[1-9]\d*|0$　//匹配非负整数（正整数 + 0）
//	　　^-[1-9]\d*|0$　//匹配非正整数（负整数 + 0）
//	　　^[1-9]\d*\.\d*|0\.\d*[1-9]\d*$　//匹配正浮点数
//	　　^-([1-9]\d*\.\d*|0\.\d*[1-9]\d*)$　//匹配负浮点数
//	　　^-?([1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0)$　//匹配浮点数
//	　　^[1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0$　//匹配非负浮点数（正浮点数 + 0）
//	　　^(-([1-9]\d*\.\d*|0\.\d*[1-9]\d*))|0?\.0+|0$　//匹配非正浮点数（负浮点数 + 0）
//	　　评注：处理大量数据时有用，具体应用时注意修正
//	　　匹配特定字符串：
//	　　^[A-Za-z]+$　//匹配由26个英文字母组成的字符串
//	　　^[A-Z]+$　//匹配由26个英文字母的大写组成的字符串
//	　　^[a-z]+$　//匹配由26个英文字母的小写组成的字符串
//	　　^[A-Za-z0-9]+$　//匹配由数字和26个英文字母组成的字符串
//	　　^\w+$　//匹配由数字、26个英文字母或者下划线组成的字符串


}

/**
//		表达式意义：
//		
//		1.字符
//		x    字符 x。例如a表示字符a
//		\\    反斜线字符。在书写时要写为\\\\。（注意：因为java在第一次解析时,把\\\\解析成正则表达式\\，在第二次解析时再解析为\，所以凡是不是1.1列举到的转义字符，包括1.1的\\,而又带有\的都要写两次）
//		\0n    带有八进制值 0的字符 n (0 <= n <= 7)
//		\0nn    带有八进制值 0的字符 nn (0 <= n <= 7)
//		\0mnn    带有八进制值 0的字符 mnn（0 <= m <= 3、0 <= n <= 7）
//		\xhh    带有十六进制值 0x的字符 hh
//		\t    制表符 ('\u0009')
//		\n    新行（换行）符 ('\u000A')
//		\r    回车符 ('\u000D')
//		\f    换页符 ('\u000C')
//		\a    报警 (bell) 符 ('\u0007')
//		\e    转义符 ('\u001B')
//		\cx    对应于 x 的控制符
// 
//		2.字符类
//		[abc]    a、b或 c（简单类）。例如[egd]表示包含有字符e、g或d。
//		[^abc]    任何字符，除了 a、b或 c（否定）。例如[^egd]表示不包含字符e、g或d。
//		[a-zA-Z]    a到 z或 A到 Z，两头的字母包括在内（范围）
//		[a-d[m-p]]    a到 d或 m到 p：[a-dm-p]（并集）
//		[a-z&&[def]]    d、e或 f（交集）
//		[a-z&&[^bc]]    a到 z，除了 b和 c：[ad-z]（减去）
//		[a-z&&[^m-p]]    a到 z，而非 m到 p：[a-lq-z]（减去）
//
//		3.预定义字符类(注意反斜杠要写两次，例如\d写为\\d)任何字符
//		（与行结束符可能匹配也可能不匹配）
//		\d    数字：[0-9]
//		\D    非数字： [^0-9]
//		\s    空白字符：[ \t\n\x0B\f\r]
//		\S    非空白字符：[^\s]
//		\w    单词字符：[a-zA-Z_0-9]
//		\W    非单词字符：[^\w]
//
//		4.POSIX 字符类（仅 US-ASCII)(注意反斜杠要写两次，例如\p{Lower}写为\\p{Lower})
//		\p{Lower}    小写字母字符：[a-z]。
//		\p{Upper}    大写字母字符：[A-Z]
//		\p{ASCII}    所有 ASCII：[\x00-\x7F]
//		\p{Alpha}    字母字符：[\p{Lower}\p{Upper}]
//		\p{Digit}    十进制数字：[0-9]
//		\p{Alnum}    字母数字字符：[\p{Alpha}\p{Digit}]
//		\p{Punct}    标点符号：!"#$%&'()*+,-./:;<=>?@[\]^_`{|}~
//		\p{Graph}    可见字符：[\p{Alnum}\p{Punct}]
//		\p{Print}    可打印字符：[\p{Graph}\x20]
//		\p{Blank}    空格或制表符：[ \t]
//		\p{Cntrl}    控制字符：[\x00-\x1F\x7F]
//		\p{XDigit}    十六进制数字：[0-9a-fA-F]
//		\p{Space}    空白字符：[ \t\n\x0B\f\r]
//
//		5.java.lang.Character 类（简单的 java 字符类型）
//		\p{javaLowerCase}    等效于 java.lang.Character.isLowerCase()
//		\p{javaUpperCase}    等效于 java.lang.Character.isUpperCase()
//		\p{javaWhitespace}    等效于 java.lang.Character.isWhitespace()
//		\p{javaMirrored}    等效于 java.lang.Character.isMirrored()
//
//		6.Unicode 块和类别的类
//		\p{InGreek}    Greek 块（简单块）中的字符
//		\p{Lu}    大写字母（简单类别）
//		\p{Sc}    货币符号
//		\P{InGreek}    所有字符，Greek 块中的除外（否定）
//		[\p{L}&&[^\p{Lu}]]     所有字母，大写字母除外（减去）
//
//		7.边界匹配器
//		^    行的开头，请在正则表达式的开始处使用^。例如：^(abc)表示以abc开头的字符串。注意编译的时候要设置参数MULTILINE，如 Pattern p = Pattern.compile(regex,Pattern.MULTILINE);
//		$    行的结尾，请在正则表达式的结束处使用。例如：(^bca).*(abc$)表示以bca开头以abc结尾的行。
//		\b    单词边界。例如\b(abc)表示单词的开始或结束包含有abc,（abcjj、jjabc 都可以匹配）
//		\B    非单词边界。例如\B(abc)表示单词的中间包含有abc,(jjabcjj匹配而jjabc、abcjj不匹配)
//		\A    输入的开头
//		\G    上一个匹配的结尾(个人感觉这个参数没什么用)。例如\\Gdog表示在上一个匹配结尾处查找dog如果没有的话则从开头查找,注意如果开头不是dog则不能匹配。
//		\Z    输入的结尾，仅用于最后的结束符（如果有的话）
//		行结束符 是一个或两个字符的序列，标记输入字符序列的行结尾。
//		以下代码被识别为行结束符：
//		‐新行（换行）符 ('\n')、
//		‐后面紧跟新行符的回车符 ("\r\n")、
//		‐单独的回车符 ('\r')、
//		‐下一行字符 ('\u0085')、
//		‐行分隔符 ('\u2028') 或
//		‐段落分隔符 ('\u2029)。
//		\z    输入的结尾
//		当编译模式时，可以设置一个或多个标志，例如
//		Pattern pattern = Pattern.compile(patternString,Pattern.CASE_INSENSITIVE + Pattern.UNICODE_CASE);
//		下面六个标志都是支持的：
//		‐CASE_INSENSITIVE：匹配字符时与大小写无关，该标志默认只考虑US ASCII字符。
//		‐UNICODE_CASE：当与CASE_INSENSITIVE结合时，使用Unicode字母匹配
//		‐MULTILINE：^和$匹配一行的开始和结尾，而不是整个输入
//		‐UNIX_LINES： 当在多行模式下匹配^和$时，只将'\n'看作行终止符
//		‐DOTALL: 当使用此标志时，.符号匹配包括行终止符在内的所有字符
//		‐CANON_EQ: 考虑Unicode字符的规范等价
//
//		8.Greedy 数量词
//		X?    X，一次或一次也没有
//		X*    X，零次或多次
//		X+    X，一次或多次
//		X{n}    X，恰好 n 次
//		X{n,}    X，至少 n 次
//		X{n,m}    X，至少 n 次，但是不超过 m 次
//
//		9.Reluctant 数量词
//		X??    X，一次或一次也没有
//		X*?    X，零次或多次
//		X+?    X，一次或多次
//		X{n}?    X，恰好 n 次
//		X{n,}?    X，至少 n 次
//		X{n,m}?    X，至少 n 次，但是不超过 m 次
//
//		10.Possessive 数量词
//		X?+    X，一次或一次也没有
//		X*+    X，零次或多次
//		X++    X，一次或多次
//		X{n}+    X，恰好 n 次
//		X{n,}+    X，至少 n 次
//		X{n,m}+    X，至少 n 次，但是不超过 m 次
//		Greedy，Reluctant，Possessive的区别在于:(注意仅限于进行.等模糊处理时)
//		greedy 量 词被看作“贪婪的”，因为它第一次就读入整个被模糊匹配的字符串。如果第一个匹配尝试（整个输入字符串）失败，匹配器就会在被匹配字符串中的最后一位后退一个字符并且再次尝试，重复这个过程，直到找到匹配或者没有更多剩下的字符可以后退为止。根据表达式中使用的量词，它最后试图匹配的内容是1 个或者0个字符。
//		但是，reluctant量词采取相反的方式：它们从被匹配字符串的开头开始，然后逐步地一次读取一个字符搜索匹配。它们最后试图匹配的内容是整个输入字符串。
//		最后，possessive量词总是读完整个输入字符串，尝试一次（而且只有一次）匹配。和greedy量词不同，possessive从不后退。
//		11.Logical 运算符
//		XY    X 后跟 Y
//		X|Y    X 或 Y
//		(X)    X，作为捕获组。例如(abc)表示把abc作为一个整体进行捕获
//		12.Back 引用
//		\n    任何匹配的 nth捕获组
//		捕获组可以通过从左到右计算其开括号来编号。例如，在表达式 ((A)(B(C)))中，存在四个这样的组：
//		1        ((A)(B(C)))
//		2        \A
//		3        (B(C))
//		4        (C)
//		在表达式中可以通过\n来对相应的组进行引用，例如(ab)34\1就表示ab34ab，(ab)34(cd)\1\2就表示ab34cdabcd。
//		13.引用
//		\    Nothing，但是引用以下字符
//		\Q    Nothing，但是引用所有字符，直到 \E。QE之间的字符串会原封不动的使用(1.1中转义字符的除外)。例如, ab\\Q{|}\\\\E
//		可以匹配ab{|}\\
//		\E    Nothing，但是结束从 \Q开始的引用
//		14.特殊构造（非捕获）
//		(?:X)    X，作为非捕获组
//		(?idmsux-idmsux)     Nothing，但是将匹配标志由 on 转为 off。比如：表达式 (?i)abc(?-i)def 这时，(?i) 打开不区分大小写开关，abc 匹配
//		idmsux说明如下：
//		‐i CASE_INSENSITIVE :US-ASCII 字符集不区分大小写。(?i)
//		‐d UNIX_LINES : 打开UNIX换行符
//		‐m MULTILINE :多行模式(?m)
//		UNIX下换行为\n
//		WINDOWS下换行为\r\n(?s)
//		‐u UNICODE_CASE : Unicode 不区分大小写。(?u)
//		‐x COMMENTS :可以在pattern里面使用注解，忽略pattern里面的whitespace，以及"#"一直到结尾（#后面为注解）。(?x)例如(?x)abc#asfsdadsa可以匹配字符串abc
//		(?idmsux-idmsux:X)     X，作为带有给定标志 on - off 的非捕获组。与上面的类似，上面的表达式，可以改写成为：(?i:abc)def，或者 (?i)abc(?-i:def)
//		(?=X)    X，通过零宽度的正 lookahead。零宽度正先行断言，仅当子表达式 X 在 此位置的右侧匹配时才继续匹配。例如，\w+(?=\d) 表示字母后面跟数字，但不捕获数字（不回溯）
//		(?!X)    X，通过零宽度的负 lookahead。零宽度负先行断言。仅当子表达式 X 不在 此位置的右侧匹配时才继续匹配。例如，\w+(?!\d) 表示字母后面不跟数字，且不捕获数字。
//		(?<=X)    X，通过零宽度的正 lookbehind。零宽度正后发断言。仅当子表达式 X 在 此位置的左侧匹配时才继续匹配。例如，(?<=19)99 表示99前面是数字19，但不捕获前面的19。（不回溯）
//		(?<!--&nbsp;&nbsp;&nbsp; X，通过零宽度的负 lookbehind。零宽度负后发断言。仅当子表达式 X 不在此位置的左侧匹配时才继续匹配。例如，--><!--)99 表示99前面不能是19，且不捕获前面的东-->
//		(?>X)    X，作为独立的非捕获组（不回溯）
//		(?=X)与(?>X)的区别在于(?>X)是不回溯的。例如被匹配的字符串为abcm
//		当表达式为a(?:b|bc)m是可以匹配的，而当表达式是a(?>b|bc)时是不能匹配的，因为当后者匹配到b时，由于已经匹配，就跳出了非捕获组，而不再次对组内的字符进行匹配。可以加快速度。

*/