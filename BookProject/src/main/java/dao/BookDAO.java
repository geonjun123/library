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
	public int insertBookData(BookVO data) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into bookshop values(?,?,?,?,?)";
		
		try {
			conn = jdbcUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, data.getIsbn());
			pstmt.setString(2, data.getTitle());
			pstmt.setString(3, data.getAuthor());
			pstmt.setString(4, data.getCompany());
			pstmt.setInt(5, data.getPrice());
			
			result = pstmt.executeUpdate();

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			jdbcUtil.close(conn, pstmt);
		}
		return result;
	}
	public BookVO getBookData(String isbn) {
		BookVO vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from bookshop where isbn=?";
		
		try {
			conn = jdbcUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, isbn);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new BookVO();
				vo.setIsbn(rs.getString("isbn"));
				vo.setTitle(rs.getString("title"));
				vo.setAuthor(rs.getString("author"));
				vo.setCompany(rs.getString("company"));
				vo.setPrice(rs.getInt("price"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			jdbcUtil.close(conn, pstmt, rs);
		}
		return vo;
	}
	public int updateBookData(BookVO vo) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update bookshop set tutle=?, author=?, company=?, price=? where isbn?";
		
		try {
			conn = jdbcUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getAuthor());
			pstmt.setString(3, vo.getCompany());
			pstmt.setInt(4, vo.getPrice());
			pstmt.setString(5, vo.getIsbn());
			
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			jdbcUtil.close(conn, pstmt);
		}
		return result;
	}
}
