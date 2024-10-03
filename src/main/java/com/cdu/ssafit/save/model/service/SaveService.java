package com.cdu.ssafit.save.model.service;

import java.util.List;

import com.cdu.ssafit.save.domain.dto.Save;

public interface SaveService {
	
	boolean isSaved(String memberId, int boardId);
	
    void insertSave(Save save);
    
    void deleteSave(String memberId, int boardId);
    
    List<Save> selectSaves(String memberId);
}
