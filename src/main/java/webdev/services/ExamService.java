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

	///////////////////////////////////////   GGGGGGGGGGGGGEEEEEEEEEEETTTTTTTTTTTTTTT  //////////////////////////////////////////////

	@GetMapping("/api/multi/{questionId}")
	public MultipleChoiceQuestion findMultiQuestionById(@PathVariable("questionId") int questionId) {
		Optional<MultipleChoiceQuestion> optional = multiRepo.findById(questionId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@GetMapping("/api/truefalse/{questionId}")
	public TrueFalseQuestion findTrueFalseQuestionById(@PathVariable("questionId") int questionId) {
		Optional<TrueFalseQuestion> optional = trueFalseRepository.findById(questionId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@GetMapping("/api/essay/{questionId}")
	public EssayQuestion findEssayQuestionById(@PathVariable("questionId") int questionId) {
		Optional<EssayQuestion> optional = essayRepo.findById(questionId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@GetMapping("/api/fill/{questionId}")
	public FillQuestion findFillQuestionById(@PathVariable("questionId") int questionId) {
		Optional<FillQuestion> optional = fillRepo.findById(questionId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	///////////////////////////////////////   PPPPPPPPPPPPUUUUUUUUUUUTTTTTTTTTTT  /////////////////////////////////////////////

	@PutMapping("/api/multi/{questionId}")
	public MultipleChoiceQuestion updateMultiQuestionById(@PathVariable("questionId") int questionId, @RequestBody MultipleChoiceQuestion question) {
		Optional<MultipleChoiceQuestion> optionalExam = multiRepo.findById(questionId);
		if(optionalExam.isPresent()) {
			return multiRepo.save(question);
		}
		return null;
	}

	@PutMapping("/api/truefalse/{questionId}")
	public TrueFalseQuestion updateTrueFasleQuestionById(@PathVariable("questionId") int questionId, @RequestBody TrueFalseQuestion question) {
		Optional<TrueFalseQuestion> optionalExam = trueFalseRepository.findById(questionId);
		if(optionalExam.isPresent()) {
			return trueFalseRepository.save(question);
		}
		return null;
	}

	@PutMapping("/api/essay/{questionId}")
	public EssayQuestion updateEssayQuestionById(@PathVariable("questionId") int questionId, @RequestBody EssayQuestion question) {
		Optional<EssayQuestion> optionalExam = essayRepo.findById(questionId);
		if(optionalExam.isPresent()) {
			return essayRepo.save(question);
		}
		return null;
	}

	@PutMapping("/api/fill/{questionId}")
	public FillQuestion updateFillQuestionById(@PathVariable("questionId") int questionId, @RequestBody FillQuestion question) {
		Optional<FillQuestion> optionalExam = fillRepo.findById(questionId);
		if(optionalExam.isPresent()) {
			return fillRepo.save(question);
		}
		return null;
	}

	///////////////////////////////////////   DDDDDDDDDDDEEEEEELLLLLLEEEETTTTEEEEEEEEE  /////////////////////////////////////////////

	@DeleteMapping("/api/multi/{questionId}")
	public void deleteMultiQuestionById(@PathVariable("questionId") int questionId) {
		Optional<MultipleChoiceQuestion> optional = multiRepo.findById(questionId);
		if(optional.isPresent()) {
			multiRepo.deleteById(questionId);
		}
	}

	@DeleteMapping("/api/truefalse/{questionId}")
	public void deleteTrueFalseQuestionById(@PathVariable("questionId") int questionId) {
		Optional<TrueFalseQuestion> optional = trueFalseRepository.findById(questionId);
		if(optional.isPresent()) {
			trueFalseRepository.deleteById(questionId);
		}
	}

	@DeleteMapping("/api/essay/{questionId}")
	public void deleteEssayQuestionById(@PathVariable("questionId") int questionId) {
		Optional<EssayQuestion> optional = essayRepo.findById(questionId);
		if(optional.isPresent()) {
			essayRepo.deleteById(questionId);
		}
	}

	@DeleteMapping("/api/fill/{questionId}")
	public void deleteFillQuestionById(@PathVariable("questionId") int questionId) {
		Optional<FillQuestion> optional = fillRepo.findById(questionId);
		if(optional.isPresent()) {
			fillRepo.deleteById(questionId);
		}
	}

	///////////////////////////////////////   PPPPPPPPPPPPOOOOOOSSSSSSSSSTTTTTTTT  /////////////////////////////////////////////

	@PostMapping("/api/{examId}/multi")
	public MultipleChoiceQuestion createMultiQuestion(@PathVariable("examId") int examId, @RequestBody MultipleChoiceQuestion question) {
		Optional<Exam> optionalExam = examRepository.findById(examId);
		question.setExam(optionalExam.get());
		return multiRepo.save(question);
	}

	@PostMapping("/api/{examId}/truefalse")
	public TrueFalseQuestion createTrueFalseQuestion(@PathVariable("examId") int examId, @RequestBody TrueFalseQuestion question) {
		Optional<Exam> optionalExam = examRepository.findById(examId);
		question.setExam(optionalExam.get());
		return trueFalseRepository.save(question);
	}

	@PostMapping("/api/{examId}/essay")
	public EssayQuestion createEssayQuestion(@PathVariable("examId") int examId, @RequestBody EssayQuestion question) {
		Optional<Exam> optionalExam = examRepository.findById(examId);
		question.setExam(optionalExam.get());
		return essayRepo.save(question);
	}

	@PostMapping("/api/{examId}/fill")
	public FillQuestion createFillQuestion(@PathVariable("examId") int examId, @RequestBody FillQuestion question) {
		Optional<Exam> optionalExam = examRepository.findById(examId);
		question.setExam(optionalExam.get());
		return fillRepo.save(question);
	}
}
