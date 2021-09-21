package com.cg.feedback.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.feedback.entity.Training;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {

	Boolean existsByFacultyIdAndCourseIdAndStartDate(Long facultyId, Long CourseId, Date startDate);

	Boolean existsByCourseIdAndStartDate(Long CourseId, Date startDate);

	Boolean existsByFacultyIdAndEndDate(Long facultyId, Date EndDate);

}
