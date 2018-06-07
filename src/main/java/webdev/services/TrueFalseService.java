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
public class TrueFalseService {
	@Autowired
	LessonRepository lessonRepository;
	@Autowired
	WidgetRepository widgetRepository;
	@Autowired
	ExamRepository examRepository;
	@Autowired
	TrueFalseQuestionRepository trueFalseRepository;
	
	///////////////////////////////////////   GGGGGGGGGGGGGEEEEEEEEEEETTTTTTTTTTTTTTT  //////////////////////////////////////////////

	@GetMapping("/api/truefalse/{questionId}")
	public TrueFalseQuestion findTrueFalseQuestionById(@PathVariable("questionId") int questionId) {
		Optional<TrueFalseQuestion> optional = trueFalseRepository.findById(questionId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	///////////////////////////////////////   PPPPPPPPPPPPUUUUUUUUUUUTTTTTTTTTTT  /////////////////////////////////////////////

	@PutMapping("/api/truefalse/{questionId}")
	public TrueFalseQuestion updateTrueFasleQuestionById(@PathVariable("questionId") int questionId, @RequestBody TrueFalseQuestion question) {
		Optional<TrueFalseQuestion> optionalExam = trueFalseRepository.findById(questionId);
		if(optionalExam.isPresent()) {
			return trueFalseRepository.save(question);
		}
		return null;
	}

	///////////////////////////////////////   DDDDDDDDDDDEEEEEELLLLLLEEEETTTTEEEEEEEEE  /////////////////////////////////////////////

	@DeleteMapping("/api/truefalse/{questionId}")
	public void deleteTrueFalseQuestionById(@PathVariable("questionId") int questionId) {
		Optional<TrueFalseQuestion> optional = trueFalseRepository.findById(questionId);
		if(optional.isPresent()) {
			trueFalseRepository.deleteById(questionId);
		}
	}

	///////////////////////////////////////   PPPPPPPPPPPPOOOOOOSSSSSSSSSTTTTTTTT  /////////////////////////////////////////////
	
	@PostMapping("/api/{examId}/truefalse")
	public TrueFalseQuestion createTrueFalseQuestion(@PathVariable("examId") int examId, @RequestBody TrueFalseQuestion question) {
		Optional<Exam> optionalExam = examRepository.findById(examId);
		question.setExam(optionalExam.get());
		return trueFalseRepository.save(question);
	}
}
