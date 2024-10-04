package com.cdu.ssafit.review.model.repository;

import java.sql.SQLException;
import java.util.Map;

import com.cdu.ssafit.review.domain.dto.Review;


public interface ReviewRepository {

	public void insertReview(Review review) throws SQLException;
	
	Map<Integer, Review> selectAll(int id) throws SQLException;

	public void updateReview(Review review) throws SQLException;
	
	public void deleteReview(int id) throws SQLException;

}
