package com.cg.feedback.model.request;

import java.util.Date;

public class AddTrainingRequest {
	
	private String trainingName;

	private Date startDate;

	private Date endDate;

	private Long facultyId;

	private Long courseId;

	public AddTrainingRequest() {
	}

	public AddTrainingRequest(String trainingName, Date startDate, Date endDate, Long facultyId, Long courseId) {
		this.trainingName = trainingName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.facultyId = facultyId;
		this.courseId = courseId;
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

	public Long getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(Long facultyId) {
		this.facultyId = facultyId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

}
