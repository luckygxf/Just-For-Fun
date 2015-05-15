package com.gxf.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class Util {
	
	/**
	 * 获取文本文件行数
	 * @param file
	 * @return
	 */
	public int getFileCountLine(File file){
		int bufferSize = 1024;
		byte buffer[] = new byte[bufferSize];
		
		//文本文件内容行数
		int lineCount = 1;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			int readCount = 0;
			
			//从文件中读取文件
			while((readCount = fis.read(buffer)) != -1){
				String str = new String(buffer, 0, readCount);
				//统计换行符
				for(int i = 0; i< str.length(); i++){
					if(str.charAt(i) == '\n'){
						lineCount ++;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}//finally
		
		return lineCount;
	}
	
	/**
	 * 获取文本文件中空行数
	 * @param file
	 * @return
	 */
	public int getSpaceLineCount(File file){
		int count = 0;
		BufferedReader bfr = null;
		try {
			FileReader fileReader = new FileReader(file);
			bfr = new BufferedReader(fileReader);
			String line = "";
			//读取一行内容
			while((line = bfr.readLine()) != null)
			{
				if(line.equals(""))
					count++;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				bfr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}//finally
		
		return count;		
	}
	
	/**
	 * 获取java代码中的注释行数
	 * 这里用的正则表达式+优化了一下，不是很准确
	 * @param file
	 * @return
	 */
	public int getAnnotationLineCount(File file){
		int count = 0;
		BufferedReader bfr = null;
		//判断注释行的这则表达式
		String expression = "((//)|(/\\*+)|((^\\s)*\\*)|((^\\s)*\\*+/))+";
		
		// 定义匹配每一行的正则匹配器  
        Pattern annotationLinePattern = Pattern.compile( expression,   
                Pattern.MULTILINE + Pattern.DOTALL);    // 注释匹配器(匹配单行、多行、文档注释) 
		try {
			FileReader fileReader = new FileReader(file);
			bfr = new BufferedReader(fileReader);
			String line = "";
			//读取一行内容
			while((line = bfr.readLine()) != null)
			{
				if(annotationLinePattern.matcher(line).find())
					count++;
				else if(line.contains("*"))
					count++;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				bfr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}//finally
		
		return count;		
	}
	
	/**
     * 获取当前工程目录
     * @return
     */
    public String getCurrentProjectPath(){
    	String curPath = System.getProperty("user.dir");
    	
    	return curPath;
    }
}
