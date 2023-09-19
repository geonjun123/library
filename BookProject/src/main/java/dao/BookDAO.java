package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.jdbcUtil;
import vo.BookVO;

public class BookDAO {
	public BookDAO() {
		
	}
	
	public ArrayList<BookVO> getBookList(){
		ArrayList<BookVO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from bookshop order by isbn";
		
		try {
			conn = jdbcUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BookVO vo = new BookVO();
				vo.setIsbn(rs.getString("isbn"));
				vo.setTitle(rs.getString("title"));
				vo.setAuthor(rs.getString("author"));
				vo.setCompany(rs.getString("company"));
				vo.setPrice(rs.getInt("price"));
				
				list.add(vo);
				
			}
		} catch (SQLException e) {e.printStackTrace();}
		finally {jdbcUtil.close(conn, pstmt, rs);} return list;
	}
	
}
