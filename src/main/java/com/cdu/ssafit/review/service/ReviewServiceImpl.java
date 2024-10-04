package com.cdu.ssafit.review.service;

import java.sql.SQLException;
import java.util.Map;

import com.cdu.ssafit.review.domain.dto.Review;
import com.cdu.ssafit.review.model.repository.ReviewRepository;
import com.cdu.ssafit.review.model.repository.ReviewRepositoryImpl;

public class ReviewServiceImpl implements ReviewService {
	
	private ReviewRepository reviewRepository;
	
	private static ReviewService reviewService = new ReviewServiceImpl();
	public ReviewServiceImpl() {
		reviewRepository = ReviewRepositoryImpl.getInstance();
	}
	public static ReviewService getInstance() {
		return reviewService;
	}
	
	@Override
	public void write(Review review) throws SQLException {
		reviewRepository.insertReview(review);
	}
	
	@Override
	public Map<Integer, Review> list(int id) throws SQLException {
		return reviewRepository.selectAll(id);
	}

	@Override
	public void update(Review review) throws SQLException {
		reviewRepository.updateReview(review);
	}

	@Override
	public void delete(int id) throws SQLException {
		reviewRepository.deleteReview(id);
	}
	
}
