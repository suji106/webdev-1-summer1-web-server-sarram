package webdev.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.Lesson;
import webdev.models.Module;
import webdev.repositories.LessonRepository;
import webdev.repositories.ModuleRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class LessonService {
	@Autowired
	LessonRepository lessonRepository;

	@Autowired
	ModuleRepository moduleRepository;
	
	@PostMapping("/api/lesson/{moduleId}")
	public Lesson createModule(
			@PathVariable("moduleId") int moduleId,
			@RequestBody Lesson newLesson) {
		Optional<Module> data = moduleRepository.findById(moduleId);
		System.out.println("qqqqqqqqqqqqqqqqqqq");
		System.out.println(newLesson.getTitle() + " " + "dddddddddddddddd");
		if(data.isPresent()) {
			Module module = data.get();
			newLesson.setModule(module);
			// changeModifiedCourse(module, newLesson.getModified());
			System.out.println(newLesson.getTitle());
			return lessonRepository.save(newLesson);
		}
		return null;		
	}
	
	@GetMapping("/api/course/{moduleId}/lesson")
	public List<Lesson> findAllModulesForCourse(
			@PathVariable("moduleId") int moduleId) {
		System.out.println(moduleId + " lllllllll");
		Optional<Module> data = moduleRepository.findById(moduleId);
		if(data.isPresent()) {
			Module module = data.get();
			System.out.println(moduleId);
			return module.getLessons();
		}
		return null;		
	}
	
	@GetMapping("/api/lesson/{lessonId}")
	public Lesson findlessonById(
			@PathVariable("lessonId") int lessonId) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		if(data.isPresent()) {
			Lesson lesson = data.get();
			System.out.println(lessonId);
			return lesson;
		}
		System.out.println("noooooooooooooooooooooooooooooooooooooooo");
		return null;		
	}
	
	@DeleteMapping("/api/lesson/{lessonId}")
	public void deleteModule(@PathVariable("lessonId") int lessonId)
	{
		lessonRepository.deleteById(lessonId);
	}
	
//	public void changeModifiedCourse(Course course, Date modified) {
//		System.out.println(course.getId() + " " + modified);
//		course.setModified(modified);
//		// courseRepository.save(course);
//	}
}