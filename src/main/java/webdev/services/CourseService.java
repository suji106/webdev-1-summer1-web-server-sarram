package webdev.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.Course;
import webdev.repositories.CourseRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseService {
	@Autowired
	CourseRepository courseRepository;
	
	@GetMapping("/api/course")
	public Iterable<Course> findAllCourses() {
		return courseRepository.findAll();
	}
	
	@GetMapping("/api/course/{courseId}")
	public Course getCourseByCourseId(@PathVariable("courseId") int id) {
		Course course = courseRepository.findById(id).get();
		return course;
	}

	@PostMapping("/api/course")
	public Course createCourse(@RequestBody Course course) {
		return courseRepository.save(course);
	}

	@DeleteMapping("/api/course/{courseId}")
	public void deleteCourse(@PathVariable("courseId") int id) {
		courseRepository.deleteById(id);
	}
	
	@GetMapping("/api/course/public")
	public Iterable<Course> findAllPublicCourses() {
		List<Course> courses = (List<Course>) courseRepository.findAll();
		List<Course> publicCourses = new ArrayList<Course>();
		for (Course course: courses) {
			if (course.getContentType() == "public") {
				publicCourses.add(course);
			}
		}
		return publicCourses;
	}
}