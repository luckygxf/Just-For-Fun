package com.gxf.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class Util {
	
	/**
	 * ��ȡ�ı��ļ�����
	 * @param file
	 * @return
	 */
	public int getFileCountLine(File file){
		int bufferSize = 1024;
		byte buffer[] = new byte[bufferSize];
		
		//�ı��ļ���������
		int lineCount = 1;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			int readCount = 0;
			
			//���ļ��ж�ȡ�ļ�
			while((readCount = fis.read(buffer)) != -1){
				String str = new String(buffer, 0, readCount);
				//ͳ�ƻ��з�
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
	 * ��ȡ�ı��ļ��п�����
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
			//��ȡһ������
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
	 * ��ȡjava�����е�ע������
	 * �����õ�������ʽ+�Ż���һ�£����Ǻ�׼ȷ
	 * @param file
	 * @return
	 */
	public int getAnnotationLineCount(File file){
		int count = 0;
		BufferedReader bfr = null;
		//�ж�ע���е�������ʽ
		String expression = "((//)|(/\\*+)|((^\\s)*\\*)|((^\\s)*\\*+/))+";
		
		// ����ƥ��ÿһ�е�����ƥ����  
        Pattern annotationLinePattern = Pattern.compile( expression,   
                Pattern.MULTILINE + Pattern.DOTALL);    // ע��ƥ����(ƥ�䵥�С����С��ĵ�ע��) 
		try {
			FileReader fileReader = new FileReader(file);
			bfr = new BufferedReader(fileReader);
			String line = "";
			//��ȡһ������
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
     * ��ȡ��ǰ����Ŀ¼
     * @return
     */
    public String getCurrentProjectPath(){
    	String curPath = System.getProperty("user.dir");
    	
    	return curPath;
    }
}
