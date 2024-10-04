package com.cdu.ssafit.review.service;

import java.sql.SQLException;
import java.util.Map;

import com.cdu.ssafit.review.domain.dto.Review;

public interface ReviewService {
	
	void write(Review review) throws SQLException;
	
	Map<Integer, Review>list(int id) throws SQLException;
	
	void update(Review review) throws SQLException;
	
	void delete(int id) throws SQLException;
}
