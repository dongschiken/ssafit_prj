package com.cdu.ssafit.save.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cdu.ssafit.board.domain.dto.Board;
import com.cdu.ssafit.save.domain.dto.Save;
import com.cdu.ssafit.save.model.repository.SaveRepository;
import com.cdu.ssafit.save.model.repository.SaveRepositoryImpl;

public class SaveServiceImpl implements SaveService {
	// SaveRepository 객체 가져오기
	private SaveRepository saveRepository;
	// 싱글톤
	private static SaveService instance = new SaveServiceImpl();
	public SaveServiceImpl() {
		saveRepository = SaveRepositoryImpl.getInstance();
	}
    public static SaveService getInstance() {
        return instance;
    }
    
    @Override
    public boolean isSaved(int seq, int boardId) throws SQLException {
        return saveRepository.isSaved(seq, boardId);
    }

    @Override
    public void insertSave(Save save) throws SQLException {
        saveRepository.insertSave(save);
    }

    @Override
    public void deleteSave(int seq, int boardId) throws SQLException {
        saveRepository.deleteSave(seq, boardId);
    }

    @Override
    public List<Save> selectSaves(int seq) throws SQLException {
    	List<Save> saves = saveRepository.selectSaves(seq);

    	List<Board> boards = new ArrayList<>();
    	for (int i = 0; i < saves.size(); i++) {
    		Board board = saveRepository.selectBoards(saves.get(i).getBoardId());
    		saves.get(i).setBoard(board);
    	}
        return saves;
    }

}
