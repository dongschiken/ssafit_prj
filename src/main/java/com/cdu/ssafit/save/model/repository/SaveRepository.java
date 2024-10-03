package com.cdu.ssafit.save.model.repository;

import java.util.List;

import com.cdu.ssafit.save.domain.dto.Save;

public interface SaveRepository {
    /*
     * 1. 찜을 추가하는 insertSave메소드
     * 2. 찜을 삭제하는 deleteSave메소드
     * 3. 찜 목록을 반환하는 selectSaves 메소드
     * 
     */
	boolean isSaved(String memberId, int boardId);
	
	void insertSave(Save save);
	
    void deleteSave(String memberId, int boardId);
    
    List<Save> selectSaves(String memberId);
    
}

