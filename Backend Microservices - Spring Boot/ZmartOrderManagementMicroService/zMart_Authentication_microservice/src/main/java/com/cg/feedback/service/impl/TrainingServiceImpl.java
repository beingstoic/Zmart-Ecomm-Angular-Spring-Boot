package com.cg.feedback.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.feedback.entity.Course;
import com.cg.feedback.entity.Faculty;
import com.cg.feedback.entity.Training;
import com.cg.feedback.exception.BadRequestException;
import com.cg.feedback.exception.NotFoundException;
import com.cg.feedback.model.CourseModel;
import com.cg.feedback.model.FacultyModel;
import com.cg.feedback.model.TrainingModel;
import com.cg.feedback.model.request.AddTrainingRequest;
import com.cg.feedback.model.request.UpdateTrainingRequest;
import com.cg.feedback.repository.CourseRepository;
import com.cg.feedback.repository.FacultyRepository;
import com.cg.feedback.repository.TrainingRepository;
import com.cg.feedback.service.TrainingService;

@Service
public class TrainingServiceImpl implements TrainingService {

	@Autowired
	private TrainingRepository trainingRepository;

	@Autowired
	private FacultyRepository facultyRepository;

	@Autowired
	private CourseRepository courseRepository;

	/*
	 * Add a training
	 */
	@Override
	public void addTraining(AddTrainingRequest addTrainingRequest) throws NotFoundException, BadRequestException {

		// validate fields
		if (StringUtils.isBlank(addTrainingRequest.getTrainingName())) {
			throw new BadRequestException("Training Name cannot be null.");
		} else if (addTrainingRequest.getFacultyId() == null) {
			throw new BadRequestException("FacultyId cannot be null.");
		} else if (addTrainingRequest.getCourseId() == null) {
			throw new BadRequestException("CourseId cannot be null.");
		} else if (addTrainingRequest.getStartDate() == null) {
			throw new BadRequestException("Start Date cannot be null.");
		} else if (addTrainingRequest.getEndDate() == null) {
			throw new BadRequestException("End Date cannot be null.");
		} else if (addTrainingRequest.getEndDate().before(addTrainingRequest.getStartDate())) {
			throw new BadRequestException("Start date cannot be greater than End date");
		} else if (addTrainingRequest.getStartDate().before(new Date())) {
			throw new BadRequestException("Start date cannot be before Current Date");
		}

		// Validate Faculty
		Optional<Faculty> faculty = facultyRepository.findById(addTrainingRequest.getFacultyId());
		if (!faculty.isPresent()) {
			throw new NotFoundException("Faculty not found");
		}
		if (trainingRepository.existsByFacultyIdAndEndDate(addTrainingRequest.getFacultyId(),
				addTrainingRequest.getEndDate())) {
			throw new BadRequestException("Faculty is already assigned.");
		}

		// Validate Course
		Optional<Course> course = courseRepository.findById(addTrainingRequest.getCourseId());
		if (!course.isPresent()) {
			throw new NotFoundException("Course not found");
		}
		if (trainingRepository.existsByCourseIdAndStartDate(addTrainingRequest.getCourseId(),
				addTrainingRequest.getStartDate())) {
			throw new BadRequestException("Same course already exists on this date.");
		}

		// Validate Training
		if (trainingRepository.existsByFacultyIdAndCourseIdAndStartDate(addTrainingRequest.getFacultyId(),
				addTrainingRequest.getCourseId(), addTrainingRequest.getStartDate())) {
			throw new BadRequestException("Training already exists.");
		}

		// Add Training
		Training training = new Training();
		training.setTrainingName(addTrainingRequest.getTrainingName());
		training.setFaculty(faculty.get());
		training.setCourse(course.get());
		training.setStartDate(addTrainingRequest.getStartDate());
		training.setEndDate(addTrainingRequest.getEndDate());
		trainingRepository.save(training);
	}

	@Override
	public TrainingModel getTraining(Long trainingId) throws NotFoundException {
		Optional<Training> trainingOptional = trainingRepository.findById(trainingId);
		if (!trainingOptional.isPresent()) {
			throw new NotFoundException("Training not found");
		}
		return getTrainingModelFromTraining(trainingOptional.get());
	}
	
	private TrainingModel getTrainingModelFromTraining(Training training) {
		if (training != null) {
		TrainingModel trainingModel = new TrainingModel();
		BeanUtils.copyProperties(training, trainingModel);
		CourseModel courseModel = new CourseModel();
		BeanUtils.copyProperties(training.getCourse(), courseModel);
		trainingModel.setCourse(courseModel);
		FacultyModel facultyModel = new FacultyModel();
		BeanUtils.copyProperties(training.getFaculty(), facultyModel);
		trainingModel.setFaculty(facultyModel);
		return trainingModel;
		} return null;
	}

