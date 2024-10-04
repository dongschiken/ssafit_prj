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
	
	/**
	 * 사용자가 운동을 찜했을 때 최종적으로 호출되는 메소드입니다.
	 * save 테이블의 member_seq와 board_id에 회원번호와 게시물 번호가 각각 추가됩니다.
	 */
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

	/**
	 * 사용자가 찜을 취소했을 때 최종적으로 호출되는 메소드입니다.
	 * save 테이블의 member_seq와 board_id에 회원번호와 게시물 번호를 각각 대조하여 데이터를 삭제합니다.
	 */
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

    /**
     * 사용자가 운동을 찜했는지의 여부를 판단할 때 호출되는 메소드입니다.
     * 찜 버튼의 상태를 변경하기 전에 찜의 여부를 판단하기 위해 먼저 호출됩니다.
     * save 테이블의 member_seq와 board_id에 회원번호와 게시물 번호를 각각 대조하여 데이터가 존재하는지 확인합니다.
     * 데이터가 존재하면 true, 존재하지 않으면 false를 반환합니다.
     */
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
    
    /**
     * 사용자가 자신의 찜 목록을 확인하고자 할 때, 일차적으로 호출되는 메소드입니다.
     * 세션 영역의 회원 정보 중 회원 번호(memberSeq)를 파라미터로 하여, save 테이블을 조회합니다.
     * 그 결과로 찜 번호와 게시물 번호를 얻어 SaveServiceImpl로 반환됩니다.
     */
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
    		System.out.println("saves : "+saves);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return saves;
    }
    
    /**
     * 사용자가 자신의 찜 목록을 확인하고자 할 때, 최종적으로 호출되는 메소드입니다.
     * selectSaves() 에서 얻어온 Save 객체가 가진 게시글 번호를 파라미터로 하여 board 테이블을 조회합니다.
     * 그 결과로 전반적인 정보(운동카드목록에 필요한)를 가진 Board 객체(운동)를 얻어 SaveServiceImpl로 반환됩니다.
     */
	@Override
	public Board selectBoards(int boardId) {
		Board board = null;
		String sql = "SELECT * FROM board WHERE id = ?";
		try(Connection conn = util.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, boardId);
			ResultSet rs = pstmt.executeQuery();
			// member_seq, title, content, view_cnt, video_url, workout_name
			while (rs.next()) {
				board = new Board();
				int id = rs.getInt("id");
				String title = rs.getString("title");//
				String content = rs.getString("content");//
				String workOutName = rs.getString("workout_name");
				int viewCnt = rs.getInt("view_cnt");
				String videoUrl = rs.getString("video_url");
				board.setId(id);
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
