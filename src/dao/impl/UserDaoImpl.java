package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.UserDao;
import util.DBUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public int login(String username, String password) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select count(*) from tbl_user where username=? and password=?";
		int count=0;
		
		try {
			conn=DBUtil.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			rs=ps.executeQuery();
			if (rs.next()) {
				 count=rs.getInt(1);
				
				
				
				
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}finally {
			try {
				DBUtil.DBClose(null, ps, rs);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return count;
	}

}
