package com.method.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

/** 
 * 9宫算法
 * by dyong 2010-10-22
 */
public class Gong9 {

	private int x = 3 ;		//
	private int avg = 0 ;	//行和列的和
	private int max = 0;	//最大值
	
	Stack<Stack<Integer>> rows ;
	Stack<Stack<Integer>> result ;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Gong9 g = new Gong9();
		
		g.gong9() ;
	}
	
	public void gong9(){
		init() ;
		
		rows = getRows() ;
		
//		result = guolv1(rows) ;
		ArrayList<Stack<Stack<Integer>>> rrs = guolv(rows) ;
		
//		result = cols(rrs) ;
		
		System.out.println("=========") ;
		int[][] re = new int[x][x] ;
//		for(int i=0;i<x;i++){
//			outStack(result.get(i)) ;
//			for(int j=0;j<result.get(i).size();j++){
//				re[i][j] = result.get(i).get(j) ;
//			}
//		}
		
		re = paixu(re) ;
		
	}
	
	private int[][] paixu(int[][] re) {
		int[] row = re[0] ;
		for(int i=0;i<x;i++){
			int p = row[i] ;
			
		}
		return re ;
	}

	/**
	 * 过滤出满足要求的一组数据
	 * 包括X组数组，为X*X的二维数组
	 * @param rows
	 * @return
	 */
	private ArrayList<Stack<Stack<Integer>>> guolv(Stack<Stack<Integer>> rows){
		ArrayList<Stack<Stack<Integer>>> list = new ArrayList<Stack<Stack<Integer>>>() ;
//		Stack<Stack<Integer>> result = new Stack<Stack<Integer>>() ;
//		HashSet<Integer> set = new HashSet<Integer>() ;
		
		for(int i=0;i<rows.size();i++){
			HashSet<Integer> set = new HashSet<Integer>() ;
			set.addAll(rows.get(i)) ;
			Stack<Stack<Integer>> result = new Stack<Stack<Integer>>() ;
			result.add(rows.get(i)) ;
			for(int p=0;p<rows.size();p++){
				Stack<Integer> s = rows.get(p) ;
				if(!contentStack(set,s)){
					set.addAll(s) ;
					result.add(s) ;
				}
			}
			if(result.size()==x){
				System.out.println("--------------") ;
				for(int y=0;y<x;y++){
					outStack(result.get(y)) ;
				}
				if(!list.contains(result)){
					list.add(result) ;
				}
//				return result ;
			}
		}
		
		
		
		return list ;
	}
	private Stack<Stack<Integer>> guolv1(Stack<Stack<Integer>> rows){
		Stack<Stack<Integer>> result = new Stack<Stack<Integer>>() ;
		HashSet<Integer> set = new HashSet<Integer>() ;
		
		for(int i=0;i<rows.size();i++){
			set.addAll(rows.get(i)) ;
			result = new Stack<Stack<Integer>>() ;
			result.add(rows.get(i)) ;
			for(int p=0;p<rows.size();p++){
				Stack<Integer> s = rows.get(p) ;
				if(!contentStack(set,s)){
					set.addAll(s) ;
					result.add(s) ;
				}
			}
			if(result.size()==x){
				return result ;
			}
		}
		
		return result ;
	}
	
	private boolean contentStack(HashSet<Integer> set,Stack<Integer> s){
		for(int i=0;i<s.size();i++){
			if(set.contains(s.get(i))){
				return true ;
			}
		}
		return false ;
	}
	
	/**
	 * 取所有满足行要求的数组
	 * @return
	 */
 	private Stack<Stack<Integer>> getRows(){
		Stack<Stack<Integer>> all = new Stack<Stack<Integer>>() ;
		Stack<Integer> stack = new Stack<Integer>() ;
		
		boolean re =true ;
		while(true){
//			缺失位补齐
			if(stack.size()<x){
				while(stack.size()<x){
					re = pushNext(stack,1) ;
				}
			} else if(stack.lastElement()<max) {
//				最后一位增加
				int p = stack.pop() ;
				re = pushNext(stack,p+1) ;
			} else {
//				最后一位为最大值，往前进位
				int p = stack.pop();
				while(p==max){
					if(stack.isEmpty()){
//						查找完，结束
						break ;
					}
					p = stack.pop();
				}
				re = pushNext(stack,p+1) ;
			}
			
//			if(stack.size()>3 && stack.get(1)==2 && stack.get(2)==16&& stack.get(3)==15){
//				System.currentTimeMillis() ;
//			}
			
			if(stack.size()==x && getCount(stack)==avg){
				all.push((Stack<Integer>) stack.clone()) ;
				outStack(stack) ;
				
				int p = stack.pop() ;
				re = pushNext(stack,p+1) ;
			}
			if(!re){
//				报错，结束
				break ;
			}
			
		}
		return all ;
	}
	private boolean pushNext(Stack<Integer> s,int p){
		boolean re = false ;
//		追加上一级
		if(p>max && !s.isEmpty()){
			int pre = s.pop()+1 ;
			re = pushNext(s,pre) ;
		} else {
			for(;p<=max;p++){
				
//				添加数据
				if(!s.contains(p)){
					s.push(p) ;
					re = true ;
					break ;
				}
			}
		}
		
//		未查找到，上追一级
		if(!re && p>=max && !s.isEmpty()){
			int pre = s.pop()+1 ;
			re = pushNext(s,pre) ;
		}
//		补足长度
		while(re && s.size()<x){
			re = pushNext(s,1);
		}
		return re ;
	}

	/**
	 * 初始化变量
	 */
	private void init(){
		int he = (1+x*x)*x*x/2 ;	//总和
		avg = he/x ;			//每行和每列和
		max = x*x ;
	}

	private int getCount(Stack<Integer> stack) {
		int all =0 ;
		for(int i=0;i<stack.size();i++){
			all+=stack.get(i) ;
		}
		return all;
	}
	private void outStack(Stack<Integer> stack) {
		for(int i=0;i<stack.size();i++){
			System.out.print(stack.get(i)+"\t") ;
		}
		System.out.println();
	}
	private boolean jiaoyan(int[][] result,int avg){
		for(int i=0;i<x;i++){
			int all =0 ;
			for(int j=0;j<x;j++){
				all += result[j][i] ;
			}
			if(all!=avg){
				return false ;
			}
		}
		return true ;
	}
}
