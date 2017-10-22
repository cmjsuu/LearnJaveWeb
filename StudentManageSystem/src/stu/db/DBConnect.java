package stu.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnect {

	// 分页查询语句
	public static final String PAGE_SELECT_SQL = "select top #pageSize o.* from (select row_number()over (order by #Column)as rownumber,*from(#SELECTSQL)as oo )as o where rownumber >=?";
	////其他简洁分页查询：select a.* from (select t.*  from (SELECT row_number() over(order by userid )rownum,userid,uname,upassword ,unickname ,usex ,ubirthday, uaddress ,utelephone ,uemail ,convert(varchar,utime,110) regtime,ustatus from es_user) t where rownum <= 8) a where a.rowNum >= 1	
	/////select top #pageSize o.* from (select row_number()over (order by #Column)as rownumber,*from(#SELECTSQL)as oo )as o where rownumber >=?
	
	static{
		try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433; DatabaseName=StudentManage","sa","123456");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void closeConnection(Connection conn,Statement stmt,ResultSet rs){
		if (conn != null) {
			if (rs != null) {
				try{
					rs.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
