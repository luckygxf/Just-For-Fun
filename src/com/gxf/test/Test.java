package com.gxf.test;

import java.io.File;

import com.gxf.util.Util;

/**
 * ������
 * @author Administrator
 *
 */
public class Test {

	public static void main(String[] args) {
		Util util = new Util();
		String filePath = "C:\\Documents and Settings\\Administrator\\����\\���ڴ��\\Ҫ��.txt";
		File txtFile = new File(filePath);
		
		int spaceCount = util.getAnnotationLineCount(txtFile);
		
		System.out.println("lineCount = " + spaceCount);
	}

}
