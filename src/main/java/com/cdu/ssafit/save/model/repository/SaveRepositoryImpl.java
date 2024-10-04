package com.cdu.ssafit.save.model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cdu.ssafit.board.domain.dto.Board;
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
    public void insertSave(Save save) throws SQLException {
        String sql = "INSERT INTO save (member_seq, board_id) VALUES (?, ?)";
        try (Connection conn = util.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, save.getMemberSeq());
            pstmt.setInt(2, save.getBoardId());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSave(int memberSeq, int boardId) throws SQLException {
        String sql = "DELETE FROM save WHERE member_seq = ? AND board_id = ?";
        try (Connection conn = util.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, memberSeq);
            pstmt.setInt(2, boardId);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isSaved(int memberSeq, int boardId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM save WHERE member_seq = ? AND board_id = ?";
        try (Connection conn = util.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, memberSeq);
            pstmt.setInt(2, boardId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
            	// 결과가 존재하면 true를 반환
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public List<Save> selectSaves(int memberSeq) throws SQLException {
    	List<Save> saves = new ArrayList<>();
    	String sql = "SELECT * FROM save WHERE member_seq = ?";
    	try (Connection conn = util.getConnection();
    			PreparedStatement pstmt = conn.prepareStatement(sql)) {
    		pstmt.setInt(1, memberSeq);
    		ResultSet rs = pstmt.executeQuery();
    		while (rs.next()) {
    			Save save = new Save(rs.getInt("id"), rs.getInt("board_id"));
    			saves.add(save);
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return saves;
    }
    
	@Override
	public Board selectBoards(int boardId) {
		Board board = null;
		String sql = "SELECT * FROM board WHERE id = ?";
		try(Connection conn = util.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, boardId);
			ResultSet rs = pstmt.executeQuery();
			
			// member_seq, title, content, view_cnt, video_url, workout_name
			while (!rs.next()) {
				board = new Board();
				String title = rs.getString("title");//
				String content = rs.getString("content");//
				String workOutName = rs.getString("workout_name");
				int viewCnt = rs.getInt("view_cnt");
				String videoUrl = rs.getString("video_url");
				board.setTitle(title);
				board.setContent(content);
				board.setWorkOutName(workOutName);
				board.setViewCnt(viewCnt);
				board.setVideoUrl(videoUrl);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return board;
	}
	
    
    
	
}
