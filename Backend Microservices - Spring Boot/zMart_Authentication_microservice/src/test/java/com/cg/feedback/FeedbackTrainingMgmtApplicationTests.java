package com.cg.feedback;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.feedback.entity.Course;
import com.cg.feedback.entity.Faculty;
import com.cg.feedback.entity.Training;
import com.cg.feedback.exception.BadRequestException;
import com.cg.feedback.exception.NotFoundException;
import com.cg.feedback.model.request.AddTrainingRequest;
import com.cg.feedback.repository.CourseRepository;
import com.cg.feedback.repository.FacultyRepository;
import com.cg.feedback.repository.TrainingRepository;
import com.cg.feedback.service.TrainingService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FeedbackTrainingMgmtApplicationTests {

	@MockBean
	private TrainingRepository trainingRepository;

	@MockBean
	private CourseRepository courseRepository;

	@MockBean
	private FacultyRepository facultyRepository;

	@Autowired
	private TrainingService service;

	@Before
	public void init() {
		Date startDate = new Date(2020, 8, 28);
		Faculty faculty = new Faculty("Tanya Tripathi", "Java", 1L, startDate);
		Optional<Faculty> facultyOptional = Optional.of(faculty);
		when(facultyRepository.findById(1L)).thenReturn(facultyOptional);

		Course course1 = new Course("Java", 1L, startDate);
		Optional<Course> courseOptional1 = Optional.of(course1);
		when(courseRepository.findById(1L)).thenReturn(courseOptional1);

		Course course2 = new Course("Java", 2L, new Date(2020, 10, 8));
		Optional<Course> courseOptional2 = Optional.of(course2);
		when(courseRepository.findById(2L)).thenReturn(courseOptional2);

		Training training = new Training();
		training.setId(100L);
		training.setFaculty(faculty);
		training.setCourse(course1);
		training.setStartDate(startDate);
		training.setEndDate(new Date(2020, 10, 1));
		training.setCreatedDate(new Date());

		when(trainingRepository.existsByFacultyIdAndCourseIdAndStartDate(1L, 1L, startDate)).thenReturn(false);
		//when(trainingRepository.existsByFacultyId(1L)).thenReturn(false);
		when(trainingRepository.existsByCourseIdAndStartDate(1L, startDate)).thenReturn(false);
		when(trainingRepository.save(training)).thenReturn(training);
		when(trainingRepository.existsById(200L)).thenReturn(true);
		when(trainingRepository.findById(100L)).thenReturn(Optional.of(training));

	}

	@Test
	public void addTrainingTest() throws NotFoundException, BadRequestException {
		AddTrainingRequest addRequest = new AddTrainingRequest();
		addRequest.setFacultyId(1L);
		addRequest.setCourseId(1L);
		addRequest.setStartDate(new Date(2020, 8, 28));
		addRequest.setEndDate(new Date(2020, 10, 1));
		service.addTraining(addRequest);
		Assert.assertEquals(trainingRepository.findById(100L).get().getId(), new Long(100));
	}

	@Test
	public void deleteTrainingTest() throws NotFoundException {
		service.deleteTraining(200L);
		verify(trainingRepository).existsById(200L);
	}

	@Test
	public void addTrainingTest_BadRequestException() throws NotFoundException, BadRequestException {

		BadRequestException badRequestException = assertThrows(BadRequestException.class, () -> {
			service.addTraining(new AddTrainingRequest());
		});
		assertEquals("FacultyId cannot be null.", badRequestException.getMessage());
	}

	@Test
	public void deleteTrainingTest_NotFoundException() throws NotFoundException {
		NotFoundException notFoundException = assertThrows(NotFoundException.class, () -> {
			service.deleteTraining(null);
		});
		assertEquals("Training not found", notFoundException.getMessage());
	}

	@Test
	public void addTrainingTest_BadRequestException1() throws NotFoundException, BadRequestException {
		AddTrainingRequest addTrainingRequest = new AddTrainingRequest();
		addTrainingRequest.setFacultyId(1l);
		BadRequestException badRequestException = assertThrows(BadRequestException.class, () -> {
			service.addTraining(addTrainingRequest);
		});
		assertEquals("CourseId cannot be null.", badRequestException.getMessage());
	}

}
