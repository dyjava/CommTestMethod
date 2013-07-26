package com.method.algorithm;

import java.util.HashSet;
import java.util.Stack;

/** 
 * by dyong 2010-10-25
 */
public class Gong {

	private int x = 5 ;		//
	private int avg = 0 ;	//行和列的和
	private int max = 0;	//最大值
	
	/**
	 * 结果
	 * 	2	7	6	
		9	5	1	
		4	3	8
	 * 
	 * 	1	2	15	16	
		12	14	3	5	
		13	7	10	4	
		8	11	6	9	
	 * @param args
	 */
	public static void main(String[] args) {
		long l1 = System.currentTimeMillis() ;
		Gong s = new Gong();
		s.x=3;
		s.init();
		s.out(s.get9Gong()) ;
		long l2 = System.currentTimeMillis() ;
		System.out.println("time:"+(l2-l1)) ;
		l1 = System.currentTimeMillis() ;

		s = new Gong();
		s.x =3;
		s.init();
		s.put();
		l2 = System.currentTimeMillis() ;
		System.out.println("time:"+(l2-l1)) ;
		l1 = System.currentTimeMillis() ;
		
		s = new Gong();
		s.x =4;
		s.init();
		s.put();
		l2 = System.currentTimeMillis() ;
		System.out.println("time:"+(l2-l1)) ;
		l1 = System.currentTimeMillis() ;
		
		s = new Gong();
		s.x =5;
		s.init();
		s.put();
		l2 = System.currentTimeMillis() ;
		System.out.println("time:"+(l2-l1)) ;
		l1 = System.currentTimeMillis() ;
		
		s = new Gong();
		s.x =6;
		s.init();
		s.put();
		l2 = System.currentTimeMillis() ;
		System.out.println("time:"+(l2-l1)) ;
	}
	/**
	 * 初始化变量
	 */
	private void init(){
		max = x*x ;
		int he = (1+max)*max/2 ;	//总和
		avg = he/x ;			//每行和每列和
	}
	
	/**
	 * 输出9宫图
	 * @return
	 */
	private int[] get9Gong(){
		init() ;
//
		int[] arr = new int[max] ;
		for(int p1=0;p1<max;p1++){
			arr[0]= p1+1 ;
			for(int p2=0;p2<max;p2++){
				if(cont(arr,p2+1)){continue;}
				arr[1]=p2+1 ;
				for(int p3=0;p3<max;p3++){
					if(cont(arr,p3+1)){continue;}
					arr[2]=p3+1 ;
					for(int p4=0;p4<max;p4++){
						if(cont(arr,p4+1)){continue;}
						arr[3]=p4+1 ;
						for(int p5=0;p5<max;p5++){
							if(cont(arr,p5+1)){continue;}
							arr[4]=p5+1 ;
							for(int p6=0;p6<max;p6++){
								if(cont(arr,p6+1)){continue;}
								arr[5]=p6+1 ;
								for(int p7=0;p7<max;p7++){
									if(cont(arr,p7+1)){continue;}
									arr[6]=p7+1 ;
									for(int p8=0;p8<max;p8++){
										if(cont(arr,p8+1)){continue;}
										arr[7]=p8+1 ;
										for(int p9=0;p9<max;p9++){
											if(cont(arr,p9+1)){continue;}
											arr[8]=p9+1 ;
											boolean b = panduan(arr) ;
											if(b){
												return arr ;
											}
											arr[8]= 0;
										}
										arr[7]= 0;
										arr[8]= 0;
									}
									arr[6]= 0;
									arr[7]= 0;
									arr[8]= 0;
								}
								arr[5]= 0;
								arr[6]= 0;
								arr[7]= 0;
								arr[8]= 0;
							}
							arr[4]= 0;
							arr[5]= 0;
							arr[6]= 0;
							arr[7]= 0;
							arr[8]= 0;
						}
						arr[3]= 0;
						arr[4]= 0;
						arr[5]= 0;
						arr[6]= 0;
						arr[7]= 0;
						arr[8]= 0;
					}
					arr[2]= 0;
					arr[3]= 0;
					arr[4]= 0;
					arr[5]= 0;
					arr[6]= 0;
					arr[7]= 0;
					arr[8]= 0;
				}
				arr[1]= 0;
				arr[2]= 0;
				arr[3]= 0;
				arr[4]= 0;
				arr[5]= 0;
				arr[6]= 0;
				arr[7]= 0;
				arr[8]= 0;
			}
			arr[0]= 0;
			arr[1]= 0;
			arr[2]= 0;
			arr[3]= 0;
			arr[4]= 0;
			arr[5]= 0;
			arr[6]= 0;
			arr[7]= 0;
			arr[8]= 0;
		}
		return null ;
	}
//	判断数组中是否包含数字P
	private boolean cont(int[] arr,int p){
		if(arr.length>0)
		for(int j=0;j<arr.length;j++){
			int pp = arr[j] ;
			if(pp==p){
				return true ;
			}
		}
		return false ;
	}
//	判断结果是否满足
	private boolean panduan(int[] arr) {
		for(int i=0;i<max;i++){
			int p = arr[i] ;
			for(int j=i+1;j<max;j++){
				int pp = arr[j] ;
				if(pp==p){
					return false ;
				}
			}
		}
//		row
		int row =0;
		for(int i=0;i<max;i++){
			row+=arr[i] ;
			if((i+1)%x==0){
				if(row!=avg){
					return false ;
				}
				row =0;
			}
		}

//		col
		for(int i=0;i<x;i++){
			int col =0;
			for(int j=0;j<x;j++){
				col+=arr[i+j*x] ;
			}
			if(col!=avg){
				return false ;
			}
		}
//		
		int a =0;
		for(int i=0;i<x;i++){
			a += arr[i+i*x] ;
		}
		if(a!=avg){
			return false ;
		}

//		
		a =0;
		for(int i=0;i<x;i++){
			a += arr[i+(x-1-i)*x] ;
		}
		if(a!=avg){
			return false ;
		}
		
		return true ;
	}
//	输出
	private void out(int[] arr){
		System.out.println("==========================") ;
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]) ;
			System.out.print("\t") ;
			if((i+1)%x==0){
				System.out.println() ;
			}
		}
	}
	
	private Stack<Integer> put(){
		Stack<Integer> stack = new Stack<Integer>() ;
		int all =0,p=1;

		while(true){
			p=1 ;
			while(stack.size()<max){
//				补一条数据
				for(int i=1;i<=max;i++){
					if(stack.contains(i)){
						continue ;
					}
					stack.push(i) ;
					break ;
				}

//				满一行后验证。最后一行不用验证，肯定满足行要求
				while(stack.size()<max && stack.size()%x==0){
					all = getLastLineAll(stack);
					
					if(all==avg){
						break ;
					}
//					增一
					int pop = stack.pop() ;
					popStack(stack,pop+1) ;
				}
//				最后一行，数据填满，验证第一列和最后一列
				if(stack.size()==max){
//					判断第一列是否满足
					all =0;
					for(int i=0;i<x-1;i++){
						all+=stack.get(i*x) ;
					}
					
					int dj =0;
					//验证斜对角
					for(int i=0;i<x-1;i++){
						dj+=stack.get(i*x+x-1-i) ;
					}
//					第一列和对角匹配，继续
					if(all+stack.get(max-x)==avg ){
						if(dj+stack.get(max-x)==avg){
							continue ;
						}
					}
//					第一列匹配失败，移除最后一行，
					Stack<Integer> last = new Stack<Integer>() ;
					for(int i=0;i<x;i++){
						p=stack.pop() ;
						last.push(p) ;
					}

//					
					if(all!=dj){
						for(int i=0;i<x-1;i++){
							p=stack.pop() ;
						}
						popStack(stack,p+1) ;
//						continue ;
					} else {
//					第一列是否能和最后一行某个值匹配，达到满足，否则倒数第二行最后一个增一
						int pipei = isRemoveLast2Line(stack,last);
						if(pipei>0 && pipei>p){
							stack.push(pipei) ;
						} else {
							p=stack.pop() ;
							popStack(stack,p+1) ;
						}
					}
//					popStack(stack,p+1) ;
				}
				
			}
			
			if(checkStack(stack)){
				outStack(stack) ;
				return stack ;
			}
			
//			if(arr[0]==max){
//				return null ;
//			}
			
			int pop = stack.pop() ;
			while(pop==max){
				pop = stack.pop() ;
			}
			pop++ ;
			popStack(stack,pop);
		}
	}
	/**
	 * stack 中的第一列是否有可能和last中的组合，够上平均值
	 * 用于填满数据后，判断第一列是否满足
	 * @param stack
	 * @param last
	 * @return
	 */
	private int isRemoveLast2Line(Stack<Integer> stack, Stack<Integer> last) {
		int all=0;
		for(int i=0;i<x-1 && stack.size()>i*x+1;i++){
			all+=stack.get(i*x) ;
		}
		while(!last.isEmpty()){
			int p=last.pop() ;
			if(all+p==avg){
				return p ;
			}
		}
		return 0 ;
	}
