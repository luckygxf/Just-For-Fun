package com.gxf.main;

import java.io.File;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

import com.gxf.util.Util;

/**
 * ����ͳ����
 * @author Administrator
 *
 */
public class Main extends ApplicationWindow {
	//�������Ŀؼ�
	private Text txt_projectPath;
	private Text txt_fileCount;
	private Text txt_contentLine;
	private Text txt_javaFileCount;
	private Text txt_xmlFileCount;
	private Text txt_jarFileCount;
	private Text txt_picFileCount;
	private Button btn_chooseProject;
	private Text txt_annotaionCount;
	private Text txt_spaceCount;
	private Text txt_codeCount;
	
	//������
	private Shell curShell;
	private String projectPath;
	private Util util = new Util();
	
	//ͳ�ƽ��
	private int fileTotal = 0;
	private int contenteLine = 0;
	private int javaFileCount = 0;
	private int codeLine = 0;
	private int picFileCount = 0;
	private int xmlFileCount = 0;
	private int jarFileCount = 0;
	private int spaceLineCount = 0;
	private int annotationLineCount = 0;
	
	
	/**
	 * Create the application window.
	 */
	public Main() {
		super(null);
		setShellStyle(SWT.MIN);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
	}

	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		
		Label lb_projectPathIcon = new Label(container, SWT.NONE);
		lb_projectPathIcon.setBounds(10, 10, 54, 12);
		lb_projectPathIcon.setText("����Ŀ¼");
		
		txt_projectPath = new Text(container, SWT.BORDER);
		txt_projectPath.setEditable(false);
		txt_projectPath.setBounds(70, 4, 346, 18);
		
		btn_chooseProject = new Button(container, SWT.NONE);
		btn_chooseProject.setBounds(443, 4, 72, 22);
		btn_chooseProject.setText("���");
		
		Label lb_fileCountsIcon = new Label(container, SWT.NONE);
		lb_fileCountsIcon.setBounds(10, 36, 54, 12);
		lb_fileCountsIcon.setText("�ļ�����");
		
		txt_fileCount = new Text(container, SWT.BORDER);
		txt_fileCount.setEditable(false);
		txt_fileCount.setBounds(92, 33, 70, 18);
		
		Label lb_contentLineIcon = new Label(container, SWT.NONE);
		lb_contentLineIcon.setBounds(10, 156, 54, 12);
		lb_contentLineIcon.setText("�ı�����");
		
		txt_contentLine = new Text(container, SWT.BORDER);
		txt_contentLine.setEditable(false);
		txt_contentLine.setBounds(92, 153, 70, 18);
		
		Label lb_javaFileCount = new Label(container, SWT.NONE);
		lb_javaFileCount.setBounds(10, 67, 76, 12);
		lb_javaFileCount.setText("java�ļ�����");
		
		txt_javaFileCount = new Text(container, SWT.BORDER);
		txt_javaFileCount.setEditable(false);
		txt_javaFileCount.setBounds(92, 64, 70, 18);
		
		Label lb_xmlFileCountIcon = new Label(container, SWT.NONE);
		lb_xmlFileCountIcon.setBounds(303, 33, 76, 12);
		lb_xmlFileCountIcon.setText("xml�ļ�����");
		
		txt_xmlFileCount = new Text(container, SWT.BORDER);
		txt_xmlFileCount.setEditable(false);
		txt_xmlFileCount.setBounds(427, 30, 70, 18);
		
		Label lb_jarFileCount = new Label(container, SWT.NONE);
		lb_jarFileCount.setBounds(10, 104, 76, 12);
		lb_jarFileCount.setText("jar������");
		
		txt_jarFileCount = new Text(container, SWT.BORDER);
		txt_jarFileCount.setEditable(false);
		txt_jarFileCount.setBounds(92, 98, 70, 18);
		
