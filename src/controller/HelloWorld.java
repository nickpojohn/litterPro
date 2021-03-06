package controller;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import Tools.DBHelper;
import Tools.ExcelUtil2;
import Tools.IoReadWrite;

/**
 * @author liting 作者 E-mail:pojohn@163.com
 * @version 创建时间：2016年11月16日 下午6:15:42
 * 类说明
 */
public class HelloWorld {

	protected Shell shell;
	
	Logger logger = Logger.getLogger(this.getClass());

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			HelloWorld window = new HelloWorld();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}		    
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(600, 680);
		shell.setText("邮储银行数据合成小软件V1.0");
		
		final Composite composite = new Composite(shell, SWT.BORDER);   
		composite.setBounds(20, 25, 600, 680);    
		composite.setToolTipText("Composite容器");
		
		final Group group = new Group(composite, SWT.NONE);   
		group.setBounds(60, 60, 300, 220);   
		group.setText("数据库连接模块：");    
		group.setToolTipText("Group容器");
		
		final Label label1 = new Label(group, SWT.NONE); 
		label1.setBounds(10, 30, 80, 25); 
		label1.setText("用户名："); 
		final Text text1 = new Text(group, SWT.BORDER);
		text1.setBounds(110, 30, 80, 25); 
		
		final Label label2 = new Label(group, SWT.NONE); 
		label2.setBounds(10, 70, 80, 25); 
		label2.setText("密  码：");
		final Text text2 = new Text(group, SWT.BORDER | SWT.PASSWORD); 
		text2.setBounds(110, 70, 80, 25);
		
		final Label label3 = new Label(group, SWT.NONE); 
		label3.setBounds(10, 100, 80, 25); 
		label3.setText("数据库IP：");
		final Text text3 = new Text(group, SWT.BORDER); 
		text3.setBounds(110, 100, 80, 25);
		
		
		final Label label4 = new Label(group, SWT.NONE); 
		label4.setBounds(10, 130, 80, 25); 
		label4.setText("端口号：");
		final Text text4 = new Text(group, SWT.BORDER); 
		text4.setBounds(110, 130, 80, 25);
		
		final Label label5 = new Label(group, SWT.NONE); 
		label5.setBounds(10, 160, 80, 25); 
		label5.setText("服务名：");
		final Text text5 = new Text(group, SWT.BORDER); 
		text5.setBounds(110, 160, 80, 25);
		
		final Label label6 = new Label(group, SWT.NONE); 
		label6.setBounds(10, 190, 80, 25); 
		label6.setText("表名称：");
		final Text text6 = new Text(group, SWT.BORDER); 
		text6.setBounds(110, 190, 80, 25);
		
		
		final Group group2 = new Group(composite, SWT.NONE);   
		group2.setBounds(60, 300, 300, 220);   
		group2.setText("字段设置模块：");    
		group2.setToolTipText("Group容器");
		
		final Label label7 = new Label(group2, SWT.NONE); 
		label7.setBounds(10, 40, 80, 20); 
		label7.setText("分组字段："); 
		final Text text7 = new Text(group2, SWT.BORDER);
		text7.setBounds(110, 40, 80, 25); 
		
		final Label label8 = new Label(group2, SWT.NONE); 
		label8.setBounds(10, 70, 80 , 20); 
		label8.setText("查询字段："); 
		final Text text8 = new Text(group2, SWT.BORDER);
		text8.setBounds(110, 70, 80, 25); 
		
		final Label label9 = new Label(group2, SWT.NONE); 
		label9.setBounds(10, 100, 80, 20); 
		label9.setText("查询值：");
		final Text text9 = new Text(group2, SWT.BORDER); 
		text9.setBounds(110, 100, 80, 25);
		
		final Label label10 = new Label(group2, SWT.NONE); 
		label10.setBounds(10, 130, 80, 20); 
		label10.setText("字段映射路径：");
		final Text text10 = new Text(group2, SWT.BORDER); 
		text10.setBounds(110, 130, 80, 25);
		
		final Button button1 = new Button(group2, SWT.NONE);
		button1.setBounds(80, 160, 50, 25); 
		button1.setText("确定");
		
