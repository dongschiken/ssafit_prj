package com.cdu.ssafit.save.model.repository;

import java.sql.SQLException;
import java.util.List;

import com.cdu.ssafit.board.domain.dto.Board;
import com.cdu.ssafit.save.domain.dto.Save;

public interface SaveRepository {
    /*
     * 1. 찜을 추가하는 insertSave메소드
     * 2. 찜을 삭제하는 deleteSave메소드
     * 3. 찜 목록을 반환하는 selectSaves 메소드
     * 
     */
	boolean isSaved(int memberSeq, int boardId) throws SQLException;
	
	void insertSave(Save save) throws SQLException;
	
    void deleteSave(int memberSeq, int boardId) throws SQLException;
    
    List<Save> selectSaves(int memberSeq) throws SQLException;
    
    Board selectBoards(int boardId);
    
}

