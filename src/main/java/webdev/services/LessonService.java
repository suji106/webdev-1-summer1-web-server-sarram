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

import webdev.models.Course;
import webdev.models.Lesson;
import webdev.models.Module;
import webdev.repositories.CourseRepository;
import webdev.repositories.LessonRepository;
import webdev.repositories.ModuleRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class LessonService {
	@Autowired
	LessonRepository lessonRepository;

	@Autowired
	ModuleRepository moduleRepository;
	
	@Autowired(required = true)
	CourseRepository courseRepository;
	
	@PostMapping("/api/lesson/{moduleId}")
	public Lesson createModule(
			@PathVariable("moduleId") int moduleId,
			@RequestBody Lesson newLesson) {
		Optional<Module> data = moduleRepository.findById(moduleId);
		if(data.isPresent()) {
			Module module = data.get();
			newLesson.setModule(module);
			ModuleService m = new ModuleService();
			module.setModified(newLesson.getModified());
			Course course = m.updateModule(module);
			moduleRepository.save(module);
			courseRepository.save(course);
			return lessonRepository.save(newLesson);
		}
		return null;		
	}
	
	@GetMapping("/api/course/{moduleId}/lesson")
	public List<Lesson> findAllModulesForCourse(
			@PathVariable("moduleId") int moduleId) {
		Optional<Module> data = moduleRepository.findById(moduleId);
		if(data.isPresent()) {
			Module module = data.get();
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
			return lesson;
		}
		return null;		
	}
	
	@DeleteMapping("/api/lesson/{lessonId}")
	public void deleteModule(@PathVariable("lessonId") int lessonId)
	{		
		ModuleService m = new ModuleService();
		Module module = lessonRepository.findById(lessonId).get().getModule();
		module.setModified(new Date());
		Course course = m.updateModule(module);
		moduleRepository.save(module);
		courseRepository.save(course);
		
		lessonRepository.deleteById(lessonId);
	}
}