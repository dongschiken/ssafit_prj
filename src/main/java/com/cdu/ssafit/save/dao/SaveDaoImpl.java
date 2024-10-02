package com.cdu.ssafit.save.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.cdu.ssafit.save.domain.dto.Save;
import com.cdu.ssafit.util.DBUtil;

public class SaveDaoImpl implements SaveDao {
	private DBUtil util = DBUtil.getInstance();
	private static SaveDao dao = new SaveDaoImpl();
	private SaveDaoImpl() {};
	public static SaveDao getInstance() {
		return dao;
	}
	
	@Override
	public List<Save> selectAll() {
		
		return null;
	}

	@Override
	public void insertSave(Save save) {
		try {
			Connection conn = util.getConnection();
			String sql = "INSERT INTO save (id, member_id, board_id) VALUES (?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public void deleteSave(int id) {
		// TODO Auto-generated method stub
		
	}
	
}
