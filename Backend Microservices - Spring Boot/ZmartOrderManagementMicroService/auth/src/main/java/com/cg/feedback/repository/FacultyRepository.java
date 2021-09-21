package com.cg.feedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.feedback.entity.Faculty;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

}
