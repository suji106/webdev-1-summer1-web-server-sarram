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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.Course;
import webdev.models.Module;
import webdev.repositories.CourseRepository;
import webdev.repositories.ModuleRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ModuleService {
	@Autowired(required = true)
	CourseRepository courseRepository;

	@Autowired(required = true)
	ModuleRepository moduleRepository;
	
	@PostMapping("/api/course/{courseId}/module")
	public Module createModule(
			@PathVariable("courseId") int courseId,
			@RequestBody Module newModule) {
		Optional<Course> data = courseRepository.findById(courseId);
		
		if(data.isPresent()) {
			Course course = data.get();
			newModule.setCourse(course);
			changeModifiedCourse(course, newModule.getModified());
			return moduleRepository.save(newModule);
		}
		return null;		
	}
	
	@PutMapping("/api/course/module")
	public Course updateModule(@RequestBody Module newModule) {
		Course course = changeModifiedCourse(newModule.getCourse(), newModule.getModified());
		return course;
	}
	
	@GetMapping("/api/course/{courseId}/module")
	public List<Module> findAllModulesForCourse(
			@PathVariable("courseId") int courseId) {
		Optional<Course> data = courseRepository.findById(courseId);
		if(data.isPresent()) {
			Course course = data.get();
			return course.getModules();
		}
		return null;		
	}
	
	@GetMapping("/api/module/{moduleId}")
	public Optional<Module> getModule(@PathVariable("moduleId") String module_id)
	{
		int moduleId = Integer.parseInt(module_id);
		return moduleRepository.findById(moduleId);
	}
	
	@DeleteMapping("/api/module/{moduleId}")
	public void deleteModule(@PathVariable("moduleId") String module_id)
	{
		int moduleId = Integer.parseInt(module_id);
		List<Course> courseIds = (List<Course>) moduleRepository.findCourseByModelId(moduleId);
		Date modified = new Date();
		moduleRepository.deleteById(moduleId);
		changeModifiedCourse(courseRepository.findById(courseIds.get(0).getId()).get(), modified);
	}
	
	public Course changeModifiedCourse(Course course, Date modified) {
		course.setModified(modified);
		Course newCourse = new Course();
		newCourse.setCreated(course.getCreated());
		newCourse.setId(course.getId());
		newCourse.setModified(modified);
		newCourse.setTitle(course.getTitle());
		newCourse.setModules(course.getModules());
		return newCourse;
	}
}