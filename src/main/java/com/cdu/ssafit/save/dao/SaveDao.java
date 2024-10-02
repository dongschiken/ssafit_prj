package com.cdu.ssafit.save.dao;

import java.util.List;

import com.cdu.ssafit.save.domain.dto.Save;

public interface SaveDao {
	
	public List<Save> selectAll();
	
	public void insertSave(Save save);
	
	public void deleteSave(int id);
	
	
}
