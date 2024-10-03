package com.cdu.ssafit.save.model.service;

import java.util.List;

import com.cdu.ssafit.member.model.repository.MemberRepositoryImpl;
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
    public boolean isSaved(String memberId, int boardId) {
        return saveRepository.isSaved(memberId, boardId);
    }

    @Override
    public void insertSave(Save save) {
        saveRepository.insertSave(save);
    }

    @Override
    public void deleteSave(String memberId, int boardId) {
        saveRepository.deleteSave(memberId, boardId);
    }

    @Override
    public List<Save> selectSaves(String memberId) {
        return saveRepository.selectSaves(memberId);
    }
}
