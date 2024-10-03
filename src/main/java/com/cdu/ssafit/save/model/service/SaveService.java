package com.cdu.ssafit.save.model.service;

import java.sql.SQLException;
import java.util.List;

import com.cdu.ssafit.save.domain.dto.Save;

public interface SaveService {
	
	boolean isSaved(int memberSeq, int boardId) throws SQLException;
	
    void insertSave(Save save) throws SQLException;
    
    void deleteSave(int memberSeq, int boardId) throws SQLException;
    
    List<Save> selectSaves(int memberSeq) throws SQLException;
}