//	最后一行数据和
	private int getLastLineAll(Stack<Integer> stack){
		int all =0;
		if(stack.size()%x==0)
		for(int i=stack.size()-1;i>=(stack.size()-x);i--){
			all+=stack.get(i) ;
		}
		return all ;
	}
//	增一
	private void popStack(Stack<Integer> stack, int pop) {
		while(pop>max){
			pop = stack.pop() ;
			pop++ ;
		}
		while(stack.contains(pop)){
			while(pop>=max){
				if(stack.size()==0){
					return ;
				}
				pop = stack.pop() ;
			}
			pop++ ;
		}
		stack.push(pop) ;
	}
//	校验
	private boolean checkStack(Stack<Integer> stack){
		if(stack.size()<max){
			return false ;
		}

//		校验对角
		int zheng = 0;
		int xie = 0;
		for(int i=0;i<x;i++){
			zheng+= stack.get(i*x+i) ;
			xie+= stack.get((x-i-1)*x+i) ;
		}
		if(zheng!=avg || xie!=avg){
			return false ;
		}
		
//		校验行和列
		for(int i=0;i<x;i++){
			int row = 0;
			int col = 0;
			for(int j=0;j<x;j++){
				row+= stack.get(i*x+j) ;
				col+= stack.get(i+j*x) ;
			}
			if(row!=avg || col!=avg){
				return false ;
			}
		}
		
//		校验是否有重复数据
		HashSet<Integer> set = new HashSet<Integer>() ;
		for(int i:stack){
			set.add(i) ;
		}
		if(set.size()<max){
			return false ;
		}
		
		return true ;
	}
//	输出
	private void outStack(Stack<Integer> stack){
		System.out.println("=============Stack============") ;
//		for(int i=0;i<x;i++){
//			for(int j=0;j<x;j++){
//				System.out.print(stack.get(i*x+j)) ;
//				System.out.print("\t") ;
//			}
//			System.out.println() ;
//		}
		while(!stack.isEmpty()){
			System.out.print(stack.pop()) ;
			System.out.print("\t") ;
			if(stack.size()%x==0){
				System.out.println() ;
			}
		}
	}
}
