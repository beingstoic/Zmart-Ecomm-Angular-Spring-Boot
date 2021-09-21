package com.cg.feedback.service;

import java.util.List;

import com.cg.feedback.exception.BadRequestException;
import com.cg.feedback.exception.NotFoundException;
import com.cg.feedback.model.CourseModel;
import com.cg.feedback.model.FacultyModel;
import com.cg.feedback.model.TrainingModel;
import com.cg.feedback.model.request.AddTrainingRequest;
import com.cg.feedback.model.request.UpdateTrainingRequest;

public interface TrainingService {

	public void addTraining(AddTrainingRequest addTrainingRequest) throws NotFoundException, BadRequestException;

	TrainingModel getTraining(Long trainingId) throws NotFoundException;
	
	List<TrainingModel> getTrainings();
	
	List<FacultyModel> getFaculties();
	
	List<CourseModel> getCourses();

	public void updateTraining(Long trainingId, UpdateTrainingRequest updateTrainingRequest)
			throws NotFoundException, BadRequestException;

	public void deleteTraining(Long trainingId) throws NotFoundException;

}
