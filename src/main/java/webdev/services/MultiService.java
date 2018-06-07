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
public class MultiService {
	@Autowired
	LessonRepository lessonRepository;
	@Autowired
	WidgetRepository widgetRepository;
	@Autowired
	ExamRepository examRepository;
	@Autowired
	MultipleChoicesQuestionRepository multiRepo;

	///////////////////////////////////////   GGGGGGGGGGGGGEEEEEEEEEEETTTTTTTTTTTTTTT  //////////////////////////////////////////////

	@GetMapping("/api/multi/{questionId}")
	public MultipleChoiceQuestion findMultiQuestionById(@PathVariable("questionId") int questionId) {
		Optional<MultipleChoiceQuestion> optional = multiRepo.findById(questionId);
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

	///////////////////////////////////////   DDDDDDDDDDDEEEEEELLLLLLEEEETTTTEEEEEEEEE  /////////////////////////////////////////////

	@DeleteMapping("/api/multi/{questionId}")
	public void deleteMultiQuestionById(@PathVariable("questionId") int questionId) {
		Optional<MultipleChoiceQuestion> optional = multiRepo.findById(questionId);
		if(optional.isPresent()) {
			multiRepo.deleteById(questionId);
		}
	}

	///////////////////////////////////////   PPPPPPPPPPPPOOOOOOSSSSSSSSSTTTTTTTT  /////////////////////////////////////////////

	@PostMapping("/api/{examId}/multi")
	public MultipleChoiceQuestion createMultiQuestion(@PathVariable("examId") int examId, @RequestBody MultipleChoiceQuestion question) {
		Optional<Exam> optionalExam = examRepository.findById(examId);
		question.setExam(optionalExam.get());
		return multiRepo.save(question);
	}
}
