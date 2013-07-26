package com.method.character;

/**
 * 2011 2011-5-10
 * by diyong
 * 
 */

public class Character {

    /**
     * @param args
     */
    public static void main(String[] args) {
	// TODO Auto-generated method stub

    }

    public static boolean isChinese(String c){
	String regEx = "[\\u4e00-\\u9fa5]";
	if(c.matches(regEx)){
	    return true ;
	}
	return false ;
    }
}
