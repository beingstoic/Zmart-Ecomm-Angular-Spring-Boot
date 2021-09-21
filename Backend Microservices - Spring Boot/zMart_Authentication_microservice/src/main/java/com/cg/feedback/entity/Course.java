package com.cg.feedback.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "courses")
public class Course extends BaseEntity {

	@Column(name = "course_name")
	private String courseName;

	public Course() {
	}

	public Course(String courseName, Long id, Date createdDate) {
		this.courseName = courseName;
		this.setId(id);
		this.setCreatedDate(createdDate);
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

}