		Label lb_picFileCount = new Label(container, SWT.NONE);
		lb_picFileCount.setBounds(303, 61, 76, 12);
		lb_picFileCount.setText("ͼƬ�ļ���");
		
		txt_picFileCount = new Text(container, SWT.BORDER);
		txt_picFileCount.setEditable(false);
		txt_picFileCount.setBounds(427, 61, 70, 18);
		
		Label lb_annotation = new Label(container, SWT.NONE);
		lb_annotation.setBounds(303, 156, 54, 12);
		lb_annotation.setText("ע������");
		
		txt_annotaionCount = new Text(container, SWT.BORDER);
		txt_annotaionCount.setEditable(false);
		txt_annotaionCount.setBounds(427, 153, 70, 18);
		
		Label lb_spaceCount = new Label(container, SWT.NONE);
		lb_spaceCount.setBounds(10, 182, 54, 12);
		lb_spaceCount.setText("������");
		
		txt_spaceCount = new Text(container, SWT.BORDER);
		txt_spaceCount.setEditable(false);
		txt_spaceCount.setBounds(92, 177, 70, 18);
		
		Label lb_codeCount = new Label(container, SWT.NONE);
		lb_codeCount.setBounds(303, 182, 54, 12);
		lb_codeCount.setText("��������");
		
		txt_codeCount = new Text(container, SWT.BORDER);
		txt_codeCount.setEditable(false);
		txt_codeCount.setBounds(427, 176, 70, 18);
		
		//��ʼ��
		init();
		
