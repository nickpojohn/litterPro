package controller;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
import Tools.ExcelUtil;
import Tools.ExcelUtil2;

/**
 * @author liting 作者 E-mail:pojohn@163.com
 * @version 创建时间：2016年11月16日 下午6:15:42
 * 类说明
 */
public class HelloWorld {

	protected Shell shell;

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
		group.setBounds(60, 60, 300, 200);   
		group.setText("数据库连接模块：");    
		group.setToolTipText("Group容器");
		
		final Label label1 = new Label(group, SWT.NONE); 
		label1.setBounds(10, 30, 90, 20); 
		label1.setText("用户名："); 
		final Text text1 = new Text(group, SWT.BORDER);
		text1.setBounds(110, 25, 80, 25); 
		
		final Label label2 = new Label(group, SWT.NONE); 
		label2.setBounds(10, 70, 90, 20); 
		label2.setText("密  码：");
		final Text text2 = new Text(group, SWT.BORDER | SWT.PASSWORD); 
		text2.setBounds(110, 65, 80, 25);
		
		final Label label3 = new Label(group, SWT.NONE); 
		label3.setBounds(10, 100, 90, 20); 
		label3.setText("数据库地址：");
		final Text text3 = new Text(group, SWT.BORDER); 
		text3.setBounds(110, 105, 80, 25);
		
		final Label label4 = new Label(group, SWT.NONE); 
		label4.setBounds(10, 130, 90, 20); 
		label4.setText("表名称：");
		final Text text4 = new Text(group, SWT.BORDER); 
		text4.setBounds(110, 145, 80, 25);
		
		
		final Group group2 = new Group(composite, SWT.NONE);   
		group2.setBounds(60, 280, 300, 200);   
		group2.setText("字段设置模块：");    
		group2.setToolTipText("Group容器");
		
//		final Label label1 = new Label(group, SWT.NONE); 
//		label1.setBounds(10, 30, 90, 20); 
//		label1.setText("用户名："); 
//		final Text text1 = new Text(group, SWT.BORDER);
//		text1.setBounds(110, 25, 80, 25); 
//		
//		final Label label2 = new Label(group, SWT.NONE); 
//		label2.setBounds(10, 70, 90, 20); 
//		label2.setText("密  码：");
//		final Text text2 = new Text(group, SWT.BORDER | SWT.PASSWORD); 
//		text2.setBounds(110, 65, 80, 25);
//		
//		final Label label3 = new Label(group, SWT.NONE); 
//		label3.setBounds(10, 100, 90, 20); 
//		label3.setText("数据库地址：");
//		final Text text3 = new Text(group, SWT.BORDER); 
//		text3.setBounds(110, 105, 80, 25);
		
		
		
		final Button button1 = new Button(group, SWT.NONE);
		button1.setBounds(130, 170, 50, 25); 
		button1.setText("确定");
		
		final Button button2 = new Button(group, SWT.NONE); 
		button2.setBounds(170, 170, 50, 25);
		button2.setText("取消");
		
         
	      //输入后单击确定后的操作
		button1.addSelectionListener(new SelectionAdapter(){
            public void widgetSelected(SelectionEvent e){
            	String name = text1.getText();
            	String passWord = text2.getText();
            	String dbUrl = text3.getText();
            	String tableName = text4.getText();
            	String splitColumn ="";//分组字段
            	String queryColumn ="";//查询字段
            	String queryValue ="";//查询值
            	
            	Workbook wb =  new HSSFWorkbook();;
            	//查询数据库连接
            	//查询所有的分组值
            	String sql1 = "select distinct "+splitColumn+"from "+tableName+"where "+queryColumn+"="+queryValue;//SQL语句
            	DBHelper db1 = new DBHelper(sql1, sql1, sql1, sql1, sql1, sql1, sql1);//创建DBHelper对象
   
                try {  	
                	ResultSet ret1 = db1.pst.executeQuery();//执行语句，得到结果集-分组值
                    while (ret1.next()) {
                        List<String[]> list = new ArrayList<String[]>();
                    	String splitValue = ret1.getString(1);
                    	String sql2 = "select * from "+tableName+"where "+splitColumn+"="+splitValue;//SQL语句                    	
                    	DBHelper db2 = new DBHelper(sql1, sql1, sql1, sql1, sql1, sql1, sql1);//创建DBHelper对象
                    	ResultSet ret2 = db2.pst.executeQuery();//执行语句，得到结果集-分组值
                    	//得到一个分组下所有的值
                    	List<String> listTemp = new ArrayList<String>();
                        while (ret2.next()) {
                        	for(int i =1;i<6;i++){
                            String data = ret2.getString(i);
                            System.out.println("data:"+data);
                            listTemp.add(data);                    
                        	}
                        	list.add((String[]) listTemp.toArray());
                        }
                        
                        //将list写入Excel  queryValue
                        String[] columnNames = new String[6];
                   
                        wb = ExcelUtil2.createWorkBookByCellValue(splitValue,list, columnNames);
                        ret2.close();  
                        db2.close();//关闭连接  
                    }
                    ret1.close();  
                    db1.close();//关闭连接
                    //生成
                    String filepath = "";
                    ExcelUtil2.ExcelFileExport(wb, filepath);

                } catch (SQLException e1) {  
                    e1.printStackTrace();  
                } 
                
                
            	MessageDialog.openError(shell, "错误", name);
                
           }
        });
        
	}
	
	

}
