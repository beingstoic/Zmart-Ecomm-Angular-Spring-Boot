package com.cg.feedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.feedback.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
	
}