		return container;
	}

	/**
	 * ��ʼ��
	 */
	private void init(){
		btn_chooseProject.addSelectionListener(new ButtonSelectionListener());
	}
	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Create the menu manager.
	 * @return the menu manager
	 */
	@Override
	protected MenuManager createMenuManager() {
		MenuManager menuManager = new MenuManager("menu");
		return menuManager;
	}

	/**
	 * Create the toolbar manager.
	 * @return the toolbar manager
	 */
	@Override
	protected ToolBarManager createToolBarManager(int style) {
		ToolBarManager toolBarManager = new ToolBarManager(style);
		return toolBarManager;
	}

	/**
	 * Create the status line manager.
	 * @return the status line manager
	 */
	@Override
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLineManager = new StatusLineManager();
		return statusLineManager;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Main window = new Main();
			window.setBlockOnOpen(true);
			window.open();
			Display.getCurrent().dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Configure the shell.
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("����ͳ�ƹ���--Developed by GuanXianSeng");
		
		//���ó���logo
		String logoPath = util.getCurrentProjectPath() + File.separator + "icons" + File.separator + "logo.png";
		ImageData logoImageData = new ImageData(logoPath);
		Image logoImage = new Image(Display.getDefault(), logoImageData);
		newShell.setImage(logoImage);
		
		curShell = newShell;
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(596, 300);
	}
	
	/**
	 * ��ȡ��Ŀ·��
	 * @return
	 */
	private void getProjectPath(){
		DirectoryDialog dialog = new DirectoryDialog(curShell);
		dialog.setText("ѡ���ļ�Ŀ¼");
		dialog.setMessage("ѡ����Ŀ�ļ�");
		
		//����һ��Ĭ��·�����������
		String defaultPath = "D:\\Github_Eclipse\\Manager_JFace";
		dialog.setFilterPath(defaultPath);
		
		String projectPath = dialog.open();
		
		if(projectPath != null)
		{
			clearCount();
		}
		
		this.projectPath = projectPath;
	}
	
	/**
	 * ����������
	 * @author Administrator
	 *
	 */
	class ButtonSelectionListener extends SelectionAdapter{

		@Override
		public void widgetSelected(SelectionEvent e) {
			if(e.getSource() == btn_chooseProject){						//�����ť				
				//��ȡ��Ŀ·��
				getProjectPath();
				//�ж�·���Ƿ�Ϊ��
				if(projectPath == null || projectPath.equals(""))
					return;
				//��ȡ�ļ�����
				fileCount();
				//��ȡ�ļ�������
				getFileContenLine(new File(projectPath));
				
				
				//��������ŵ�
				txt_projectPath.setText(projectPath);
				txt_fileCount.setText(String.valueOf(fileTotal));
				txt_contentLine.setText(String.valueOf(contenteLine));
				txt_javaFileCount.setText(String.valueOf(javaFileCount));
				txt_codeCount.setText(String.valueOf(codeLine));
				txt_picFileCount.setText(String.valueOf(picFileCount));
				txt_xmlFileCount.setText(String.valueOf(xmlFileCount));
				txt_jarFileCount.setText(String.valueOf(jarFileCount));
				txt_spaceCount.setText(String.valueOf(spaceLineCount));
				txt_annotaionCount.setText(String.valueOf(annotationLineCount));
			}
		}		
	}
	
	/**
	 * ��ȡ�ļ�����
	 * @return
	 */
	public int fileCount(){
		if(projectPath == null || projectPath.equals(""))
			return 0;
		File projectFile = new File(projectPath);
		int count = 0;
		count = getFileCount(projectFile);
		
//		System.out.println("count = " + count);
		return count;
	}
	
	/**
	 * �ݹ��ȡ�ļ�
	 * @param fileToCount
	 * @return
	 */
	public int getFileCount(File fileToCount){
		if(!fileToCount.isDirectory())
		{
			//��ȡjava�ļ�����
			if(fileToCount.getName().endsWith(".java"))
			{
				javaFileCount++;
				//��ȡ��������
				int temp = util.getFileCountLine(fileToCount);
				codeLine += temp;
				
				//��ȡ������Ŀ
				spaceLineCount += util.getSpaceLineCount(fileToCount);
				//��ȡע������
				annotationLineCount += util.getAnnotationLineCount(fileToCount);
			}
			//��ȡͼƬ����
			if(validPicName(fileToCount.getName())){
				picFileCount++;
			}
			//��ȡxml�ļ�
			if(fileToCount.getName().endsWith(".xml")){
				xmlFileCount++;
			}
			//��ȡjar�ļ�����
			if(fileToCount.getName().endsWith(".jar")){
				jarFileCount++;
			}
			
			
			return fileTotal++;
		}
		else{
			File files[] = fileToCount.listFiles();
			for(int i = 0; i < files.length; i++){
				getFileCount(files[i]);
			}
		}
		return fileTotal;
	}
	
	/**
	 * �ļ�����
	 * @param fileToCount
	 * @return
	 */
	public int getFileContenLine(File fileToCount){
		if(!fileToCount.isDirectory())
		{	
			if(fileToCount.getName().endsWith(".class"))
				return 0;
			int temp = util.getFileCountLine(fileToCount);
			contenteLine += temp;
			return contenteLine;
		}
		else{
			File files[] = fileToCount.listFiles();
			for(int i = 0; i < files.length; i++){
				getFileContenLine(files[i]);
			}
		}
		return contenteLine;
	}
	
	/**
	 * ��֤�Ƿ�ΪͼƬ����
	 * @param fileName
	 * @return
	 */
	private boolean  validPicName(String fileName){
		String picNames[] = {"png", "jpg", "bmp", "jpeg", "gif"};
		
		for(int i = 0; i < picNames.length; i++){
			if(fileName.toLowerCase().endsWith(picNames[i]))
				return true;
		}
		
		return false;
	}
	
	/**
	 * ��������������Ϊ0 
	 */
	private void clearCount(){
		 fileTotal = 0;
		 contenteLine = 0;
		 javaFileCount = 0;
		 codeLine = 0;
		 picFileCount = 0;
		 xmlFileCount = 0;
		 jarFileCount = 0;
		 spaceLineCount = 0;	
		 annotationLineCount = 0;
	}

}
