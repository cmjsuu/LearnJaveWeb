package stu.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import stu.db.DBConnect;

import stu.dao.ManagerDAO;
import stu.vo.SystemManagerInfo;

public class ImplManagerDAO implements ManagerDAO{
	
	public SystemManagerInfo GetManagerInfoByName(String name)
	{
		String sql = "SELECT MANAGEID,MANAGENAME,MANAGEPASSW FROM SystemManagerInfo WHERE MANAGENAME =?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SystemManagerInfo managerInfo = null;
		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				managerInfo = new SystemManagerInfo();				
				managerInfo.setManageName(rs.getString("MANAGENAME"));
				managerInfo.setManageID(rs.getInt("manageID"));
				managerInfo.setManagePassw(rs.getString("managePassw"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return managerInfo;
	}
}
