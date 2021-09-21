package com.cg.feedback.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "faculties")
public class Faculty extends BaseEntity {

	@Column(name = "faculty_name")
	private String facultyName;

	@Column(name = "faculty_skill")
	private String facultySkill;

	public Faculty() {
	}

	public Faculty(String facultyName, String facultySkill, Long id, Date createdDate) {
		this.facultyName = facultyName;
		this.facultySkill = facultySkill;
		this.setId(id);
		this.setCreatedDate(createdDate);
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	public String getFacultySkill() {
		return facultySkill;
	}

	public void setFacultySkill(String facultySkill) {
		this.facultySkill = facultySkill;
	}

}
