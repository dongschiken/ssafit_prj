package com.cdu.ssafit.save.model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cdu.ssafit.save.domain.dto.Save;
import com.cdu.ssafit.util.DBUtil;

public class SaveRepositoryImpl implements SaveRepository {
	// DB에 접근
	private DBUtil util = DBUtil.getInstance();
	
	// 싱글톤
	private static SaveRepository instance = new SaveRepositoryImpl();
	private SaveRepositoryImpl() {};
	public static SaveRepository getInstance() {
		return instance;
	}
	
	@Override
    public void insertSave(Save save) {
        String sql = "INSERT INTO saves (member_id, board_id) VALUES (?, ?)";
        try (Connection conn = util.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, save.getMemberId());
            pstmt.setInt(2, save.getBoardId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSave(String memberId, int boardId) {
        String sql = "DELETE FROM saves WHERE member_id = ? AND board_id = ?";
        try (Connection conn = util.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, memberId);
            pstmt.setInt(2, boardId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Save> selectSaves(String memberId) {
        List<Save> saves = new ArrayList<>();
        String sql = "SELECT * FROM saves WHERE member_id = ?";
        try (Connection conn = util.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, memberId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Save save = new Save(rs.getInt("id"), memberId, rs.getInt("board_id"));
                saves.add(save);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return saves;
    }

    @Override
    public boolean isSaved(String memberId, int boardId) {
        String sql = "SELECT COUNT(*) FROM saves WHERE member_id = ? AND board_id = ?";
        try (Connection conn = util.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, memberId);
            pstmt.setInt(2, boardId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
            	// 결과가 존재하면 true를 반환
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
	
	
}
