package webdev.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import webdev.models.EssayQuestion;
import webdev.models.Exam;
import webdev.models.FillQuestion;
import webdev.models.Lesson;
import webdev.models.MultipleChoiceQuestion;
import webdev.models.Question;
import webdev.models.TrueFalseQuestion;
import webdev.models.Widget;
import webdev.repositories.AssignmentRepository;
import webdev.repositories.EssayQuestionRepository;
import webdev.repositories.ExamRepository;
import webdev.repositories.FillQuestionRepository;
import webdev.repositories.LessonRepository;
import webdev.repositories.MultipleChoicesQuestionRepository;
import webdev.repositories.TrueFalseQuestionRepository;
import webdev.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*")
public class ExamService {
	@Autowired
	LessonRepository lessonRepository;
	@Autowired
	WidgetRepository widgetRepository;
	@Autowired
	ExamRepository examRepository;
	@Autowired
	TrueFalseQuestionRepository trueFalseRepository;
	@Autowired
	MultipleChoicesQuestionRepository multiRepo;
	@Autowired
	EssayQuestionRepository essayRepo;
	@Autowired
	FillQuestionRepository fillRepo;

	///////////////////////////////////////   EEEEEEXXXXXXXXXAAAAAAAAAMMMMMMMMMMMM  //////////////////////////////////////////////

	@GetMapping("/api/exam")
	public List<Exam> findAllExams() {
		return (List<Exam>) examRepository.findAll();
	}

	@GetMapping("/api/exam/{examId}")
	public List<Question> findExamForExamId(@PathVariable("examId") int examId) {
		Optional<Exam> optionalExam = examRepository.findById(examId);
		if(optionalExam.isPresent()) {
			Exam exam = optionalExam.get();
			List<Question> questions = exam.getQuestions();
			return questions;
		}
		return null;
	}

	@GetMapping("/api/lesson/{lessonId}/exam")
	public List<Exam> findExamsForLessonId(@PathVariable("lessonId") int lessonId) {
		Optional<Lesson> optionalLesson = lessonRepository.findById(lessonId);
		if(optionalLesson.isPresent()) {
			Lesson lesson = optionalLesson.get();
			List<Widget> widgets = lesson.getWidgets();
			List<Exam> exams = new ArrayList<Exam>();

			List<Widget> examWidgets = widgets.stream()
					.filter(widget -> "Exam".equals(widget.getWidgetType()))
					.collect(Collectors.toList());         

			for(Widget widget: examWidgets) {
				Optional<Exam> examOptional = examRepository.findById(widget.getId());
				exams.add(examOptional.get());
			}

			return exams;
		}
		return null;
	}

	@PostMapping("/api/lesson/{lessonId}/exam")
	public Exam createExamForLesson(@RequestBody Exam exam, @PathVariable("lessonId") int lessonId) {
		Optional<Lesson> optionalLesson = lessonRepository.findById(lessonId);
		if(optionalLesson.isPresent()) {
			Lesson lesson = optionalLesson.get();
			exam.setLesson(lesson);
			return examRepository.save(exam);
		}
		return null;
	}
	
	@DeleteMapping("/api/exam/{examId}")
	public void deleteExamWithExamId(@PathVariable("examId") int examId) {
		Optional<Exam> optionalExam = examRepository.findById(examId);
		if(optionalExam.isPresent()) {
			examRepository.deleteById(examId);
		}
	}
}
