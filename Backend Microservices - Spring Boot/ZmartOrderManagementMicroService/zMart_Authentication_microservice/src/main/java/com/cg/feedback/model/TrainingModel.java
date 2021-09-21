package com.cg.feedback.model;

import java.util.Date;

public class TrainingModel {

	private Long id;
	
	private String trainingName;

	private Date startDate;

	private Date endDate;

	private FacultyModel faculty;

	private CourseModel course;

	public TrainingModel() {
	}

	public TrainingModel(Long id, String trainingName, Date startDate, Date endDate, FacultyModel faculty, CourseModel course) {
		this.id = id;
		this.trainingName = trainingName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.faculty = faculty;
		this.course = course;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTrainingName() {
		return trainingName;
	}

	public void setTrainingName(String trainingName) {
		this.trainingName = trainingName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public FacultyModel getFaculty() {
		return faculty;
	}

	public void setFaculty(FacultyModel faculty) {
		this.faculty = faculty;
	}

	public CourseModel getCourse() {
		return course;
	}

	public void setCourse(CourseModel course) {
		this.course = course;
	}

}
