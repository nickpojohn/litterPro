package Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import Tools.DBHelper;
import Tools.ExcelUtil2;

/**
 * @author liting 作者 E-mail:pojohn@163.com
 * @version 创建时间：2016年11月17日 下午6:50:47
 * 类说明
 */
public class TestJdbc {

	@Test
	public void testdb(){
    	Workbook wb =  new HSSFWorkbook();;

	     String url = "jdbc:mysql://localhost:3306/shiro4" ;    
	     String username = "root" ;   
	     String password = "root" ; 
	     
		String sql1 = "select distinct name2 from data2 where name3='1'";
//		String sql1 = "insert into data(name1,name2,name3) values(1,1,1)";

		DBHelper db1 = new DBHelper(url,username,password, sql1);//创建DBHelper对象
        try {  	
        	ResultSet ret1 = db1.pst.executeQuery();//执行语句，得到结果集-分组值
            while (ret1.next()) {
                List<String[]> list = new ArrayList<String[]>();
            	String splitValue = ret1.getString(1);
            	String sql2 = "select * from data2 where name2="+splitValue;//SQL语句                    	
            	DBHelper db2 = new DBHelper(url,username,password, sql2);//创建DBHelper对象
            	ResultSet ret2 = db2.pst.executeQuery();//执行语句，得到结果集-分组值
            	//得到一个分组下所有的值
            	
                while (ret2.next()) {
                	List<String> listTemp = new ArrayList<String>();
                	for(int i =1;i<4;i++){
                    String data = ret2.getString(i);
                    System.out.println("data:"+data);
                    listTemp.add(data);                    
                	}
                	list.add(listTemp.toArray(new String[3]));
                }
                
                //将list写入Excel  queryValue
                String[] columnNames = new String[3];
                columnNames[0]="name1";
                columnNames[1]="name2";
                columnNames[2]="name3";
                
                wb = ExcelUtil2.createWorkBookByCellValue(wb,splitValue,list,columnNames);
                ret2.close();  
                db2.close();//关闭连接  
            }
            ret1.close();  
            db1.close();//关闭连接
            //生成
            String filepath = "E:/myout.xls";
            ExcelUtil2.ExcelFileExport(wb, filepath);

        } catch (SQLException e1) {  
            e1.printStackTrace();  
        } 


	}

	
}
