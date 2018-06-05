package webdev.services;

import java.util.List;
import java.util.Optional;

import org.junit.experimental.theories.internal.Assignments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.Assignment;
import webdev.models.Exam;
import webdev.models.Lesson;
import webdev.models.Question;
import webdev.models.Widget;
import webdev.repositories.AssignmentRepository;
import webdev.repositories.LessonRepository;
import webdev.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*")
public class AssignmentService {

	@Autowired
	LessonRepository lessonRepo;
	@Autowired
	WidgetRepository widgetRepository;
	@Autowired
	AssignmentRepository assignmentRepo;

	@GetMapping("/api/assignment")
	public Iterable<Assignment> findAllAssignment() {
		return assignmentRepo.findAll();
	}
	
	@GetMapping("/api/lesson/{lessonId}/assignment")
	public List<Assignment> findAssignmentByLessonId(@PathVariable("lessonId") int lessonId) {
		Optional<Lesson> lessonOptional = lessonRepo.findById(lessonId);
		if(!lessonOptional.isPresent()) {
			Lesson lesson = lessonOptional.get();
			List<Widget> widgets = lesson.getWidgets();
			List<Assignment> assignments = null;
			for(Widget widget: widgets) {
				String widgetType = widgetRepository.checkIfExam(widget.getId());
				if(widgetType.equals("Assignment")) {
					Optional<Assignment> assignmentOptional = assignmentRepo.findById(widget.getId());
					assignments.add(assignmentOptional.get());
				}
			}
			return assignments;
		}
		return null;
	}

	@GetMapping("/api/assignment/{assignmentId}")
	public Assignment findAssignmentById(@PathVariable("assignmentId") int assignmentId) {
		Optional<Assignment> optional = assignmentRepo.findById(assignmentId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@PutMapping("/api/assignment/{assignmentId}")
	public Assignment updateAssignmentById(@PathVariable("assignmentId") int assignmentId, @RequestBody Assignment assignment) {
		Optional<Assignment> optionalExam = assignmentRepo.findById(assignmentId);
		if(optionalExam.isPresent()) {
			return assignmentRepo.save(assignment);
		}
		return null;
	}

	@DeleteMapping("/api/assignment/{assignmentId}")
	public void deleteAssignmentById(@PathVariable("assignmentId") int assignmentId) {
		Optional<Assignment> optional = assignmentRepo.findById(assignmentId);
		if(optional.isPresent()) {
			assignmentRepo.deleteById(assignmentId);
		}
	}
	
	@PostMapping("/api/lesson/{lessonId}/assignment")
	public Assignment createAssignment(@PathVariable("lessonId") int lessonId, @RequestBody Assignment assignment) {
		Optional<Lesson> lessonOptional = lessonRepo.findById(lessonId);
		if(lessonOptional.isPresent()) {
			assignment.setLesson(lessonOptional.get());
			return assignmentRepo.save(assignment);
		}
		return null;
	}
}