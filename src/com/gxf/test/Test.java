package com.gxf.test;

import java.io.File;

import com.gxf.util.Util;

/**
 * 测试类
 * @author Administrator
 *
 */
public class Test {

	public static void main(String[] args) {
		Util util = new Util();
		String filePath = "C:\\Documents and Settings\\Administrator\\桌面\\中期答辩\\要求.txt";
		File txtFile = new File(filePath);
		
		int spaceCount = util.getAnnotationLineCount(txtFile);
		
		System.out.println("lineCount = " + spaceCount);
	}

}
