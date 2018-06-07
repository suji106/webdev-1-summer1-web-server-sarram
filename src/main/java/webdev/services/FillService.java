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
public class FillService {
	@Autowired
	LessonRepository lessonRepository;
	@Autowired
	WidgetRepository widgetRepository;
	@Autowired
	ExamRepository examRepository;
	@Autowired
	FillQuestionRepository fillRepo;
	
	///////////////////////////////////////   GGGGGGGGGGGGGEEEEEEEEEEETTTTTTTTTTTTTTT  //////////////////////////////////////////////

	@GetMapping("/api/fill/{questionId}")
	public FillQuestion findFillQuestionById(@PathVariable("questionId") int questionId) {
		Optional<FillQuestion> optional = fillRepo.findById(questionId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	///////////////////////////////////////   PPPPPPPPPPPPUUUUUUUUUUUTTTTTTTTTTT  /////////////////////////////////////////////
	@PutMapping("/api/fill/{questionId}")
	public FillQuestion updateFillQuestionById(@PathVariable("questionId") int questionId, @RequestBody FillQuestion question) {
		Optional<FillQuestion> optionalExam = fillRepo.findById(questionId);
		if(optionalExam.isPresent()) {
			return fillRepo.save(question);
		}
		return null;
	}

	///////////////////////////////////////   DDDDDDDDDDDEEEEEELLLLLLEEEETTTTEEEEEEEEE  /////////////////////////////////////////////

	@DeleteMapping("/api/fill/{questionId}")
	public void deleteFillQuestionById(@PathVariable("questionId") int questionId) {
		Optional<FillQuestion> optional = fillRepo.findById(questionId);
		if(optional.isPresent()) {
			fillRepo.deleteById(questionId);
		}
	}

	///////////////////////////////////////   PPPPPPPPPPPPOOOOOOSSSSSSSSSTTTTTTTT  /////////////////////////////////////////////

	@PostMapping("/api/{examId}/fill")
	public FillQuestion createFillQuestion(@PathVariable("examId") int examId, @RequestBody FillQuestion question) {
		Optional<Exam> optionalExam = examRepository.findById(examId);
		question.setExam(optionalExam.get());
		return fillRepo.save(question);
	}
}
