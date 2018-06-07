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
public class EssayService {
	@Autowired
	LessonRepository lessonRepository;
	@Autowired
	WidgetRepository widgetRepository;
	@Autowired
	ExamRepository examRepository;
	@Autowired
	EssayQuestionRepository essayRepo;
	
	///////////////////////////////////////   GGGGGGGGGGGGGEEEEEEEEEEETTTTTTTTTTTTTTT  //////////////////////////////////////////////

	@GetMapping("/api/essay/{questionId}")
	public EssayQuestion findEssayQuestionById(@PathVariable("questionId") int questionId) {
		Optional<EssayQuestion> optional = essayRepo.findById(questionId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	///////////////////////////////////////   PPPPPPPPPPPPUUUUUUUUUUUTTTTTTTTTTT  /////////////////////////////////////////////

	@PutMapping("/api/essay/{questionId}")
	public EssayQuestion updateEssayQuestionById(@PathVariable("questionId") int questionId, @RequestBody EssayQuestion question) {
		Optional<EssayQuestion> optionalExam = essayRepo.findById(questionId);
		if(optionalExam.isPresent()) {
			return essayRepo.save(question);
		}
		return null;
	}

	///////////////////////////////////////   DDDDDDDDDDDEEEEEELLLLLLEEEETTTTEEEEEEEEE  /////////////////////////////////////////////


	@DeleteMapping("/api/essay/{questionId}")
	public void deleteEssayQuestionById(@PathVariable("questionId") int questionId) {
		Optional<EssayQuestion> optional = essayRepo.findById(questionId);
		if(optional.isPresent()) {
			essayRepo.deleteById(questionId);
		}
	}
	
	///////////////////////////////////////   PPPPPPPPPPPPOOOOOOSSSSSSSSSTTTTTTTT  /////////////////////////////////////////////


	@PostMapping("/api/{examId}/essay")
	public EssayQuestion createEssayQuestion(@PathVariable("examId") int examId, @RequestBody EssayQuestion question) {
		Optional<Exam> optionalExam = examRepository.findById(examId);
		question.setExam(optionalExam.get());
		return essayRepo.save(question);
	}

}