//		final Button button2 = new Button(group2, SWT.NONE); 
//		button2.setBounds(120, 130, 50, 25);
//		button2.setText("取消");
		
         
	      //输入后单击确定后的操作
		button1.addSelectionListener(new SelectionAdapter(){
            public void widgetSelected(SelectionEvent e){
            	String name = text1.getText();
            	String passWord = text2.getText();
            	String Ip = text3.getText();
            	String Port = text4.getText();
            	String servirceName = text5.getText();
            	String tableName = text6.getText();


            	String splitColumn =text7.getText();//分组字段
            	String queryColumn =text8.getText();//查询字段
            	String queryValue =text9.getText();;//查询值
            	String filePath =text10.getText();;//文本
            	
            	filePath="/testjar/11.txt";
//                String url = "jdbc:oracle:" + "thin:@"+Ip+":"+Port+":"+servirceName;
                String url = "jdbc:oracle:thin:@20.5.193.39:1521:ncbsdb";

//       	     	String url = "jdbc:mysql://localhost:3306/shiro4" ;    
//                String url = "jdbc:mysql://"+Ip+":"+Port+"/"+servirceName;

            	Workbook wb =  new HSSFWorkbook();;
            	//查询数据库连接
            	//查询所有的分组值
            	String sql1 = "select distinct "+splitColumn+" from "+tableName+" where "+queryColumn+"=?";//SQL语句
//        		String sql1 = "select distinct name2 from data2 where name3='1'";

            	DBHelper db1 = new DBHelper(url,name,passWord,sql1);//创建DBHelper对象
                try {
                	db1.pst.setString(1,queryValue);
                	ResultSet ret1 = db1.pst.executeQuery();//执行语句，得到结果集-分组值
                    while (ret1.next()) {
                        List<String[]> list = new ArrayList<String[]>();
                    	String splitValue = ret1.getString(1);
                    	String sql2 = "select * from "+tableName+" where "+splitColumn+"=?";//SQL语句                    	
                    	DBHelper db2 = new DBHelper(url,name,passWord, sql2);//创建DBHelper对象
                    	db2.pst.setString(1,splitValue);
                    	ResultSet ret2 = db2.pst.executeQuery();//执行语句，得到结果集-分组值
                    	ResultSetMetaData rsmd = ret2.getMetaData();
                    	//获取当前表共有多少列  
                        int tableLength = rsmd.getColumnCount(); 
                    	//得到一个分组下所有的值
                        while (ret2.next()) {
                        	List<String> listTemp = new ArrayList<String>();
                        	for(int i =1;i<tableLength+1;i++){
                            String data = ret2.getString(i);
                            listTemp.add(data);                    
                        	}
                        	list.add((String[]) listTemp.toArray(new String[tableLength]));
                        }

                        IoReadWrite readWrite = new IoReadWrite();
                        String txt = readWrite.read(filePath);
            			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            			Matcher m = p.matcher(txt);
            			txt = m.replaceAll("");
                        String[] columnNames = txt.split("\\|");
                        //将list写入Excel  queryValue
//                        String[] columnNames = new String[tableLength];
//                        columnNames[0]="name1";
//                        columnNames[1]="name2";
//                        columnNames[2]="name3";
                        
                        wb = ExcelUtil2.createWorkBookByCellValue(wb,splitValue,list, columnNames);
                        ret2.close();  
                        db2.close();//关闭连接  
                    }
                    ret1.close();  
                    db1.close();//关闭连接
                    //生成
                    String excelPath = "/testjar/1.xls";
                    ExcelUtil2.ExcelFileExport(wb, excelPath);
                	MessageDialog.openInformation(shell, "成功", excelPath);

                } catch (SQLException e1) {  
                    e1.printStackTrace();
                	MessageDialog.openError(shell, "错误", e1.getMessage());

                } 
                
                
                
           }
        });
        
	}
	
	

}
