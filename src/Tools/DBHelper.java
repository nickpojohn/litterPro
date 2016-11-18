package Tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author liting 作者 E-mail:pojohn@163.com
 * @version 创建时间：2016年11月16日 下午8:54:55
 * 类说明
 */
public class DBHelper {
	
    public  String url = "";  
    public static final String driverName = "oracle.jdbc.driver.OracleDriver";
//    public static final String driverName = "com.mysql.jdbc.Driver"; 
    public  String user = "";  
    public  String password = "";  
    
    
	public Connection conn = null;  
    public PreparedStatement pst = null;  
  
    public DBHelper(String url,String user,String password,String sql) {  
        try {  
            Class.forName(driverName);// 加载Oracle驱动程序
            conn = DriverManager.getConnection(url, user, password);//获取连接  
            pst = conn.prepareStatement(sql);//准备执行语句  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    public void close() {  
        try {  
            this.conn.close();  
            this.pst.close();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
}  

