package com.cg.feedback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.feedback.exception.BadRequestException;
import com.cg.feedback.exception.NotFoundException;
import com.cg.feedback.model.CourseModel;
import com.cg.feedback.model.FacultyModel;
import com.cg.feedback.model.TrainingModel;
import com.cg.feedback.model.request.AddTrainingRequest;
import com.cg.feedback.model.request.UpdateTrainingRequest;
import com.cg.feedback.model.response.SuccessResponse;
import com.cg.feedback.service.TrainingService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/training")
public class TrainingController {

	@Autowired
	TrainingService trainingService;

	/*
	 * End Point to add a new training.
	 */
	@PostMapping
	public SuccessResponse addTraining(@RequestBody AddTrainingRequest addTrainingRequest)
			throws BadRequestException, NotFoundException {
		trainingService.addTraining(addTrainingRequest);
		return new SuccessResponse(true,"Successfully added new training");
	}
	
	/*
	 * End Point to get a training.
	 */
	@GetMapping("/{trainingId}")
	public TrainingModel getTraining(@PathVariable Long trainingId) throws NotFoundException {
		return trainingService.getTraining(trainingId);
	} 
	

	/*
	 * End Point to get all the trainings.
	 */
	@GetMapping
	public List<TrainingModel> getTrainings() {
		return trainingService.getTrainings();
	}
	
	/*
	 * End Point to get all the faculties.
	 */
	@GetMapping("/faculties")
	public List<FacultyModel> getFaculties() {
		return trainingService.getFaculties();
	}

	
	/*
	 * End Point to get all the courses.
	 */
	@GetMapping("/courses")
	public List<CourseModel> getCourses() {
		return trainingService.getCourses();
	}


	/*
	 * End Point to update a training.
	 */
	@PutMapping("/{trainingId}")
	public SuccessResponse updateTraining(@PathVariable Long trainingId,
			@RequestBody UpdateTrainingRequest updateTrainingRequest) throws BadRequestException, NotFoundException {
		
		trainingService.updateTraining(trainingId, updateTrainingRequest);
		return new SuccessResponse(true);
	}

	/*
	 * End Point to delete a training.
	 */
	@DeleteMapping("/{trainingId}")
	public SuccessResponse deleteTraining(@PathVariable Long trainingId) throws NotFoundException, BadRequestException {
		trainingService.deleteTraining(trainingId);
		return new SuccessResponse(true);
	}

}