	/*
	 * Get All Trainings
	 */
	@Override
	public List<TrainingModel> getTrainings() {
		List<Training> trainingList = trainingRepository.findAll();
		if (!trainingList.isEmpty()) {
			List<TrainingModel> trainingModelList = new ArrayList<TrainingModel>();
			for (Training training : trainingList) {
				trainingModelList.add(getTrainingModelFromTraining(training));
			}
			return trainingModelList;
		}
		return null;
	}

	/*
	 * Get All Faculties
	 */
	@Override
	public List<FacultyModel> getFaculties() {
		List<Faculty> facultyList = facultyRepository.findAll();
		if (!facultyList.isEmpty()) {
			List<FacultyModel> facultyModelList = new ArrayList<FacultyModel>();
			for (Faculty faculty : facultyList) {
				FacultyModel trainingModel = new FacultyModel();
				BeanUtils.copyProperties(faculty, trainingModel);
				facultyModelList.add(trainingModel);
			}
			return facultyModelList;
		}
		return null;
	}

	/*
	 * Get All Courses
	 */
	@Override
	public List<CourseModel> getCourses() {
		List<Course> courseList = courseRepository.findAll();
		if (!courseList.isEmpty()) {
			List<CourseModel> courseModelList = new ArrayList<CourseModel>();
			for (Course course : courseList) {
				CourseModel trainingModel = new CourseModel();
				BeanUtils.copyProperties(course, trainingModel);
				courseModelList.add(trainingModel);
			}
			return courseModelList;
		}
		return null;
	}

	/*
	 * Update a Training
	 */
	@Override
	public void updateTraining(Long trainingId, UpdateTrainingRequest updateTrainingRequest)
			throws NotFoundException, BadRequestException {

		// If nothing was filled
		if (updateTrainingRequest.getStartDate() == null && updateTrainingRequest.getEndDate() == null
				&& updateTrainingRequest.getFacultyId() == null) {
			throw new BadRequestException("Nothing to update. Please fill atleast 1 field.");
		}

		Optional<Training> trainingOptional = trainingRepository.findById(trainingId);
		if (!trainingOptional.isPresent()) {
			throw new NotFoundException("Training not found");
		}

		Training training = trainingOptional.get();

		// validate dates
		if (updateTrainingRequest.getStartDate() != null && updateTrainingRequest.getEndDate() != null) {
			if (updateTrainingRequest.getEndDate().before(updateTrainingRequest.getStartDate())) {
				throw new BadRequestException("Start date cannot be greater than End date");
			}
			training.setStartDate(updateTrainingRequest.getStartDate());
			training.setEndDate(updateTrainingRequest.getEndDate());

		} else if (updateTrainingRequest.getStartDate() != null) {
			if (training.getEndDate().before(updateTrainingRequest.getStartDate())) {
				throw new BadRequestException("Start date cannot be greater than End date");
			} else if (updateTrainingRequest.getStartDate().before(new Date())) {
				throw new BadRequestException("Start date cannot be before Current date");
			}
			training.setStartDate(updateTrainingRequest.getStartDate());

		} else if (updateTrainingRequest.getEndDate() != null) {
			if (updateTrainingRequest.getEndDate().before(training.getStartDate())) {
				throw new BadRequestException("Start date cannot be greater than End date");
			} else if (updateTrainingRequest.getEndDate().before(new Date())) {
				throw new BadRequestException("End date cannot be before Current date");
			}
			training.setEndDate(updateTrainingRequest.getEndDate());
		}
		// validate faculty id
		if (updateTrainingRequest.getFacultyId() != null) {
			Optional<Faculty> faculty = facultyRepository.findById(updateTrainingRequest.getFacultyId());
			if (!faculty.isPresent()) {
				throw new NotFoundException("Faculty not found");
			}
			training.setFaculty(faculty.get());
		}
		// validate name
		if (StringUtils.isNotBlank(updateTrainingRequest.getTrainingName())) {
			training.setTrainingName(updateTrainingRequest.getTrainingName());
		}

		trainingRepository.save(training);
	}

	/*
	 * Delete a Training
	 */
	@Override
	public void deleteTraining(Long trainingId) throws NotFoundException {
		if (!trainingRepository.existsById(trainingId)) {
			throw new NotFoundException("Training not found");
		}
		trainingRepository.deleteById(trainingId);
	}

}
