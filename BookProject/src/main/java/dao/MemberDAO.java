package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import common.jdbcUtil;

public class MemberDAO {
	public ArrayList<String> getMemberList(){
		ArrayList<String> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select userid from member";
		
		conn = jdbcUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(rs.getString("userid"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			jdbcUtil.close(conn, pstmt, rs);
		}
		return list;
	}
	public int insertMember(String userId, String userPwd) {
		int n = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into member values(?,?)";
		
		conn = jdbcUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			n = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			jdbcUtil.close(conn, pstmt);
		}
		return n;
	}
	
	public int updateMember(String userId, String userPwd) {
		int n = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into member values(?,?)";
		
		conn = jdbcUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			n = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			jdbcUtil.close(conn, pstmt);
		}
		return n;
	}
	public int deleteMember(String userId)
	{
		int n = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete from member where userid=?";
		
		conn = jdbcUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			n = pstmt.executeUpdate();
		} catch(SQLException e)
		{
			e.printStackTrace();
		}finally {
			jdbcUtil.close(conn, pstmt);
		}
		return n;
	}
	public boolean getMemberPwd(String id, String pwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select userpwd from member where userid=?";
		boolean result = false;
		
		conn = jdbcUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(pwd.equals(rs.getString("userpwd")))
					result = true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
			}finally {
				jdbcUtil.close(conn, pstmt, rs);
		}
		return result;
	}
}